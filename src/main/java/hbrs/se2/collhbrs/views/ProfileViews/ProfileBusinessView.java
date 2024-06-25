package hbrs.se2.collhbrs.views.ProfileViews;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H6;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import hbrs.se2.collhbrs.model.entity.Business;
import hbrs.se2.collhbrs.service.SessionService;
import hbrs.se2.collhbrs.util.Globals;
import hbrs.se2.collhbrs.views.AppView;
import jakarta.annotation.security.RolesAllowed;

@Route(value = Globals.Pages.PROFIL_BUSINESS, layout = AppView.class)
@RolesAllowed(Globals.Roles.BUSINESS)
public class ProfileBusinessView extends Composite<VerticalLayout> {

    public ProfileBusinessView(SessionService sessionService) {

        HorizontalLayout layoutRow = new HorizontalLayout();
        Avatar avatar = new Avatar();
        VerticalLayout layoutColumn2 = new VerticalLayout();
        H1 h1 = new H1();
        H6 h6 = new H6();
        HorizontalLayout layoutRow2 = new HorizontalLayout();
        Icon icon = new Icon();
        Icon icon2 = new Icon();
        Icon icon3 = new Icon();
        Icon icon4 = new Icon();
        Icon icon5 = new Icon();
        VerticalLayout layoutColumn3 = new VerticalLayout();
        H6 h62 = new H6();
        VerticalLayout layoutColumn4 = new VerticalLayout();
        getContent().setWidth("100%");
        getContent().getStyle().set("flex-grow", "1");
        layoutRow.setWidthFull();
        getContent().setFlexGrow(1.0, layoutRow);
        layoutRow.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow.setWidth("100%");
        layoutRow.getStyle().set("flex-grow", "1");
        avatar.setName("Firstname Lastname");
        avatar.setWidth("200px");
        avatar.setHeight("200px");
        layoutColumn2.setHeightFull();
        layoutRow.setFlexGrow(1.0, layoutColumn2);
        layoutColumn2.setWidth("100%");
        layoutColumn2.getStyle().set("flex-grow", "1");

        h1.setText("Hallo " + sessionService.getCurrentBusiness().getName() + "! ");


        h1.setWidth("max-content");
        h6.setText("Rating:");
        h6.setWidth("max-content");
        layoutRow2.setWidthFull();
        layoutColumn2.setFlexGrow(1.0, layoutRow2);
        layoutRow2.addClassName(LumoUtility.Gap.MEDIUM);
        layoutRow2.setWidth("100%");
        layoutRow2.getStyle().set("flex-grow", "1");

        layoutColumn3.setHeightFull();
        layoutRow2.setFlexGrow(1.0, layoutColumn3);
        layoutColumn3.setWidth("100%");
        layoutColumn3.getStyle().set("flex-grow", "1");
        h62.setText("5/5 Sternen");
        h62.setWidth("max-content");
        layoutColumn4.setWidthFull();
        getContent().setFlexGrow(1.0, layoutColumn4);
        layoutColumn4.setWidth("100%");
        layoutColumn4.getStyle().set("flex-grow", "1");
        getContent().add(layoutRow);
        layoutRow.add(avatar);
        layoutRow.add(layoutColumn2);
        layoutColumn2.add(h1);
        layoutColumn2.add(h6);
        layoutColumn2.add(layoutRow2);
        layoutRow2.add(icon);
        layoutRow2.add(icon2);
        layoutRow2.add(icon3);
        layoutRow2.add(icon4);
        layoutRow2.add(icon5);
        layoutRow2.add(layoutColumn3);
        layoutColumn3.add(h62);
        getContent().add(layoutColumn4);
    }
}
