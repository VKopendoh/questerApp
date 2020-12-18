package com.vkopendoh.questerapp.frontend.ui.views.registration;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.binder.ValidationResult;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.router.Route;
import com.vkopendoh.questerapp.frontend.clients.UserClient;
import com.vkopendoh.questerapp.frontend.ui.models.UserCreateModel;
import com.vkopendoh.questerapp.frontend.ui.models.UserModel;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Vladimir Kopendoh
 */
//https://github.com/vaadin/vaadin-form-example/blob/master/src/main/java/org/vaadin/examples/form/data/UserDetailsService.java
@Route("register")
public class RegistrationView extends VerticalLayout {

    private static final long serialVersionUID = -2255023981926231581L;
    private PasswordField passwordField1;
    private PasswordField passwordField2;

    private UserClient userClient;
    private BeanValidationBinder<UserCreateModel> binder;

    private boolean enablePasswordValidation;

    public RegistrationView(@Autowired UserClient userClient) {
        this.userClient = userClient;

        H3 title = new H3("Signup form");

        TextField firstnameField = new TextField("First name");
        TextField lastnameField = new TextField("Last name");
        EmailField emailField = new EmailField("Email");

        passwordField1 = new PasswordField("Enter password");
        passwordField2 = new PasswordField("Password again");

        Span errorMessage = new Span();

        Button submitButton = new Button("Join the community");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        FormLayout formLayout = new FormLayout(title, firstnameField, lastnameField, emailField, passwordField1, passwordField2,
                errorMessage, submitButton);
        formLayout.setMaxWidth("500px");
        formLayout.getStyle().set("margin", "0 auto");

        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1, FormLayout.ResponsiveStep.LabelsPosition.TOP),
                new FormLayout.ResponsiveStep("490px", 2, FormLayout.ResponsiveStep.LabelsPosition.TOP));

        formLayout.setColspan(title, 2);
        formLayout.setColspan(errorMessage, 2);
        formLayout.setColspan(submitButton, 2);

        errorMessage.getStyle().set("color", "var(--lumo-error-text-color)");
        errorMessage.getStyle().set("padding", "15px 0");

        add(formLayout);

        binder = new BeanValidationBinder<UserCreateModel>(UserCreateModel.class);

        binder.forField(firstnameField).asRequired().bind("firstName");
        binder.forField(lastnameField).asRequired().bind("lastName");

        // withValidator?
        binder.forField(emailField).asRequired(new EmailValidator("Value is not a valid email address")).bind("email");

        // Another custom validator, this time for passwords
        binder.forField(passwordField1).asRequired().withValidator(this::passwordValidator).bind("password");

        passwordField2.addValueChangeListener(e -> {
            enablePasswordValidation = true;
            binder.validate();
        });

        binder.setStatusLabel(errorMessage);

        submitButton.addClickListener(e -> {
            try {
                UserCreateModel detailsBean = new UserCreateModel();

                binder.writeBean(detailsBean);

                userClient.save(detailsBean);

                showSuccess(detailsBean);

            } catch (ValidationException e1) {
                // validation errors are already visible for each field,
                // and bean-level errors are shown in the status label.

                // We could show additional messages here if we want, do logging, etc.

            }
        });

    }

    private void showSuccess(UserCreateModel createdUser) {
        Notification notification = Notification.show("Data saved, welcome " + createdUser.getEmail());
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
    }

    private ValidationResult passwordValidator(String pass1, ValueContext ctx) {

        if (pass1 == null || pass1.length() < 8) {
            return ValidationResult.error("Password should be at least 8 characters long");
        }

        if (!enablePasswordValidation) {
            enablePasswordValidation = true;
            return ValidationResult.ok();
        }

        String pass2 = passwordField2.getValue();

        if (pass1 != null && pass1.equals(pass2)) {
            return ValidationResult.ok();
        }

        return ValidationResult.error("Passwords do not match");
    }

}
