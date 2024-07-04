package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;

import java.io.InputStream;
import java.util.Base64;

@Route(value = Globals.Pages.PROFIL_STUDENT, layout = AppView.class)
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.STUDENT)
public class ProfilStudentView extends Composite<VerticalLayout> {
    private static final String FLEX_GROW = "flex-grow";
    private static final String MIN_CONTENT = "min-content";
    private static final String PX = "200px";

    private final ProfilStudentLayout profileLayout = new ProfilStudentLayout();
    private final Button buttonCancel = new Button("Cancel");
    private final Button buttonConfirm = new Button("Save");
    private final Div avatarWrapper = new Div();
    private final VerticalLayout layoutColumn2 = new VerticalLayout();
    private final VerticalLayout layoutColumn3 = new VerticalLayout();
    private final VerticalLayout layoutColumn4 = new VerticalLayout();
    private final VerticalLayout layoutColumn5 = new VerticalLayout();
    private final HorizontalLayout layoutRow = new HorizontalLayout();
    private final HorizontalLayout layoutRow2 = new HorizontalLayout();
    private final HorizontalLayout layoutRow3 = new HorizontalLayout();
    private final H1 h12 = new H1("Personal data:");
    private final H1 h13 = new H1("Documents");
    private final H6 h6 = new H6("Rating:");
    private final H1 h1;
    private final Paragraph textMedium2 = new Paragraph("Lebenslauf:");
    private final StudentDTO student;
    private final Avatar avatar = new Avatar();
    private final Icon icon = new Icon();
    private final Icon icon2 = new Icon();
    private final Icon icon3 = new Icon();
    private final Icon icon4 = new Icon();
    private final Icon icon5 = new Icon();
    private Dialog dialog;
    private Button buttonPDataEdit;
    private Button buttonMailEdit;
    private Button buttonMerkzettel;
    private Button buttonLebenslauf;

    public ProfilStudentView(SessionService sessionService, ProfileService profileService) {
        configureLayout();
        configureAvatar();
        configureUploadComponent(sessionService, profileService);
        loadProfileImage(sessionService, profileService);

        student = sessionService.getCurrentStudent();
        h1 = new H1("Hallo " + student.getUser().getUsername() + "!");

        configureDialog();
        configureButtons();
        setLayouts();

        configureEditEmailDialog();
        addProfileInformation();

        getContent().setWidth("100%");
        getContent().getStyle().set(FLEX_GROW, "1");
        getContent().setFlexGrow(1.0, layoutColumn2);
        getContent().add(layoutColumn2);
    }

    private void configureLayout() {
        layoutRow.setWidthFull();
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.getStyle().set(FLEX_GROW, "1");

        layoutColumn2.setWidthFull();
        layoutColumn2.getStyle().set(FLEX_GROW, "1");

        layoutRow2.setWidthFull();
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.getStyle().set(FLEX_GROW, "1");

        layoutRow3.setWidthFull();
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.getStyle().set(FLEX_GROW, "1");

        layoutColumn4.setWidth(MIN_CONTENT);
        layoutColumn4.setMinWidth("750px");
        layoutColumn4.getStyle().set(FLEX_GROW, "1");

        layoutColumn5.setWidthFull();
        layoutColumn5.getStyle().set(FLEX_GROW, "1");
    }

    private void configureAvatar() {
        avatar.setName("Firstname Lastname");
        avatar.setWidth(PX);
        avatar.setHeight(PX);
        avatarWrapper.add(avatar);
    }

    private void configureUploadComponent(SessionService sessionService, ProfileService profileService) {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        avatarWrapper.addClickListener(event -> upload.setVisible(true));
        upload.addSucceededListener(event -> handleUploadSuccess(buffer, sessionService, profileService));
        layoutColumn4.add(upload);
    }

    private void handleUploadSuccess(MemoryBuffer buffer, SessionService sessionService, ProfileService profileService) {
        try (InputStream inputStream = buffer.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            profileService.deleteProfileImage(sessionService.getCurrentStudent().getProfile().getProfileID());
            profileService.saveProfileImage(sessionService.getCurrentStudent().getProfile().getProfileID(), base64Image);
            avatar.setImage("data:image/jpeg;base64," + base64Image);
        } catch (Exception e) {
            Notification.show("Failed to upload image");
            e.printStackTrace();
        }
    }

    private void loadProfileImage(SessionService sessionService, ProfileService profileService) {
        try {
            String base64Image = profileService.getProfileImage(sessionService.getCurrentStudent().getProfile().getProfileID());
            if (base64Image != null) {
                avatar.setImage("data:image/jpeg;base64," + base64Image);
            }
        } catch (Exception e) {
            Notification.show("Failed to load profile image");
            e.printStackTrace();
        }
    }

    private void configureDialog() {
        dialog = new Dialog();
        dialog.setHeaderTitle("Change personal data");
        dialog.add(profileLayout);
        createFooter(dialog);
    }

    private void configureButtons() {
        buttonMailEdit = new Button("Edit Email");
        buttonMerkzettel = new Button("Notepad");
        buttonLebenslauf = new Button("Upload Lebenslauf");
        buttonPDataEdit = new Button("Edit personal data");

        buttonMailEdit.setWidth(MIN_CONTENT);
        buttonMerkzettel.setWidth(MIN_CONTENT);
        buttonMerkzettel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLebenslauf.setWidth(MIN_CONTENT);
        buttonLebenslauf.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPDataEdit.setWidth(MIN_CONTENT);
        buttonPDataEdit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        buttonPDataEdit.addClickListener(e -> dialog.open());
    }

    private void configureEditEmailDialog() {
        buttonMailEdit.addClickListener(e -> {
            Dialog emailDialog = new Dialog();
            VerticalLayout emailLayout = new VerticalLayout();
            EmailField emailField = new EmailField("E-Mail:");
            Button confirmEmailButton = new Button("Save");
            Button cancelEmailButton = new Button("Cancel");

            emailLayout.add(emailField);
            emailLayout.getStyle().set("width", "20rem").set("max-width", "100%");
            emailDialog.setHeaderTitle("E-Mail ändern:");
            emailDialog.add(emailLayout);
            emailDialog.getFooter().add(cancelEmailButton, confirmEmailButton);

            cancelEmailButton.addClickListener(e1 -> emailDialog.close());
            confirmEmailButton.addClickListener(e1 -> {
                if (!emailField.isEmpty()) {
                    student.getUser().setEmail(emailField.getValue());
                    Notification.show("E-Mail wurde geändert.");
                }
                emailDialog.close();
            });

            emailDialog.open();
        });
    }

    private void addProfileInformation() {
        String profileInfo = "Email: " + student.getUser().getEmail() + "\n" +
                " Last name: " + student.getLastName() + "\n" +
                " Username: " + student.getUser().getUsername();
        Paragraph profileParagraph = new Paragraph(profileInfo);
        profileParagraph.setWidth("100%");
        profileParagraph.getStyle().set("font-size", "var(--lumo-font-size-m)");

        layoutColumn4.add(h12, profileParagraph, buttonPDataEdit);
    }

    private void createFooter(Dialog d) {
        buttonConfirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonConfirm.addClickShortcut(Key.ENTER);
        buttonCancel.addClickShortcut(Key.ESCAPE);
        buttonCancel.addClickListener(e -> d.close());
        buttonConfirm.addClickListener(e -> {
            changeData();
            dialog.close();
        });
        d.getFooter().add(buttonCancel, buttonConfirm);
    }

    private void changeData() {
        User user = student.getUser();
        if (!profileLayout.getTfVorname().isEmpty()) {
            user.setUsername(profileLayout.getTfVorname());
            student.setUser(user);
        }
        if (!profileLayout.getTfNachname().isEmpty()) {
            student.setLastName(profileLayout.getTfNachname());
        }
    }

    private void setLayouts() {
        layoutRow.add(avatarWrapper, layoutColumn3);
        layoutRow2.add(icon, icon2, icon3, icon4, icon5);
        layoutRow3.add(layoutColumn4, layoutColumn5);

        layoutColumn2.add(layoutRow, layoutRow3);
        layoutColumn3.add(h1, h6, layoutRow2, buttonMailEdit, buttonMerkzettel);
        layoutColumn5.add(h13, textMedium2, buttonLebenslauf);

        getContent().add(layoutColumn2);
    }
}