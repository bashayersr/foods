package com.example.FoodTrucksSystem.Repository;


import java.util.List;

import com.example.FoodTrucksSystem.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersRepository extends JpaRepository<Customers,Long> {

    @Query(value = "select * From Customers", nativeQuery=true)
    List<Customers> findAllCustomers();

    @Query(value = "select * From Customers u where u.Custid = ?1", nativeQuery=true)
    Customers getById(Long id);

    @Query(value = "select * From Customers u where u.email = ?1", nativeQuery=true)
    Customers findByEmail(String email);
}
