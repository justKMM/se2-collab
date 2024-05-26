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
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Gap;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;

@Route(value = Globals.Pages.PROFILSTUDENT, layout = AppView.class)
@CssImport("./styles/index.css")
public class ProfilStudentView extends Composite<VerticalLayout> {

        private Button button_p_data_edit;
        private Button button_mail_edit;
        private Button button_merkzettel;
        private Button button_lebenslauf;
        private ProfilLayout profileLayout;
        Dialog dialog;


        Icon icon;

    public ProfilStudentView() {
            VerticalLayout layoutColumn2 = new VerticalLayout();
            HorizontalLayout layoutRow = new HorizontalLayout();
            Avatar avatar = new Avatar();
            VerticalLayout layoutColumn3 = new VerticalLayout();

            H1 h1 = new H1("Hallo " /* add userName*/ );
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

            Paragraph textMedium = new Paragraph("Placeholer: Email: test@mail.de, geb.Datum: 01.01.2024, Adresse: straße PLZ Stadt, Handynummer");
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
            textMedium.setWidth("100%");
            textMedium.getStyle().set("font-size", "var(--lumo-font-size-m)");
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
            layoutColumn4.add(h12, textMedium, button_p_data_edit);
            layoutColumn5.add(h13, textMedium2, button_lebenslauf);


            //setRatingIcons();

            dialog = getContentProfile();

            button_p_data_edit.addClickListener(e -> dialog.open());
    }

    public Dialog getContentProfile(){
        profileLayout = new ProfilLayout();
        profileLayout.setWidth("50em");

        dialog.setHeaderTitle("Persönliche Daten ändern");
        dialog.add(profileLayout);
        // Button Footer erstellen
        createFooter(dialog);
        return dialog;
    }

    public static void createFooter(Dialog d){
            Button button_cancel = new Button("Abbrechen");
            Button button_confirm = new Button("Speichern");

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

    public void setRatingIcons(){
        icon = new Icon("vaadin:star");
    }

}
