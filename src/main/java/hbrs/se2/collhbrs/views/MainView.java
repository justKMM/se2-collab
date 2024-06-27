package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.ProfileViews.ProfilStudentView;
import hbrs.se2.collhbrs.views.ProfileViews.ProfileBusinessView;
import jakarta.annotation.security.PermitAll;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@Route(Globals.Pages.MAIN)
@PermitAll
public class MainView extends VerticalLayout implements BeforeEnterObserver {

    @Override
    public synchronized void beforeEnter(BeforeEnterEvent event) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            String role = userDetails.getAuthorities().iterator().next().getAuthority();
            switch (role) {
                case "ROLE_"+Globals.Roles.STUDENT -> event.forwardTo(ProfilStudentView.class);
                case "ROLE_"+Globals.Roles.BUSINESS -> event.forwardTo(ProfileBusinessView.class);
                default -> throw new IllegalStateException("Unexpected value: " + role);
            }
        }
    }
}

