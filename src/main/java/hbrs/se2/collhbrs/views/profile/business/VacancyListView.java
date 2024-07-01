package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.VacancyDTO;
import hbrs.se2.collhbrs.model.entity.Vacancy;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.service.VacancyService;
import hbrs.se2.collhbrs.util.EntityFactory;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Route(value = "business/list", layout = AppView.class)
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class VacancyListView extends Div implements AfterNavigationObserver {

    @Autowired
    EntityFactory entityFactory;
    @Autowired
    SessionService sessionService;
    @Autowired
    VacancyService vacancyService;
    private final Grid<VacancyDTO> grid = new Grid<>();

    public VacancyListView() {
        addClassName("feed-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(this::createCard);
        add(grid);
    }

    private static VacancyDTO getVacancy(Vacancy vacancy) {
        return new VacancyDTO(vacancy);
    }

    private HorizontalLayout createCard(VacancyDTO vacancy) {
        String spacing = "spacing-s";
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add(spacing);

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add(spacing);


        Span name = new Span(vacancy.getBusiness().getName());
        name.addClassName("name");
        Span date = new Span(vacancy.getTitel());
        date.addClassName("date");
        header.add(name, date);

        Span post = new Span(String.valueOf(vacancy.getDescription()));
        post.addClassName("post");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add(spacing);

        description.add(header, post, actions);
        card.add(description);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {

        List<VacancyDTO> vacancyList = vacancyService.getVacanciesByBusinessId(sessionService.getCurrentBusiness().getBusinessID())
                .stream()
                .map(VacancyListView::getVacancy)
                .collect(Collectors.toList());

        grid.setItems(vacancyList);
    }
}
