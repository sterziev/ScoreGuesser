package ex.guesser.areas.user.services;

import ex.guesser.areas.common.commonFunctions.CustomMapperUser;
import ex.guesser.areas.common.dtos.InfoContainer;
import ex.guesser.areas.errorHandling.errors.LeagueNotFound;
import ex.guesser.areas.errorHandling.errors.UserAlreadyLoggedInException;
import ex.guesser.areas.errorHandling.errors.UserNotFound;
import ex.guesser.areas.errorHandling.errors.WrongPassword;
import ex.guesser.areas.points.entities.Points;
import ex.guesser.areas.points.models.dtos.PointsDto;
import ex.guesser.areas.user.entities.MiniLeague;
import ex.guesser.areas.user.entities.Role;
import ex.guesser.areas.user.entities.User;
import ex.guesser.areas.user.models.binding.RegisterBindingModel;
import ex.guesser.areas.user.models.binding.UpdatePasswordBM;
import ex.guesser.areas.user.models.binding.UpdateUserPersonalBindingModel;
import ex.guesser.areas.user.models.dtos.UserDTO;
import ex.guesser.areas.user.models.dtos.UserWithPointsDto;
import ex.guesser.areas.user.repositories.MiniLeagueRepository;
import ex.guesser.areas.user.repositories.RoleRepository;
import ex.guesser.areas.user.repositories.UserRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder encoder;

    private final ModelMapper mapper;
    private CustomMapperUser customMapper;
    private final UserRepository userRepository;
    private final MiniLeagueRepository miniLeagueRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(BCryptPasswordEncoder encoder, CustomMapperUser customMapper, UserRepository userRepository, MiniLeagueRepository miniLeagueRepository, RoleRepository roleRepository) {
        this.encoder = encoder;
        this.customMapper = customMapper;
        this.miniLeagueRepository = miniLeagueRepository;
        this.mapper = new ModelMapper();
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.initMapper();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User result = this.userRepository.findByUsername(username);

        if(result == null) throw new UsernameNotFoundException("Username not found.");


        return result;
    }

    @Override
    public User register(RegisterBindingModel bindingModel){
        User user = this.mapper.map(bindingModel, User.class);

        user.setPassword(this.encoder.encode(bindingModel.getPassword()));
        LocalDate birthday = LocalDate.of(Integer.parseInt(bindingModel.getYear()), Integer.parseInt(bindingModel.getMonth()), Integer.parseInt(bindingModel.getDay()));
        user.setBirthday(birthday);
        Role role = this.roleRepository.findFirstByAuthority("ROLE_USER");
        user.addRole(role);
        user.setCredentialsNonExpired(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setEnabled(true);

        return this.userRepository.save(user);

    }

    @Override
    public List<UserDTO> getAll() {
        List<User> users = this.userRepository.findAll();
        Type usersListType = new TypeToken<List<UserDTO>>() {}.getType();
        List<UserDTO> map = this.mapper.map(users, usersListType);
        return map;

    }

    @Override
    public UserDTO getById(String id) {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) throw new UserNotFound();
        UserDTO userDTO = this.mapper.map(user.get(), UserDTO.class);
        userDTO.setDay(user.get().getBirthday().getDayOfMonth() + "");
        userDTO.setMonth(user.get().getBirthday().getMonthValue() + "");
        userDTO.setYear(user.get().getBirthday().getYear() + "");

        return userDTO;
    }

    @Override
    public void deleteById(String id) {
        Optional<User> user = this.userRepository.findById(id);
        if (!user.isPresent()) return;

        this.userRepository.deleteById(id);
    }

    @Override
    public void updateUserPersonalInfo(String id, UpdateUserPersonalBindingModel bindingModel) {
        User oldUser =  this.userRepository.findById(id).orElse(null);
        if (oldUser == null) return;
        LocalDate birthday = LocalDate.of(Integer.parseInt(bindingModel.getYear()), Integer.parseInt(bindingModel.getMonth()), Integer.parseInt(bindingModel.getDay()));
        User updatedUser = this.mapper.map(bindingModel, User.class);
        oldUser.setFirstName(updatedUser.getFirstName());
        oldUser.setLastName(updatedUser.getLastName());
        oldUser.setBirthday(birthday);
        oldUser.setCountry(updatedUser.getCountry());
        oldUser.setSex(updatedUser.getSex());
        oldUser.setFavTeam(updatedUser.getFavTeam());
        this.userRepository.save(oldUser);

    }

    @Override
    public User editPassword(String id, UpdatePasswordBM bindingModel) {
        Optional<User> userOptional =  this.userRepository.findById(id);
        if (!userOptional.isPresent()) throw new UserNotFound();
        User user =  userOptional.get();

        if (!this.encoder.matches(bindingModel.getOldPassword(),user.getPassword())){
            throw new WrongPassword();
        }

        user.setPassword(this.encoder.encode(bindingModel.getPassword()));
        return this.userRepository.save(user);
    }

    @Override
    public void isLoggedIn(Authentication authentication) {
        if (authentication!=null){
            User user = (User)authentication.getPrincipal();
            throw new UserAlreadyLoggedInException(user.getUsername());
        }
    }

    @Override
    public List<UserWithPointsDto> getUserPoints() {
        List<User> users = this.userRepository.findAll();
        Type userTypeList = new TypeToken<List<UserWithPointsDto>>() {}.getType();

        return this.mapper.map(users, userTypeList);

    }

    @Override
    public void updateUserRoles(String id, UserDTO bindingModel) {
        User updatedUser =  this.userRepository.findById(id).orElse(null);
        if (updatedUser == null) throw new UserNotFound();

        updatedUser.setAuthorities(new HashSet<>());

        if (bindingModel.getIsAdmin()){
            Role role = this.roleRepository.findFirstByAuthority("ROLE_ADMIN");
            updatedUser.addRole(role);
        }
        if (bindingModel.getIsModerator()){
            Role role = this.roleRepository.findFirstByAuthority("ROLE_MODERATOR");
            updatedUser.addRole(role);
        }
        if (bindingModel.getIsUser()){
            Role role = this.roleRepository.findFirstByAuthority("ROLE_USER");
            updatedUser.addRole(role);
        }
        if (updatedUser.getAuthorities().size()==0){
            Role role = this.roleRepository.findFirstByAuthority("ROLE_USER");
            updatedUser.addRole(role);
        }
        this.userRepository.save(updatedUser);
    }

    @Override
    public List<UserWithPointsDto> getUserPointsFromLeague(String id) {
        MiniLeague miniLeague = this.miniLeagueRepository.findById(id).orElse(null);
        if (miniLeague==null){
            throw new LeagueNotFound();
        }
        List<String> participantsID = miniLeague.
                getParticipants().
                stream().
                map(User::getId).
                collect(Collectors.toList());

        List<User> users = this.userRepository.findAllById(participantsID);
        Type userTypeList = new TypeToken<List<UserWithPointsDto>>() {}.getType();
        List<UserWithPointsDto> userPoints = this.mapper.map(users, userTypeList);

        return userPoints;
    }

    @Override
    public InfoContainer getInfoContainer(String name) {
        User userEntity = this.userRepository.findByUsernameFetchMiniLeaguesAndPoints(name);
        if (userEntity ==null){
            throw new UserNotFound();
        }
        return customMapper.mapUserToInfoContainer(userEntity);
    }

    private void initMapper() {
        Converter<PointsDto, UserWithPointsDto> userWithPointsConverter = new Converter<PointsDto, UserWithPointsDto>() {
            @Override
            public UserWithPointsDto convert(MappingContext<PointsDto, UserWithPointsDto> context) {
                PointsDto s = context.getSource();
                UserWithPointsDto d = context.getDestination();

                d.setId(s.getUser().getId());
                d.setPointsNumber(s.getCurrentPoints());
                d.setUsername(s.getUser().getUsername());
                d.setFirstName(s.getUser().getFirstName());
                d.setLastName(s.getUser().getLastName());
                d.setFavTeam(s.getUser().getFavTeam());
                d.setCountry(s.getUser().getCountry());
                d.setSex(s.getUser().getSex());

                return d;
            }
        };
        Converter<User, UserDTO> userDtoRoes = new Converter<User, UserDTO>() {
            @Override
            public UserDTO convert(MappingContext<User, UserDTO> context) {
                User s = context.getSource();
                UserDTO d = context.getDestination();

                s.getAuthorities()
                        .forEach(a->{
                            switch (a.getAuthority()){
                                case "ROLE_ADMIN": d.setIsAdmin(true); break;
                                case "ROLE_USER": d.setIsUser(true); break;
                                case "ROLE_MODERATOR": d.setIsModerator(true); break;
                            }
                        });
                d.setId(s.getId());
                d.setFirstName(s.getFirstName());
                d.setLastName(s.getLastName());
                d.setUsername(s.getUsername());
                d.setFavTeam(s.getFavTeam());
                d.setSex(s.getSex().toString());
                d.setCountry(s.getCountry());

                return d;
            }
        };

        Converter<Set<Points>, Integer> convertRowPoints = new Converter<Set<Points>, Integer>()
        {
            public Integer convert(MappingContext<Set<Points>, Integer> context)
            {
                // If the dog weighs more than 25, then it must be large
                Integer p = context.getSource().stream().mapToInt(Points::getPoints).sum();
                return p;
            }
        };

        PropertyMap<User, UserWithPointsDto> rowPointsMapping = new PropertyMap<User, UserWithPointsDto>()
        {
            protected void configure()
            {
                // Note: this is not normal code. It is "EDSL" so don't get confused
                using(convertRowPoints).map(source.getUserPoints()).setPointsNumber(0);
            }
        };



        mapper.addConverter(userWithPointsConverter);
        mapper.addConverter(userDtoRoes);
        mapper.addMappings(rowPointsMapping);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByUsername(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
