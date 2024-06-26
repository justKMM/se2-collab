package hbrs.se2.collhbrs.views;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
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
import com.vaadin.flow.server.VaadinSession;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.util.Utils;
import hbrs.se2.collhbrs.views.ProfileViews.ProfilStudentView;
import hbrs.se2.collhbrs.views.SearchView.SearchView;

@CssImport("./styles/index.css")
@Route(Globals.Pages.APP)
public class AppView extends AppLayout {

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
        MenuItem update = menuBar.addItem("Update Password", e -> updatePass());
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
        // Tab[] tab_array = new Tab[]{createTab("Dashboard", AppView.class)};
        Tab[] tab_array = new Tab[]{createTab("Profile", ProfilStudentView.class)};
        //Tab[] tab_array = new Tab[]{createTab("Job suche", SearchView.class)};


        // new Tab
        tab_array = Utils.append(tab_array, createTab("Job suche", SearchView.class));
        // tab_array = Utils.append(tab_array, createTab("Profil", ProfilStudentView.class));


        return tab_array;
    }

    private void logoutUser() {
        UI.getCurrent().getPage().setLocation(Globals.Pages.LOGIN);
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
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
    private void updatePass(){
        UI.getCurrent().navigate("update-password");
    }
}
