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
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.views.AppView;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.model.dto.UserDTO;

@PageTitle("Add vacancy")
@Route(value = Globals.Pages.BUSINESSVACANCY, layout = AppView.class)
@CssImport("./styles/index.css")
//@RolesAllowed("ADMIN")
public class AddVacancyView extends Div {

    private TextField titel = new TextField("Titel");
    private TextField description = new TextField("Description");

    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<VacancyDTO> binder = new Binder(VacancyDTO.class);

    public AddVacancyView(VacancyService vacancyService) {
        addClassName("add-vacancy-view");

        VerticalLayout c1 = new VerticalLayout();
        c1.add(createTitle());
        c1.add(createFormLayout());
        c1.add(createButtonLayout());

        add(c1);

        binder.bindInstanceFields(this); // Nr. 1 HOOK / API-Methode
        clearForm();

        cancel.addClickListener(event -> clearForm());

        save.addClickListener(
                e -> {
                    // Speicherung der Daten über das zuhörige Control-Object.
                    // Daten des Autos werden aus Formular erfasst und als DTO übergeben.
                    // Zusätzlich wird das aktuelle UserDTO übergeben.
                    BusinessDTO businessDTO =
                            (BusinessDTO) UI.getCurrent().getSession().getAttribute(Globals.CURRENT_USER);
                    vacancyService.saveVacancy(binder.getBean(), businessDTO);

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
