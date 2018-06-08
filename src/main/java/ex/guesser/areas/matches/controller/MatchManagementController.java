package ex.guesser.areas.matches.controller;

import ex.guesser.areas.common.controller.BaseController;
import ex.guesser.areas.common.dtos.CustomLocalDateTimeDto;
import ex.guesser.areas.matches.models.binding.AddMatchBinningModel;
import ex.guesser.areas.matches.models.binding.MatchDisplayContainer;
import ex.guesser.areas.matches.models.binding.MatchStatusChangeBindingContainer;
import ex.guesser.areas.matches.models.dtos.MatchCloseDtoContainer;
import ex.guesser.areas.matches.models.dtos.TeamNamesDto;
import ex.guesser.areas.matches.services.MatchService;
import ex.guesser.areas.matches.services.TeamService;
import ex.guesser.areas.points.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/matches")
@PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
public class MatchManagementController extends BaseController{
    private final MatchService matchService;
    private final PredictionService predictionService;
    private final TeamService teamService;

    @Autowired
    public MatchManagementController(MatchService matchService, PredictionService predictionService, TeamService teamService) {
        this.matchService = matchService;
        this.predictionService = predictionService;
        this.teamService = teamService;
    }

    @GetMapping("/changeCurrentToClose")
    public ModelAndView changeCurrentToClose() {

        MatchCloseDtoContainer matchCloseDtosContainer = this.matchService.getMatchesForChangesByStatus("current");
        return this.view("match/changeMatchStatus","matches",matchCloseDtosContainer);
    }

    @GetMapping("/changeClosedToFinish")
    public ModelAndView changeClosedToFinish() {

        MatchCloseDtoContainer matchCloseDtosContainer = this.matchService.getMatchesForChangesByStatus("closed");
        return this.view("match/changeMatchStatus","matches",matchCloseDtosContainer);
    }

    @GetMapping("/changePlannedToCurrent")
    public ModelAndView changePlannedToCurrent() {
        MatchCloseDtoContainer matchCloseDtosContainer = this.matchService.getMatchesForChangesByStatus("planned");
        return this.view("match/changeMatchStatus","matches",matchCloseDtosContainer);
    }

    @PostMapping("/changeStatus")
    public String currentToClose(@ModelAttribute MatchStatusChangeBindingContainer bindingModel,
                                 BindingResult bindingResult, Model model) {

        this.matchService.changeStatus(bindingModel);
        MatchDisplayContainer matchDisplayContainer = this.predictionService.getAllPredictions();
        model.addAttribute("matches", matchDisplayContainer);



        return "redirect:/matches/showAll";
    }

    @GetMapping("/addMatch")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView addMatch(@ModelAttribute AddMatchBinningModel bindingModel, ModelAndView modelAndView) {
        List<TeamNamesDto> teamNames = this.teamService.getTeamNames();
        return this.view("match/addMatch","match",bindingModel,"teams", teamNames);
    }

    @PostMapping("/addMatch")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ModelAndView add(@Valid @ModelAttribute("match") AddMatchBinningModel bindingModel, BindingResult bindingResult,
                            ModelAndView modelAndView, Principal principal) {
        if (bindingResult.hasErrors()) {
            if (bindingResult.hasGlobalErrors()) {
                bindingResult.rejectValue("home", null, bindingResult.getGlobalError().getDefaultMessage());
                bindingResult.rejectValue("away", null, bindingResult.getGlobalError().getDefaultMessage());
            }
            modelAndView.setViewName("match/addMatch");
            //modelAndView.setViewName("match/addMatch?error");

            List<TeamNamesDto> teamNames = this.teamService.getTeamNames();
            modelAndView.addObject("date", new CustomLocalDateTimeDto());
            modelAndView.addObject("teams", teamNames);
            return modelAndView;
        }

        this.matchService.addMatch(bindingModel);

        modelAndView.setViewName("redirect:/matches/showAll");
        MatchDisplayContainer matchDisplayContainer = this.predictionService.getAllPredictions();
        modelAndView.addObject("matches", matchDisplayContainer);
        return modelAndView;
    }
}
