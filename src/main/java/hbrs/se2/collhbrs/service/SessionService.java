package hbrs.se2.collhbrs.service;

import com.vaadin.flow.server.VaadinSession;
import hbrs.se2.collhbrs.model.dto.BusinessDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.util.Globals;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    public void endSession() {}

    public StudentDTO getCurrentStudent() {
        return (StudentDTO) VaadinSession.getCurrent().getAttribute(Globals.CURRENT_USER);
    }

    public BusinessDTO getCurrentBusiness() {
        return (BusinessDTO) VaadinSession.getCurrent().getAttribute(Globals.CURRENT_USER);
    }
}
