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
public class BusinessRegistrationView extends BaseRegistrationView {

    private TextField companyNameField;

    @Autowired
    public BusinessRegistrationView(@Qualifier("registerProxy") RegisterService registerService) {
        super(registerService);
        setupBusinessForm();
    }

    private void setupBusinessForm() {
        companyNameField = new TextField("Unternehmensname");

        FormLayout passwordLayout = new FormLayout();
        passwordLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 2)
        );
        passwordLayout.add(passwordField, passwordConfirmationField);
        passwordLayout.setColspan(passwordField, 1);
        passwordLayout.setColspan(passwordConfirmationField, 1);

        add(companyNameField, 2);
        add(usernameField, 2);
        add(emailField, 2);
        add(passwordLayout);
        add(submitButton, 2);
        add(cancelButton, 2);
    }

    @Override
    protected void register() {
        String username = usernameField.getValue();
        String password = passwordField.getValue();
        String email = emailField.getValue();
        String companyName = companyNameField.getValue();

        registerService.registerBusiness(username, password, email, companyName);
        UI.getCurrent().navigate(Globals.Pages.LOGIN);
    }
}