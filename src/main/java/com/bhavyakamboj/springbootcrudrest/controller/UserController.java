package com.bhavyakamboj.springbootcrudrest.controller;

import com.bhavyakamboj.springbootcrudrest.exception.ResourceNotFoundException;
import com.bhavyakamboj.springbootcrudrest.model.User;
import com.bhavyakamboj.springbootcrudrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // annotation is a combination of Spring’s @Controller and @ResponseBody annotations.
@RequestMapping("/api/v1/") // annotation declares that the url for all the apis in this controller will start with /api/v1
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users") // annotation is a short form of @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> getAllUsers(){
        System.out.println("Someone queried /users");
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}") // annotation is a short form of @RequestMapping(value="/users/{id}", method=RequestMethod.GET).
    public ResponseEntity<User> getUserByID(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException{
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new ResourceNotFoundException("User not found on :: " + userId));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users") // annotation is a short form of @RequestMapping(value="/users", method=RequestMethod.POST)
    public User createUser(@Valid @RequestBody User user){
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}") // annotation is a short form of @RequestMapping(value="/users/{id}", method=RequestMethod.PUT)
    public ResponseEntity<User> updateUser(
            @PathVariable(value = "id") Long userId,
            @Valid @RequestBody User userDetails) throws ResourceNotFoundException{
            User user = userRepository.findById(userId)
                    .orElseThrow(()-> new ResourceNotFoundException("User not found on ::"+ userId));

            user.setEmailId(userDetails.getEmailId());
            user.setLastName(userDetails.getLastName());
            user.setFirstName(userDetails.getFirstName());
            user.setUpdatedAt(new Date());
            final User updatedUser = userRepository.save(user);
            return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/users/{id}") // annotation is a short form of @RequestMapping(value="/users/{id}", method=RequestMethod.DELETE)
    public Map<String,Boolean> deleteUser(@PathVariable(value = "id") Long userId) throws Exception{
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found at ::"+userId));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
    //@PathVariable - annotation is used to bind a path variable with a method parameter
 }
