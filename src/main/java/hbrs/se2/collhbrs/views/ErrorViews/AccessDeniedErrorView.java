package hbrs.se2.collhbrs.views.ErrorViews;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.router.AccessDeniedException;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.ErrorParameter;
import com.vaadin.flow.router.HasErrorParameter;
import com.vaadin.flow.server.HttpStatusCode;
import hbrs.se2.collhbrs.util.Globals;
import jakarta.annotation.security.PermitAll;

@Tag(Tag.DIV)
@PermitAll
public class AccessDeniedErrorView extends Composite<VerticalLayout>
        implements HasErrorParameter<AccessDeniedException> {
    FormLayout formLayout;
    private final Button returnButton = createButton("Return to dashboard", ButtonVariant.LUMO_PRIMARY);
    @Override
    public int setErrorParameter(BeforeEnterEvent event,
                                 ErrorParameter<AccessDeniedException> parameter) {
        getContent().removeAll();

        setupLayout();
        setupFields();
        addButton();

        getContent().add(formLayout);
        getContent().setAlignSelf(FlexComponent.Alignment.CENTER, formLayout);

        return HttpStatusCode.UNAUTHORIZED.getCode();
    }

    private void setupLayout() {
        addClassName("error");
        getContent().setWidth("100%");
        formLayout = new FormLayout();
        formLayout.setMaxWidth("500px");
        formLayout.setResponsiveSteps(
                new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP)
        );
    }

    private void setupFields() {
        H1 title = new H1("Error 400:");
        H3 message = new H3("Access denied");

        formLayout.add(title, message);
        formLayout.setColspan(title, 2);
        formLayout.setColspan(message, 2);
    }

    private Button createButton(String text, ButtonVariant... variants) {
        Button button = new Button(text);
        button.addThemeVariants(variants);
        button.addClassName("button-layout");
        return button;
    }

    private void addButton() {
        returnButton.addClickListener(e -> {
            UI.getCurrent().navigate(Globals.Pages.MAIN);
        });

        formLayout.add(returnButton);
    }
}
