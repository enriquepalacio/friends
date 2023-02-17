package com.palacio.friends.controller;

import com.palacio.friends.model.Friend;
import com.palacio.friends.service.FriendService;
import com.palacio.friends.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class FriendController {

    @Autowired
    FriendService friendService;

    @PostMapping("/friend")
    public Friend create(@Valid @RequestBody Friend friend) {
        return friendService.save(friend);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError ->
                new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }

    @GetMapping("/friend")
    public Iterable<Friend> read(){
        return friendService.findAll();
    }

    @PutMapping("/friend")
    public ResponseEntity<Friend> update(@RequestBody Friend friend){
        ResponseEntity<Friend> result;
        if(friendService.findById(friend.getId()).isPresent()){
            result = new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        } else {
            result = new ResponseEntity(friend, HttpStatus.BAD_REQUEST);
        }
        return result;
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
