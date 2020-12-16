package com.vkopendoh.questerapp.frontend.ui.views.users;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vkopendoh.questerapp.frontend.clients.UserClient;
import com.vkopendoh.questerapp.frontend.ui.models.UserModel;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author Vladimir Kopendoh
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class UsersViewTest {

    private UsersView usersView;
    @Mock
    private UserClient userClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        List<UserModel> users = new ArrayList<>();
        UserModel userOne = new UserModel();
        userOne.setFirstName("UserOne");
        userOne.setLastName("LastName1");
        userOne.setEmail("userOne@mail.com");
        userOne.setUserId("id1");

        UserModel userTwo = new UserModel();
        userTwo.setFirstName("UserTwo");
        userTwo.setLastName("LastName2");
        userTwo.setEmail("userTwo@mail.com");
        userTwo.setUserId("id1");

        users.add(userOne);
        users.add(userTwo);
        BDDMockito.given(userClient.findAll(any())).willReturn(users);
        this.usersView = new UsersView(userClient);
    }

    @Test
    public void formShownWhenContactSelected() {
        Grid<UserModel> grid = usersView.grid;
        UserModel user = getFirstItem(grid);

        UserForm form = usersView.form;

        Assert.assertFalse(form.isVisible());
        grid.asSingleSelect().setValue(user);
        Assert.assertTrue(form.isVisible());
        Assert.assertEquals(user.getFirstName(), form.firstName.getValue());
    }

    private UserModel getFirstItem(Grid<UserModel> grid) {
        ListDataProvider<UserModel> provider = (ListDataProvider<UserModel>) grid.getDataProvider();
        return provider.getItems().iterator().next();
    }
}


