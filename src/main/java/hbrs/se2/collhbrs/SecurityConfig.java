package hbrs.se2.collhbrs;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import hbrs.se2.collhbrs.service.ImplUserDetailsService;
import hbrs.se2.collhbrs.views.AuthentificationViews.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    @Autowired
    ImplUserDetailsService UserDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(request ->
                            request.requestMatchers(
                                    AntPathRequestMatcher.antMatcher(HttpMethod.GET, "/images/*.png")).permitAll());
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public UserDetailsService users() {
        return UserDetailsService;
    }
}
