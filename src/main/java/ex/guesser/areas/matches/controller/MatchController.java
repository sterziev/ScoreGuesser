package ex.guesser.areas.matches.controller;

import ex.guesser.areas.common.commonFunctions.CustomMapperUser;
import ex.guesser.areas.common.controller.BaseController;
import ex.guesser.areas.matches.models.binding.MatchDisplayContainer;
import ex.guesser.areas.matches.services.MatchService;
import ex.guesser.areas.matches.services.TeamService;
import ex.guesser.areas.points.services.PredictionService;
import ex.guesser.areas.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;

@Controller()
@RequestMapping("/matches")
public class MatchController extends BaseController{
    private final MatchService matchService;
    private final TeamService teamService;
    private final PredictionService predictionService;
    private final UserService userService;

    @Autowired
    public MatchController(MatchService matchService, TeamService teamService, CustomMapperUser mapper, PredictionService predictionService, UserService userService) {
        this.matchService = matchService;
        this.teamService = teamService;
        this.userService = userService;
        this.predictionService = predictionService;
    }



    @PostMapping("/persistPrediction")
    public ModelAndView persistPrediction(@Valid  @ModelAttribute MatchDisplayContainer bindingModel,
                                          BindingResult bindingResult, Principal principal, ModelAndView modelAndView) {

        if (bindingResult.hasErrors()){
            this.redirect("/");
        }
        int matchesNotPredicted = this.predictionService.addPrediction(bindingModel, principal);
//        MatchDisplayContainer matchDisplayContainer = this.predictionService.getAllPredictions();
//        bindingResult.reject(null,matchesNotPredicted+"");


        return this.redirect("/");

    }

    @GetMapping("/showAll")
    public ModelAndView showAllMatches() {

        MatchDisplayContainer matchDisplayContainer = this.predictionService.getAllPredictions();
        return this.view("match/showAll","matches",matchDisplayContainer);
    }
}
