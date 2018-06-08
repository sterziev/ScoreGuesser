package ex.guesser.areas.user.controller;

import ex.guesser.areas.common.controller.BaseController;
import ex.guesser.areas.matches.models.binding.MatchDisplayContainer;
import ex.guesser.areas.points.services.PredictionService;
import ex.guesser.areas.user.models.dtos.MiniLeagueDto;
import ex.guesser.areas.user.models.dtos.UserWithPointsDto;
import ex.guesser.areas.user.services.MiniLeagueService;
import ex.guesser.areas.user.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserPointsController extends BaseController{
    private final UserService userService;
    private final MiniLeagueService miniLeagueService;
    private final PredictionService predictionService;

    public UserPointsController(UserService userService, MiniLeagueService miniLeagueService, PredictionService predictionService) {
        this.userService = userService;
        this.miniLeagueService = miniLeagueService;
        this.predictionService = predictionService;
    }

    @GetMapping("/standing")
    public ModelAndView standingGeneral() {
        List<UserWithPointsDto> usersPoints = this.userService.getUserPoints()
                .stream()
                .sorted((x2,x1)->x1.getPointsNumber().compareTo(x2.getPointsNumber()))
                .collect(Collectors.toList());

        return this.view("user/standing","userPoints", usersPoints);

    }


    @GetMapping("/standing/ml/{id}")
    public ModelAndView showMiniLeague(@PathVariable String id){
        List<UserWithPointsDto> usersPoints = this.userService.getUserPointsFromLeague(id)
                .stream()
                .sorted((x2,x1)->x1.getPointsNumber().compareTo(x2.getPointsNumber()))
                .collect(Collectors.toList());
        MiniLeagueDto miniLeague = this.miniLeagueService.findById(id);

        return this.view("user/standing","userPoints", usersPoints,"currentLeague",miniLeague);
    }

    @GetMapping("/userInfo/{id}")
    public ModelAndView index(@PathVariable String id) {

        MatchDisplayContainer matchDisplayContainer = this.predictionService.getFinishedAndClosedPredictions(id);
        return this.view("user/userPointsAndPredictions","matches",matchDisplayContainer, "guestId",id);
    }
}
