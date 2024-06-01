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
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.service.db.exceptions.DatabaseLayerException;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "")
@CssImport("./styles/index.css")
public class LoginView extends VerticalLayout {

    @Autowired
    private LoginService loginService;

    public LoginView() {
        addClassName("main");
        setSizeFull();

        LoginForm component = createLoginForm();
        add(component);
        this.setAlignItems(Alignment.CENTER);

        HorizontalLayout additionalInfoLayout = new HorizontalLayout();
        additionalInfoLayout.add(new Text("Don't have an Account? "), new Anchor(Globals.Pages.SIGNUP, "Sign up"));
        additionalInfoLayout.setAlignItems(Alignment.CENTER);

        VerticalLayout layout = new VerticalLayout(component, additionalInfoLayout);
        layout.setAlignItems(Alignment.CENTER);
        add(layout);

        component.addForgotPasswordListener(e -> {
            UI.getCurrent().navigate(Globals.Pages.FORGOT_PASSWORD);
        });

        component.addLoginListener(input -> handleLogin(input));
    }

    // TODO: Fehler Message fürs Username & Passwort Feld wenn man keins eingibt übersetzen

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
            boolean isAuthenticated = loginService.authenticate(input.getUsername(), input.getPassword());

            if (isAuthenticated && loginService.isBlacklisted(input.getUsername(), input.getPassword())) {
                showNotification("Login failed: User is blacklisted", NotificationVariant.LUMO_ERROR);
                return;
            }

            if (isAuthenticated) {
                UserDTO user = loginService.getCurrentUser();

                grabAndSetUserIntoSession(user);
                showNotification("Successfully logged in as: " + user.getUsername(), NotificationVariant.LUMO_SUCCESS);
                UI.getCurrent().navigate(Globals.Pages.MAIN);
            } else {
                showNotification("Login failed: User not found", NotificationVariant.LUMO_ERROR);
            }
        } catch (DatabaseLayerException e) {
            // Handle database exceptions
            showNotification("Login failed", NotificationVariant.LUMO_ERROR);
            e.printStackTrace();
        }
    }

    private void showNotification(String message, NotificationVariant variant) {
        Notification notification = Notification.show(message);
        notification.addThemeVariants(variant);
        notification.open();
    }

    private void grabAndSetUserIntoSession(UserDTO user) {
        UserDTO userDTO = loginService.getCurrentUser();

        // Es hat mit dem unteren Befehl nicht funktioniert also hab ich direkt den User gesetzt in Global
        Globals.CURRENT_USER = userDTO.getUsername();
        // TODO: Was passiert hier ???
        UI.getCurrent().getSession().setAttribute(Globals.CURRENT_USER, userDTO);
    }


}
