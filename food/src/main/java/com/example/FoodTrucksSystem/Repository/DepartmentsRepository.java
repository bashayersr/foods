package com.example.FoodTrucksSystem.Repository;


import java.util.List;

import com.example.FoodTrucksSystem.model.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsRepository extends JpaRepository<Departments,Long> {

    @Query(value = "select * From Departments", nativeQuery=true)
    List<Departments> findAllDepartments();

    @Query(value = "select * From Departments u where u.Deptid = ?1", nativeQuery=true)
    Departments getById(Long id);


}
