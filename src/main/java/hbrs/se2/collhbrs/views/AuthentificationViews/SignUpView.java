package hbrs.se2.collhbrs.views.AuthentificationViews;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.service.RegisterService;
import hbrs.se2.collhbrs.util.Globals;

@Route(value = Globals.Pages.SIGNUP)
@CssImport("./styles/index.css")
public class SignUpView extends FormLayout {

    private BusinessRegistrationView businessRegistrationView;
    private StudentRegistrationView studentRegistrationView;

    public SignUpView(RegisterService registerService) {

        setupLayout();

        businessRegistrationView = new BusinessRegistrationView(registerService);
        studentRegistrationView = new StudentRegistrationView(registerService);

        RadioButtonGroup<String> roleSelector = new RadioButtonGroup<>();
        roleSelector.setLabel("Register as:");
        roleSelector.setItems("Student", "Company");

        FormLayout dynamicFields = new FormLayout();

        roleSelector.setValue("Student");
        dynamicFields.add(studentRegistrationView);

        roleSelector.addValueChangeListener(event -> {
            dynamicFields.removeAll();
            if ("Student".equals(event.getValue())) {
                dynamicFields.add(studentRegistrationView);
            } else if ("Company".equals(event.getValue())) {
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
