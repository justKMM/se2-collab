package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.dom.ElementFactory;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@CssImport("./styles/index.css")
@Route(value = Globals.Pages.SEARCH_STUDENT, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class SearchView extends Composite<VerticalLayout> {

    public static final String CENTER = "center";
    public static final String MIN_CONTENT = "min-content";
    public static final String PX200 = "200px";
    public static final String PX400 = "400px";
    public static final String WIDTH = "100%";
    final
    SessionService sessionService;
    private final HorizontalLayout layoutRow = new HorizontalLayout();
    private final ComboBox<String> comboBoxEmploymentType = new ComboBox<>();

    // private final ComboBox<String> comboBoxDescription = new ComboBox<>();
    private final TextField descriptionTextfield = new TextField();
    private final Button buttonSearch = new Button("Search");
    private final Button applyButton = new Button("Bewerben");
    private final Button saveApplicationButton = new Button("Merke");
    private final Button rateButton = new Button("Rate");
    // private final Button buttonBusiness = new Button("Business");
    private final String[] comboBoxItems = {"Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt",
            "Masterprojekt", "Büro", "Homeoffice"};
    private final Dialog dialogApply = new Dialog();
    private VacancyDTO vacancyDTO;


    public SearchView(VacancyService vacancyService, SessionService sessionService) {
        List<Vacancy> vacancyList = vacancyService.getAllVacancies();
        VirtualList<Vacancy> virtualList = new VirtualList<>();
        virtualList.setItems(vacancyList);

        buttonSearch.addClickListener(event -> {
            List<Vacancy> filteredVacancies = filterVacancies(vacancyService, vacancyList);
            virtualList.setItems(filteredVacancies);
        });

        // infoLayout.add(new Div(bewerben_button, merke_button, rate_button));
        /*
                FormLayout formLayout = new FormLayout();
                formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                        new FormLayout.ResponsiveStep("20em", 2));
                formLayout.add(bewerben_button);
                formLayout.add(merke_button);
                formLayout.add(rate_button,2);*/
        ComponentRenderer<Component, Vacancy> vacancyCardRenderer = new ComponentRenderer<>(
                vacancy -> {
                    HorizontalLayout cardLayout = new HorizontalLayout();
                    cardLayout.setMargin(true);

                    VerticalLayout vacancyLayout = new VerticalLayout();
                    vacancyLayout.setSpacing(false);
                    vacancyLayout.setPadding(false);
                    vacancyLayout.getElement().appendChild(ElementFactory.createStrong(vacancy.getTitle()));
                    vacancyLayout.add(new Div(new Text(vacancy.getEmploymentType())));
                    VerticalLayout infoLayout = new VerticalLayout();
                    infoLayout.setSpacing(false);
                    infoLayout.setPadding(false);

                    infoLayout.add("Business" + vacancy.getBusiness().getName());
                    infoLayout.add(new Div("Description: " + vacancy.getDescription()));
                    infoLayout.add(new Div(new Text("Ort: " + vacancy.getLocation())));
                    infoLayout.add(new Div(new Text("Publishing Date: " + vacancy.getPublishDate().toString())));
                    // infoLayout.add(new Div(bewerben_button, merke_button, rate_button));
                /*
                FormLayout formLayout = new FormLayout();
                formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1),
                        new FormLayout.ResponsiveStep("20em", 2));
                formLayout.add(bewerben_button);
                formLayout.add(merke_button);
                formLayout.add(rate_button,2);*/
                    vacancyLayout.add(new Details("Description", infoLayout));


                    cardLayout.add(vacancyLayout);
                    return cardLayout;
                });
        virtualList.setRenderer(vacancyCardRenderer);
        setLayouts();
        setComboBoxSampleData(comboBoxEmploymentType);
        getContent().add(virtualList);
        this.sessionService = sessionService;
    }

    private List<Vacancy> filterVacancies(VacancyService vacancyService, List<Vacancy> vacancyList) {
        if (comboBoxEmploymentType.isEmpty() && descriptionTextfield.isEmpty()) {
            return vacancyList;
        }

        if (!comboBoxEmploymentType.isEmpty() && !descriptionTextfield.isEmpty()) {
            List<Vacancy> vacancyListByType = vacancyService.getVacanciesByEmploymentType(comboBoxEmploymentType.getValue());
            List<Vacancy> vacancyListByTitle = vacancyService.getVacanciesByTitle(descriptionTextfield.getValue());
            return vacancyListByType.stream()
                    .filter(vacancyListByTitle::contains)
                    .collect(Collectors.toList());
        }

        if (!comboBoxEmploymentType.isEmpty()) {
            return vacancyService.getVacanciesByEmploymentType(comboBoxEmploymentType.getValue());
        }

        if (!descriptionTextfield.isEmpty()) {
            return vacancyService.getVacanciesByTitle(descriptionTextfield.getValue());
        }

        return new ArrayList<>();
    }

    private VerticalLayout setDialog(Vacancy vacancy) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setPadding(false);
        verticalLayout.setSpacing(false);
        verticalLayout.getStyle().set("width", "18rem").set("max-width", "100%");
        verticalLayout.add(new Text("Möchtest du dich auf die Stelle " + vacancy.getTitle() + " bewerben?"));

        return verticalLayout;
    }


    private void setButtons(Vacancy vacancy) {
        rateButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);


        applyButton.addClickListener(event -> {


            // Layout
            Button saveButton = new Button("Apply");
            Button cancelButton = new Button("Cancel");
            saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
            cancelButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
            dialogApply.getFooter().add(cancelButton);
            dialogApply.getFooter().add(saveButton);
            dialogApply.add(setDialog(vacancy));

            saveButton.addClickListener(event1 -> {
                // create new ApplyClass
            });
            cancelButton.addClickListener(event1 -> dialogApply.close());


        });

        saveApplicationButton.addClickListener(event -> {

        });

        rateButton.addClickListener(event -> {

        });
    }


    private void setComboBoxSampleData(ComboBox<String> comboBox) {
        comboBox.setItems(comboBoxItems);
        comboBox.setAllowCustomValue(false);
    }


    private void setLayouts() {
        getContent().setWidth(WIDTH);
        getContent().getStyle().set("flex-grow", "1");
        // getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        getContent().getStyle().set(CENTER, "1");
        getContent().setFlexGrow(1.0, layoutRow);

        layoutRow.setWidthFull();
        layoutRow.setAlignItems(FlexComponent.Alignment.BASELINE);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth(WIDTH);
        layoutRow.setHeight(MIN_CONTENT);
        layoutRow.getStyle().set(CENTER, "1");
        layoutRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, comboBoxEmploymentType);
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, descriptionTextfield);
        layoutRow.setAlignSelf(FlexComponent.Alignment.END, buttonSearch);

        comboBoxEmploymentType.setLabel("Jobtitel");
        comboBoxEmploymentType.setWidth(PX200);

        descriptionTextfield.setLabel("Job beschreibung");
        descriptionTextfield.setWidth(PX400);


        buttonSearch.setWidth(MIN_CONTENT);
        buttonSearch.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(layoutRow);
        layoutRow.add(comboBoxEmploymentType);
        layoutRow.add(descriptionTextfield);
        layoutRow.add(buttonSearch);
    }
}
