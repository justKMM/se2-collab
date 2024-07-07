package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.RequirmentsDTO;
import hbrs.se2.collhbrs.model.dto.ResponsibilitiesDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import hbrs.se2.collhbrs.service.*;
import hbrs.se2.collhbrs.util.EntityFactory;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.MarkdownConverter;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route(value = Globals.Pages.SEARCH_STUDENT, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class SearchView extends Composite<VerticalLayout> {

    private static final String FONT_WEIGHT = "font-weight";
    private static final String INNER_HTML = "innerHTML";

    private final VerticalLayout layout;
    private final List<VacancyDTO> vacancies;
    private final List<RequirmentsDTO> allRequirements;
    private final List<ResponsibilitiesDTO> allResponsibilities;
    private final EntityFactory entityFactory = new EntityFactory();
    private final MarkdownConverter markdownConverter = new MarkdownConverter();
    private final SessionService sessionService;
    private final ApplicationService applicationService;
    private final String[] comboBoxItems =
            {
                    "Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt",
                    "Masterprojekt", "Büro", "Homeoffice"
            };

    @Autowired
    public SearchView(VacancyService vacancyService, RequirementsService requirementsService,
                      ResponsibilitiesService responsibilitiesService, SessionService sessionService,
                      ApplicationService applicationService) {
        this.sessionService = sessionService;
        this.applicationService = applicationService;
        this.layout = new VerticalLayout();
        this.vacancies = new ArrayList<>();
        layout.getStyle().setAlignItems(Style.AlignItems.CENTER);
        vacancies.clear();
        for (Vacancy vacancy : vacancyService.getAllVacancies()) {
            vacancies.add(new VacancyDTO(vacancy));
        }
        allRequirements = new ArrayList<>();
        allResponsibilities = new ArrayList<>();

        for (VacancyDTO vacancy : vacancies) {
            allRequirements.addAll(requirementsService.getRequirementsByVacancyId(vacancy.getVacancyID())
                    .stream()
                        .map(SearchView::getRequirements)
                    .toList());

            allResponsibilities.addAll(responsibilitiesService.getResponsibilitiesByVacancyId(vacancy.getVacancyID())
                    .stream()
                    .map(SearchView::getResponsibilities)
                    .toList());
        }
        updateVacancyList(vacancies);
        getContent().getStyle().setAlignItems(Style.AlignItems.CENTER);
        getContent().add(searchbar(), layout);
    }

    public static RequirmentsDTO getRequirements(Requirements requirements) {
        return new RequirmentsDTO(requirements);
    }

    public static ResponsibilitiesDTO getResponsibilities(Responsibilities responsibilities) {
        return new ResponsibilitiesDTO(responsibilities);
    }

    public HorizontalLayout searchbar() {
        HorizontalLayout search = new HorizontalLayout();
        ComboBox<String> employmentType = new ComboBox<>();
        employmentType.setItems(comboBoxItems);
        Button searchButton = new Button("Search");
        TextField searchTextField = new TextField();
        searchTextField.setPlaceholder("Location, Description, etc.");
        searchTextField.setWidth("100%");
        search.setWidth("100%");
        search.setMaxWidth("700px");
        search.add(employmentType, searchTextField, searchButton);
        searchButton.addClickListener(event -> {
            String searchText = searchTextField.getValue();
            String selectedEmploymentType = employmentType.getValue();
            performSearch(searchText, selectedEmploymentType);
        });
        return search;
    }

    private void performSearch(String searchText, String employmentType) {
        List<VacancyDTO> searchedVacancies = vacancies.stream()
                .filter(vacancy -> (employmentType == null || employmentType.isEmpty() || vacancy.getEmploymentType().equalsIgnoreCase(employmentType)) &&
                        (searchText == null || searchText.isEmpty() || vacancyMatchesSearchText(vacancy, searchText)))
                .toList();
        updateVacancyList(searchedVacancies);
    }

    private boolean vacancyMatchesSearchText(VacancyDTO vacancy, String searchText) {
        return vacancy.getTitle().toLowerCase().contains(searchText.toLowerCase()) ||
                vacancy.getDescription().toLowerCase().contains(searchText.toLowerCase()) ||
                vacancy.getBusiness().getName().toLowerCase().contains(searchText.toLowerCase())
                || vacancy.getLocation().toLowerCase().contains(searchText.toLowerCase());
    }

    private void updateVacancyList(List<VacancyDTO> vacanciesToDisplay) {
        layout.removeAll();
        for (VacancyDTO vacancyDTO : vacanciesToDisplay) {
            List<String> requirements = new ArrayList<>();
            List<String> responsibilities = new ArrayList<>();

            for (RequirmentsDTO req : allRequirements) {
                if (req.getVacancy().getVacancyID() == vacancyDTO.getVacancyID()) {
                    requirements.add(req.getRequirementsName());
                }
            }

            for (ResponsibilitiesDTO resp : allResponsibilities) {
                if (resp.getVacancy().getVacancyID() == vacancyDTO.getVacancyID()) {
                    responsibilities.add(resp.getResponsibilitiesName());
                }
            }
            layout.add(createCard(vacancyDTO, requirements, responsibilities));
        }
    }

    public VerticalLayout createCard(VacancyDTO vacancy, List<String> requirements, List<String> responsibilities) {
        VerticalLayout cardLayout = new VerticalLayout();
        Avatar avatar = new Avatar();
        avatar.setImage("data:image/jpeg;base64," + vacancy.getBusiness().getUser().getProfile().getAvatar());
        HorizontalLayout avatarLayout = new HorizontalLayout();
        avatarLayout.add(avatar, new H5(vacancy.getBusiness().getName()));
        H3 title = new H3(vacancy.getTitle());
        Button type = new Button(vacancy.getEmploymentType());
        type.setWidth("min-content");
        type.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        type.setEnabled(true);
        HorizontalLayout dateLayout = new HorizontalLayout(
                new H4("Date: "),
                new Span(vacancy.getPublishDate().toString())
        );
        HorizontalLayout locationLayout = new HorizontalLayout(
                new H4("Location: "),
                new Span(vacancy.getLocation())
        );
        HorizontalLayout infoLayout = new HorizontalLayout(dateLayout, locationLayout);
        H4 profileDescription = new H4("About us ");
        Div profileDescriptionParagraph = new Div();
        profileDescriptionParagraph.getElement().setProperty(INNER_HTML, markdownConverter.convertToHtml(vacancy.getDescription()));
        VerticalLayout contactLayout = new VerticalLayout();
        Span email = new Span("Email: ");
        email.getStyle().set(FONT_WEIGHT, "bold");
        HorizontalLayout emailLayout = new HorizontalLayout(email,
                new Span(vacancy.getBusiness().getUser().getEmail()));
        Span linkedIn = new Span("LinkedIn: ");
        linkedIn.getStyle().set(FONT_WEIGHT, "bold");
        HorizontalLayout linkendInLayout = new HorizontalLayout(linkedIn,
                new Span(vacancy.getBusiness().getUser().getProfile().getLinkedinUsername()));
        Span xing = new Span("Xing:");
        xing.getStyle().set(FONT_WEIGHT, "bold");
        HorizontalLayout xingLayout = new HorizontalLayout(xing,
                new Span(vacancy.getBusiness().getUser().getProfile().getXingUsername()));
        contactLayout.add(emailLayout, linkendInLayout, xingLayout);
        Button learnMore = new Button("Learn more");
        Button reminder = new Button("Reminder");
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(learnMore, reminder);
        cardLayout.add(title, avatarLayout, type, infoLayout, contactLayout, profileDescription,
                profileDescriptionParagraph, buttonLayout);
        cardLayout.setWidth("100%");
        cardLayout.setMaxWidth("700px");
        cardLayout.getStyle().set("border", "1px solid #ccc");
        cardLayout.getStyle().set("border-radius", "8px");
        cardLayout.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");

        learnMore.addClickListener(event -> openDialog(vacancy, requirements, responsibilities));

        return cardLayout;
    }

    private void openDialog(VacancyDTO vacancy, List<String> requirements, List<String> responsibilities) {
        Dialog dialog = new Dialog();
        dialog.setWidth("800px");
        dialog.setHeight("600px");
        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.setPadding(true);
        dialogLayout.setSpacing(true);
        Avatar avatar = new Avatar();
        avatar.setImage("data:image/jpeg;base64," + vacancy.getBusiness().getUser().getProfile().getAvatar());
        H2 title = new H2(vacancy.getTitle());
        Button type = new Button(vacancy.getTitle());
        type.setWidth("min-content");
        type.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        type.setEnabled(true);
        HorizontalLayout dateLayout = new HorizontalLayout(
                new H4("Date: "),
                new Span(vacancy.getPublishDate().toString())
        );
        HorizontalLayout locationLayout = new HorizontalLayout(
                new H4("Location: "),
                new Span(vacancy.getLocation())
        );
        HorizontalLayout infoLayout = new HorizontalLayout(dateLayout, locationLayout);
        H4 description = new H4("Description: ");
        Div desParagraph = new Div();
        desParagraph.getElement().setProperty(INNER_HTML, markdownConverter.convertToHtml(vacancy.getDescription()));
        Div requirementsDiv = new Div();
        requirementsDiv.add(new H3("Requirements:"));
        requirements.forEach(req -> {
            Div reqParagraph = new Div();
            reqParagraph.getElement().setProperty(INNER_HTML, markdownConverter.convertToHtml(req));
            requirementsDiv.add(reqParagraph);
        });
        Div responsibilitiesDiv = new Div();
        responsibilitiesDiv.add(new H3("Responsibilities:"));
        responsibilities.forEach(resp -> {
            Div respParagraph = new Div();
            respParagraph.getElement().setProperty(INNER_HTML, markdownConverter.convertToHtml(resp));
            responsibilitiesDiv.add(respParagraph);
        });
        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button apply = new Button("Apply");
        apply.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        apply.addClickListener(e -> {
            applicationService.saveApplication(
                    entityFactory.createApplication(
                            vacancy.getVacancy(),
                            sessionService.getCurrentStudent().getStudent()
                    )
            );
            Notification.show("applied!");
        });
        Button closeButton = new Button("Close", event -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_ERROR);
        buttonLayout.add(apply, closeButton);
        dialogLayout.add(title, infoLayout, description, desParagraph, responsibilitiesDiv, requirementsDiv, buttonLayout);
        dialog.add(dialogLayout);
        dialog.open();
    }
}
