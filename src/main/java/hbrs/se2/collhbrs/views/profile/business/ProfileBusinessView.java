package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import hbrs.se2.collhbrs.views.profile.ProfileBaseView;
import jakarta.annotation.security.RolesAllowed;

@Route(value = Globals.Pages.PROFIL_BUSINESS, layout = AppView.class)
@RolesAllowed(Globals.Roles.BUSINESS)
public class ProfileBusinessView extends ProfileBaseView {

    public ProfileBusinessView(ProfileService profileService, SessionService sessionService) {
        super(profileService, sessionService);
        customizeView();
    }

    @Override
    protected void customizeView() {
        // Add business-specific components and logic here
    }
}