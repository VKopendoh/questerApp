package com.vkopendoh.questerapp.frontend.ui.views.login;

import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vkopendoh.questerapp.frontend.clients.UserClient;
import com.vkopendoh.questerapp.frontend.ui.models.UserLoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


/**
 * @author Vladimir Kopendoh
 */
@Component
@Scope("prototype")
@Route("login")
@PageTitle("Login | Quester")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {

    private final UserClient userClient;
    private LoginForm login = new LoginForm();

    @Autowired
    public LoginView(UserClient userClient) {
        this.userClient = userClient;
        addClassName("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        Anchor register = new Anchor("register", "Sign up");

        login.setAction("login");
        login.addLoginListener((ComponentEventListener<AbstractLogin.LoginEvent>) event -> {
            UserLoginModel creds = new UserLoginModel();
            creds.setEmail(event.getUsername());
            creds.setPassword(event.getPassword());
            HttpHeaders headers = userClient.login(creds);
            String token = headers.getFirst("token");
            String userId = headers.getFirst("userId");
            if (token != null)
                VaadinSession.getCurrent().setAttribute("token", token);

        });
        login.addLoginListener((ComponentEventListener<AbstractLogin.LoginEvent>) event -> {

        });

        add(new H1("Quester"), login, register);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {

        if (beforeEnterEvent.getLocation()
                .getQueryParameters()
                .getParameters()
                .containsKey("error")) {
            login.setError(true);
        }
    }


}
