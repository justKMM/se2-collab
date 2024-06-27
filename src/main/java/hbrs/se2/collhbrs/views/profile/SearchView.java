package hbrs.se2.collhbrs.views.profile;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;

@CssImport("./styles/index.css")
@Route(value = Globals.Pages.Search_Student, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class SearchView extends Composite<VerticalLayout> {

    private HorizontalLayout layoutRow = new HorizontalLayout();
    private ComboBox<String> comboBox = new ComboBox<>();
    private TextField textField = new TextField();
    private Button buttonSearch = new Button("Search");

    private String[] comboBoxItems = {"Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt",
            "Masterprojekt", "Büro", "Homeoffice"};


    public SearchView() {
        setLayouts();
        setComboBoxSampleData(comboBox);
    }

    private void setComboBoxSampleData(ComboBox<String> comboBox) {
            comboBox.setItems(comboBoxItems);
    }


    private void setLayouts(){
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        getContent().setFlexGrow(1.0, layoutRow);

        layoutRow.setWidthFull();
        layoutRow.setAlignItems(FlexComponent.Alignment.BASELINE);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.setHeight("min-content");
        layoutRow.setAlignItems(FlexComponent.Alignment.CENTER);
        layoutRow.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, comboBox);
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, textField);
        layoutRow.setAlignSelf(FlexComponent.Alignment.END, buttonSearch);

        comboBox.setLabel("Jobtitel");
        comboBox.setWidth("200px");

        textField.setLabel("Job beschreibung");
        textField.setWidth("400px");


        buttonSearch.setWidth("min-content");
        buttonSearch.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        getContent().add(layoutRow);
        layoutRow.add(comboBox);
        layoutRow.add(textField);
        layoutRow.add(buttonSearch);
    }
}
