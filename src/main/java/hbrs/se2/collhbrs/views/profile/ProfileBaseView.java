package hbrs.se2.collhbrs.views.profile;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.notification.Notification;
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

    protected transient ProfileService profileService;
    protected transient SessionService sessionService;
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
        subtitle = new H6("Verwalten Sie Ihre Profilinformationen");
        notification = new Notification();
        header = new HorizontalLayout(avatar, title, subtitle);
        layout = new VerticalLayout(header);
        buffer = new MemoryBuffer();
        upload = new Upload(buffer);

        getContent().add(layout);
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
        getContent().add(layoutRow, layoutColumn4);
        setGreetingText();
        h6.setText("Bewertung:");
        h62.setText("5/5 Sterne");
    }

    private void configureHorizontalLayout(HorizontalLayout layoutRow) {
        layoutRow.setWidthFull();
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set(FLEX_GROW, "1");
    }

    private void configureVerticalLayout(VerticalLayout layoutColumn2) {
        layoutColumn2.setHeightFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set(FLEX_GROW, "1");
    }

    private void configureRatingIcons(HorizontalLayout layoutRow2) {
        layoutRow2.setWidthFull();
        layoutRow2.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set(FLEX_GROW, "1");

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
    }

    private void configureColumn4(VerticalLayout layoutColumn4) {
        layoutColumn4.setWidthFull();
        layoutColumn4.getStyle().set(FLEX_GROW, "1");
    }

    private void configureAvatar() {
        avatar.setName("Firstname Lastname");
        avatar.setWidth(PX);
        avatar.setHeight(PX);
        avatarWrapper.add(avatar);
    }

    private void configureUploadComponent() {
        upload.addSucceededListener(event -> {
            try (InputStream inputStream = buffer.getInputStream()) {
                byte[] bytes = inputStream.readAllBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                saveProfileImage(base64Image);
                avatar.setImage("data:image/jpeg;base64," + base64Image);
            } catch (Exception e) {
                Notification.show("Fehler beim hochladen des Avatars");
            }
        });
        layout.add(upload);
    }

    private void saveProfileImage(String base64Image) {
        if (isBusinessUser()) {
            handleProfileImageUpload(sessionService.getCurrentBusiness().getProfile().getProfileID(), base64Image);
        } else if (isStudentUser()) {
            handleProfileImageUpload(sessionService.getCurrentStudent().getProfile().getProfileID(), base64Image);
        }
    }

    private void handleProfileImageUpload(Long profileId, String base64Image) {
        try {
            profileService.deleteProfileImage(profileId);
            profileService.saveProfileImage(profileId, base64Image);
        } catch (Exception e) {
            Notification.show("Fehler beim speichern des Avatars");
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
            Notification.show("Fehler beim laden des Avatars");
        }
    }

    private void setGreetingText() {
        String greeting = "";
        if (isBusinessUser()) {
            greeting = "Hallo " + sessionService.getCurrentBusiness().getName() + "!";
        } else if (isStudentUser()) {
            greeting = "Hallo " + sessionService.getCurrentStudent().getUsername() + "!";
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