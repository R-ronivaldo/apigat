package com.example.apiexample.controller;

import java.util.List;
import java.util.Optional;

import com.example.apiexample.model.User;
import com.example.apiexample.respository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id){
        Optional<User> userFind = this.userRepository.findById(id);

        if (userFind.isPresent()){
            return userFind.get();
        }

        return null;
    }

    @PostMapping("/login")
	public String getAtivosByEmail(@RequestBody User userRequest) {
		List<User> users = this.userRepository.findByEmail(userRequest.getEmail());

        User userFind = users.get(0);

        System.out.println(userFind.getPassword());
        System.out.println(userRequest.getPassword());
        
        // TODO - Descobrir pq essa condicional não está funcionando
        if (userFind.getPassword().equals(userRequest.getPassword())) {
            // Token fake   
            return "token180320181122334455";
        }
        return null;
	}

    @PostMapping("/user")
    public User addUser(@RequestBody User user){
        return this.userRepository.save(user);
    }
}
