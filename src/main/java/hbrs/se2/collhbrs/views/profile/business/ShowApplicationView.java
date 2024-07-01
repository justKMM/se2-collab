package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Application;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.EntityFactory;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = Globals.Pages.SHOW_APPLICATION, layout = AppView.class)
@PageTitle("Show Applications")
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class ShowApplicationView extends Div implements AfterNavigationObserver {

    @Autowired
    EntityFactory entityFactory;
    @Autowired
    SessionService sessionService;
    private final Grid<StudentDTO> grid = new Grid<>();

    public ShowApplicationView() {
        addClassName("feed-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(this::createCard);
        add(grid);
    }

    private static ApplicationDTO createPerson(Application application) {
        return new ApplicationDTO(application);
    }

    private HorizontalLayout createCard(StudentDTO application) {
        String spacing = "spacing-s";
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add(spacing);


        Image image = new Image();
        image.setSrc("");
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add(spacing);


        Span name = new Span(application.getUsername());
        name.addClassName("name");
        Span date = new Span(application.getEmail());
        date.addClassName("date");
        header.add(name, date);

        Span post = new Span(String.valueOf(application.getStudentID()));
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
        /*List<ApplicationDTO> applications = Arrays.asList(
                createPerson()
        );
        grid.setItems(applications);*/
    }
}
