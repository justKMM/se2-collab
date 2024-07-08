package hbrs.se2.collhbrs.views.authentification.registration;

import com.vaadin.flow.component.textfield.TextField;
import hbrs.se2.collhbrs.service.registration.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class BusinessRegistrationView extends BaseRegistrationView {

    private TextField companyNameTextField;

    @Autowired
    public BusinessRegistrationView(@Qualifier("registerProxy") RegisterService registerService) {
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