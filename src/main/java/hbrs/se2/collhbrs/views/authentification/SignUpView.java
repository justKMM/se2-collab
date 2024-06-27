package hbrs.se2.collhbrs.views.authentification;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import hbrs.se2.collhbrs.service.RegisterService;
import hbrs.se2.collhbrs.util.Globals;

@Route(value = Globals.Pages.SIGNUP)
@CssImport("./styles/index.css")
@AnonymousAllowed
public class SignUpView extends FormLayout {

    private final BusinessRegistrationView businessRegistrationView;
    private final StudentRegistrationView studentRegistrationView;
    private final String student = "Student";
    private final String company = "Company";

    public SignUpView(RegisterService registerService) {

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
