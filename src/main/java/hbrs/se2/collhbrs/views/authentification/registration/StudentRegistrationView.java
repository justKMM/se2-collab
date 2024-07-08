package hbrs.se2.collhbrs.views.authentification.registration;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import hbrs.se2.collhbrs.service.registration.RegisterService;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class StudentRegistrationView extends BaseRegistrationView {

    private TextField firstNameTextField;
    private TextField lastNameTextField;

    @Autowired
    public StudentRegistrationView(@Qualifier("registerProxy") RegisterService registerService) {
        super(registerService);
        setupStudentForm();
    }

    private void setupStudentForm() {
        firstNameTextField = new TextField("Vornamen");
        lastNameTextField = new TextField("Nachnamen");

        FormLayout nameLayout = new FormLayout();
        nameLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2)
        );
        nameLayout.add(firstNameTextField, lastNameTextField);
        nameLayout.setColspan(firstNameTextField, 1);
        nameLayout.setColspan(lastNameTextField, 1);

        FormLayout passwordLayout = new FormLayout();
        passwordLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2)
        );
        passwordLayout.add(passwordField, passwordConfirmationField);
        passwordLayout.setColspan(passwordField, 1);
        passwordLayout.setColspan(passwordConfirmationField, 1);

        add(nameLayout, usernameField, emailField, passwordLayout, submitButton, cancelButton);
    }

    @Override
    protected void register() {
        String username = usernameField.getValue();
        String password = passwordField.getValue();
        String email = emailField.getValue();
        String firstName = this.firstNameTextField.getValue();
        String lastName = this.lastNameTextField.getValue();

        registerService.registerStudent(username, password, email, firstName, lastName);
        UI.getCurrent().navigate(Globals.Pages.LOGIN);
    }
}