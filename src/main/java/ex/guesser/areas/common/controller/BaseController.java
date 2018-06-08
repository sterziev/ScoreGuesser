package ex.guesser.areas.common.controller;

import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
    protected ModelAndView view(String view) {
        ModelAndView modelAndView = new ModelAndView(view);

        return modelAndView;
    }

    protected ModelAndView view(String view, String name, Object model) {
        ModelAndView modelAndView = new ModelAndView(view);
        modelAndView.addObject(name, model);

        return modelAndView;
    }

    protected ModelAndView view(String view, String name, Object model, String name2, Object model2) {
        ModelAndView modelAndView = new ModelAndView(view);

        modelAndView.addObject(name, model);
        modelAndView.addObject(name2, model2);

        return modelAndView;
    }

    protected ModelAndView redirect(String url) {
        return new ModelAndView("redirect:" + url);
    }

}
