package ex.guesser.areas.common.interceptor;

import ex.guesser.areas.common.dtos.CustomLocalDateTimeDto;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomLocalDateFormInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
         modelAndView.addObject("date", new CustomLocalDateTimeDto());
    }
}
