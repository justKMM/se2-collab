package hbrs.se2.collhbrs.views.profile.business;

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
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;

import java.io.InputStream;
import java.util.Base64;

@Route(value = Globals.Pages.PROFIL_BUSINESS, layout = AppView.class)
@RolesAllowed(Globals.Roles.BUSINESS)
public class ProfileBusinessView extends Composite<VerticalLayout> {
    private static final String FLEX_GROW = "flex-grow";
    private static final String MAX_CONTENT = "max-content";
    private static final String PX = "200px";

    private final Div avatarWrapper = new Div();
    private final Avatar avatar = new Avatar();
    private final H1 h1 = new H1();
    private final H6 h6 = new H6();
    private final H6 h62 = new H6();
    private final VerticalLayout layoutColumn4 = new VerticalLayout();

    public ProfileBusinessView(SessionService sessionService, ProfileService profileService) {
        configureLayout(sessionService);
        configureAvatar();
        configureUploadComponent(sessionService, profileService);
        loadProfileImage(sessionService, profileService);
    }

    private void configureLayout(SessionService sessionService) {
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

        setGreetingText(sessionService);
        h6.setText("Rating:");
        h62.setText("5/5 Sternen");
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

        Icon icon = new Icon();
        Icon icon2 = new Icon();
        Icon icon3 = new Icon();
        Icon icon4 = new Icon();
        Icon icon5 = new Icon();

        layoutRow2.add(icon, icon2, icon3, icon4, icon5);
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
        avatarWrapper.add(avatar); // Add avatar to the wrapper
    }

    private void configureUploadComponent(SessionService sessionService, ProfileService profileService) {
        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        avatarWrapper.addClickListener(event -> upload.setVisible(true));
        upload.addSucceededListener(event -> handleUploadSuccess(buffer, sessionService, profileService));
        layoutColumn4.add(upload);  // Ensure upload component is added to layoutColumn4
    }

    private void handleUploadSuccess(MemoryBuffer buffer, SessionService sessionService, ProfileService profileService) {
        try (InputStream inputStream = buffer.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            String base64Image = Base64.getEncoder().encodeToString(bytes);
            profileService.deleteProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID());
            profileService.saveProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID(), base64Image);
            avatar.setImage("data:image/jpeg;base64," + base64Image);
        } catch (Exception e) {
            Notification.show("Failed to upload image");
            e.printStackTrace();
        }
    }

    private void loadProfileImage(SessionService sessionService, ProfileService profileService) {
        try {
            String base64Image = profileService.getProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID());
            if (base64Image != null) {
                avatar.setImage("data:image/jpeg;base64," + base64Image);
            }
        } catch (Exception e) {
            Notification.show("Failed to load profile image");
            e.printStackTrace();
        }
    }

    private void setGreetingText(SessionService sessionService) {
        String greeting = "Hallo " + sessionService.getCurrentBusiness().getName() + "!";
        h1.setText(greeting);
        h1.setWidth(MAX_CONTENT);
    }
}