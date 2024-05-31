package hbrs.se2.collhbrs.views.ProfileView;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import lombok.Getter;

/*
    Dialog View
 */



@CssImport("./styles/index.css")
public class ProfilStudentLayout extends VerticalLayout {

    private Button button_cancel = new Button("Abbrechen");
    @Getter
    private Button button_confirm = new Button("Speichern");
    @Getter
    private TextField tf_vorname = new TextField("Vorname:");
    @Getter
    private TextField tf_nachname = new TextField("Nachname:");
    // private TextField tf_strasse = new TextField("Straße und Hausnummer:");
    // private TextField nf_plz = new TextField("PLZ:");
    // private TextField tf_ort = new TextField("Ort:");
    // private TextField tf_telefon = new TextField("Telefon:");
    // @Getter
    // private EmailField ef_email = new EmailField("E-Mail:");
    // private ComboBox<String> cb_anrede = new ComboBox<>("Anrede:");
    @Getter
    private TextField interestField = new TextField("Interessen:");
    @Getter
    private TextField skillsField = new TextField("Kompetenzen:");
    @Getter
    private TextField degreeField = new TextField("Studiengang");



    // private final String[] anredeArray = {"Herr", "Frau", "Andere"};
    public ProfilStudentLayout(){
        addClassName("profile-form");

        HorizontalLayout layout_name = new HorizontalLayout();
        HorizontalLayout layout_adresse = new HorizontalLayout();
        //HorizontalLayout layout_mail = new HorizontalLayout();
        HorizontalLayout layout_interest_skill_degree = new HorizontalLayout();

        // Error Message für Mail
        //ef_email.setErrorMessage("Enter a valid E-Mail.");
        // ef_email.setPlaceholder("example@mail.com");

        /*nf_plz.setMaxLength(5);
        nf_plz.setAllowedCharPattern("[0-9]");

        tf_telefon.setAllowedCharPattern("[0-9()+-]");
        tf_telefon.setPattern("^[+]?[(]?[0-9]{2}[)]?[-s.]?[0-9]{4}[-s.]?[0-9]{4,8}$");
        tf_telefon.setHelperText("Format: +(12)3456-7890123");
        tf_telefon.setMaxLength(15);*/

        layout_name.add(tf_vorname, tf_nachname);
        // layout_adresse.add(tf_strasse, tf_ort, nf_plz);
        // layout_mail.add(tf_telefon, ef_email);

        layout_interest_skill_degree.add(interestField, skillsField, degreeField);

        // cb_anrede.setItems(anredeArray);

        add(/*cb_anrede,*/
                layout_name,
            layout_adresse,
            /*layout_mail,*/
            layout_interest_skill_degree
        );





    }







}
