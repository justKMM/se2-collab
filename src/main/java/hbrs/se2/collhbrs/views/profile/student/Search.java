package hbrs.se2.collhbrs.views.profile.student;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.dom.Style;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.RequirmentsDTO;
import hbrs.se2.collhbrs.model.dto.ResponsibilitiesDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.MarkdownConverter;
import hbrs.se2.collhbrs.views.AppView;
import hbrs.se2.collhbrs.views.profile.business.MyVacanciesView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Route(value = Globals.Pages.SEARCH_STUDENT, layout = AppView.class)
@RolesAllowed(Globals.Roles.STUDENT)
public class Search extends Composite<VerticalLayout> {

    private final VerticalLayout layout;
    private Button searchButton;
    private ComboBox<String> employmenttype;
    private TextField searchTextField;
    private List<VacancyDTO> vacancies;
    private List<VacancyDTO> searchedVacancies;
    private MarkdownConverter markdownConverter = new MarkdownConverter();
    private final VacancyService vacancyService;
    private final String[] comboBoxItems =
            {
                    "Minijob", "Teilzeit", "Vollzeit", "Praktikum", "Bachelorprojekt",
                    "Masterprojekt", "Büro", "Homeoffice"
            };

    @Autowired
    public Search(VacancyService vacancyService) {
        this.vacancyService = vacancyService;
        this.layout = new VerticalLayout();
        this.vacancies = new ArrayList<>();
        layout.getStyle().setAlignItems(Style.AlignItems.CENTER);
        vacancies.clear();
        for (Vacancy vacancy : vacancyService.getAllVacancies()) {
            vacancies.add(new VacancyDTO(vacancy));
        }
        updateVacancyList(vacancies);
        getContent().getStyle().setAlignItems(Style.AlignItems.CENTER);
        getContent().add(searchbar(), layout);
    }

    public HorizontalLayout searchbar() {
        HorizontalLayout search = new HorizontalLayout();
        employmenttype = new ComboBox<>();
        employmenttype.setItems(comboBoxItems);
        searchButton = new Button("Search");
        searchTextField = new TextField();
        searchTextField.setPlaceholder("Location, Description, etc.");
        searchTextField.setWidth("100%");
        search.setWidth("100%");
        search.setMaxWidth("700px");
        search.add(employmenttype, searchTextField, searchButton);
        searchButton.addClickListener(event -> {
            String searchText = searchTextField.getValue();
            String selectedEmploymentType = employmenttype.getValue();
            performSearch(searchText, selectedEmploymentType);
        });
        return search;
    }

    private void performSearch(String searchText, String employmentType) {
        searchedVacancies = vacancies.stream()
                .filter(vacancy -> (employmentType == null || employmentType.isEmpty() || vacancy.getEmploymentType().equalsIgnoreCase(employmentType)) &&
                        (searchText == null || searchText.isEmpty() || vacancyMatchesSearchText(vacancy, searchText)))
                .collect(Collectors.toList());
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
            layout.add(createCard(vacancyDTO));
        }
    }

    public VerticalLayout createCard(VacancyDTO vacancy) {
        VerticalLayout cardLayout = new VerticalLayout();
        Avatar avatar = new Avatar();
        avatar.setImage("data:image/jpeg;base64," + vacancy.getBusiness().getUser().getProfile().getAvatar());
        HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.add(avatar, new H5(vacancy.getBusiness().getName()));
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
        H6 description = new H6("Description: ");
        Div desParagraph = new Div();
        desParagraph.getElement().setProperty("innerHTML", markdownConverter.convertToHtml(vacancy.getDescription()));
        Button learnMore = new Button("Learn more");
        Button reminder = new Button("Reminder");
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.add(learnMore, reminder);
        cardLayout.add(title, type, titleLayout, infoLayout, description, desParagraph, buttonLayout);
        cardLayout.setWidth("100%");
        cardLayout.setMaxWidth("700px");
        cardLayout.getStyle().set("border", "1px solid #ccc");
        cardLayout.getStyle().set("border-radius", "8px");
        cardLayout.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");

        learnMore.addClickListener(event -> openDialog(vacancy));

        return cardLayout;
    }

    private void openDialog(VacancyDTO vacancy) {
        Dialog dialog = new Dialog();
        dialog.setWidth("500px");
        dialog.setHeight("400px");

        VerticalLayout dialogLayout = new VerticalLayout();
        dialogLayout.setPadding(true);
        dialogLayout.setSpacing(true);
        Avatar avatar = new Avatar();
        avatar.setImage("data:image/jpeg;base64," + vacancy.getBusiness().getUser().getProfile().getAvatar());
        HorizontalLayout titleLayout = new HorizontalLayout();
        titleLayout.add(avatar, new H3(vacancy.getBusiness().getName()));
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
        desParagraph.getElement().setProperty("innerHTML", markdownConverter.convertToHtml(vacancy.getDescription()));
        Button closeButton = new Button("Close", event -> dialog.close());
        closeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        dialogLayout.add(title, description, closeButton);
        dialog.add(dialogLayout);
        dialog.open();
    }
}
