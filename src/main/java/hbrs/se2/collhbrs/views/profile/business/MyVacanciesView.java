package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.RequirmentsDTO;
import hbrs.se2.collhbrs.model.dto.ResponsibilitiesDTO;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Requirements;
import hbrs.se2.collhbrs.model.entity.Responsibilities;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.service.RequirementsService;
import hbrs.se2.collhbrs.service.ResponsibilitiesService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.EntityFactory;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PageTitle("Vacancy Details")
@Route(value = Globals.Pages.MY_VACANCIES, layout = AppView.class)@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class MyVacanciesView extends Composite<VerticalLayout> implements AfterNavigationObserver {

    private final EntityFactory entityFactory;
    private final RequirementsService requirementsService;
    private final ResponsibilitiesService responsibilitiesService;
    private final SessionService sessionService;
    private final VacancyService vacancyService;
    private VerticalLayout layout;

    @Autowired
    public MyVacanciesView(EntityFactory entityFactory,
                           RequirementsService requirementsService,
                           ResponsibilitiesService responsibilitiesService,
                           SessionService sessionService,
                           VacancyService vacancyService) {
        this.entityFactory = entityFactory;
        this.requirementsService = requirementsService;
        this.responsibilitiesService = responsibilitiesService;
        this.sessionService = sessionService;
        this.vacancyService = vacancyService;
        this.layout = new VerticalLayout();
        getContent().add(layout);
    }

    public VerticalLayout createVacancyCard(String titleValue, String locationValue, String descriptionValue, Date publishDate, List<String> requirements, List<String> responsibilities) {
        VerticalLayout cardLayout = new VerticalLayout();
        cardLayout.addClassName("vacancy-card");

        H3 title = new H3(titleValue);

        H5 date = new H5("Date: " + publishDate.toString());
        H5 location = new H5("Location: " + locationValue);
        H5 description = new H5("Description: ");
        Paragraph desParagraph = new Paragraph(descriptionValue);

        Div requirementsDiv = new Div();
        requirementsDiv.add(new H5("Requirements:"));
        requirements.forEach(req -> requirementsDiv.add(new Paragraph(req)));

        Div responsibilitiesDiv = new Div();
        responsibilitiesDiv.add(new H5("Responsibilities:"));
        responsibilities.forEach(resp -> responsibilitiesDiv.add(new Paragraph(resp)));

        Button editButton = new Button("Edit");
        editButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        cardLayout.add(title, date, location, description, desParagraph, requirementsDiv, responsibilitiesDiv, editButton);

        cardLayout.setWidth("100%");
        cardLayout.setMaxWidth("600px");
        cardLayout.getStyle().set("border", "1px solid #ccc");
        cardLayout.getStyle().set("border-radius", "8px");
        cardLayout.getStyle().set("padding", "8px");
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
                    responsibilities.add(resp.getRequirementsName());
                }
            }

            VerticalLayout card = createVacancyCard(
                    vacancy.getTitel(),
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

    public static VacancyDTO getVacancy(Vacancy vacancy) {
        return new VacancyDTO(vacancy);
    }

    public static RequirmentsDTO getRequirements(Requirements requirements) {
        return new RequirmentsDTO(requirements);
    }

    public static ResponsibilitiesDTO getResponsibilities(Responsibilities responsibilities) {
        return new ResponsibilitiesDTO(responsibilities);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        Long businessId = sessionService.getCurrentBusiness().getBusinessID();

        List<VacancyDTO> vacancies = vacancyService.getVacanciesByBusinessId(businessId)
                .stream()
                .map(MyVacanciesView::getVacancy)
                .collect(Collectors.toList());

        List<RequirmentsDTO> allRequirements = new ArrayList<>();
        List<ResponsibilitiesDTO> allResponsibilities = new ArrayList<>();

        for (VacancyDTO vacancy : vacancies) {
            allRequirements.addAll(requirementsService.getRequirementsByVacancyId(vacancy.getVacancyID())
                    .stream()
                    .map(MyVacanciesView::getRequirements)
                    .collect(Collectors.toList()));

            allResponsibilities.addAll(responsibilitiesService.getResponsibilitiesByVacancyId(vacancy.getVacancyID())
                    .stream()
                    .map(MyVacanciesView::getResponsibilities)
                    .collect(Collectors.toList()));
        }
        layout.removeAll();
        layout.add(createVacancyCards(vacancies, allRequirements, allResponsibilities));
    }
}

