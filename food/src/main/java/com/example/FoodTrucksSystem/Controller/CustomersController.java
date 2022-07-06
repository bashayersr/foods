package com.example.FoodTrucksSystem.Controller;


import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.example.FoodTrucksSystem.DTO.API;
import com.example.FoodTrucksSystem.Service.CustomersService;
import com.example.FoodTrucksSystem.model.Customers;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomersController {

    Logger logger=LoggerFactory.getLogger(CustomersController.class);
    @Autowired
    private final CustomersService customersService;




    @GetMapping
    public ResponseEntity<List<Customers>> getCustomers(){
        logger.info("getAllCustomers =>called");
        List<Customers> cust=customersService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(cust);
    }

    @GetMapping("/search/{custid}")
    public ResponseEntity<API> getCustomersById(@PathVariable Long custid)
    {
        logger.info("getCustomersById =>called");
        Optional<Customers> c = customersService.getCustomerById(custid);
        if (c.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Search Customer : "+c.toString(),200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("custid is valid",200));
    }

    @PostMapping("/add")
    public ResponseEntity<API> addCustomer(@RequestBody @Valid Customers mycust){
//        if (errors.hasErrors()){
//            return ResponseEntity.status(400).body(new API(errors.getFieldError().getDefaultMessage(),400));
//        }
        logger.info("addNewCustomer =>called");
        int statfunc= customersService.addNewCustomer(mycust);

        switch (statfunc){
            case 0:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Customer is valid",200));
            case 1:
                return ResponseEntity.status(HttpStatus.OK).body(new API("Add New Customer ! "+mycust.toString(),200));
            case 2:
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Email is Exists :"+mycust.getEmail(),200));

            default:
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new API("Error Server",200));
        }
    }



    @PutMapping("/edit/{custid}")
    ResponseEntity<API> editCustomer(@PathVariable Long custid,@RequestBody Customers cust) {
        logger.info("editCustomerById =>called");
        Optional<Customers> result = customersService.editCustomerById(custid, cust);
        if (result.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(new API("Edit Customer "+custid+" for : "+result,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("custid is valid",200));
    }

    @DeleteMapping("/delete/{custid}")
    ResponseEntity<API> deleteCustomerById(@PathVariable Long custid) {
        logger.info("removeCustomer =>called");
        if (customersService.removeCustomer(custid))
            return ResponseEntity.status(HttpStatus.OK).body(new API("Delete Customer "+custid,200));
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new API("Not Found Customer "+custid,200));
    }


}