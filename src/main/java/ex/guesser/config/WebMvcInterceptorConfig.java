package ex.guesser.config;

import ex.guesser.areas.common.interceptor.CustomLocalDateFormInterceptor;
import ex.guesser.areas.common.interceptor.InfoAddingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcInterceptorConfig implements WebMvcConfigurer {

    private final CustomLocalDateFormInterceptor customLocalDateFormInterceptor;
    private final InfoAddingInterceptor infoAddingInterceptor;

    @Autowired
    public WebMvcInterceptorConfig(CustomLocalDateFormInterceptor customLocalDateFormInterceptor, InfoAddingInterceptor infoAddingInterceptor) {
        this.customLocalDateFormInterceptor = customLocalDateFormInterceptor;
        this.infoAddingInterceptor = infoAddingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.customLocalDateFormInterceptor)
                .addPathPatterns("/register", "/user/**","/matches/addMatch");

        registry.addInterceptor(this.infoAddingInterceptor)
                .addPathPatterns("/","/standing/**","/userInfo/**");
    }
}
