package ex.guesser.areas.user.controller;

import ex.guesser.areas.common.controller.BaseController;
import ex.guesser.areas.errorHandling.errors.AlreadyInLeagueException;
import ex.guesser.areas.errorHandling.errors.LeagueNotFound;
import ex.guesser.areas.errorHandling.errors.NotAuthorizedToCreateLeagueException;
import ex.guesser.areas.user.models.binding.JoinMiniLeagueBM;
import ex.guesser.areas.user.models.binding.MiniLeagueBM;
import ex.guesser.areas.user.services.MiniLeagueService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller()
@RequestMapping("/miniLeague")
public class MiniLeagueController extends BaseController {
    private final MiniLeagueService miniLeagueService;

    public MiniLeagueController(MiniLeagueService miniLeagueService) {
        this.miniLeagueService = miniLeagueService;
    }

    @GetMapping("/create")
    public ModelAndView create(MiniLeagueBM miniLeagueBM){

        return this.view("miniLeague/create","league",miniLeagueBM);
    }

    @PostMapping("/create")
    public ModelAndView create(@Valid @ModelAttribute("league") MiniLeagueBM miniLeagueBM, BindingResult bindingResult,
                               Authentication authentication){
        if (bindingResult.hasErrors()) {
            return this.view("miniLeague/create");
        }


        this.miniLeagueService.create(miniLeagueBM, authentication);

        return this.redirect("/");
    }

    @GetMapping("/join")
    public ModelAndView join(JoinMiniLeagueBM miniLeagueBM){

        return this.view("miniLeague/join","league",miniLeagueBM);
    }

    @PostMapping("/join")
    public ModelAndView join(@Valid @ModelAttribute("league") JoinMiniLeagueBM miniLeagueBM, BindingResult bindingResult,
                             Authentication authentication){
        if (bindingResult.hasErrors()) {
            return this.view("miniLeague/join");
        }


        try {
            this.miniLeagueService.join(miniLeagueBM, authentication);
        }
        catch (NotAuthorizedToCreateLeagueException notAtuth){
            bindingResult.reject(null,"You have no access to this league");
        }
        catch (LeagueNotFound lnf){
            bindingResult.reject(null,lnf.getMessage());
        }
        catch (AlreadyInLeagueException ail){
            bindingResult.reject(null,ail.getMessage());
        }
        if (bindingResult.hasErrors()) {
            return this.view("miniLeague/join");
        }

        return this.redirect("/");
    }


}
