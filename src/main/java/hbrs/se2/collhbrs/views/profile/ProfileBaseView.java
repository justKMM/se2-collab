package hbrs.se2.collhbrs.views.profile;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.theme.lumo.LumoUtility;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;
import java.util.Base64;

public abstract class ProfileBaseView extends Composite<Div> {

    private static final String FLEX_GROW = "flex-grow";
    private static final String MAX_CONTENT = "max-content";
    private static final String PX = "200px";
    private final Div avatarWrapper = new Div();
    private final H1 h1 = new H1();
    private final H6 h6 = new H6();
    private final H6 h62 = new H6();
    private final VerticalLayout layoutColumn4 = new VerticalLayout();

    private com.vaadin.flow.component.textfield.TextField xingUsernameField;

    private Button generateXingLinkButton;

    private Anchor xingProfileLink;

    private com.vaadin.flow.component.textfield.TextField linkedInUsernameField;

    private Button generateLinkedInLinkButton;

    private Anchor linkedInProfileLink;

    protected ProfileService profileService;
    protected SessionService sessionService;
    protected Avatar avatar;
    protected H1 title;
    protected H6 subtitle;
    protected Notification notification;
    protected HorizontalLayout header;
    protected VerticalLayout layout;
    protected Upload upload;
    protected MemoryBuffer buffer;

    @Autowired
    protected ProfileBaseView(ProfileService profileService, SessionService sessionService) {
        this.profileService = profileService;
        this.sessionService = sessionService;
        initLayout();
        configureLayout();
        configureAvatar();
        configureUploadComponent();
        loadProfileImage();
    }

    private void initLayout() {
        avatar = new Avatar();
        title = new H1("Profil");
        notification = new Notification();
        header = new HorizontalLayout(avatar, title);
        layout = new VerticalLayout(header);
        buffer = new MemoryBuffer();
        upload = new Upload(buffer);

        Div outerDiv = new Div(layout);
        outerDiv.getStyle().set("margin", "0 auto");
        outerDiv.getStyle().set("max-width", "800px");
        outerDiv.getStyle().set("padding", "0px");

        getContent().add(outerDiv);
    }

    protected void showNotification(String message) {
        notification.setText(message);
        notification.open();
    }

    protected abstract void customizeView();

    private void configureLayout() {
        HorizontalLayout layoutRow = new HorizontalLayout();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        Span profiltitle = new Span("Profilbild hochladen");
        profiltitle.getStyle().set("font-size", "18px");
        profiltitle.getStyle().set("font-weight", "bold");
        profiltitle.getStyle().set("max-width", "800px");
        profiltitle.getStyle().set("padding", "10px");

        getContent().setWidth("100%");
        getContent().getStyle().set(FLEX_GROW, "1");
        configureHorizontalLayout(layoutRow);
        configureVerticalLayout(layoutColumn2);
        configureRatingIcons(layoutRow2);
        configureColumn3(layoutColumn3);
        configureColumn4(layoutColumn4);
        layoutRow.add(avatarWrapper, layoutColumn2);
        layoutColumn2.add(h1, h6, layoutRow2);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(h62);
        layoutColumn4.add(profiltitle);
        getContent().add(layoutRow, layoutColumn4);
        setGreetingText();
        h6.setText("Bewertung:");
        h62.setText("5/5 Sterne");

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.getStyle().set("margin", "0 auto");
        contentLayout.getStyle().set("max-width", "800px");
        contentLayout.getStyle().set("background-color", "black");

        contentLayout.getStyle().set("padding", "0px");

        layout.add(contentLayout); // Hinzufügen zum Hauptlayout
    }

    private void configureHorizontalLayout(HorizontalLayout layoutRow) {
        layoutRow.setWidthFull();
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set(FLEX_GROW, "1");
        layoutRow.getStyle().set("margin", "0 auto");
        layoutRow.getStyle().set("max-width", "800px");
        layoutRow.getStyle().set("background-color", "#F3F5F7");
        layoutRow.getStyle().set("padding", "0px");
        layoutRow.getStyle().set("border-radius", "10px");

    }

    private void configureVerticalLayout(VerticalLayout layoutColumn2) {
        layoutColumn2.setHeightFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set(FLEX_GROW, "1");
        layoutColumn2.getStyle().set("margin", "0 auto");
        layoutColumn2.getStyle().set("max-width", "800px");
        layoutColumn2.getStyle().set("padding", "20px 20px 0 20px");
    }

    private void configureRatingIcons(HorizontalLayout layoutRow2) {
        layoutRow2.setWidthFull();
        layoutRow2.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set(FLEX_GROW, "1");
        layoutRow2.getStyle().set("margin", "0 auto");
        layoutRow2.getStyle().set("max-width", "800px");
        layoutRow2.getStyle().set("padding", "20px");

        for (int i = 0; i < 5; i++) {
            Icon icon = new Icon("vaadin", "star");
            icon.getStyle().set("color", "gold");
            layoutRow2.add(icon);
        }
    }

    private void configureColumn3(VerticalLayout layoutColumn3) {
        layoutColumn3.setHeightFull();
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set(FLEX_GROW, "1");
        layoutColumn3.getStyle().set("margin", "0 auto");
        layoutColumn3.getStyle().set("max-width", "800px");
        layoutColumn3.getStyle().set("padding", "20px");
    }

    private void configureColumn4(VerticalLayout layoutColumn4) {
        layoutColumn4.setWidthFull();
        layoutColumn4.getStyle().set(FLEX_GROW, "1");
        layoutColumn4.getStyle().set("margin", "0 auto");
        layoutColumn4.getStyle().set("max-width", "800px");
        layoutColumn4.getStyle().set("padding", "0px");
    }

    private void configureAvatar() {
        avatar.setName("Firstname Lastname");
        avatar.setWidth("150px");
        avatar.setHeight("150px");
        avatarWrapper.add(avatar);
        avatarWrapper.getStyle().set("display", "flex");
        avatarWrapper.getStyle().set("justify-content", "CENTER");
        avatarWrapper.getStyle().set("margin", "0 auto");
        avatarWrapper.getStyle().set("max-width", "800px");
        avatarWrapper.getStyle().set("padding", "20px");
    }

    private void configureUploadComponent() {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        avatarWrapper.addClickListener(event -> upload.setVisible(true));
        upload.addSucceededListener(event -> handleUploadSuccess(buffer));
        layoutColumn4.add(upload);
    }

    private void handleUploadSuccess(MemoryBuffer buffer) {
        try (InputStream inputStream = buffer.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            if (isBusinessUser()) {
                profileService.deleteProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID());
                profileService.saveProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID(), base64Image);
            } else if (isStudentUser()) {
                profileService.deleteProfileImage(sessionService.getCurrentStudent().getProfile().getProfileID());
                profileService.saveProfileImage(sessionService.getCurrentStudent().getProfile().getProfileID(), base64Image);
            }
            avatar.setImage("data:image/jpeg;base64," + base64Image);
        } catch (Exception e) {
            Notification.show("Failed to upload image");
        }
    }

    private void loadProfileImage() {
        try {
            String base64Image = null;
            if (isBusinessUser()) {
                base64Image = profileService.getProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID());
            } else if (isStudentUser()) {
                base64Image = profileService.getProfileImage(sessionService.getCurrentStudent().getProfile().getProfileID());
            }
            if (base64Image != null) {
                avatar.setImage("data:image/jpeg;base64," + base64Image);
            }
        } catch (Exception e) {
            Notification.show("Failed to load profile image");
        }
    }

    private void setGreetingText() {
        String greeting = "";
        if (isBusinessUser()) {
            greeting = "Hallo, " + sessionService.getCurrentBusiness().getName() + "!";
        } else if (isStudentUser()) {
            greeting = "Hallo, " + sessionService.getCurrentStudent().getUsername() + "!";
        }
        h1.setText(greeting);
        h1.setWidth(MAX_CONTENT);
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