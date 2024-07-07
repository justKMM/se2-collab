package hbrs.se2.collhbrs.service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.VaadinSession;
import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class SessionService implements Serializable {

    private final SecurityService securityService;

    public SessionService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void endSession() {
        UI.getCurrent().getPage().setLocation(Globals.Pages.LOGIN);
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }

    public UserDTO getCurrentUser() {
        return (UserDTO) VaadinSession.getCurrent().getAttribute(Globals.CURRENT_USER);
    }

    public StudentDTO getCurrentStudent() {
        return (StudentDTO) getCurrentUser();
    }

    public BusinessDTO getCurrentBusiness() {
        return (BusinessDTO) getCurrentUser();
    }

    public String getUserRole() {
        return securityService.loadUserByUsername(getCurrentUser().getUsername())
                .getAuthorities().toString();
    }
}
