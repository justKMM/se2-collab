package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.details.Details;
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
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


@CssImport("./styles/index.css")
@Route(value = Globals.Pages.SEARCH_STUDENT, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class SearchView extends Composite<VerticalLayout> {

    public final String CENTER = "center";
    public final String MIN_CONTENT = "min-content";
    public final String PX200 = "200px";
    public final String PX400 = "400px";
    public final String WIDTH = "100%";


    private final HorizontalLayout layoutRow = new HorizontalLayout();
    private final ComboBox<String> comboBoxEmploymenttype = new ComboBox<>();
    // private final TextField description_textfield = new TextField();
    private final ComboBox<String> comboBoxDescription = new ComboBox<>();
    private final Button buttonSearch = new Button("Search");
    private final Button bewerben_button = new Button("Merken");
    private final Button merke_button = new Button("Bewerben");

    private final String[] comboBoxItems = {"Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt",
            "Masterprojekt", "Büro", "Homeoffice"};

    @Autowired
    SessionService sessionService;



    private ComponentRenderer<Component, Vacancy> VacancyCardRenderer = new ComponentRenderer<>(
            vacancy -> {
                HorizontalLayout cardLayout = new HorizontalLayout();
                cardLayout.setMargin(true);

                VerticalLayout vacancyLayout = new VerticalLayout();
                vacancyLayout.setSpacing(false);
                vacancyLayout.setSpacing(false);
                vacancyLayout.getElement().appendChild(ElementFactory.createStrong(vacancy.getTitle()));
                vacancyLayout.add(new Div(new Text(vacancy.getEmploymentType())));
                VerticalLayout infoLayout = new VerticalLayout();
                infoLayout.setSpacing(false);
                infoLayout.setSpacing(false);
                infoLayout.add("Business: " + vacancy.getBusiness().getName());
                infoLayout.add(new Div("Description: " + vacancy.getDescription()));
                infoLayout.add(new Div(new Text("Ort: " + vacancy.getLocation())));
                infoLayout.add(new Div(new Text("Publishing Date: " + vacancy.getPublishDate().toString())));


                vacancyLayout.add(new Details("Description", infoLayout));

                HorizontalLayout h1 = new HorizontalLayout(bewerben_button, merke_button);


                cardLayout.add(vacancyLayout);
                return cardLayout;
            });



    public SearchView(VacancyService vacancyService) {
        List<Vacancy> vacancyList = vacancyService.getAllVacancies();
        VirtualList<Vacancy> virtualList = new VirtualList<>();

        virtualList.setItems(vacancyList);


        buttonSearch.addClickListener(event ->{
            if(comboBoxEmploymenttype.isEmpty() && comboBoxDescription.isEmpty()){
                virtualList.setItems(vacancyList);
            }
            if(!comboBoxEmploymenttype.isEmpty() && comboBoxDescription.isEmpty()) {
                for (String item : comboBoxItems) {
                    if (comboBoxEmploymenttype.getValue().equals(item)) {
                        List<Vacancy> vacancyList1 = vacancyService.getVacanciesByEmploymentType(comboBoxEmploymenttype.getValue());
                        virtualList.setItems(vacancyList1);
                        break;
                    }
                }
            }

            if(!comboBoxDescription.isEmpty()){
                for(Vacancy vacancy: vacancyList) {
                    if (comboBoxDescription.getValue().equals(vacancy.getTitle())){
                        List<Vacancy> vacancyList1 = vacancyService.getVacanciesByTitle(comboBoxDescription.getValue());
                        virtualList.setItems(vacancyList1);
                        break;
                    }
                }
            }
        });



        virtualList.setRenderer(VacancyCardRenderer);
        // setButtons();
        setLayouts();
        setComboBoxSampleData(comboBoxEmploymenttype);

        getContent().add(virtualList);

    }
    private void setButtons(){



        bewerben_button.addClickListener(event -> {

        });

        merke_button.addClickListener(event -> {

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
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, comboBoxEmploymenttype);
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, comboBoxDescription);
        layoutRow.setAlignSelf(FlexComponent.Alignment.END, buttonSearch);

        comboBoxEmploymenttype.setLabel("Jobtitel");
        comboBoxEmploymenttype.setWidth(PX200);

        comboBoxDescription.setLabel("Job beschreibung");
        comboBoxDescription.setWidth(PX400);


        buttonSearch.setWidth(MIN_CONTENT);
        buttonSearch.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(layoutRow);
        layoutRow.add(comboBoxEmploymenttype);
        layoutRow.add(comboBoxDescription);
        layoutRow.add(buttonSearch);
    }
}
