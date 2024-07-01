package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
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

    public ProfileBusinessView(SessionService sessionService, ProfileService profileService) {

        HorizontalLayout layoutRow = new HorizontalLayout();
        Div avatarWrapper = new Div(); // Wrapping Div
        Avatar avatar = new Avatar();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        H6 h6 = new H6();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Icon icon = new Icon();
        Icon icon2 = new Icon();
        Icon icon3 = new Icon();
        Icon icon4 = new Icon();
        Icon icon5 = new Icon();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H6 h62 = new H6();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        getContent().setWidth("100%");
        String flexGrow = "flex-grow";
        getContent().getStyle().set(flexGrow, "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set(flexGrow, "1");
        avatar.setName("Firstname Lastname");
        avatar.setWidth("200px");
        avatar.setHeight("200px");
        avatarWrapper.add(avatar); // Add avatar to the wrapper
        layoutColumn2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set(flexGrow, "1");

        h1.setText("Hallo " + (sessionService.getCurrentBusiness()).getName() + "! ");


        String maxContent = "max-content";
        h1.setWidth(maxContent);
        h6.setText("Rating:");
        h6.setWidth(maxContent);
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set(flexGrow, "1");

        layoutColumn3.setHeightFull();
        layoutRow2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set(flexGrow, "1");
        h62.setText("5/5 Sternen");
        h62.setWidth(maxContent);
        layoutColumn4.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set(flexGrow, "1");
        getContent().add(layoutRow);
        layoutRow.add(avatarWrapper); // Add wrapper to the layout
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(h6);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(icon);
        layoutRow2.add(icon2);
        layoutRow2.add(icon3);
        layoutRow2.add(icon4);
        layoutRow2.add(icon5);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(h62);
        getContent().add(layoutColumn4);

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);

        avatarWrapper.addClickListener(event -> upload.setVisible(true));

        upload.addSucceededListener(event -> {
            try (InputStream inputStream = buffer.getInputStream()) {
                byte[] bytes = inputStream.readAllBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                profileService.deleteProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID());
                profileService.saveProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID(), base64Image);
                avatar.setImage("data:image/jpeg;base64," + base64Image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        layoutColumn4.add(upload);

        String base64Image = profileService.getProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID());
        if (base64Image != null) {
            avatar.setImage("data:image/jpeg;base64," + base64Image);
        }
    }
}
