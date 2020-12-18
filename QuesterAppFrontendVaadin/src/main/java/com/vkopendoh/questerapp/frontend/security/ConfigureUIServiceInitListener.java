package com.vkopendoh.questerapp.frontend.security;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vkopendoh.questerapp.frontend.ui.MainLayout;
import com.vkopendoh.questerapp.frontend.ui.views.login.LoginView;
import com.vkopendoh.questerapp.frontend.ui.views.registration.RegistrationView;
import org.springframework.stereotype.Component;

/**
 * @author Vladimir Kopendoh
 */
@Component
public class ConfigureUIServiceInitListener implements VaadinServiceInitListener {
    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiEvent -> {
            final UI ui = uiEvent.getUI();
            ui.addBeforeEnterListener(this::authenticateNavigation);
        });
    }

    private void authenticateNavigation(BeforeEnterEvent event) {
      /*  if(SecurityUtils.isUserLoggedIn()){
            event.rerouteTo(MainLayout.class);
        }*/
        /* if (RegistrationView.class.equals(event.getNavigationTarget())) {
            return;
        }
        if (!LoginView.class.equals(event.getNavigationTarget())
                && !SecurityUtils.isUserLoggedIn()) {
            event.rerouteTo(LoginView.class);
        }*/
    }
}
