package com.example.FoodTrucksSystem.Repository;


import java.util.List;

import com.example.FoodTrucksSystem.model.PaymentTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypesRepository extends JpaRepository<PaymentTypes,Long> {

    @Query(value = "select * From Payment_Types", nativeQuery=true)
    List<PaymentTypes> findAllPaymentTypes();

    @Query(value = "select * From Payment_Types u where u.payid = ?1", nativeQuery=true)
    PaymentTypes getById(Long id);

}
