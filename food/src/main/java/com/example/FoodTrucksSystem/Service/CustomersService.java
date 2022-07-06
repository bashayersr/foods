package com.example.FoodTrucksSystem.Service;

import java.util.List;
import java.util.Optional;

import com.example.FoodTrucksSystem.Repository.CustomersRepository;
import com.example.FoodTrucksSystem.model.Customers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CustomersService {


    @Autowired
    private final CustomersRepository customersRepository;


    public List<Customers> getAllCustomers() {
        return customersRepository.findAll();
    }
    public Optional<Customers> getCustomerById(Long custid) {
        return customersRepository.findById(custid);
    }
    public int addNewCustomer(Customers cust) {

       if(existsByEmail(cust.getEmail())){
          return 2;
       }

        customersRepository.save(cust);
       return 1;
//
    }

    public  Optional<Customers> editCustomerById(Long custid, Customers cust) {
        if (customersRepository.existsById(custid))
        {
            Customers customers = customersRepository.getById(custid);
            if(customers!=null)
            {
                customers.setCustName(cust.getCustName());
                customers.setPassword(cust.getPassword());
                customers.setEmail(cust.getEmail());
                customers.setPhone(cust.getPhone());
                customers.setAddress(cust.getAddress());
                return Optional.of(customersRepository.save(customers));
            }
        }
        return Optional.empty();
    }
    public  boolean removeCustomer(Long custid) {
        if (customersRepository.existsById(custid)) {
            customersRepository.deleteById(custid);
            return true;
        }
        return false;
    }

    private boolean existsByEmail(String email) {
        return customersRepository.findByEmail(email) != null;
    }

}
