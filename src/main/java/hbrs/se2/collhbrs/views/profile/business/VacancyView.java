package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;

@PageTitle("Add vacancy")
@Route(value = Globals.Pages.VACANCY, layout = AppView.class)
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class VacancyView extends Composite<VerticalLayout> {

    private Button cancel;
    private Button save;
    private ComboBox comboBox;
    private TextArea textArea;


    private String[] comboBoxItems = {"Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt",
            "Masterprojekt", "Büro", "Homeoffice"};

    public VacancyView(VacancyService vacancyService) {
        setUpUI();
        cancel.addClickListener(e -> {
            comboBox.clear();
            textArea.clear();
        });
    }

    private void setUpUI() {
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H3 h3 = new H3();
        comboBox = new ComboBox();
        textArea = new TextArea();
        HorizontalLayout layoutRow = new HorizontalLayout();
        save = new Button("Save");
        cancel = new Button("Cancel");
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);
        layoutColumn2.setWidth("100%");
        layoutColumn2.setMaxWidth("800px");
        layoutColumn2.setHeight("min-content");
        h3.setText("Add Vacancy");
        h3.setWidth("100%");
        comboBox.setLabel("Title");
        comboBox.setWidth("min-content");
        comboBox.setItems(comboBoxItems);
        textArea.setLabel("Description");
        textArea.setWidth("100%");
        textArea.setHeight("150px");
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        save.setWidth("min-content");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cancel.setWidth("min-content");
        getContent().add(layoutColumn2);
        layoutColumn2.add(h3);
        layoutColumn2.add(comboBox);
        layoutColumn2.add(textArea);
        layoutColumn2.add(layoutRow);
        layoutRow.add(save);
        layoutRow.add(cancel);
    }
}
