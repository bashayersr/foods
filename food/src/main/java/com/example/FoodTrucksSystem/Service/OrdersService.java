package com.example.FoodTrucksSystem.Service;


import java.util.List;
import java.util.Optional;

import com.example.FoodTrucksSystem.Repository.OrdersRepository;
import com.example.FoodTrucksSystem.model.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersService {


    private final OrdersRepository ordersRepository;
    @Autowired


    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }
    public Optional<Orders> getOrderById(Long orderid) {
        return ordersRepository.findById(orderid);
    }

    public List<Orders> getOrderByCustomer(Long custid) {
        return ordersRepository.findAllOrdersByCustid(custid);
    }
    public List<Orders> getOrderByTruckOwner(Long trckownrid) {
        return ordersRepository.findAllOrdersByTruckOwner(trckownrid);
    }
    public int addNewOrder(Orders ord) {

        try
        {
            ordersRepository.save(ord);
            return 1;
        }
        catch (Exception e) {
        }
        return 0;

    }

    public  Optional<Orders> editOrderById(Long orderid, Orders ord) {
        if (ordersRepository.existsById(orderid))
        {
            Orders orders = ordersRepository.getById(orderid);
            if(orders!=null)
            {
                orders.setCustomers(ord.getCustomers());
                orders.setTrucks(ord.getTrucks());
                orders.setTruckOwners(ord.getTruckOwners());
                orders.setPaymentTypes(ord.getPaymentTypes());
                orders.setOrderDate(ord.getOrderDate());
                orders.setQuantity(ord.getQuantity());
                orders.setPrice(ord.getPrice());

                return Optional.of(ordersRepository.save(orders));
            }
        }
        return Optional.empty();
    }
    public  boolean removeOrder(Long orderid) {
        if (ordersRepository.existsById(orderid)) {
            ordersRepository.deleteById(orderid);
            return true;
        }
        return false;
    }

}
