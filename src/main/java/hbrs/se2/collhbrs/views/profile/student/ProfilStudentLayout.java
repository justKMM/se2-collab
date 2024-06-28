package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

/*
    Dialog View Layout
 */


@CssImport("./styles/index.css")
public class ProfilStudentLayout extends VerticalLayout {

    @Getter
    private final Button buttonConfirm = new Button("Speichern");
    @Getter
    private final TextField tfVorname = new TextField("Vorname:");
    @Getter
    private final TextField tfNachname = new TextField("Nachname:");
    @Getter
    private final TextField interestField = new TextField("Interessen:");
    @Getter
    private final TextField skillsField = new TextField("Kompetenzen:");
    @Getter
    private final TextField degreeField = new TextField("Studiengang: ");
    @Getter
    private final TextField xingUsernameField = new TextField("Xing Username:");
    @Getter
    private final TextField linkedinUsernameField = new TextField("Linkedin Username:");
    @Getter
    private final TextField profileDescriptionField = new TextField("Profilbeschreibung:");

    @Getter
    private final TextField avatarUrlField = new TextField("Avatar URL:");

    public ProfilStudentLayout() {
        addClassName("profile-form");

        HorizontalLayout layoutName = new HorizontalLayout();
        HorizontalLayout layoutAdresse = new HorizontalLayout();
        HorizontalLayout layoutInterestSkillDegree = new HorizontalLayout();
        HorizontalLayout layoutXingLinkedin = new HorizontalLayout();
        HorizontalLayout layoutProfileDescription = new HorizontalLayout();
        HorizontalLayout layoutAvatar = new HorizontalLayout();

        layoutName.add(tfVorname, tfNachname);

        layoutInterestSkillDegree.add(interestField, skillsField, degreeField);
        layoutXingLinkedin.add(xingUsernameField, linkedinUsernameField);
        layoutProfileDescription.add(profileDescriptionField);
        layoutAvatar.add(avatarUrlField);

        add(
                layoutName,
                layoutAdresse,
                layoutInterestSkillDegree,
                layoutXingLinkedin,
                layoutProfileDescription,
                layoutAvatar
        );


    }

    public String getTfVorname() {
        return tfVorname.getValue().toString();
    }

    public String getTfNachname() {
        return tfNachname.getValue().toString();
    }

    public TextField getInterestField() {
        return interestField;
    }

    public TextField getSkillsField() {
        return skillsField;
    }

    public TextField getDegreeField() {
        return degreeField;
    }

    public TextField getXingUsernameField() {
        return xingUsernameField;
    }

    public TextField getLinkedinUsernameField() {
        return linkedinUsernameField;
    }

    public TextField getProfileDescriptionField() {
        return profileDescriptionField;
    }

    public TextField getAvatarUrlField() {
        return avatarUrlField;
    }
}
