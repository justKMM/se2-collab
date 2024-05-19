package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.entity.Benutzer;
import hbrs.se2.collhbrs.entity.Profil;
import hbrs.se2.collhbrs.service.RegisterService;

@Route(value = "tmp")
@PageTitle("User Registration")
@CssImport("./styles/index.css")
public class RegistrationView extends Div {

    private TextField username = new TextField("Benutzername");
    private PasswordField password = new PasswordField("Passwort");
    private PasswordField passwordConfirmation = new PasswordField("Passwort (Wdh)");
    private Button register = new Button("Registrieren");

    public RegistrationView(RegisterService registerService) {

        addClassName("register");
        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        clearForm();

        register.addClickListener(e -> {

            Profil profil = new Profil();
            Benutzer benutzer = new Benutzer();
            benutzer.setProfil(profil);
            benutzer.setUsername(username.getValue());
            benutzer.setPasswort(password.getValue());
            benutzer.setBlacklisted(0);
            registerService.saveProfil(profil);
            registerService.saveBenutzer(benutzer);

            Notification.show("Benutzer erfolgreich registriert");
            UI.getCurrent().navigate("login");
        });
    }

    private void clearForm() {
        password.clear();
        passwordConfirmation.clear();
    }

    private H3 createTitle() {
        return new H3("Benutzer-Registration");
    }

    private FormLayout createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(username, password, passwordConfirmation);
        return formLayout;
    }

    private HorizontalLayout createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        register.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        buttonLayout.add(register);
        return buttonLayout;
    }
}