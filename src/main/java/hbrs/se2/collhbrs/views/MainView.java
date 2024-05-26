package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.util.Globals;

// @Route(value = Globals.Pages.MAIN)
@CssImport("./styles/index.css")
public class MainView extends HorizontalLayout {

    /*
        Changed to AppView


    * */
    Button button_profile;
    public MainView() {
        addClassName("menu");


        VerticalLayout vertical_layout = new VerticalLayout();
        H1 header = new H1("MainView: Menu");
        vertical_layout.setWidthFull();
        vertical_layout.setWidth("100%");
        vertical_layout.getStyle().set("flex-grow", "1");
        vertical_layout.setAlignSelf(FlexComponent.Alignment.CENTER, header);
        header.setWidth("max-content");




        button_profile = new Button("Go to Profile");
        button_profile.addClickListener(e -> UI.getCurrent().navigate(Globals.Pages.PROFILSTUDENT));

        vertical_layout.add(header, button_profile);

        add(vertical_layout);
    }
}
