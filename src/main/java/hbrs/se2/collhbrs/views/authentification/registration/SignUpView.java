package hbrs.se2.collhbrs.views.authentification.registration;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import hbrs.se2.collhbrs.service.registration.RegisterServiceImpl;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Route(value = Globals.Pages.SIGNUP)
@CssImport("./styles/index.css")
@AnonymousAllowed
@Component
public class SignUpView extends FormLayout {

    private final BusinessRegistrationView businessRegistrationView;
    private final StudentRegistrationView studentRegistrationView;

    @Autowired
    public SignUpView(RegisterServiceImpl registerService) {
        String student = "Student";
        String company = "Company";

        setupLayout();

        businessRegistrationView = new BusinessRegistrationView(registerService);
        studentRegistrationView = new StudentRegistrationView(registerService);

        RadioButtonGroup<String> roleSelector = new RadioButtonGroup<>();
        roleSelector.setLabel("Register as:");
        roleSelector.setItems(student, company);

        FormLayout dynamicFields = new FormLayout();

        roleSelector.setValue(student);
        dynamicFields.add(studentRegistrationView);

        roleSelector.addValueChangeListener(event -> {
            dynamicFields.removeAll();
            if (student.equals(event.getValue())) {
                dynamicFields.add(studentRegistrationView);
            } else if (company.equals(event.getValue())) {
                dynamicFields.add(businessRegistrationView);
            }
        });

        add(roleSelector, dynamicFields);
    }

    private void setupLayout() {
        addClassName("sign-in");
        setMaxWidth("500px");
    }
}