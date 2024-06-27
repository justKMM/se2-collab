package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.contextmenu.MenuItem;
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
import hbrs.se2.collhbrs.service.SecurityService;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.Utils;
import hbrs.se2.collhbrs.views.AuthentificationViews.UpdatePasswordView;
import hbrs.se2.collhbrs.views.ProfileViews.ProfilStudentView;
import hbrs.se2.collhbrs.views.SearchView.SearchView;
import org.springframework.beans.factory.annotation.Autowired;

@CssImport("./styles/index.css")
@Route(Globals.Pages.APP)
public class AppView extends AppLayout {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionService sessionService;

    private Tabs sidemenu;
    private H1 viewTitle;

    public AppView() {
        setUpUI();
    }

    private static Tab createTab(String s, Class<? extends Component> navgationTarget) {
        final Tab t = new Tab();
        t.add(new RouterLink(s, navgationTarget));
        ComponentUtil.setData(t, Class.class, navgationTarget);
        return t;
    }

    private void setUpUI() {
        setPrimarySection(Section.DRAWER);

        addToNavbar(true, createHeaderContent());
        sidemenu = createMenu();

        addToDrawer(createDrawerContent(sidemenu));
    }

    private Component createHeaderContent() {
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

        MenuBar menuBar =new MenuBar();
        MenuItem item = menuBar.addItem("Logout", e -> logoutUser());
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

        Tab[] tab_array = new Tab[]{
                createTab("Profile", ProfilStudentView.class),
                createTab("Search job", SearchView.class),
                createTab("Update Password", UpdatePasswordView.class),
        };
        return tab_array;
    }

    private void logoutUser() {
        sessionService.endSession();
    }

    public void addToNavbar(boolean touchOptimized, Component... components) {
        String slot = "navbar" + (touchOptimized ? " touch-optimized" : "");
        SlotUtils.addToSlot(this, slot, components);
    }

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
