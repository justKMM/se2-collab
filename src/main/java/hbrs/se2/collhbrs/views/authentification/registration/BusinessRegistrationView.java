package hbrs.se2.collhbrs.views.authentification.registration;

import com.vaadin.flow.component.textfield.TextField;
import hbrs.se2.collhbrs.service.registration.RegisterServiceImpl;
import org.springframework.stereotype.Component;

@Component
public class BusinessRegistrationView extends BaseRegistrationView {

    private TextField companyNameTextField;

    public BusinessRegistrationView(RegisterServiceImpl registerService) {
        super(registerService);
        setupBusinessForm();
    }

    private void setupBusinessForm() {
        companyNameTextField = new TextField("Unternehmensname");
        add(companyNameTextField);
    }

    @Override
    protected void register() {
        String username = usernameField.getValue();
        String password = passwordField.getValue();
        String email = emailField.getValue();
        String companyName = companyNameTextField.getValue();

        registerService.registerBusiness(username, password, email, companyName);
    }
}