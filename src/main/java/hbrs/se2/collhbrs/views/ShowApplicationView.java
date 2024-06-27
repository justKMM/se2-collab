package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.HeaderRow;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.InMemoryDataProvider;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import hbrs.se2.collhbrs.model.dto.ApplicationDTO;
import hbrs.se2.collhbrs.service.ManageApplicationService;
import hbrs.se2.collhbrs.util.Globals;
import jakarta.annotation.security.RolesAllowed;
import org.apache.commons.lang3.StringUtils;
import java.util.List;

@Route(value = Globals.Pages.SHOW_APPLICATION, layout = AppView.class)
@PageTitle("Show Applications")
@CssImport("./styles/index.css")
@RolesAllowed(Globals.Roles.BUSINESS)
public class ShowApplicationView extends Div  {

    private List<ApplicationDTO> personList;

    public ShowApplicationView( ManageApplicationService manageApplicationService ) {
        addClassName("show-application-view");
        personList = manageApplicationService.readAllApplications();
        add(this.createTitle());
        add(this.createGridTable());
    }

    private Component createGridTable() {
        Grid<ApplicationDTO> grid = new Grid<>();
        InMemoryDataProvider<ApplicationDTO> dataProvider = new ListDataProvider<>(
                personList);
        grid.setDataProvider(dataProvider);

        Grid.Column<ApplicationDTO> brandColumn = grid
                .addColumn("Title").setHeader("Title");
        Grid.Column<ApplicationDTO> modelColumn = grid.addColumn("Description")
                .setHeader("Description");


        HeaderRow filterRow = grid.appendHeaderRow();

        // First filter
        TextField modelField = new TextField();
        modelField.addValueChangeListener(event -> dataProvider.addFilter(
                application -> StringUtils.containsIgnoreCase("test",  modelField.getValue())));

        modelField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(modelColumn).setComponent(modelField);
        modelField.setSizeFull();
        modelField.setPlaceholder("Filter");

        // Second filter
        TextField brandField = new TextField();
        brandField.addValueChangeListener(event -> dataProvider
                .addFilter(application -> StringUtils.containsIgnoreCase(
                        "test", brandField.getValue())));

        brandField.setValueChangeMode(ValueChangeMode.EAGER);

        filterRow.getCell(brandColumn).setComponent(brandField);
        brandField.setSizeFull();
        brandField.setPlaceholder("Filter");

        return grid;
    }

    private Component createTitle() {
        return new H3("Search for Applications");
    }


}
