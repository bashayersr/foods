package com.example.FoodTrucksSystem.Controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.FoodTrucksSystem.DTO.API;
import com.example.FoodTrucksSystem.Service.UsersService;
import com.example.FoodTrucksSystem.model.Users;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    Logger logger=LoggerFactory.getLogger(UsersController.class);
    private final UsersService usersService;
    @Autowired




    @GetMapping
    public ResponseEntity<List<Users>> getUsers(){
        logger.info("getAllUsers =>called");
        List<Users> user=usersService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/search/{usrid}")
    public ResponseEntity<API> getUsersById(@PathVariable Long usrid){
        logger.info("getUsersById =>called");
        Optional<Users> c = usersService.getUserById(usrid);
        if (c.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Search User : "+c.toString(),200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("usrid is valid",200));
    }

    @PostMapping("/add")
    public ResponseEntity<API> addUser(@RequestBody @Valid Users myuser){
        logger.info("adduser =>called");
        int statfunc=0;
        statfunc=usersService.addNewUser(myuser);

        switch (statfunc){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("User is valid",200));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new API("Add New User ! "+myuser.toString(),200));
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Email is Exists :"+myuser.getEmail(),200));
            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new API("Error Server",200));
        }
    }



    @PutMapping("/edit/{usrid}")
    ResponseEntity<API> editUsers(@PathVariable Long usrid,@RequestBody Users usr) {
        logger.info("editUserById =>called");
        Optional<Users> result = usersService.editUserById(usrid, usr);
        if (result.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Edit User "+usrid+" for : "+result,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("usrid is valid",200));
        //return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
    }
    @DeleteMapping ("/delete/{usrid}")
    ResponseEntity<API> deleteUserById(@PathVariable Long usrid) {
        logger.info("removeUser =>called");
        if (usersService.removeUser(usrid))
            return ResponseEntity.status(HttpStatus.OK).body(new API("Delete User "+usrid,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Not Found User "+usrid,200));
    }
}
