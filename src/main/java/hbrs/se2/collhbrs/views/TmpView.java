package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import java.util.stream.Stream;

@Route(value = "registration")
@CssImport("./styles/index.css")
public class TmpView extends FormLayout {

    private H3 title;
    private TextField firstName;
    private TextField lastName;
    private TextField username;
    private EmailField email;
    private PasswordField password;
    private PasswordField passwordConfirm;
    private Span errorMessageField;
    private Button submitButton;

    public TmpView() {

        addClassName("register");
        title = new H3("Sign Up");
        firstName = new TextField("First name");
        lastName = new TextField("Last name");
        username =  new TextField("Username");
        email = new EmailField("Email");
        password = new PasswordField("Password");
        passwordConfirm = new PasswordField("Confirm password");

        setRequiredIndicatorVisible(
                firstName,
                lastName,
                username,
                email,
                password,
                passwordConfirm
        );

        errorMessageField = new Span();

        submitButton = new Button("Sign Up");
        submitButton.addThemeVariants(ButtonVariant.LUMO_ICON);
        submitButton.addClassName("button-layout");

        username.addClassName("username");

        add(
                title, firstName, lastName, username, email, password,
                passwordConfirm, errorMessageField,
                submitButton
        );

        setMaxWidth("500px");

        setResponsiveSteps(
                new ResponsiveStep("0", 1, ResponsiveStep.LabelsPosition.TOP),
                new ResponsiveStep("490px", 2, ResponsiveStep.LabelsPosition.TOP)
        );

        setColspan(title, 2);
        setColspan(email, 2);
        setColspan(errorMessageField, 2);
        setColspan(submitButton, 2);
    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
