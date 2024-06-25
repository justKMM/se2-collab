package hbrs.se2.collhbrs;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.service.SecurityService;
import hbrs.se2.collhbrs.views.AuthentificationViews.LoginView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends VaadinWebSecurity {

    @Autowired
    SecurityService securityService;

    @Autowired
    LoginService loginService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginView.class);
    }

    @Bean
    public AuthenticationProvider customAuthenticationProvider() {
        return new AuthenticationProvider() {

            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                return new UsernamePasswordAuthenticationToken(
                        securityService.loadUserByUsername(authentication.getName()),
                        authentication.getCredentials(),
                        securityService.loadUserByUsername(authentication.getName()).getAuthorities()
                );
            }

            @Override
            public boolean supports(Class<?> authentication) {
                return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
            }
        };
    }


}
