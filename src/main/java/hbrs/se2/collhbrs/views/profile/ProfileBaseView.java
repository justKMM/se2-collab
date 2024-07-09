package hbrs.se2.collhbrs.views.profile;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.dom.Style;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.MarkdownConverter;

import java.io.InputStream;
import java.util.Base64;

public abstract class ProfileBaseView extends Composite<VerticalLayout> {


    protected final transient ProfileService profileService;
    protected final transient SessionService sessionService;
    protected final transient VerticalLayout layout = new VerticalLayout();
    protected transient MarkdownConverter markdownConverter = new MarkdownConverter();
    protected Avatar avatar;
    protected H6 linkedIn;
    protected H6 xing;
    protected Div description;
    protected Upload upload;
    protected MemoryBuffer buffer;
    protected final static String OPACITY = "opacity";

    protected ProfileBaseView(ProfileService profileService, SessionService sessionService) {
        this.profileService = profileService;
        this.sessionService = sessionService;
        layout.getStyle().setAlignItems(Style.AlignItems.FLEX_START);
        layout.getStyle().set("max-width", "1000px");
        layout.add(header(sessionService.getCurrentUser()), description(sessionService.getCurrentUser()));
        getContent().setSizeFull();
        getContent().getStyle().setAlignItems(Style.AlignItems.CENTER);
        getContent().add(layout);
    }


    private HorizontalLayout header(UserDTO user) {
        HorizontalLayout header = new HorizontalLayout();
        VerticalLayout infoLayout = new VerticalLayout();
        avatar = new Avatar();
        avatar.setImage("data:image/jpeg;base64,"+user.getProfile().getAvatar());
        avatar.setHeight("150px");
        avatar.setWidth("150px");
        H2 username = new H2(setGreetingText());
        H6 email = new H6(user.getEmail());
        email.getStyle().set(OPACITY, "0.8");
        linkedIn = new H6(user.getProfile().getLinkedinUsername());
        linkedIn.getStyle().set(OPACITY, "0.8");
        xing = new H6(user.getProfile().getXingUsername());
        xing.getStyle().set(OPACITY, "0.8");
        HorizontalLayout emailLayout = new HorizontalLayout(
                new H6("Email: "), email);
        HorizontalLayout linkedInLayout = new HorizontalLayout(
                new H6("LinkedIn: "), linkedIn);
        HorizontalLayout xingLayout = new HorizontalLayout(
                new H6("Xing: "), xing);
        HorizontalLayout buttonLayout = new HorizontalLayout(new Button("bearbeiten", event ->
            openEditDialog(user)), new Button("bild hochladen", event -> openAvatarDialog(user)));
        infoLayout.add(username, emailLayout, linkedInLayout, xingLayout, buttonLayout);
        header.add(avatar, infoLayout);
        return header;
    }

    private VerticalLayout description(UserDTO user) {
        description = new Div();
        description.getElement().setProperty(
                "innerHTML",
                markdownConverter.convertToHtml(user.getProfile().getProfileDescription())
        );


        return new VerticalLayout(
                new H3("Beschreibung: "),
               description
        );
    }

    private void openEditDialog(UserDTO user) {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");
        dialog.setHeight("500px");
        VerticalLayout editLayout = new VerticalLayout();
        HorizontalLayout inputLayout = new HorizontalLayout();
        TextField linkedInTexField = new TextField("LinkedIn: ");
        TextField xingTextField = new TextField("Xing: ");
        TextArea descriptionTextField = new TextArea("Beschreibung: ");
        description.setWidth("400px");
        description.setHeight("200px");
        inputLayout.add(linkedIn, xing);
        HorizontalLayout descriptionLayout = new HorizontalLayout(descriptionTextField);
        Button save = new Button("Speichern");
        Button cancel = new Button("Abbrechen");
        if (linkedInTexField.getValue().isEmpty()) {
            linkedInTexField.setValue(user.getProfile().getLinkedinUsername());
        }
        if (xingTextField.getValue().isEmpty()) {
            xingTextField.setValue(user.getProfile().getXingUsername());
        }
        if (descriptionTextField.getValue().isEmpty()) {
            descriptionTextField.setValue(user.getProfile().getProfileDescription());
        }
        save.addClickListener(event -> {
            profileService.saveSocials(
                    sessionService.getCurrentUser().getProfile(),
                    linkedInTexField.getValue(),
                    xingTextField.getValue(),
                    descriptionTextField.getValue()
            );
            updateProfileData(user);
            dialog.close();
        });
        cancel.addClickListener(event -> dialog.close());
        HorizontalLayout buttonLayout = new HorizontalLayout(save, cancel);
        editLayout.add(inputLayout, descriptionLayout, buttonLayout);
        dialog.add(editLayout);
        dialog.open();
    }

    private void openAvatarDialog(UserDTO user) {
        Dialog dialog = new Dialog();
        dialog.setWidth("600px");
        dialog.setHeight("500px");
        VerticalLayout avatarLayout = new VerticalLayout();
        buffer = new MemoryBuffer();
        upload = new Upload(buffer);
        upload.setAcceptedFileTypes("image/jpeg");
        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button saveButton = new Button("Speichern", buttonClickEvent -> {
            try (InputStream inputStream = buffer.getInputStream()) {
                byte[] bytes = inputStream.readAllBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                profileService.deleteProfileImage(user.getProfile().getProfileID());
                profileService.saveProfileImage(user.getProfile().getProfileID(), base64Image);
                user.getProfile().setAvatar(base64Image); // Hier das Avatar-Bild im UserDTO aktualisieren
                Notification.show("Bild erfolgreich hochgeladen");
            } catch (Exception e) {
                Notification.show("Fehler beim Hochladen des Bildes");
            }
            updateProfileData(user);
            dialog.close();
        });

        Button cancelButton = new Button("Abbrechen", buttonClickEvent -> dialog.close());
        buttonLayout.add(saveButton, cancelButton);
        avatarLayout.add(new H3("Profil Bild hochladen"), upload, buttonLayout);
        dialog.add(avatarLayout);
        dialog.open();
    }

    private void updateProfileData(UserDTO user) {
        linkedIn.setText(user.getProfile().getLinkedinUsername());
        xing.setText(user.getProfile().getXingUsername());
        description.getElement().setProperty(
                "innerHTML",
                markdownConverter.convertToHtml(user.getProfile().getProfileDescription())
        );
        avatar.setImage("data:image/jpeg;base64," + user.getProfile().getAvatar());
    }

    private String setGreetingText() {
        if (isBusinessUser()) {
            return "Hallo " + sessionService.getCurrentBusiness().getUsername() + "!";
        } else if (isStudentUser()) {
            return "Hallo " + sessionService.getCurrentStudent().getUsername() + "!";
        }
        return "";
    }

    private boolean isBusinessUser() {
        try {
            return sessionService.getCurrentBusiness() != null;
        } catch (ClassCastException e) {
            return false;
        }
    }

    private boolean isStudentUser() {
        try {
            return sessionService.getCurrentStudent() != null;
        } catch (ClassCastException e) {
            return false;
        }
    }
}
