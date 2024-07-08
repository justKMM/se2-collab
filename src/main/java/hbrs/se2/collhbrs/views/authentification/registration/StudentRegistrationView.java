package hbrs.se2.collhbrs.views.authentification.registration;

import com.vaadin.flow.component.textfield.TextField;
import hbrs.se2.collhbrs.service.registration.RegisterProxy;
import org.springframework.stereotype.Component;

@Component
public class StudentRegistrationView extends BaseRegistrationView {


    private TextField firstNameTextField;
    private TextField lastNameTextField;

    public StudentRegistrationView(RegisterProxy registerProxy) {
        super(registerProxy);
        setupStudentForm();
    }

    private void setupStudentForm() {
        firstNameTextField = new TextField("Vornamen");
        lastNameTextField = new TextField("Nachnamen");
        add(firstNameTextField, lastNameTextField);
    }

    @Override
    protected void register() {
        String username = usernameField.getValue();
        String password = passwordField.getValue();
        String email = emailField.getValue();
        String firstName = this.firstNameTextField.getValue();
        String lastName = this.lastNameTextField.getValue();

        registerProxy.registerStudent(username, password, email, firstName, lastName);
    }
}