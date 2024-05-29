package hbrs.se2.collhbrs.views.AuthentificationViews;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import hbrs.se2.collhbrs.entity.User;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.util.Globals;

@Route(value = "")
@RouteAlias(value = Globals.Pages.LOGIN_ALIAS)
@CssImport("./styles/index.css")
public class LoginView extends VerticalLayout {

    public LoginView(LoginService loginService) {
        addClassName("main");
        setSizeFull();

        LoginForm component = createLoginForm();
        add(component);
        this.setAlignItems(Alignment.CENTER);

        RouterLink studentRegisterLink = new RouterLink("Register as a student", StudentRegistrationView.class);
        add(studentRegisterLink);

        RouterLink unternehmenRegisterLink = new RouterLink("Register as a company", BusinessRegistrationView.class);
        add(unternehmenRegisterLink);
        component.addLoginListener(input -> handleLogin(input, loginService));
    }

    // TODO: Fehler Message fürs Username & Passwort Feld wenn man keins eingibt übersetzen

    private LoginForm createLoginForm() {
        LoginForm component = new LoginForm();
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();

        i18nForm.setTitle("Login");
        i18nForm.setUsername("Username");
        i18nForm.setPassword("Password");
        i18nForm.setSubmit("login");
        i18nForm.setForgotPassword("Forgot Password?");
        i18n.setForm(i18nForm);
        component.setI18n(i18n);

        return component;
    }

    private void handleLogin(LoginForm.LoginEvent input, LoginService loginService) {
        try {
            User user = loginService.getUser(input.getUsername(), input.getPassword());
            Notification notificationSuccess = Notification.show("Successfully logged in as " + user.getUsername());
            notificationSuccess.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
            if (user.getBlacklisted() == 1) {
                Notification notificationBlacklisted = Notification.show("Login failed: User is blacklisted");
                notificationBlacklisted.addThemeVariants(NotificationVariant.LUMO_ERROR);
                return;
            }
            notificationSuccess.open();
            UI.getCurrent().navigate(Globals.Pages.MAIN);
        } catch (Exception e) {
            Notification notificationFailed = Notification.show("Login failed");
            notificationFailed.addThemeVariants(NotificationVariant.LUMO_ERROR);
            e.printStackTrace();
        }
    }
}