package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.StudentService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import hbrs.se2.collhbrs.views.profile.ProfileBaseView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

@Route(value = Globals.Pages.PROFIL_STUDENT, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class ProfilStudentView extends ProfileBaseView {

    private static final int MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB
    private final StudentService studentService;
    private Button downloadButton;

    public ProfilStudentView(ProfileService profileService, SessionService sessionService, StudentService studentService) {
        super(profileService, sessionService);
        this.studentService = studentService;
    }

    /*private void handlePdfUploadSuccess(MemoryBuffer buffer) {
        try (InputStream inputStream = buffer.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            String base64Resume = Base64.getEncoder().encodeToString(bytes);

            studentService.deleteResume(sessionService.getCurrentStudent().getStudentID());
            studentService.saveResume(sessionService.getCurrentStudent().getStudentID(), base64Resume);
            Notification.show("Lebenslauf erfolgreich hochgeladen.");
            downloadButton.setEnabled(true);
        } catch (Exception e) {
            Notification.show("Fehler beim Hochladen des Lebenslaufs: " + e.getMessage());
        }
    }*/

   /* private void downloadResume() {
        Long studentId = sessionService.getCurrentStudent().getStudentID();
        String base64Resume = studentService.getResume(studentId);
        if (base64Resume != null && !base64Resume.isEmpty()) {
            byte[] pdfBytes = Base64.getDecoder().decode(base64Resume);
            StreamResource resource = new StreamResource("Lebenslauf.pdf", () -> new ByteArrayInputStream(pdfBytes));
            resource.setContentType("application/pdf");

            Anchor downloadLink = new Anchor(resource, "Download");
            downloadLink.getElement().setAttribute("download", true);
            downloadLink.getStyle().set("display", "none");
            getContent().add(downloadLink);

            downloadLink.getElement().executeJs("this.click();");

            Notification.show("Lebenslauf-Download gestartet.");
        } else {
            Notification.show("Kein Lebenslauf gefunden.");
            downloadButton.setEnabled(false);
        }
    }

    private void deleteResume() {
        Long studentId = sessionService.getCurrentStudent().getStudentID();
        studentService.deleteResume(studentId);
        Notification.show("Lebenslauf erfolgreich gelöscht.");
        downloadButton.setEnabled(false);
    }

    private void checkResumeExists() {
        Long studentId = sessionService.getCurrentStudent().getStudentID();
        String base64Resume = studentService.getResume(studentId);
        if (base64Resume != null && !base64Resume.isEmpty()) {
            downloadButton.setEnabled(true);
        } else {
            downloadButton.setEnabled(false);
            Notification.show("Kein Lebenslauf vorhanden.");
        }
    }*/
}