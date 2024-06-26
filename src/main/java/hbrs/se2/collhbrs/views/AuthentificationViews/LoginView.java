package hbrs.se2.collhbrs.views.AuthentificationViews;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;

@Route(Globals.Pages.LOGIN)
@CssImport("./styles/index.css")
@AnonymousAllowed
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    @Autowired
    private LoginService loginService;
    private final LoginForm loginForm;
    public LoginView() {
        addClassName("main");
        setSizeFull();

        loginForm = createLoginForm();
        loginForm.setAction("login");
        add(loginForm);
        this.setAlignItems(Alignment.CENTER);

        HorizontalLayout additionalInfoLayout = new HorizontalLayout();
        additionalInfoLayout.add(new Text("Don't have an Account? "), new Anchor(Globals.Pages.SIGNUP, "Sign up"));
        additionalInfoLayout.setAlignItems(Alignment.CENTER);

        VerticalLayout layout = new VerticalLayout(loginForm, additionalInfoLayout);
        layout.setAlignItems(Alignment.CENTER);
        add(layout);

        loginForm.addForgotPasswordListener(e -> {
            UI.getCurrent().navigate(Globals.Pages.FORGOT_PASSWORD);
        });

        loginForm.addLoginListener(input -> handleLogin(input));
    }

    private LoginForm createLoginForm() {
        LoginForm component = new LoginForm();
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();

        i18nForm.setTitle("Sign in");
        i18nForm.setUsername("Username");
        i18nForm.setPassword("Password");
        i18nForm.setSubmit("Sign in");
        i18nForm.setForgotPassword("Forgot Password?");
        i18n.setForm(i18nForm);
        component.setI18n(i18n);

        return component;
    }

    private void handleLogin(LoginForm.LoginEvent input) {
        try {
            loginService.startSession(new UserDTO(loginService.login(input.getUsername(), input.getPassword())));
        } catch (Exception e) {
            Notification.show("User with this username and/or password could not be found!");
        }
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            loginForm.setError(true);
            Notification notification = Notification.show("Login failed");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        }
    }
}
