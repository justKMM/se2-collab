package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
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
public class AddVacancyView extends Composite<VerticalLayout> {

    private final transient EntityFactory entityFactory;
    private final transient RequirementsService requirementsService;
    private final transient ResponsibilitiesService responsibilitiesService;
    private final transient SessionService sessionService;
    private final transient VacancyService vacancyService;

    private MultiSelectListBox<String> requirementsList;
    private MultiSelectListBox<String> responsibilitiesList;

    private static final String DELETE = "Löschen";

    @Autowired
    public AddVacancyView(EntityFactory entityFactory, RequirementsService requirementsService, ResponsibilitiesService responsibilitiesService, SessionService sessionService, VacancyService vacancyService, MarkdownConverter markdownConverter) {
        this.entityFactory = entityFactory;
        this.requirementsService = requirementsService;
        this.responsibilitiesService = responsibilitiesService;
        this.sessionService = sessionService;
        this.vacancyService = vacancyService;
        setUpUI();
    }

    private void setUpUI() {
        HorizontalLayout titleLayout = new HorizontalLayout();
        TextField title = new TextField("Titel: ");
        title.setWidth("100%"); // Vergrößert die Breite des Titel-Feldes
        titleLayout.add(title);
        titleLayout.setWidth("100%");

        HorizontalLayout infoLayout = new HorizontalLayout();
        ComboBox<String> comboBox = new ComboBox<>("Employment Type: ");
        TextField location = new TextField("Location: ");
        comboBox.setWidth("50%"); // Stellt sicher, dass der Kombinationsfeld die Hälfte des verfügbaren Platzes einnimmt
        location.setWidth("50%"); // Stellt sicher, dass das Standortfeld die Hälfte des verfügbaren Platzes einnimmt
        comboBox.setItems("Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt", "Masterprojekt", "Büro", "Homeoffice");
        infoLayout.add(comboBox, location);
        infoLayout.setWidth("100%"); // Stellt sicher, dass das Layout die volle Breite einnimmt

        H3 h3 = new H3("Stellenanzeige hinzufügen");
        H6 subtitle = new H6("Stellenausschreibungen mit Markdown schreiben");

        TextArea requirements = new TextArea("Anforderungen: ");
        TextArea responsibilities = new TextArea("Aufgaben: ");
        requirements.setWidth("100%");
        responsibilities.setWidth("100%");

        requirementsList = new MultiSelectListBox<>();
        responsibilitiesList = new MultiSelectListBox<>();
        requirementsList.setWidth("100%");
        responsibilitiesList.setWidth("100%");

        TextArea textArea = new TextArea("Beschreibung");
        textArea.setWidth("100%");
        textArea.setHeight("200px");

        VerticalLayout layoutColumn2 = new VerticalLayout();
        layoutColumn2.setWidthFull();
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        layoutColumn2.setPadding(true);

        FormLayout formLayout2Col = new FormLayout();
        formLayout2Col.setWidth("100%");

        Button addRequirements = new Button("Hinzufügen");
        Button deleteRequirements = new Button(DELETE);
        addRequirements.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        deleteRequirements.addThemeVariants(ButtonVariant.LUMO_ERROR);

        Button addResponsibility = new Button("Hinzufügen");
        Button deleteResponsibilities = new Button(DELETE);
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
                Notification.show("Markdown wird gleich gerendert");
            }
        });

        addResponsibility.addClickListener(event -> {
            String responsibility = responsibilities.getValue();
            if (!responsibility.isEmpty() && !responsibilityItems.contains(responsibility)) {
                responsibilityItems.add(responsibility);
                updateResponsibilitiesList(responsibilityItems);
                responsibilities.clear();
                Notification.show("Markdown wird gleich gerendert");
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

        HorizontalLayout layoutRow = new HorizontalLayout();
        layoutRow.setWidthFull();
        layoutRow.setHeight("min-content");
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);

        Button save = new Button("Speichern");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        Button cancel = new Button(DELETE);

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

        layoutColumn2.add(h3, subtitle, titleLayout, infoLayout, formLayout2Col, textArea, layoutRow);
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
