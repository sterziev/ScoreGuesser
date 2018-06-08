package ex.guesser.areas.common.controller;

import ex.guesser.areas.matches.models.binding.MatchDisplayContainer;
import ex.guesser.areas.points.services.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController extends BaseController {
    private final PredictionService predictionService;

    @Autowired
    public HomeController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/")
    public ModelAndView index() {

        MatchDisplayContainer matchDisplayContainer = this.predictionService.getAllPredictions();
//        MatchDisplayContainer matchDisplayContainer = this.predictionService.getCurrentPredictions(principal);
        return this.view("index","matches",matchDisplayContainer);
    }
}
