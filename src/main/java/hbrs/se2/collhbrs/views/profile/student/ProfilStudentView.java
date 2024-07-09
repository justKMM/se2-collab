
package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import hbrs.se2.collhbrs.model.entity.Profile;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.StudentService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import hbrs.se2.collhbrs.views.profile.ProfileBaseView;
import jakarta.annotation.security.RolesAllowed;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

@Route(value = Globals.Pages.PROFIL_STUDENT, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class ProfilStudentView extends ProfileBaseView {

    private static final int MAX_FILE_SIZE = 5 * 1024 * 1024; // 5 MB
    private final StudentService studentService;
    private Button downloadButton;

    private Button generateXingLinkButton;
    private Button deleteXingLinkButton;
    private Anchor xingProfileLink;

    private Button generateLinkedInLinkButton;
    private Button deleteLinkedInLinkButton;
    private Anchor linkedInProfileLink;

    public ProfilStudentView(ProfileService profileService, SessionService sessionService, StudentService studentService) {
        super(profileService, sessionService);
        this.studentService = studentService;
        customizeView();
    }

    @Override
    protected void customizeView() {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes("application/pdf");
        upload.setMaxFiles(1);
        upload.setMaxFileSize(MAX_FILE_SIZE);
        Icon phoneIcon1 = VaadinIcon.TRASH.create();
        Icon phoneIcon2 = VaadinIcon.TRASH.create();
        Icon phoneIcon3 = VaadinIcon.TRASH.create();

        upload.addSucceededListener(event -> handlePdfUploadSuccess(buffer));

        upload.addFileRejectedListener(event -> {
            String errorMessage = event.getErrorMessage();
            if (errorMessage.contains("exceeds the maximum file size")) {
                Notification.show("Die hochgeladene Datei ist größer als 5 MB.");
            } else if (errorMessage.contains("accepted type")) {
                Notification.show("Die hochgeladene Datei ist keine PDF.");
            } else {
                Notification.show("Fehler beim Hochladen der Datei.");
            }
        });

        upload.addFailedListener(event -> {
            Notification.show("Upload fehlgeschlagen: " + event.getReason().getMessage());
        });

        Div outerDiv = new Div();
        outerDiv.setId("outer-div-container");
        outerDiv.getStyle().set("display", "flex");
        outerDiv.getStyle().set("justify-content", "center");
        outerDiv.getStyle().set("align-items", "flex-start");
        outerDiv.getStyle().set("width", "100%");

        Div innerDiv = new Div();
        innerDiv.getStyle().set("width", "100%");
        innerDiv.getStyle().set("max-width", "800px");
        innerDiv.getStyle().set("padding", "20px");

        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(false);
        mainLayout.setPadding(false);
        mainLayout.setSizeFull();

        Span title = new Span("Lebenslauf");
        title.getStyle().set("font-size", "24px").set("font-weight", "bold").set("margin", "20px 20px 0 20px");

        Div uploadSection = new Div();
        uploadSection.getStyle().set("border-radius", "5px").set("padding", "20px");

        Span uploadLabel = new Span("Hier Lebenslauf hochladen:");
        uploadLabel.getStyle().set("font-size", "16px").set("font-weight", "bold");

        uploadSection.add(uploadLabel, upload);
        mainLayout.add(title, uploadSection);

        downloadButton = new Button("Lebenslauf herunterladen", event -> downloadResume());
        Button deleteButton = new Button(phoneIcon1, event -> deleteResume());

        HorizontalLayout buttonLayout = new HorizontalLayout(downloadButton, deleteButton);
        buttonLayout.setSpacing(true);
        buttonLayout.setPadding(true);

        mainLayout.add(buttonLayout);
        getContent().add(mainLayout);

        checkResumeExists();

        // Xing

        Span xingLabel = new Span("Dein Xing-Account:");
        xingLabel.getStyle().set("font-size", "16px")
                            .set("font-weight", "bold")
                            .set("padding", "20px 20px 0 20px");


        com.vaadin.flow.component.textfield.TextField xingUsernameField = new com.vaadin.flow.component.textfield.TextField();
        xingUsernameField.setWidth("300px");
        xingUsernameField.setPlaceholder("Xing-Benutzernamen eingeben");
        xingUsernameField.getStyle().set("padding", "20px");


        generateXingLinkButton = new Button("speichern", event -> {
            String xingUsername = xingUsernameField.getValue();
            if (xingUsername != null && !xingUsername.isEmpty()) {
                profileService.saveXingUsername(sessionService.getCurrentStudent().getProfile().getProfileID(), xingUsername);

                String xingProfileUrl = "https://www.xing.com/profile/" + xingUsername;
                if (xingProfileLink == null) {
                    xingProfileLink = new Anchor(xingProfileUrl, "Xing-Profil ansehen");
                    xingProfileLink.setTarget("_blank");
                    xingProfileLink.getStyle()
                            .set("font-size", "16px")
                            .set("background-color", "#4CAF50")
                            .set("color", "white")
                            .set("padding", "10px 20px")
                            .set("text-decoration", "none")
                            .set("border-radius", "5px")
                            .set("font-weight", "bold")
                            .set("cursor", "pointer")
                            .set("display", "inline-block")
                            .set("margin-top", "20px");
                    mainLayout.add(xingProfileLink);
                } else {
                    xingProfileLink.setHref(xingProfileUrl);
                }
            } else {
                Notification.show("Bitte geben Sie Ihren Xing Benutzernamen ein.");
            }
        });
        deleteXingLinkButton = new Button(phoneIcon2, event -> {
            xingUsernameField.clear();
            profileService.saveXingUsername(sessionService.getCurrentStudent().getProfile().getProfileID(), "");
            if (xingProfileLink != null) {
                mainLayout.remove(xingProfileLink);
                xingProfileLink = null;
            }
            Notification.show("Xing Benutzername gelöscht.");
        });

        HorizontalLayout xingLayout = new HorizontalLayout(xingUsernameField, generateXingLinkButton, deleteXingLinkButton);
        xingLayout.setSpacing(true);
        xingLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        xingLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        mainLayout.add(xingLabel, xingLayout);

        // LinkedIn
        Span linkedInLabel = new Span("Dein LinkedIn-Account:");
        linkedInLabel.getStyle().set("font-size", "16px")
                            .set("font-weight", "bold")
                            .set("padding", "20px 20px 0 20px");

        com.vaadin.flow.component.textfield.TextField linkedInUsernameField = new com.vaadin.flow.component.textfield.TextField();
        linkedInUsernameField.setWidth("300px");
        linkedInUsernameField.setPlaceholder("LinkedIn-Benutzernamen eingeben");
        linkedInUsernameField.getStyle().set("padding", "20px");

        generateLinkedInLinkButton = new Button("speichern", event -> {
            String linkedInUsername = linkedInUsernameField.getValue();
            if (linkedInUsername != null && !linkedInUsername.isEmpty()) {
                profileService.saveLinkedInUsername(sessionService.getCurrentStudent().getProfile().getProfileID(), linkedInUsername);

                String linkedInProfileUrl = "https://www.linkedin.com/in/" + linkedInUsername;
                if (linkedInProfileLink == null) {
                    linkedInProfileLink = new Anchor(linkedInProfileUrl, "LinkedIn-Profil ansehen");
                    linkedInProfileLink.setTarget("_blank");
                    linkedInProfileLink.getStyle()
                            .set("font-size", "16px")
                            .set("background-color", "#4CAF50")
                            .set("color", "white")
                            .set("padding", "10px 20px")
                            .set("text-decoration", "none")
                            .set("border-radius", "5px")
                            .set("font-weight", "bold")
                            .set("cursor", "pointer")
                            .set("display", "inline-block")
                            .set("margin-top", "20px");
                    mainLayout.add(linkedInProfileLink);
                } else {
                    linkedInProfileLink.setHref(linkedInProfileUrl);
                }
            } else {
                Notification.show("Bitte geben Sie Ihren LinkedIn Benutzernamen ein.");
            }
        });

        deleteLinkedInLinkButton = new Button(phoneIcon3, event -> {
            linkedInUsernameField.clear();
            profileService.saveLinkedInUsername(sessionService.getCurrentStudent().getProfile().getProfileID(), "");
            if (linkedInProfileLink != null) {
                mainLayout.remove(linkedInProfileLink);
                linkedInProfileLink = null;
            }
            Notification.show("LinkedIn Benutzername gelöscht. ");
        });

        HorizontalLayout linkedInLayout = new HorizontalLayout(linkedInUsernameField, generateLinkedInLinkButton, deleteLinkedInLinkButton);
        linkedInLayout.setSpacing(true);
        linkedInLayout.setAlignItems(FlexComponent.Alignment.BASELINE);
        linkedInLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.START);
        mainLayout.add(linkedInLabel, linkedInLayout);

        Profile currentProfile = sessionService.getCurrentStudent().getProfile();
        String xingUsername = currentProfile.getXingUsername();
        String linkedInUsername = currentProfile.getLinkedinUsername();

        if (xingUsername != null && !xingUsername.isEmpty()) {
            xingUsernameField.setValue(xingUsername);
            String xingProfileUrl = "https://www.xing.com/profile/" + xingUsername;
            if (xingProfileLink == null) {
                xingProfileLink = new Anchor(xingProfileUrl, "Mein Xing-Profil ansehen");
                xingProfileLink.setTarget("_blank");
                xingProfileLink.getStyle()
                        .set("font-size", "16px")
                        .set("background-color", "#4CAF50")
                        .set("color", "white")
                        .set("padding", "10px 20px")
                        .set("text-decoration", "none")
                        .set("border-radius", "5px")
                        .set("font-weight", "bold")
                        .set("cursor", "pointer")
                        .set("display", "inline-block")
                        .set("margin-top", "20px");
                mainLayout.add(xingProfileLink);
            } else {
                xingProfileLink.setHref(xingProfileUrl);
            }
        } else {
            if (xingProfileLink != null) {
                mainLayout.remove(xingProfileLink);
                xingProfileLink = null;
            }
        }

        if (linkedInUsername != null && !linkedInUsername.isEmpty()) {
            linkedInUsernameField.setValue(linkedInUsername);
            String linkedInProfileUrl = "https://www.linkedin.com/in/" + linkedInUsername;
            if (linkedInProfileLink == null) {
                linkedInProfileLink = new Anchor(linkedInProfileUrl, "Mein LinkedIn-Profil ansehen");
                linkedInProfileLink.setTarget("_blank");
                linkedInProfileLink.getStyle().set("font-size", "16px").set("margin-top", "20px");
                linkedInProfileLink.getStyle()
                        .set("font-size", "16px")
                        .set("background-color", "#4CAF50")
                        .set("color", "white")
                        .set("padding", "10px 20px")
                        .set("text-decoration", "none")
                        .set("border-radius", "5px")
                        .set("font-weight", "bold")
                        .set("cursor", "pointer")
                        .set("display", "inline-block")
                        .set("margin-top", "20px");
                mainLayout.add(linkedInProfileLink);
            } else {
                linkedInProfileLink.setHref(linkedInProfileUrl);
            }
        } else {
            if (linkedInProfileLink != null) {
                mainLayout.remove(linkedInProfileLink);
                linkedInProfileLink = null;
            }
        }
        innerDiv.add(mainLayout);
        outerDiv.add(innerDiv);
        getContent().add(outerDiv);
    }

    private void handlePdfUploadSuccess(MemoryBuffer buffer) {
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
    }

    private void downloadResume() {
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
        downloadButton.setEnabled(base64Resume != null && !base64Resume.isEmpty());
    }
}