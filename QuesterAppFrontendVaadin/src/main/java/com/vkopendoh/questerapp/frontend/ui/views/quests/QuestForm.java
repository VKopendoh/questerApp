package com.vkopendoh.questerapp.frontend.ui.views.quests;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;
import com.vkopendoh.questerapp.frontend.ui.models.QuestModel;

/**
 * @author Vladimir Kopendoh
 */
public class QuestForm extends FormLayout {
    private QuestModel quest;

    TextField name = new TextField("Name");
    TextField description = new TextField("Description");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<QuestModel> binder = new BeanValidationBinder<>(QuestModel.class);

    public QuestForm() {
        addClassName("form");

        // bindInstanceFields matches fields in Contact and ContactForm based on their names.
        binder.bindInstanceFields(this);
        add(name,
                description,
                createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(event -> validateAndSave());
        delete.addClickListener(event -> fireEvent(new DeleteEvent(this, quest)));
        close.addClickListener(event -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(e -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {
        try {
            binder.writeBean(quest);
            fireEvent(new SaveEvent(this, quest));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    public void setQuest(QuestModel quest) {
        this.quest = quest;
        binder.readBean(quest);
    }

    // Events
    public static abstract class QuestFormEvent extends ComponentEvent<QuestForm> {
        private QuestModel quest;

        protected QuestFormEvent(QuestForm source, QuestModel quest) {
            super(source, false);
            this.quest = quest;
        }

        public QuestModel getQuest() {
            return quest;
        }
    }

    public static class SaveEvent extends QuestFormEvent {
        SaveEvent(QuestForm source, QuestModel quest) {
            super(source, quest);
        }
    }

    public static class DeleteEvent extends QuestFormEvent {
        DeleteEvent(QuestForm source, QuestModel user) {
            super(source, user);
        }

    }

    public static class CloseEvent extends QuestFormEvent {
        CloseEvent(QuestForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
