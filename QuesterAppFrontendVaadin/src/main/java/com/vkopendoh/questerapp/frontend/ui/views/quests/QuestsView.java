package com.vkopendoh.questerapp.frontend.ui.views.quests;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vkopendoh.questerapp.frontend.clients.UserClient;
import com.vkopendoh.questerapp.frontend.ui.MainLayout;
import com.vkopendoh.questerapp.frontend.ui.models.QuestModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Vladimir Kopendoh
 */
@Component
@Scope("prototype")
@Route(value = "", layout = MainLayout.class)
@PageTitle("Quests")
public class QuestsView extends VerticalLayout {

    private static final long serialVersionUID = -454109589077688345L;

    private final UserClient userClient;

    Grid<QuestModel> grid = new Grid<>(QuestModel.class);
    TextField filterText = new TextField();
    QuestForm form;

    public QuestsView(UserClient userClient) {
        this.userClient = userClient;
        addClassName("list-view");
        setSizeFull();
        configureGrid();

        form = new QuestForm();
        form.addListener(QuestForm.SaveEvent.class, this::saveQuest);
        form.addListener(QuestForm.DeleteEvent.class, this::deleteQuest);
        form.addListener(QuestForm.CloseEvent.class, e -> closeEditor());
        closeEditor();

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolbar(), content);
        updateList();
    }

    private void deleteQuest(QuestForm.DeleteEvent event) {
        userClient.deleteQuest(event.getQuest());
        updateList();
        closeEditor();
    }

    private void saveQuest(QuestForm.SaveEvent event) {

        //userClient.save(event.getContact());
        updateList();
        closeEditor();
    }

    private void closeEditor() {
        form.setQuest(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private HorizontalLayout getToolbar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addQuestBtn = new Button("Add quest");
        addQuestBtn.addClickListener(click -> addQuest());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addQuestBtn);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addQuest() {
        grid.asSingleSelect().clear();
        editQuest(new QuestModel());
    }

    private void updateList() {
        grid.setItems(userClient.findAllQuestsByUserId("id"));
    }

    private void configureGrid() {
        grid.addClassName("grid");
        grid.setSizeFull();
        grid.setColumns("name", "description");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(event -> editQuest(event.getValue()));
    }

    private void editQuest(QuestModel quest) {
        if (quest == null) {
            closeEditor();
        } else {
            form.setQuest(quest);
            form.setVisible(true);
            addClassName("editing");
        }
    }
}
