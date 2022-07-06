package com.example.FoodTrucksSystem.Repository;

import java.util.List;

import com.example.FoodTrucksSystem.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;


@Repository
public interface UsersRepository  extends JpaRepository<Users,Long> {


    @Query(value = "select * From Users", nativeQuery=true)
    List<Users> findAllUsers();

    @Query(value = "select * From Users u where u.Usrid = ?1", nativeQuery=true)
    Users getById(Long id);

    @Query(value = "select * From Users u where u.email = ?1", nativeQuery=true)
    Users findByEmail(String email);
}
