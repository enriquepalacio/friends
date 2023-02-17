package com.palacio.friends.controller;

import com.palacio.friends.model.Friend;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IntegrationTests {
    @Autowired
    FriendController friendController;

    @Test
    public void testCreateReadDelete(){
        Friend friend = new Friend("Gordon", "Moore");

        Friend friendResult = friendController.create(friend);

        Iterable<Friend> friends = friendController.read();
        Assertions.assertThat(friends).filteredOn("firstName", "Gordon").isNotEmpty();

        friendController.delete(friendResult.getId());

        Assertions.assertThat(friendController.read()).filteredOn("firstName", "Gordon").isEmpty();
    }
}
