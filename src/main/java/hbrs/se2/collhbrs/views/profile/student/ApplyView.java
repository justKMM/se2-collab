package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;


@CssImport("./styles/index.css")
@Route(value = Globals.Pages.APPlY_STUDENT, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class ApplyView extends Composite<VerticalLayout> {
    public ApplyView(VacancyService vacancyService, SessionService sessionService){
        H2 h2 = new H2();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Button buttonPrimary = new Button();
        Button buttonPrimary2 = new Button();
        getContent().setWidth("min-content");
        getContent().setHeight("min-content");
        getContent().setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        getContent().setAlignItems(FlexComponent.Alignment.CENTER);


        h2.setText("Möchtest du dich auf die Stelle " );



        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, h2);
        h2.setWidth("max-content");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("min-content");
        layoutRow.setHeight("min-content");
        buttonPrimary.setText("Button");
        buttonPrimary.setWidth("min-content");
        buttonPrimary.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPrimary2.setText("Button");
        buttonPrimary2.setWidth("min-content");
        buttonPrimary2.addThemeVariants(ButtonVariant.LUMO_ERROR);
        getContent().add(h2);
        getContent().add(layoutRow);
        layoutRow.add(buttonPrimary);
        layoutRow.add(buttonPrimary2);
    }

}
