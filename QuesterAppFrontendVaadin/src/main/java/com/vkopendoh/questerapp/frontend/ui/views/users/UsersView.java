package com.vkopendoh.questerapp.frontend.ui.views.users;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.shared.ui.Transport;
import com.vkopendoh.questerapp.frontend.clients.UserClient;
import com.vkopendoh.questerapp.frontend.ui.MainLayout;
import com.vkopendoh.questerapp.frontend.ui.models.UserModel;

/**
 * @author Vladimir Kopendoh
 */
@Route(value = "", layout = MainLayout.class)
@PageTitle("Users")
public class UsersView extends VerticalLayout {
    private UserClient userClient;

    private Grid<UserModel> grid = new Grid<>(UserModel.class);
    private TextField filterText = new TextField();
    private UserForm form;

    public UsersView(UserClient userClient) {
        this.userClient = userClient;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new UserForm();
        form.addListener(UserForm.SaveEvent.class, this::saveUser);
        form.addListener(UserForm.DeleteEvent.class, this::deleteUser);
        form.addListener(UserForm.CloseEvent.class, e -> closeEditor());
        closeEditor();

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private void deleteUser(UserForm.DeleteEvent event) {
        userClient.delete(event.getContact());
        updateList();
        closeEditor();
    }

    private void saveUser(UserForm.SaveEvent event) {
        userClient.save(event.getContact());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setUser(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addContactBtn = new Button("Add user");
        addContactBtn.addClickListener(click -> addUser());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addContactBtn);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addUser() {
        grid.asSingleSelect().clear();
        editUser(new UserModel());
    }

    private void updateList() {
        grid.setItems(userClient.findAll(filterText.getValue()));
    }

    private void configureGrid() {
        grid.addClassName("user-grid");
        grid.setSizeFull();
        grid.setColumns("firstName", "lastName", "email");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editUser(event.getValue()));
    }

    private void editUser(UserModel user) {
        if (user == null) {
            closeEditor();
        } else {
            form.setUser(user);
            form.setVisible(true);
            addClassName("editing");
        }
    }
}
