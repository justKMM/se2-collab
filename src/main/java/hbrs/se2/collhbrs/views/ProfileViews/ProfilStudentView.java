package hbrs.se2.collhbrs.views.ProfileViews;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dialog.Dialog;
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
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = Globals.Pages.PROFIL_STUDENT, layout = AppView.class)
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.STUDENT)
public class ProfilStudentView extends Composite<VerticalLayout> {

    private  Button button_p_data_edit;
    private  Button button_mail_edit;
    private  Button button_merkzettel;
    private  Button button_lebenslauf;
    private final ProfilStudentLayout profileLayout = new ProfilStudentLayout();
    private final Button button_cancel = new Button("Cancel");
    private final Button button_confirm = new Button("Save");


    private VerticalLayout layoutColumn2 = new VerticalLayout();
    private VerticalLayout layoutColumn3 = new VerticalLayout();
    private VerticalLayout layoutColumn4 = new VerticalLayout();
    private VerticalLayout layoutColumn5 = new VerticalLayout();

    private HorizontalLayout layoutRow = new HorizontalLayout();
    private HorizontalLayout layoutRow2 = new HorizontalLayout();
    private HorizontalLayout layoutRow3 = new HorizontalLayout();

    private H1 h12 = new H1("Personal data:");
    private H1 h13 = new H1("Documents");
    private H6 h6 = new H6("Rating:");
    private H1 h1;

    private Paragraph textMedium2 = new Paragraph("Lebenslauf:");

    Dialog dialog;
    private Icon icon = new Icon();
    private Icon icon2 = new Icon();
    private Icon icon3 = new Icon();
    private Icon icon4 = new Icon();
    private Icon icon5 = new Icon();

    Avatar avatar = new Avatar();

    private StudentDTO student;

    private ProfileService profileService;

    @Autowired
    public ProfilStudentView(ProfileService profileService, SessionService sessionService) {

        student = sessionService.getCurrentStudent();
        h1 = new H1("Hallo " + student.getUser().getUsername() +  "!");


        // dialog Object für Änderungsmaske
        dialog = new Dialog();



        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        getContent().setFlexGrow(1.0, layoutColumn2);

        setRatingIcons();
        setButtons();
        setLayouts();



        // dialog = getContentProfile();

        button_p_data_edit.addClickListener(e -> {
            dialog.setHeaderTitle("Change personal data");
            dialog.add(profileLayout);
            createFooter(dialog);
            dialog.open();
        });

        button_mail_edit.addClickListener(e -> {
            Dialog d = new Dialog();
            VerticalLayout verticalLayout = new VerticalLayout();
            EmailField ef_email = new EmailField("E-Mail:");
            Button button_confirm_mail = new Button("Save");
            Button button_cancel_mail = new Button("Cancel");
            verticalLayout.add(ef_email);
            verticalLayout.getStyle().set("width", "20rem").set("max-width", "100%");
            d.setHeaderTitle("E-Mail ändern: ");
            d.add(verticalLayout);
            d.getFooter().add(button_cancel_mail, button_confirm_mail);

            button_cancel_mail.addClickListener(e1 -> d.close());
            button_confirm_mail.addClickListener(e1 -> {
                if(!ef_email.isEmpty()) {
                    student.getUser().setEmail(ef_email.getValue());
                    Notification.show("E-Mail wurde geändert. ");
                }
                d.close();
            });

            d.open();
        });





        String s = "Email: " + sessionService.getCurrentStudent().getUser().getEmail() + "\n" +
                " Last name: " + sessionService.getCurrentStudent().getLastName() + "\n" +
                " Username: " +  sessionService.getCurrentStudent().getUser().getUsername();
        Paragraph textMedium = new Paragraph(
                s);

        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        layoutColumn4.add(h12, textMedium, button_p_data_edit);




    }

    private void setButtons() {
        button_mail_edit = new Button("Edit Email");
        button_merkzettel = new Button("Notespad");
        button_lebenslauf = new Button("upload Lebenslauf");
        button_p_data_edit = new Button("Edit personal data");

        button_mail_edit.setWidth("min-content");
        button_merkzettel.setWidth("min-content");
        button_merkzettel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button_lebenslauf.setWidth("min-content");
        button_lebenslauf.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button_p_data_edit.setWidth("min-content");
        button_p_data_edit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }

    public void createFooter(Dialog d) {

        button_confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button_cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button_confirm.addClickShortcut(Key.ENTER);
        button_cancel.addClickShortcut(Key.ESCAPE);

        button_cancel.addClickListener(e -> d.close());
        button_confirm.addClickListener(e -> {
            changeData();



            dialog.close();
        });
        // speichern fehlt noch
        d.getFooter().add(button_cancel);
        d.getFooter().add(button_confirm);
    }

    private void setRatingIcons() {
        // müssen noch ausgewählt werden für das Rating (später)

        icon = VaadinIcon.STAR.create();
        icon2 = VaadinIcon.STAR.create();
        icon3 = VaadinIcon.STAR.create();
        icon4 = VaadinIcon.STAR.create();
        icon5 = VaadinIcon.STAR.create();
    }

    private void changeData(){
        User u = student.getUser();
        u.setUsername(profileLayout.getTf_vorname());

        if(!profileLayout.getTf_vorname().isEmpty()) {
            student.setUser(u);
            // Notification.show("First name has been changed.");
        }
        if(!profileLayout.getTf_nachname().isEmpty()) {
            student.setLastName(profileLayout.getTf_nachname());
            // Notification.show("Last name has been changed.  ");


        }
    }

    private void setLayouts(){
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.START, button_merkzettel);
        layoutColumn2.setWidthFull();
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        avatar.setName("Firstname Lastname");
        layoutRow.setAlignSelf(FlexComponent.Alignment.CENTER, avatar);
        avatar.setMinWidth("200px");
        avatar.setMinHeight("200px");
        layoutColumn3.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        h1.setWidth("max-content");
        h6.setWidth("max-content");
        layoutRow2.setWidthFull();
        layoutColumn3.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(Gap.MEDIUM);
        layoutRow2.setWidth("min-content");
        layoutRow2.setHeight("min-content");
        layoutRow3.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow3);
        layoutRow3.addClassName(Gap.MEDIUM);
        layoutRow3.setWidth("100%");
        layoutRow3.getStyle().set("flex-grow", "1");
        layoutColumn4.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("min-content");
        layoutColumn4.setMinWidth("750px");
        layoutColumn4.setHeight("100%");
        layoutColumn4.setMinHeight("200px");
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.CENTER, h12);
        h12.setWidth("max-content");
        layoutColumn4.setAlignSelf(FlexComponent.Alignment.START, button_p_data_edit);
        layoutColumn5.setHeightFull();
        layoutRow3.setFlexGrow(1.0, layoutColumn5);
        layoutColumn5.getStyle().set("flex-grow", "1");
        layoutColumn5.getStyle().set("flex-grow", "1");
        layoutColumn5.setAlignSelf(FlexComponent.Alignment.CENTER, h13);
        h13.setWidth("max-content");
        textMedium2.setWidth("100%");
        textMedium2.getStyle().set("font-size", "var(--lumo-font-size-m)");


        profileLayout.setWidth("50em");

        // Objekte den Layouts zuweisen
        getContent().add(layoutColumn2);
        layoutRow.add(avatar, layoutColumn3);
        layoutRow2.add(icon, icon2, icon3, icon4, icon5);
        layoutRow3.add(layoutColumn4, layoutColumn5);
        layoutColumn2.add(layoutRow, layoutRow3);
        layoutColumn3.add(h1, h6, layoutRow2, button_mail_edit, button_merkzettel);

        layoutColumn5.add(h13, textMedium2, button_lebenslauf);
    }
}
