package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.service.ProfileService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import hbrs.se2.collhbrs.views.profile.ProfileBaseView;
import jakarta.annotation.security.RolesAllowed;

@Route(value = Globals.Pages.PROFIL_BUSINESS, layout = AppView.class)
@RolesAllowed(Globals.Roles.BUSINESS)
public class ProfileBusinessView extends ProfileBaseView {

    private final ProfileService profileService;
    private final SessionService sessionService;

    public ProfileBusinessView(ProfileService profileService, SessionService sessionService) {
        super(profileService, sessionService);
        this.profileService = profileService;
        this.sessionService = sessionService;
        customizeView();
    }

    @Override
    protected void customizeView() {
        // Laden des Business-Profils des angemeldeten Benutzers
        long userId = sessionService.getCurrentUser().getUserID();
        Business business = profileService.getBusinessProfile(userId);

        if (business == null) {
            business = new Business();
            User user = profileService.findUserById(userId); // Methode zum Abrufen des User-Entities
            business.setUser(user);
        }

        // Main layout für die Ansicht
        VerticalLayout mainLayout = new VerticalLayout();
        mainLayout.setSpacing(false);
        mainLayout.setPadding(false);
        mainLayout.setSizeFull();

        // Titel hinzufügen
        Span title = new Span("Unternehmensprofil");
        title.getStyle().set("font-size", "24px").set("font-weight", "bold").set("margin-bottom", "20px");

        // Formularlayout für die Details des Unternehmens
        FormLayout formLayout = new FormLayout();
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1),
                new FormLayout.ResponsiveStep("21em", 2));

        // Textfelder für Adresse, Stadt, Postleitzahl und Land
        TextField addressField = new TextField("Adresse");
        addressField.setPlaceholder("Adresse eingeben");
        addressField.setWidthFull();
        addressField.setValue(business.getAddress() != null ? business.getAddress() : "");

        TextField cityField = new TextField("Stadt");
        cityField.setPlaceholder("Stadt eingeben");
        cityField.setWidthFull();
        cityField.setValue(business.getCity() != null ? business.getCity() : "");

        TextField zipCodeField = new TextField("Postleitzahl");
        zipCodeField.setPlaceholder("Postleitzahl eingeben");
        zipCodeField.setWidthFull();
        zipCodeField.setValue(business.getZipCode() != null ? business.getZipCode() : "");

        TextField countryField = new TextField("Land");
        countryField.setPlaceholder("Land eingeben");
        countryField.setWidthFull();
        countryField.setValue(business.getCountry() != null ? business.getCountry() : "");

        formLayout.add(addressField, cityField, zipCodeField, countryField);

        formLayout.getStyle()
                .set("display", "flex")
                .set("justify-content", "center")
                .set("margin", "0 auto")
                .set("max-width", "800px")
                .set("padding", "20px");

        // Button zum Speichern der Unternehmensdetails
        Business finalBusiness = business;
        Button saveButton = new Button("Speichern", event -> {
            finalBusiness.setAddress(addressField.getValue());
            finalBusiness.setCity(cityField.getValue());
            finalBusiness.setZipCode(zipCodeField.getValue());
            finalBusiness.setCountry(countryField.getValue());

            profileService.saveBusinessProfile(finalBusiness);
            Notification.show("Unternehmensdetails erfolgreich gespeichert.");
        });

        // Layout für die Hauptansicht hinzufügen
        mainLayout.add(title, formLayout, saveButton);
        mainLayout.getStyle()
                .set("display", "flex")
                .set("justify-content", "center")
                .set("margin", "0 auto")
                .set("max-width", "800px")
                .set("padding", "20px");

        getContent().add(mainLayout);
    }
}
