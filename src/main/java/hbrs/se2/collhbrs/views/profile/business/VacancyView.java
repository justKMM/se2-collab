package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.listbox.MultiSelectListBox;
import com.vaadin.flow.component.notification.Notification;
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
import hbrs.se2.collhbrs.util.MarkdownConverter;
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

    private final EntityFactory entityFactory;
    private final RequirementsService requirementsService;
    private final ResponsibilitiesService responsibilitiesService;
    private final SessionService sessionService;
    private final VacancyService vacancyService;

    private MultiSelectListBox<String> requirementsList;
    private MultiSelectListBox<String> responsibilitiesList;
    private TextField title;

    @Autowired
    public VacancyView(EntityFactory entityFactory, RequirementsService requirementsService, ResponsibilitiesService responsibilitiesService, SessionService sessionService, VacancyService vacancyService, MarkdownConverter markdownConverter) {
        this.entityFactory = entityFactory;
        this.requirementsService = requirementsService;
        this.responsibilitiesService = responsibilitiesService;
        this.sessionService = sessionService;
        this.vacancyService = vacancyService;
        setUpUI();
    }

    private void setUpUI() {
        ComboBox<String> comboBox = new ComboBox<>("Employment Type: ");
        comboBox.setItems("Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt", "Masterprojekt", "Büro", "Homeoffice");

        title = new TextField();
        title.setLabel("Title: ");
        H6 subtitle = new H6("Write Vacanies with Markdown for easy formatting and styling!");

        TextField location = new TextField("Location: ");
        TextArea requirements = new TextArea("Requirements: ");
        TextArea responsibilities = new TextArea("Responsibilities: ");

        requirements.setWidth("100%");
        responsibilities.setWidth("100%");

        requirementsList = new MultiSelectListBox<>();
        responsibilitiesList = new MultiSelectListBox<>();

        TextArea textArea = new TextArea("Description");
        textArea.setWidth("100%");
        textArea.setHeight("200px");

        VerticalLayout layoutColumn2 = new VerticalLayout();
        layoutColumn2.setWidthFull();
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        layoutColumn2.setPadding(true);

        H3 h3 = new H3("Add Vacancy");

        FormLayout formLayout2Col = new FormLayout();
        formLayout2Col.setWidth("100%");

        Button addRequirements = new Button("Add");
        Button deleteRequirements = new Button("Delete");
        addRequirements.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteRequirements.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button addResponsibility = new Button("Add");
        Button deleteResponsibilities = new Button("Delete");
        addResponsibility.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteResponsibilities.addThemeVariants(ButtonVariant.LUMO_ERROR);

        HorizontalLayout requirementsButtonsLayout = new HorizontalLayout(addRequirements, deleteRequirements);
        VerticalLayout requirementsLayout = new VerticalLayout(requirements, requirementsButtonsLayout, requirementsList);

        HorizontalLayout responsibilitiesButtonsLayout = new HorizontalLayout(addResponsibility, deleteResponsibilities);
        VerticalLayout responsibilitiesLayout = new VerticalLayout(responsibilities, responsibilitiesButtonsLayout, responsibilitiesList);

        formLayout2Col.add(requirementsLayout, responsibilitiesLayout);

        List<String> requirementItems = new ArrayList<>();
        List<String> responsibilityItems = new ArrayList<>();

        addRequirements.addClickListener(event -> {
            String requirement = requirements.getValue();
            if (!requirement.isEmpty() && !requirementItems.contains(requirement)) {
                requirementItems.add(requirement);
                updateRequirementsList(requirementItems);
                requirements.clear();
                //TODO: rename notification text
                Notification.show("Markdown renders later");
            }
        });

        addResponsibility.addClickListener(event -> {
            String responsibility = responsibilities.getValue();
            if (!responsibility.isEmpty() && !responsibilityItems.contains(responsibility)) {
                responsibilityItems.add(responsibility);
                updateResponsibilitiesList(responsibilityItems);
                responsibilities.clear();
                //TODO: rename notification text
                Notification.show("Markdown renders later");
            }
        });

        deleteRequirements.addClickListener(event -> {
            requirementsList.getSelectedItems().forEach(requirementItems::remove);
            updateRequirementsList(requirementItems);
        });

        deleteResponsibilities.addClickListener(event -> {
            responsibilitiesList.getSelectedItems().forEach(responsibilityItems::remove);
            updateResponsibilitiesList(responsibilityItems);
        });

        requirementsList.setWidth("100%");
        responsibilitiesList.setWidth("100%");

        HorizontalLayout layoutRow = new HorizontalLayout();
        layoutRow.setWidthFull();
        layoutRow.setHeight("min-content");
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);

        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancel = new Button("Cancel");

        save.addClickListener(event -> {
            Vacancy vacancy = entityFactory.createVacancy(comboBox.getValue(), title.getValue(), location.getValue(), textArea.getValue(), sessionService.getCurrentBusiness().getBusiness(), Date.valueOf(LocalDate.now()));
            vacancyService.saveVacancy(vacancy);

            for (String requirement : requirementItems) {
                requirementsService.saveRequirements(entityFactory.createRequirements(vacancy, requirement));
            }
            for (String responsibility : responsibilityItems) {
                responsibilitiesService.saveResponsibilities(entityFactory.createResponsibilities(vacancy, responsibility));
            }

            requirementItems.clear();
            responsibilityItems.clear();
            updateRequirementsList(requirementItems);
            updateResponsibilitiesList(responsibilityItems);

            comboBox.clear();
            title.clear();
            location.clear();
            requirements.clear();
            responsibilities.clear();
            textArea.clear();
        });

        cancel.addClickListener(event -> {
            comboBox.clear();
            title.clear();
            location.clear();
            requirements.clear();
            responsibilities.clear();
            textArea.clear();
            requirementItems.clear();
            responsibilityItems.clear();
            updateRequirementsList(requirementItems);
            updateResponsibilitiesList(responsibilityItems);
        });

        layoutRow.add(save, cancel);

        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);

        layoutColumn2.add(h3, subtitle, comboBox, location, title, formLayout2Col, textArea, layoutRow);
        getContent().add(layoutColumn2);

        updateRequirementsList(requirementItems);
        updateResponsibilitiesList(responsibilityItems);
    }

    private void updateRequirementsList(List<String> items) {
        requirementsList.setItems(items);
    }

    private void updateResponsibilitiesList(List<String> items) {
        responsibilitiesList.setItems(items);
    }
}
