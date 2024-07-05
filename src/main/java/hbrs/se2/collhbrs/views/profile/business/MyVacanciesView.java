package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.RequirmentsDTO;
import hbrs.se2.collhbrs.model.dto.ResponsibilitiesDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.model.entity.traits.Requirements;
import hbrs.se2.collhbrs.model.entity.traits.Responsibilities;
import hbrs.se2.collhbrs.service.RequirementsService;
import hbrs.se2.collhbrs.service.ResponsibilitiesService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.MarkdownConverter;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Vacancy Details")
@Route(value = Globals.Pages.MY_VACANCIES, layout = AppView.class)
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class MyVacanciesView extends Composite<VerticalLayout> implements AfterNavigationObserver {

    private final RequirementsService requirementsService;
    private final ResponsibilitiesService responsibilitiesService;
    private final SessionService sessionService;
    private final VacancyService vacancyService;
    private final VerticalLayout layout;
    private final MarkdownConverter markdownConverter;
    private List<VacancyDTO> vacancies;

    @Autowired
    public MyVacanciesView(RequirementsService requirementsService,
                           ResponsibilitiesService responsibilitiesService,
                           SessionService sessionService,
                           VacancyService vacancyService,
                           MarkdownConverter markdownConverter) {
        this.requirementsService = requirementsService;
        this.responsibilitiesService = responsibilitiesService;
        this.sessionService = sessionService;
        this.vacancyService = vacancyService;
        this.markdownConverter = markdownConverter;
        this.layout = new VerticalLayout();
        getContent().add(layout);
    }

    public static VacancyDTO getVacancy(Vacancy vacancy) {
        return new VacancyDTO(vacancy);
    }

    public static RequirmentsDTO getRequirements(Requirements requirements) {
        return new RequirmentsDTO(requirements);
    }

    public static ResponsibilitiesDTO getResponsibilities(Responsibilities responsibilities) {
        return new ResponsibilitiesDTO(responsibilities);
    }

    public VerticalLayout createVacancyCard(long id, String titleValue, String typeValue, String locationValue, String descriptionValue, Date publishDate, List<String> requirements, List<String> responsibilities) {
        VerticalLayout cardLayout = new VerticalLayout();
        cardLayout.addClassName("vacancy-card");

        H3 title = new H3(titleValue);
        Paragraph type = new Paragraph(typeValue);
        H5 date = new H5("Date: " + publishDate.toString());
        H5 location = new H5("Location: " + locationValue);
        H5 description = new H5("Description: ");

        Div desParagraph = new Div();
        desParagraph.getElement().setProperty("innerHTML", markdownConverter.convertToHtml(descriptionValue));

        Div requirementsDiv = new Div();
        requirementsDiv.add(new H5("Requirements:"));
        requirements.forEach(req -> {
            Div reqParagraph = new Div();
            reqParagraph.getElement().setProperty("innerHTML", markdownConverter.convertToHtml(req));
            requirementsDiv.add(reqParagraph);
        });

        Div responsibilitiesDiv = new Div();
        responsibilitiesDiv.add(new H5("Responsibilities:"));
        responsibilities.forEach(resp -> {
            Div respParagraph = new Div();
            respParagraph.getElement().setProperty("innerHTML", markdownConverter.convertToHtml(resp));
            responsibilitiesDiv.add(respParagraph);
        });

        Button editButton = new Button("Delete");
        editButton.addThemeVariants(ButtonVariant.LUMO_ERROR);

        editButton.addClickListener(event -> {
            vacancyService.deleteVacancy(id);
            UI.getCurrent().navigate(MyVacanciesView.class);
        });

        cardLayout.add(title, type, date, location, description, desParagraph, requirementsDiv, responsibilitiesDiv, editButton);

        cardLayout.setWidth("100%");
        cardLayout.setMaxWidth("700px");
        cardLayout.getStyle().set("border", "1px solid #ccc");
        cardLayout.getStyle().set("border-radius", "8px");
        cardLayout.getStyle().set("box-shadow", "0 2px 4px rgba(0, 0, 0, 0.1)");

        return cardLayout;
    }

    public VerticalLayout createVacancyCards(List<VacancyDTO> vacancies, List<RequirmentsDTO> allRequirements, List<ResponsibilitiesDTO> allResponsibilities) {
        VerticalLayout cardsLayout = new VerticalLayout();
        cardsLayout.setWidth("100%");
        cardsLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        cardsLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        for (VacancyDTO vacancy : vacancies) {
            List<String> requirements = new ArrayList<>();
            List<String> responsibilities = new ArrayList<>();

            for (RequirmentsDTO req : allRequirements) {
                if (req.getVacancy().getVacancyID() == vacancy.getVacancyID()) {
                    requirements.add(req.getRequirementsName());
                }
            }

            for (ResponsibilitiesDTO resp : allResponsibilities) {
                if (resp.getVacancy().getVacancyID() == vacancy.getVacancyID()) {
                    responsibilities.add(resp.getResponsibilitiesName());
                }
            }

            VerticalLayout card = createVacancyCard(
                    vacancy.getVacancyID(),
                    vacancy.getTitle(),
                    vacancy.getEmploymentType(),
                    vacancy.getLocation(),
                    vacancy.getDescription(),
                    vacancy.getPublishDate(),
                    requirements,
                    responsibilities
            );
            cardsLayout.add(card);
        }

        return cardsLayout;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {

        List<VacancyDTO> vacancies = vacancyService.getVacanciesByBusinessId(
                        sessionService.getCurrentBusiness().getBusinessID()
                )
                .stream()
                .map(MyVacanciesView::getVacancy)
                .collect(Collectors.toList());

        List<RequirmentsDTO> allRequirements = new ArrayList<>();
        List<ResponsibilitiesDTO> allResponsibilities = new ArrayList<>();

        for (VacancyDTO vacancy : vacancies) {
            allRequirements.addAll(requirementsService.getRequirementsByVacancyId(vacancy.getVacancyID())
                    .stream()
                    .map(MyVacanciesView::getRequirements)
                    .toList());

            allResponsibilities.addAll(responsibilitiesService.getResponsibilitiesByVacancyId(vacancy.getVacancyID())
                    .stream()
                    .map(MyVacanciesView::getResponsibilities)
                    .toList());
        }
        layout.removeAll();
        layout.add(createVacancyCards(vacancies, allRequirements, allResponsibilities));
    }
}
