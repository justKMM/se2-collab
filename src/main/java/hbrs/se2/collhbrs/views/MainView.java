package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.login.LoginI18n;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import com.vaadin.flow.router.RouterLink;
import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.service.LoginService;

@Route(value = "" )
@RouteAlias(value = "login")
@CssImport("./styles/index.css")
public class MainView extends VerticalLayout {

    public MainView(LoginService loginService) {

        addClassName("main");
        setSizeFull();

        LoginForm component = new LoginForm();
        LoginI18n i18n = LoginI18n.createDefault();
        LoginI18n.Form i18nForm = i18n.getForm();

        i18nForm.setTitle("Anmelden");
        i18nForm.setUsername("Benutzername");
        i18nForm.setPassword("Passwort");
        i18nForm.setSubmit("Anmelden");
        i18nForm.setForgotPassword("Passwort vergessen?");
        i18n.setForm(i18nForm);
        component.setI18n(i18n);

        add(component);
        this.setAlignItems( Alignment.CENTER );

        RouterLink studentRegisterLink = new RouterLink("Als Student registrieren", StudentRegistrationView.class);
        add(studentRegisterLink);

        RouterLink unternehmenRegisterLink = new RouterLink("Als Unternehmen registrieren", UnternehmenRegistrationView.class);
        add(unternehmenRegisterLink);


        component.addLoginListener(input -> {
            try {
                Benutzer benutzer = loginService.getBenutzer(input.getUsername(), input.getPassword());
                if (benutzer.getBlacklisted() == 1) {
                    Notification.show("Login failed: User is blacklisted");
                    return;
                }
                Notification.show("Succesfully logged in as " + benutzer.getUsername());
            } catch (Exception e) {
                Notification.show("Login failed");
                e.printStackTrace();
            }
        });




    }
}
