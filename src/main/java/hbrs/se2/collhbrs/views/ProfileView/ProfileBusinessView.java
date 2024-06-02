package hbrs.se2.collhbrs.views.ProfileView;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;

@Route(value = Globals.Pages.PROFILBUSSINESS)
public class ProfileBusinessView extends FormLayout {

    public ProfileBusinessView(SessionService sessionService) {
        add(
                new Text("Name: " + sessionService.getCurrentBusiness().getName() + " "),
                new Text("Email: " + sessionService.getCurrentBusiness().getUser().getEmail() + " "),
                new Text("lorem: " + sessionService.getCurrentBusiness().getUser().getPassword() + " ")
        );
    }

}
