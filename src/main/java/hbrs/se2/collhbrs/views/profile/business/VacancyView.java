package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.service.RequirementsService;
import hbrs.se2.collhbrs.service.ResponsibilitiesService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.EntityFactory;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@PageTitle("Add vacancy")
@Route(value = Globals.Pages.VACANCY, layout = AppView.class)
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class VacancyView extends Composite<VerticalLayout> {

    private final List<String> requirementItems = new ArrayList<>();
    private final List<String> responsibilityItems = new ArrayList<>();
    @Autowired
    EntityFactory entityFactory;
    @Autowired
    RequirementsService requirementsService;
    @Autowired
    ResponsibilitiesService responsibilitiesService;
    @Autowired
    SessionService sessionService;
    @Autowired
    VacancyService vacancyService;
    private MultiSelectListBox<String> requirementsList;
    private MultiSelectListBox<String> responsibilitiesList;

    public VacancyView() {
        setUpUI();
    }

    private void setUpUI() {
        ComboBox<String> comboBox;
        TextArea textArea;
        TextField location;
        TextField requirements;
        TextField responsibilities;
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3("Add Vacancy");
        comboBox = new ComboBox<>("Titel");
        comboBox.setItems("Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt",
                "Masterprojekt", "Büro", "Homeoffice");
        location = new TextField("Location");
        FormLayout formLayout2Col = new FormLayout();
        requirements = new TextField("Requirements");
        responsibilities = new TextField("Responsibilities");
        Button addRequirements = new Button("Add");
        Button addResponsibility = new Button("Add");
        Button deleteRequirements = new Button("Delete");
        Button deleteResponsibilities = new Button("Delete");
        addRequirements.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addResponsibility.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteRequirements.addThemeVariants(ButtonVariant.LUMO_ERROR);
        deleteResponsibilities.addThemeVariants(ButtonVariant.LUMO_ERROR);
        requirementsList = new MultiSelectListBox<>();
        responsibilitiesList = new MultiSelectListBox<>();
        textArea = new TextArea("Description");
        textArea.setWidth("100%");
        textArea.setHeight("200px");
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancel = new Button("Cancel");

        String minContent = "min-content";
        layoutColumn2.setWidthFull();
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight(minContent);
        layoutColumn2.setPadding(true);

        h3.setWidth(minContent);

        comboBox.setWidth(minContent);

        formLayout2Col.setWidth("100%");

        HorizontalLayout requirementsButtonsLayout = new HorizontalLayout(addRequirements, deleteRequirements);
        VerticalLayout requirementsLayout = new VerticalLayout(requirements, requirementsButtonsLayout, requirementsList);
        HorizontalLayout responsibilitiesButtonsLayout = new HorizontalLayout(addResponsibility, deleteResponsibilities);
        VerticalLayout responsibilitiesLayout = new VerticalLayout(responsibilities, responsibilitiesButtonsLayout, responsibilitiesList);
        formLayout2Col.add(requirementsLayout, responsibilitiesLayout);

        addRequirements.addClickListener(event -> {
            String requirement = requirements.getValue();
            if (!requirement.isEmpty() && !requirementItems.contains(requirement)) {
                requirementItems.add(requirement);
                updateRequirementsList();
                requirements.clear();
            }
        });

        addResponsibility.addClickListener(event -> {
            String responsibility = responsibilities.getValue();
            if (!responsibility.isEmpty() && !responsibilityItems.contains(responsibility)) {
                responsibilityItems.add(responsibility);
                updateResponsibilitiesList();
                responsibilities.clear();
            }
        });

        deleteRequirements.addClickListener(event -> {
            requirementsList.getSelectedItems().forEach(requirementItems::remove);
            updateRequirementsList();
        });

        deleteResponsibilities.addClickListener(event -> {
            responsibilitiesList.getSelectedItems().forEach(responsibilityItems::remove);
            updateResponsibilitiesList();
        });

        requirementsList.setWidth("100%");
        responsibilitiesList.setWidth("100%");

        layoutRow.setWidthFull();
        layoutRow.setHeight("min-content");
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);

        save.addClickListener(event -> {
            vacancyService.saveVacancy(entityFactory.createVacancy(comboBox.getValue(),
                    location.getValue(), textArea.getValue(), sessionService.getCurrentBusiness().getBusiness(),
                    Date.valueOf(LocalDate.now())));
            List<Vacancy> vacancies = vacancyService.getVacanciesByBusinessId(sessionService.getCurrentBusiness().getBusiness().getBusinessID());
            for (Vacancy vacancy : vacancies) {
                for (String requirement : requirementItems) {
                    requirementsService.saveRequirements(entityFactory.createRequirements(vacancy, requirement));
                }
                for (String responsibility : responsibilityItems) {
                    responsibilitiesService.saveResponsibilities(entityFactory.createResponsibilties(vacancy, responsibility));
                }
            }
            requirementItems.clear();
            responsibilityItems.clear();
        });

        cancel.addClickListener(event -> {
            comboBox.clear();
            requirements.clear();
            responsibilities.clear();
            textArea.clear();
        });

        layoutRow.add(save, cancel);

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);

        getContent().add(layoutColumn2);
        layoutColumn2.add(h3, comboBox, location, formLayout2Col, textArea, layoutRow);

        updateRequirementsList();
        updateResponsibilitiesList();
    }

    private void updateRequirementsList() {
        requirementsList.setItems(requirementItems);
    }

    private void updateResponsibilitiesList() {
        responsibilitiesList.setItems(responsibilityItems);
    }
}
