package ex.guesser.areas.common.interceptor;

import ex.guesser.areas.common.commonFunctions.CustomMapperUser;
import ex.guesser.areas.common.dtos.InfoContainer;
import ex.guesser.areas.user.entities.MiniLeague;
import ex.guesser.areas.user.entities.User;
import ex.guesser.areas.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class InfoAddingInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomMapperUser mapper;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        InfoContainer container = new InfoContainer();
        modelAndView.getModel().putIfAbsent("currentLeague", new MiniLeague());
        if (modelAndView.getModel().containsKey("guestId")){
            String id = (String) modelAndView.getModel().get("guestId");
            User userEntity = this.userRepository.findByUserIdFetchMiniLeaguesAndPoints(id);
            container = mapper.mapUserToInfoContainer(userEntity);
        }
        else if (principal instanceof UserDetails) {
            User user = ((User)principal);
            User userEntity = this.userRepository.findByUsernameFetchMiniLeaguesAndPoints(user.getUsername());
            container = mapper.mapUserToInfoContainer(userEntity);
        } else {
            container = getInfoForAnonymous();
        }
        modelAndView.addObject("container", container);
    }

    private InfoContainer getInfoForAnonymous() {
        return new InfoContainer();
    }
}
