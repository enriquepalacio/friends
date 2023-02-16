package com.palacio.friends.controller;

import com.palacio.friends.model.Friend;
import com.palacio.friends.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    public Friend create(@RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @GetMapping("/friend")
    public Iterable<Friend> read(){
        return friendService.findAll();
    }

    @PutMapping("/friend")
    public Friend update(@RequestBody Friend friend){
        return friendService.save(friend);
    }

    @DeleteMapping("/friend/{id}")
    public void delete(@PathVariable Integer id){
        friendService.deleteById(id);
    }

    @GetMapping("/friend/{id}")
    public Optional<Friend> findById(@PathVariable Integer id){
        return friendService.findById(id);
    }

    @GetMapping("/friend/search")
    public Iterable<Friend> findByQuery(
        @RequestParam(value = "first", required = false) String firstName,
        @RequestParam(value = "last", required = false) String lastName)
    {   Iterable<Friend> result = null;
        if(firstName != null && lastName != null){
            result = friendService.findByFirstNameAndLastName(firstName, lastName);
        } else if(firstName != null){
            result = friendService.findByFirstName(firstName);
        } else if(lastName != null){
            result = friendService.findByLastName(lastName);
        } else {
            result = friendService.findAll();
        }
        return result;
    }
}
