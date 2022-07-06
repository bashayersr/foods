package com.example.FoodTrucksSystem.Controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.FoodTrucksSystem.DTO.API;
import com.example.FoodTrucksSystem.Service.OrdersService;
import com.example.FoodTrucksSystem.model.Orders;
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
@RequestMapping("/orders")
public class OrdersController {

    Logger logger=LoggerFactory.getLogger(OrdersController.class);

    private final OrdersService ordersService;
    @Autowired


    @GetMapping
    public ResponseEntity<List<Orders>> getOrders(){
        logger.info("getAllOrders =>called");
        List<Orders> ordr=ordersService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(ordr);
    }

    @GetMapping("/search/{orderid}")
    public ResponseEntity<API> getOrderById(@PathVariable Long orderid)
    {
        logger.info("getOrderById =>called");
        Optional<Orders> c = ordersService.getOrderById(orderid);
        if (c.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Search Order : "+c.toString(),200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("orderid is valid",200));
    }

    @GetMapping("/search_order_by_cust/{custid}")
    public ResponseEntity<List<Orders>> getOrderByCustomer(@PathVariable Long custid)
    {
        logger.info("getOrderByOrder =>called");
        List<Orders> c = ordersService.getOrderByCustomer(custid);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }

    @GetMapping("/search_order_by_truck_ownr/{custid}")
    public ResponseEntity<List<Orders>> getOrderByTruckOwner(@PathVariable Long trckownr)
    {
        logger.info("getOrderByOrder =>called");
        List<Orders> c = ordersService.getOrderByTruckOwner(trckownr);
        return ResponseEntity.status(HttpStatus.OK).body(c);
    }
    @PostMapping("/add")
    public ResponseEntity<API> addOrder(@RequestBody @Valid Orders myordr){
        logger.info("addNewOrder =>called");
        int statfunc=0;
        statfunc= ordersService.addNewOrder(myordr);

        switch (statfunc){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Order is valid",200));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new API("Add New Order ! "+myordr.toString(),200));

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new API("Error Server",200));
        }
    }



    @PutMapping("/edit/{orderid}")
    ResponseEntity<API> editOrder(@PathVariable Long orderid,@RequestBody Orders ordr) {
        logger.info("editOrderById =>called");
        Optional<Orders> result = ordersService.editOrderById(orderid, ordr);
        if (result.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Edit Order "+orderid+" for : "+result,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("orderid is valid",200));
    }

    @DeleteMapping("/delete/{orderid}")
    ResponseEntity<API> deleteOrderById(@PathVariable Long orderid) {
        logger.info("removeOrder =>called");
        if (ordersService.removeOrder(orderid))
            return ResponseEntity.status(HttpStatus.OK).body(new API("Delete Order "+orderid,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Not Found Order "+orderid,200));
    }

}
