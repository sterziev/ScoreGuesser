package ex.guesser.areas.points.services;

import ex.guesser.areas.matches.services.MatchService;
import ex.guesser.areas.points.repositories.PointRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class PointsServiceImpl implements PointsService {


    private final PointRepository pointRepository;
    private final PredictionService predictionService;
    private final MatchService matchService;
    private final ModelMapper mapper;

    @Autowired
    public PointsServiceImpl(@Lazy PredictionService predictionService, PointRepository pointRepository, MatchService matchService) {
        this.predictionService = predictionService;
        this.pointRepository = pointRepository;
        this.matchService = matchService;
        this.mapper = new ModelMapper();
    }



//    @Override
//    public List<PointsDto> findAllByUserName(String name) {
//        List<Points> allPoints = this.pointRepository.findAllByUser_Username(name);
//        Type pointsTypeList = new TypeToken<List<PointsDto>>() {}.getType();
//        return this.mapper.map(allPoints, pointsTypeList);
//    }

//    @Override
//    public List<PointsDto> findAll() {
//        List<Points> allPoints = this.pointRepository.findAll();
//        Type pointsTypeList = new TypeToken<List<PointsDto>>() {}.getType();
//        return this.mapper.map(allPoints, pointsTypeList);
//    }

//    @Override
//    public Map<String,Integer> findAllCurrentPointsDistinct() {
//        List<PointsDto> pointsDtos = this.findAll();
//        Map<String,Integer> userPointsMap = new HashMap<>();
//        Set<String> uniqueUsers = pointsDtos.stream().map(m -> m.getUser().getId()).collect(Collectors.toSet());
//        for (String uniqueUser : uniqueUsers) {
//            int points = pointsDtos.stream()
//                    .filter(f->f.getUser().getId().equals(uniqueUser))
//                    .mapToInt(PointsDto::getPoints)
//                    .sum();
//            userPointsMap.put(uniqueUser,points);
//        }
//        return userPointsMap;
//    }




}
