package hbrs.se2.collhbrs.views.profile.business;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.model.dto.StudentDTO;
import hbrs.se2.collhbrs.model.entity.Student;
import hbrs.se2.collhbrs.model.entity.User;
import hbrs.se2.collhbrs.service.LoginService;
import hbrs.se2.collhbrs.service.ManageApplicationService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.EntityFactory;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

@Route(value = Globals.Pages.SHOW_APPLICATION, layout = AppView.class)
@PageTitle("Show Applications")
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class ShowApplicationView extends Div implements AfterNavigationObserver {

    private Grid<StudentDTO> grid = new Grid<>();

    @Autowired
    EntityFactory entityFactory;

    @Autowired
    SessionService sessionService;

    public ShowApplicationView() {
        addClassName("feed-view");
        setSizeFull();
        grid.setHeight("100%");
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid.addComponentColumn(student -> createCard(student));
        add(grid);
    }

    private HorizontalLayout createCard(StudentDTO application) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

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
        actions.getThemeList().add("spacing-s");

        description.add(header, post, actions);
        card.add(description);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        List<StudentDTO> student = Arrays.asList(
                createPerson(entityFactory.createStudent(entityFactory.createUser(entityFactory.createProfile(),
                        "russelmrcl", "Password8", "russel@info.com"), "Musk"))
        );
        grid.setItems(student);
    }

    private static StudentDTO createPerson(Student student) {
        return new StudentDTO(student);
    }
}
