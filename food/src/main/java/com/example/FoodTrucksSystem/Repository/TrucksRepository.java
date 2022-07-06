package com.example.FoodTrucksSystem.Repository;


import java.util.List;

import com.example.FoodTrucksSystem.model.Trucks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface TrucksRepository extends JpaRepository<Trucks,Long> {

    @Query(value = "select * From Trucks", nativeQuery=true)
    List<Trucks> findAllTrucks();

    @Query(value = "select * From Trucks u where u.truckrid = ?1", nativeQuery=true)
    Trucks getById(Long id);

}
