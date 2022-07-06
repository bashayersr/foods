package com.example.FoodTrucksSystem.Service;


import java.util.List;
import java.util.Optional;

import com.example.FoodTrucksSystem.Repository.UsersRepository;
import com.example.FoodTrucksSystem.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;
    @Autowired

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }
    public Optional<Users> getUserById(Long usrid) {
        return usersRepository.findById(usrid);
    }
    public int addNewUser(Users user) {

        if(existsByEmail(user.getEmail()))
        {
            return 2;
        }
        try
        {
            usersRepository.save(user);
            return 1;
        }
        catch (Exception e) {
        }
        return 0;
    }

    public  Optional<Users> editUserById(Long usrid, Users usr) {
        if (usersRepository.existsById(usrid))
        {
            Users users = usersRepository.getById(usrid);
            if(users!=null)
            {
                users.setUsrname(usr.getUsrname());
                users.setPassword(usr.getPassword());
                users.setEmail(usr.getEmail());
                users.setIsadmin(usr.getIsadmin());

                return Optional.of(usersRepository.save(users));
            }
        }
        return Optional.empty();
    }
    public  boolean removeUser(Long usrid) {
        if (usersRepository.existsById(usrid)) {
            usersRepository.deleteById(usrid);
            return true;
        }
        return false;
    }

    private boolean existsByEmail(String email) {
        return usersRepository.findByEmail(email) != null;
    }
}
