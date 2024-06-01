package hbrs.se2.collhbrs.views.ProfileView;

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
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import hbrs.se2.collhbrs.model.dto.ProfileDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.dto.UserDTO;
import hbrs.se2.collhbrs.model.dto.imp.StudentDTOImpl;
import hbrs.se2.collhbrs.model.entity.*;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = Globals.Pages.PROFILSTUDENT, layout = AppView.class)
@CssImport("./styles/index.css")
public class ProfilStudentView extends Composite<VerticalLayout> {

    private final Button button_p_data_edit;
    private final Button button_mail_edit;
    private final Button button_merkzettel;
    private final Button button_lebenslauf;
    private final ProfilStudentLayout profileLayout;
    private final Button button_cancel = new Button("Abbrechen");
    private final Button button_confirm = new Button("Speichern");
    private final StudentDTOImpl student = null;
    Dialog dialog;
    Icon icon;
    private List<Interest> interestList = null;
    private List<FirstName> firstNameList = null;
    private List<DegreeProgramm> degreeProgrammList = null;
    private List<Skill> skillList = null;
    private ProfileService profileService = null;
    private UserDTO userDTO = null;
    private ProfileDTO profileDTO = null;
    private StudentDTO studentDTO = null;


    @Autowired
    public ProfilStudentView(ProfileService profileService) {

        H1 h1 = new H1("Hallo " + Globals.CURRENT_USER + "!");


        //Paragraph textMedium = new Paragraph("Email::" + user.getEmail());


        VerticalLayout layoutColumn2 = new VerticalLayout();
        HorizontalLayout layoutRow = new HorizontalLayout();
        Avatar avatar = new Avatar();
        VerticalLayout layoutColumn3 = new VerticalLayout();


        H6 h6 = new H6("Bewertung:");
        HorizontalLayout layoutRow2 = new HorizontalLayout();


        // müssen noch ausgewählt werden für das Rating (später)
        icon = new Icon();
        Icon icon2 = new Icon();
        Icon icon3 = new Icon();
        Icon icon4 = new Icon();
        Icon icon5 = new Icon();

        // dialog Object für Änderungsmaske
        dialog = new Dialog();
        button_mail_edit = new Button("Email bearbeiten");
        button_merkzettel = new Button("Merkzettel");
        button_lebenslauf = new Button("Lebenslauf hochladen");
        button_p_data_edit = new Button("Persönliche Daten bearbeiten");


        HorizontalLayout layoutRow3 = new HorizontalLayout();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        H1 h12 = new H1("Persönliche Daten:");


        H1 h13 = new H1("Dokumente");
        Paragraph textMedium2 = new Paragraph("Lebenslauf:");

        button_mail_edit.setWidth("min-content");
        layoutColumn3.setAlignSelf(FlexComponent.Alignment.START, button_merkzettel);
        button_merkzettel.setWidth("min-content");
        button_merkzettel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button_lebenslauf.setWidth("min-content");
        button_lebenslauf.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button_p_data_edit.setWidth("min-content");
        button_p_data_edit.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        // Bullshit, braucht man aber fürs Design
        VerticalLayout layoutColumn5 = new VerticalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutColumn2.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn2);
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


        // Objekte den Layouts zuweisen
        getContent().add(layoutColumn2);
        layoutRow.add(avatar, layoutColumn3);
        layoutRow2.add(icon, icon2, icon3, icon4, icon5);
        layoutRow3.add(layoutColumn4, layoutColumn5);
        layoutColumn2.add(layoutRow, layoutRow3);
        layoutColumn3.add(h1, h6, layoutRow2, button_mail_edit, button_merkzettel);

        layoutColumn5.add(h13, textMedium2, button_lebenslauf);


        //setRatingIcons();

        // dialog = getContentProfile();

        button_p_data_edit.addClickListener(e -> dialog.open());


        // Aus der Methode rausgezogen um Zugriff auf ProfileLayout zu haben

        profileLayout = new ProfilStudentLayout();

        profileLayout.setWidth("50em");

        dialog.setHeaderTitle("Persönliche Daten ändern");
        dialog.add(profileLayout);

        createFooter(dialog);


        // Profil abspeichern


        // TODO: Datenbankanbindung fehlt noch


        // TODO: Darf nicht so gemacht werden. Ein Boundary muss einen Controller haben, der die Daten aus der Datenbank holt

        this.profileService = profileService;


        userDTO = profileService.getUserByUsername(Globals.CURRENT_USER);


        profileDTO = userDTO.getProfile();


        studentDTO = profileService.getStudentByStudentID(userDTO.getUserID());


        // Student erstellen den wir schon kennen
        Profile profile = new Profile();
        profile.setProfileDescription(profileDTO.getProfileDescription());
        profile.setAvatarUrl(profileDTO.getAvatarUrl());
        profile.setXingUsername(profileDTO.getXingUsername());
        profile.setLinkedinUsername(profileDTO.getLinkedinUsername());
        profile.setProfileID(profileDTO.getProfileID());


        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setUsername(userDTO.getUsername());
        user.setUserID(userDTO.getUserID());
        user.setBlacklisted(userDTO.getBlacklisted());
        user.setProfile(profile);


        Student student = new Student();
        student.setStudentID(studentDTO.getStudentID());
        student.setLastName(studentDTO.getLastName());
        student.setUser(user);

        // Paragraph textMedium = new Paragraph("Placeholer: Email: test@mail.de, geb.Datum: 01.01.2024, Adresse: straße PLZ Stadt, Handynummer");
        Paragraph textMedium = new Paragraph(
                "Email: " + user.getEmail() +
                        "Nachname: " + student.getLastName() +
                        "Username: " + user.getUsername());

        textMedium.setWidth("100%");
        textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
        layoutColumn4.add(h12, textMedium, button_p_data_edit);


        System.out.println(user.getProfile().getProfileID());


        button_confirm.addClickListener(e -> {


            // Nachnamen ziehen und setzen
            String lastName = profileLayout.getTf_nachname().getValue();
            student.setLastName(lastName);

            // Interessen ziehen und setzen
            String[] interests = profileLayout.getInterestField().getValue().split(" ");
            interestList = new ArrayList<>();
            for (String interestName : interests) {
                Interest interest = new Interest();
                interest.setInterestName(interestName);
                interest.setStudent(student);
                interestList.add(interest);
            }
            // Vornamen ziehen und setzen
            String[] firstNames = profileLayout.getTf_vorname().getValue().split(" ");
            firstNameList = new ArrayList<>();
            for (String firstNameName : firstNames) {
                FirstName firstName = new FirstName();
                firstName.setFirstNameName(firstNameName);
                firstName.setStudent(student);
                firstNameList.add(firstName);
            }
            // Skills ziehen und setzen
            String[] skills = profileLayout.getSkillsField().getValue().split(" ");
            skillList = new ArrayList<>();
            for (String skillName : skills) {
                Skill skill = new Skill();
                skill.setSkillName(skillName);
                skill.setStudent(student);
                skillList.add(skill);
            }
            // Studiengänge ziehen und setzen
            String[] degrees = profileLayout.getDegreeField().getValue().split("");
            degreeProgrammList = new ArrayList<>();
            for (String degreeProgrammName : degrees) {
                DegreeProgramm degreeProgramm = new DegreeProgramm();
                degreeProgramm.setDegreeProgrammName(degreeProgrammName);
                degreeProgramm.setStudent(student);
                degreeProgrammList.add(degreeProgramm);
            }
            // Xing Username ziehen und setzen
            String xingUsername = profileLayout.getXingUsernameField().getValue();
            profile.setXingUsername(xingUsername);

            // Linkedin Username ziehen und setzen
            String linkedinUsername = profileLayout.getLinkedinUsernameField().getValue();
            profile.setLinkedinUsername(linkedinUsername);

            // Profilbeschreibung ziehen und setzen
            String profileDescription = profileLayout.getProfileDescriptionField().getValue();
            profile.setProfileDescription(profileDescription);

            // Avatar URL ziehen und setzen
            String avatarUrl = profileLayout.getAvatarUrlField().getValue();
            profile.setAvatarUrl(avatarUrl);


            // DTOs von der Boundary an einen Controller weitergeben!


            // Ich speicher die hier als Prototyp
            profileService.saveProfile(profile);
            profileService.saveUser(user);
            profileService.saveStudent(student);

            for (Interest interest : interestList) {
                profileService.saveInterest(interest);
            }
            for (FirstName firstName : firstNameList) {
                profileService.saveFirstName(firstName);
            }
            for (Skill skill : skillList) {
                profileService.saveSkill(skill);
            }
            for (DegreeProgramm degreeProgramm : degreeProgrammList) {
                profileService.saveDegreeProgramm(degreeProgramm);
            }


            dialog.close();
        });


    }

    public void createFooter(Dialog d) {


        // Button design
        button_confirm.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button_cancel.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button_confirm.addClickShortcut(Key.ENTER);
        button_cancel.addClickShortcut(Key.ESCAPE);
        // addListener
        button_cancel.addClickListener(e -> d.close());
        // speichern fehlt noch
        d.getFooter().add(button_cancel);
        d.getFooter().add(button_confirm);
    }

    public void setRatingIcons() {
        icon = new Icon("vaadin:star");
    }

}
