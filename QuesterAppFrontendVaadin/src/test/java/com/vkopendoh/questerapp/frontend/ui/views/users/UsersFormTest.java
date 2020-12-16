package com.vkopendoh.questerapp.frontend.ui.views.users;

import com.vkopendoh.questerapp.frontend.ui.models.UserModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author Vladimir Kopendoh
 */
class UsersFormTest {

    private List<UserModel> users;
    private UserModel userOne;
    private UserModel userTwo;

    @BeforeEach
    public void setupData() {
        users = new ArrayList<>();
        userOne = new UserModel();
        userOne.setFirstName("UserOne");
        userOne.setLastName("LastName1");
        userOne.setEmail("userOne@mail.com");
        userOne.setUserId("id1");
        users.add(userOne);
    }

    @Test
    public void formFieldsPopulated() {
        UserForm form = new UserForm();
        form.setUser(userOne);
        Assert.assertEquals("UserOne", form.firstName.getValue());
        Assert.assertEquals("LastName1", form.lastName.getValue());
        Assert.assertEquals("userOne@mail.com", form.email.getValue());
    }

    @Test
    public void saveEventHasCorrectValues() {
        UserForm form = new UserForm();
        UserModel user = new UserModel();
        form.setUser(user);

        form.firstName.setValue("UserOne");
        form.lastName.setValue("LastName1");
        form.email.setValue("userOne@mail.com");

        AtomicReference<UserModel> savedUserRef = new AtomicReference<>(null);
        form.addListener(UserForm.SaveEvent.class, e -> {
            savedUserRef.set(e.getContact());
        });
        form.save.click();
        UserModel savedUser = savedUserRef.get();

        Assert.assertEquals("UserOne", savedUser.getFirstName());
        Assert.assertEquals("LastName1", savedUser.getLastName());
        Assert.assertEquals("userOne@mail.com", savedUser.getEmail());
    }

}
