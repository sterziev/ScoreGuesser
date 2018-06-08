package ex.guesser.areas.common.commonFunctions;

import ex.guesser.areas.common.dtos.InfoContainer;
import ex.guesser.areas.points.entities.Points;
import ex.guesser.areas.user.entities.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public final class CustomMapperUser {
    private final ModelMapper modelMapper;


    public CustomMapperUser() {
        this.modelMapper = new ModelMapper();
        this.initMapper();
    }


    public InfoContainer mapUserToInfoContainer(User user){
        return this.modelMapper.map(user,InfoContainer.class);
    }

    private void initMapper() {
        Converter<User, InfoContainer> infoContainerConverter = new Converter<User, InfoContainer>() {
            @Override
            public InfoContainer convert(MappingContext<User, InfoContainer> context) {
                User s = context.getSource();
                InfoContainer d = context.getDestination();

                d.setCountry(s.getCountry());
                d.setFavTeam(s.getFavTeam());
                d.setFirstName(s.getFirstName());
                d.setLastName(s.getLastName());
                d.setSex(s.getSex());
                d.setUsername(s.getUsername());
                d.setLeagues(s.getMiniLeagues());
                d.setAdminsOf(s.getAdminLeague());
                Map<Integer, Integer> pointsPerRound = new HashMap<>();

                if (s.getUserPoints() != null) {
                    for (Points points : s.getUserPoints()) {
                        int round = points.getFootballMatch().getRound();
                        int p = points.getPoints();
                        pointsPerRound.putIfAbsent(round, 0);
                        pointsPerRound.put(round, pointsPerRound.get(round) + p);
                    }
                }
                d.setPointsPerRound(pointsPerRound);
                d.setAveragePoints(pointsPerRound.values().stream().mapToInt(x->x).average().orElse(0));
                d.setMaxPoints(pointsPerRound.values().stream().max(Integer::compareTo).orElse(0));
                d.setMinPoints(pointsPerRound.values().stream().min(Integer::compareTo).orElse(0));
                d.setPoints(pointsPerRound.values().stream().mapToInt(x->x).sum());

                return d;
            }

        };
        modelMapper.addConverter(infoContainerConverter);
    }
}
