package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H4;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.model.dto.FirstNameDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.service.ApplicationService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.MarkdownConverter;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Route(value = Globals.Pages.SHOW_APPLICATION, layout = AppView.class)
@PageTitle("Show Applications")
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class ShowApplicationView extends Composite<VerticalLayout> implements AfterNavigationObserver {

    private static final String INNER_HTML = "innerHTML";
    private final VerticalLayout layout = new VerticalLayout();
    private final List<ApplicationDTO> applicationsList = new ArrayList<>();
    private final transient MarkdownConverter markdownConverter;
    private final transient ApplicationService applicationService;
    private final transient SessionService sessionService;
    private final transient VacancyService vacancyService;

    public ShowApplicationView(
            MarkdownConverter markdownConverter,
            ApplicationService applicationService,
            SessionService sessionService,
            VacancyService vacancyService
    ) {
        this.markdownConverter = markdownConverter;
        this.applicationService = applicationService;
        this.sessionService = sessionService;
        this.vacancyService = vacancyService;
        getContent().getStyle().setAlignItems(Style.AlignItems.CENTER);
        getContent().add(layout);
    }

    public VerticalLayout studentCard(ApplicationDTO application, VacancyDTO vacancy) {
        VerticalLayout studentCardLayout = new VerticalLayout();
        HorizontalLayout vacancyInfo = new HorizontalLayout();
        vacancyInfo.add(new H4(vacancy.getTitle()));
        HorizontalLayout avaterLayout = new HorizontalLayout();
        Avatar studentAvatar = new Avatar();
        studentAvatar.setImage(
                "data:image/jpeg;base64," + application.getStudent().getUser().getProfile().getAvatar()
        );
        Button type = new Button(vacancy.getEmploymentType());
        type.setWidth("min-content");
        type.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        type.setEnabled(true);
        H4 studentName = new H4(
                new FirstNameDTO(
                        applicationService.getFirstName(application.getStudent())).getFirstNameName()
                        + " "
                        + application.getStudent().getLastName()
        );
        avaterLayout.add(studentAvatar, studentName);
        H6 profileDescription = new H6("Profilbeschreibung");
        Div profileDescriptionParagraph = new Div();
        profileDescriptionParagraph.getElement().setProperty(
                INNER_HTML, markdownConverter.convertToHtml(
                        application.getStudent().getUser().getProfile().getProfileDescription())
        );
        HorizontalLayout buttonLayout = new HorizontalLayout();

        Button showProfileButton = new Button("Profil anzeigen");
        showProfileButton.addClickListener(e -> {
            // Hier Profil anzeigen vom Studenten!
        });

        Button downloadCoverLetterButton = getDownloadCoverLetterButton(application);


        buttonLayout.add(showProfileButton, downloadCoverLetterButton);


        studentCardLayout.add(vacancyInfo, type, avaterLayout, profileDescription, profileDescriptionParagraph, buttonLayout);
        studentCardLayout.setWidth("100%");
        studentCardLayout.setMaxWidth("700px");
        studentCardLayout.getStyle().set("border", "1px solid #ccc");
        studentCardLayout.getStyle().set("border-radius", "8px");
        studentCardLayout.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");
        return studentCardLayout;
    }

    private @NotNull Button getDownloadCoverLetterButton(ApplicationDTO application) {
        Button downloadCoverLetterButton = new Button("Download Anschreiben");
        downloadCoverLetterButton.addClickListener(e -> {
            String base64coverLetter = applicationService.getCoverLetter(application.getApplicationID());
            if (base64coverLetter != null && !base64coverLetter.isEmpty()) {

                byte[] pdfBytes = Base64.getDecoder().decode(base64coverLetter);
                StreamResource resource = new StreamResource("anschreiben.pdf", () -> new ByteArrayInputStream(pdfBytes));
                resource.setContentType("application/pdf");

                Anchor downloadLink = new Anchor(resource, "Download");
                downloadLink.getElement().setAttribute("download", true);
                downloadLink.getStyle().set("display", "none");
                getContent().add(downloadLink);

                downloadLink.getElement().executeJs("this.click();");

                Notification.show("Anschreiben-Download gestartet.");
            } else {
                Notification.show("Kein Anschreiben gefunden.");
                downloadCoverLetterButton.setEnabled(false);
            }
        });
        return downloadCoverLetterButton;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        loadAndDisplayApplications(sessionService.getCurrentBusiness().getBusinessID());
    }

    private void loadAndDisplayApplications(Long businessId) {
        List<Vacancy> vacancies = vacancyService.getVacanciesByBusinessId(businessId);
        layout.getStyle().setAlignItems(Style.AlignItems.CENTER);
        for (Vacancy vacancy : vacancies) {
            VacancyDTO vacancyDTO = new VacancyDTO(vacancy);
            List<Application> applications = applicationService.getAllApplications(vacancy.getVacancyID());
            for (Application application : applications) {
                ApplicationDTO applicationDTO = new ApplicationDTO(application);
                applicationsList.add(applicationDTO);
                layout.add(studentCard(applicationDTO, vacancyDTO));
            }
        }
    }
}
