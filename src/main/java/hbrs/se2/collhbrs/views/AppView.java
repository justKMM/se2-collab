package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.shared.SlotUtils;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.Utils;
import hbrs.se2.collhbrs.views.authentification.UpdatePasswordView;
import hbrs.se2.collhbrs.views.profile.business.MyVacanciesView;
import hbrs.se2.collhbrs.views.profile.business.ProfileBusinessView;
import hbrs.se2.collhbrs.views.profile.business.ShowApplicationView;
import hbrs.se2.collhbrs.views.profile.business.VacancyView;
import hbrs.se2.collhbrs.views.profile.student.ProfilStudentView;
import hbrs.se2.collhbrs.views.profile.student.SearchView;
import org.springframework.beans.factory.annotation.Autowired;

@CssImport("./styles/index.css")
@Route(Globals.Pages.APP)
public class AppView extends AppLayout {

    private final SessionService sessionService;

    @Autowired
    public AppView(SessionService sessionService) {
        this.sessionService = sessionService;
        setUpUI();
    }

    private static Tab createTab(String s, Class<? extends Component> navigationTarget) {
        final Tab t = new Tab();
        t.add(new RouterLink(s, navigationTarget));
        ComponentUtil.setData(t, Class.class, navigationTarget);
        return t;
    }

    private void setUpUI() {
        Tabs sideMenu;
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        sideMenu = createMenu();
        addToDrawer(createDrawerContent(sideMenu));
    }

    private Component createHeaderContent() {
        H1 viewTitle;
        HorizontalLayout layout = new HorizontalLayout();
        layout.setId("header");
        layout.setWidthFull();
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        layout.add(new DrawerToggle());
        viewTitle = new H1();
        viewTitle.setWidthFull();
        layout.add(viewTitle);

        HorizontalLayout topRightLayout = new HorizontalLayout();
        topRightLayout.setWidthFull();
        topRightLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.END);
        topRightLayout.setAlignItems(FlexComponent.Alignment.CENTER);

        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Abmelden", e -> logoutUser());
        topRightLayout.add(menuBar);

        layout.add(topRightLayout);
        return layout;
    }

    private Tabs createMenu() {
        final Tabs tabs = new Tabs();
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        tabs.add(createMenuItems());
        return tabs;
    }

    private Component[] createMenuItems() {

        Tab[] tabs = new Tab[]{};

        if (sessionService.getUserRole().contains(Globals.Roles.STUDENT)) {
            tabs = Utils.append(tabs, createTab("Profil", ProfilStudentView.class));
            tabs = Utils.append(tabs, createTab("Job suchen", SearchView.class));
            tabs = Utils.append(tabs, createTab("Passwort ändern", UpdatePasswordView.class));
        } else if (sessionService.getUserRole().contains(Globals.Roles.BUSINESS)) {
            tabs = Utils.append(tabs, createTab("Profil", ProfileBusinessView.class));
            tabs = Utils.append(tabs, createTab("Passwort ändern", UpdatePasswordView.class));
            tabs = Utils.append(tabs, createTab("Stellenausschreibung hinzufügen", VacancyView.class));
            tabs = Utils.append(tabs, createTab("Meine Stellenausschreibungen", MyVacanciesView.class));
            tabs = Utils.append(tabs, createTab("Bewerbungen einsehen", ShowApplicationView.class));
        }
        return tabs;
    }

    private void logoutUser() {
        sessionService.endSession();
    }

    @Override
    public void addToNavbar(boolean touchOptimized, Component... components) {
        String slot = "navbar" + (touchOptimized ? " touch-optimized" : "");
        SlotUtils.addToSlot(this, slot, components);
    }

    @Override
    public void addToDrawer(Component... components) {
        SlotUtils.addToSlot(this, "drawer", components);
    }

    private Component createDrawerContent(Tabs menu) {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        verticalLayout.add(menu);
        return verticalLayout;
    }
}
