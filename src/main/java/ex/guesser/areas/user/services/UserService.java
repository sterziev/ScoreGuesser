package ex.guesser.areas.user.services;

import ex.guesser.areas.common.dtos.InfoContainer;
import ex.guesser.areas.user.entities.User;
import ex.guesser.areas.user.models.binding.RegisterBindingModel;
import ex.guesser.areas.user.models.binding.UpdatePasswordBM;
import ex.guesser.areas.user.models.binding.UpdateUserPersonalBindingModel;
import ex.guesser.areas.user.models.dtos.UserDTO;
import ex.guesser.areas.user.models.dtos.UserWithPointsDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(RegisterBindingModel bindingModel);
    List<UserDTO> getAll();
    UserDTO getById(String id);

    void deleteById(String id);

    void updateUserPersonalInfo(String id, UpdateUserPersonalBindingModel bindingModel);

    User editPassword(String id, UpdatePasswordBM bindingModel);

    User resetPassword(String id, UpdatePasswordBM bindingModel);

    void isLoggedIn(Authentication authentication);

    List<UserWithPointsDto> getUserPoints();

    void updateUserRoles(String id, UserDTO bindingModel);

    List<UserWithPointsDto> getUserPointsFromLeague(String id);

    InfoContainer getInfoContainer(String name);
}
