package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.service.ApplicationService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;

import java.util.ArrayList;
import java.util.List;

@Route(value="student/meine-bewerbungen", layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class MyApplicationView extends Composite<VerticalLayout> {

    private final VerticalLayout layout;
    private final ApplicationService applicationService;
    private final SessionService sessionService;
    private final List<Application> applications = new ArrayList<>();

    public MyApplicationView(ApplicationService applicationService, SessionService sessionService) {
        this.applicationService = applicationService;
        this.sessionService = sessionService;
        this.layout = new VerticalLayout();
        this.layout.getStyle().setAlignItems(Style.AlignItems.CENTER);
        getContent().getStyle().setAlignItems(Style.AlignItems.CENTER);
        displayStudentApplications(sessionService.getCurrentStudent().getStudentID());
        getContent().add(layout);
    }

    private void displayStudentApplications(Long studentId) {
        List<ApplicationDTO> applicationList = new ArrayList<>();
        for (Application application : applicationService.getAllApplicationsByStudent(studentId)) {
            applicationList.add(new ApplicationDTO(application));
        }
        for (ApplicationDTO application : applicationList) {
            layout.add(applicationCard(application));
        }
    }

    public VerticalLayout applicationCard(ApplicationDTO application) {
        VerticalLayout applicationCard = new VerticalLayout();
        HorizontalLayout businessLayout = new HorizontalLayout();
        Avatar avatar = new Avatar();
        avatar.setImage(
                "data:image/jpeg;base64," +
                application.getVacancy().getBusiness().getUser().getProfile().getAvatar()
        );
        businessLayout.add(avatar, new H4(application.getVacancy().getBusiness().getName()));
        HorizontalLayout infoLayout = new HorizontalLayout();
        infoLayout.add(new H3("Bewerbung als: "), new Span(application.getVacancy().getTitle()));
        VerticalLayout vacancyLayout = new VerticalLayout();
        vacancyLayout.add(
                new H3("Stellenbeschreibung: "),
                new Span(application.getVacancy().getDescription())
        );
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(new Button("zurückziehen", buttonClickEvent -> {
            openDialog(application, applicationCard);
        }));
        applicationCard.add(businessLayout, infoLayout, vacancyLayout, buttonLayout);
        applicationCard.setWidth("100%");
        applicationCard.setMaxWidth("700px");
        applicationCard.getStyle().set("border", "1px solid #ccc");
        applicationCard.getStyle().set("border-radius", "8px");
        applicationCard.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        return applicationCard;
    }

    public void openDialog(ApplicationDTO application, VerticalLayout applicationCard) {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");
        dialog.setHeight("200px");
        VerticalLayout dialogLayout = new VerticalLayout();
        H5 title = new H5("Möchstest du die Bewerbung zurückziehen?");
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(new Button("Ja", buttonClickEvent -> {
            applicationService.deleteApplication(application.getApplication());
            layout.remove(applicationCard);
            dialog.close();
        }), new Button("Abbrechen" , buttonClickEvent -> {
            dialog.close();
        }));
        dialogLayout.setPadding(true);
        dialogLayout.setSpacing(true);
        dialogLayout.add(title, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }
}
