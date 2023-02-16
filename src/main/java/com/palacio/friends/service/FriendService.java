package com.palacio.friends.service;

import com.palacio.friends.model.Friend;
import org.springframework.data.repository.CrudRepository;

public interface FriendService extends CrudRepository<Friend, Integer> {

    public  Iterable<Friend> findByFirstNameAndLastName(String firstName, String lastName);

    public  Iterable<Friend> findByFirstName(String firstName);

    public  Iterable<Friend> findByLastName(String lastName);

}
