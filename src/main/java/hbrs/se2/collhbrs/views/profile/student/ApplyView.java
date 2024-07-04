package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;


@CssImport("./styles/index.css")
@Route(value = Globals.Pages.APPlY_STUDENT, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class ApplyView extends Composite<VerticalLayout> {


}
