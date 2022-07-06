package com.example.FoodTrucksSystem.Repository;

import java.util.List;

import com.example.FoodTrucksSystem.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders,Long> {

    @Query(value = "select * From Orders", nativeQuery=true)
    List<Orders> findAllOrders();

    @Query(value = "select * From Orders u where u.orderid = ?1", nativeQuery=true)
    Orders getById(Long id);

    @Query(value = "select * From Orders where custid= ?1", nativeQuery=true)
    List<Orders> findAllOrdersByCustid(Long custid);


    @Query(value = "select * From Orders where 	truck_owner_id= ?1", nativeQuery=true)
    List<Orders> findAllOrdersByTruckOwner(Long trckownrid);
}
