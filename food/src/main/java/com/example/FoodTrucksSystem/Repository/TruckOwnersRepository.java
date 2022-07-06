package com.example.FoodTrucksSystem.Repository;

import java.util.List;

import com.example.FoodTrucksSystem.model.TruckOwners;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckOwnersRepository extends JpaRepository<TruckOwners,Long> {

    @Query(value = "select * From Truck_Owners", nativeQuery=true)
    List<TruckOwners> findAllTruckOwners();

    @Query(value = "select * From Truck_Owners u where u.Id = ?1", nativeQuery=true)
    TruckOwners getById(Long id);

    @Query(value = "select * From Truck_Owners u where u.email = ?1", nativeQuery=true)
    TruckOwners findByEmail(String email);

}