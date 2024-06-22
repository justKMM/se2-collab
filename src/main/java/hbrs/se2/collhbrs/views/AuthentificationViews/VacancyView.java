package hbrs.se2.collhbrs.views.AuthentificationViews;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.views.AppView;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Add vacancy")
@Route(value = Globals.Pages.BUSINESS_VACANCY, layout = AppView.class)
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class VacancyView extends Div {

    private TextField titel = new TextField("Titel");
    private TextField description = new TextField("Description");

    private Button cancel = new Button("Cancel");
    private  Button save = new Button("Save");

    private Binder<VacancyDTO> binder = new Binder(VacancyDTO.class);

    public VacancyView(VacancyService vacancyService) {
        addClassName("add-vacancy-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this); // Nr. 1 HOOK / API-Methode
        clearForm();

        cancel.addClickListener(event -> clearForm());

        save.addClickListener(
                e -> {
                    // Speicherung der Daten über das zuhörige Control-Object.
                    // Daten des Autos werden aus Formular erfasst und als DTO übergeben.
                    // Zusätzlich wird das aktuelle UserDTO übergeben.
                    UserDTO userDTO =
                            (UserDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
                    vacancyService.createVacancy(binder.getBean(), userDTO);

                    Notification.show("Vacancy stored.");
                    clearForm();
                });

    }
    private void clearForm() {
        binder.setBean(new VacancyDTO());
    }

    private Component createTitle() {
        return new H3("Vacancy information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        formLayout.add(titel,description);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }
}
