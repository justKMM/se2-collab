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
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
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
    private static final String MAX_CONTENT = "max-content";
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
    Dialog dialog;
    Avatar avatar = new Avatar();
    private Button buttonPDataEdit;
    private Button buttonMailEdit;
    private Button buttonMerkzettel;
    private Button buttonLebenslauf;
    private Icon icon = new Icon();
    private Icon icon2 = new Icon();
    private Icon icon3 = new Icon();
    private Icon icon4 = new Icon();
    private Icon icon5 = new Icon();


    public ProfilStudentView(SessionService sessionService, ProfileService profileService) {
        this.avatar.setName("Firstname Lastname"); // Warum ?
        this.avatar.setWidth(PX);
        this.avatar.setHeight(PX);

        this.avatarWrapper.add(this.avatar); // Add avatar to the wrapper

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);

        avatarWrapper.addClickListener(event -> upload.setVisible(true));

        upload.addSucceededListener(event -> {
            try (InputStream inputStream = buffer.getInputStream()) {
                byte[] bytes = inputStream.readAllBytes();
                String base64Image = Base64.getEncoder().encodeToString(bytes);
                profileService.deleteProfileImage(sessionService.getCurrentStudent().getProfile().getProfileID());
                profileService.saveProfileImage(sessionService.getCurrentStudent().getProfile().getProfileID(), base64Image);
                avatar.setImage("data:image/jpeg;base64," + base64Image);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        layoutColumn4.add(upload);

        try {
            String base64Image = profileService.getProfileImage(sessionService.getCurrentBusiness().getProfile().getProfileID());
            if (base64Image != null) {
                avatar.setImage("data:image/jpeg;base64," + base64Image);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        student = sessionService.getCurrentStudent();

        h1 = new H1("Hallo " + student.getUser().getUsername() + "!");

        // dialog Object für Änderungsmaske
        dialog = new Dialog();

        getContent().setWidth("100%");
        getContent().getStyle().set(FLEX_GROW, "1");
        getContent().setFlexGrow(1.0, layoutColumn2);

        setRatingIcons();
        setButtons();
        setLayouts();


        buttonPDataEdit.addClickListener(e -> {
            dialog.setHeaderTitle("Change personal data");
            dialog.add(profileLayout);
            createFooter(dialog);
            dialog.open();
        });

        buttonMailEdit.addClickListener(e -> {
            Dialog d = new Dialog();
            VerticalLayout verticalLayout = new VerticalLayout();
            EmailField efEmail = new EmailField("E-Mail:");
            Button buttonConfirmMail = new Button("Save");
            Button buttonCancelMail = new Button("Cancel");
            verticalLayout.add(efEmail);
            verticalLayout.getStyle().set("width", "20rem").set("max-width", "100%");
            d.setHeaderTitle("E-Mail ändern: ");
            d.add(verticalLayout);
            d.getFooter().add(buttonCancelMail, buttonConfirmMail);

            buttonCancelMail.addClickListener(e1 -> d.close());
            buttonConfirmMail.addClickListener(e1 -> {
                if (!efEmail.isEmpty()) {
                    student.getUser().setEmail(efEmail.getValue());
                    Notification.show("E-Mail wurde geändert. ");
                }
                d.close();
            });

            d.open();
        });

        String s = "Email: " + student.getUser().getEmail() + "\n" +
                " Last name: " + student.getLastName() + "\n" +
                " Username: " + student.getUser().getUsername();
        Paragraph textMedium = new Paragraph(
                s);

        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        layoutColumn4.add(h12, textMedium, buttonPDataEdit);


    }

    private void setButtons() {
        buttonMailEdit = new Button("Edit Email");
        buttonMerkzettel = new Button("Notespad");
        buttonLebenslauf = new Button("upload Lebenslauf");
        buttonPDataEdit = new Button("Edit personal data");

        buttonMailEdit.setWidth(MIN_CONTENT);
        buttonMerkzettel.setWidth(MIN_CONTENT);
        buttonMerkzettel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLebenslauf.setWidth(MIN_CONTENT);
        buttonLebenslauf.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonPDataEdit.setWidth(MIN_CONTENT);
        buttonPDataEdit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }

    public void createFooter(Dialog d) {

        buttonConfirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonCancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonConfirm.addClickShortcut(Key.ENTER);
        buttonCancel.addClickShortcut(Key.ESCAPE);

        buttonCancel.addClickListener(e -> d.close());
        buttonConfirm.addClickListener(e -> {
            changeData();

            dialog.close();
        });
        // speichern fehlt noch
        d.getFooter().add(buttonCancel);
        d.getFooter().add(buttonConfirm);
    }

    private void setRatingIcons() {

        icon = VaadinIcon.STAR.create();
        icon2 = VaadinIcon.STAR.create();
        icon3 = VaadinIcon.STAR.create();
        icon4 = VaadinIcon.STAR.create();
        icon5 = VaadinIcon.STAR.create();
    }

    private void changeData() {
        User u = student.getUser();
        u.setUsername(profileLayout.getTfVorname());

        if (!profileLayout.getTfVorname().isEmpty()) {
            student.setUser(u);
        }
        if (!profileLayout.getTfNachname().isEmpty()) {
            student.setLastName(profileLayout.getTfNachname());
        }
    }

    private void setLayouts() {
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.START, buttonMerkzettel);
        layoutColumn2.setWidthFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set(FLEX_GROW, "1");
        layoutRow.setWidthFull();
        layoutRow.add(avatarWrapper); // Add wrapper to the layout
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set(FLEX_GROW, "1");
        avatar.setName("Firstname Lastname");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, avatar);
        avatar.setMinWidth(PX);
        avatar.setMinHeight(PX);
        layoutColumn3.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set(FLEX_GROW, "1");
        h1.setWidth(MAX_CONTENT);
        h6.setWidth(MAX_CONTENT);
        layoutRow2.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth(MIN_CONTENT);
        layoutRow2.setHeight(MIN_CONTENT);
        layoutRow3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set(FLEX_GROW, "1");
        layoutColumn4.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth(MIN_CONTENT);
        layoutColumn4.setMinWidth("750px");
        layoutColumn4.setHeight("100%");
        layoutColumn4.setMinHeight(PX);
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.CENTER, h12);
        h12.setWidth(MAX_CONTENT);
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.START, buttonPDataEdit);
        layoutColumn5.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.getStyle().set(FLEX_GROW, "1");
        layoutColumn5.getStyle().set(FLEX_GROW, "1");
        layoutColumn5.setAlignSelf(FlexComponent.Alignment.CENTER, h13);
        h13.setWidth(MAX_CONTENT);
        textMedium2.setWidth("100%");
        textMedium2.getStyle().set("font-size", "var(--lumo-font-size-m)");

        profileLayout.setWidth("50em");

        // Objekte den Layouts zuweisen
        getContent().add(layoutColumn2);
        layoutRow.add(avatar, layoutColumn3);
        layoutRow2.add(icon, icon2, icon3, icon4, icon5);
        layoutRow3.add(layoutColumn4, layoutColumn5);
        layoutColumn2.add(layoutRow, layoutRow3);
        layoutColumn3.add(h1, h6, layoutRow2, buttonMailEdit, buttonMerkzettel);
        layoutColumn5.add(h13, textMedium2, buttonLebenslauf);
    }
}
