package com.palacio.friends;

import com.palacio.friends.controller.FriendController;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class FriendsApplicationTests {

	@Autowired
	private FriendController friendController;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(friendController);
	}

}
