package com.example.employee.controller;

import org.springframework.web.bind.annotation.*;
import com.example.employee.service.*;
import com.example.employee.model.*;
import java.util.*;


@RestController
public class CustomerController{

    CustomerService customerService = new CustomerService();

    @GetMapping("/customers")
    public ArrayList<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{id}")
    public Customer getCustomerById(@PathVariable("id") int id){
        return customerService.getCustomerById(id);
    }
    
    @GetMapping("/customers/transfer/{id1}/{id2}/{transferAmount}")
    public String transferAmount(@PathVariable("id1") int id1 , @PathVariable("id2") int id2 , @PathVariable("transferAmount") int transferAmount){
        return customerService.transferAmount(id1, id2, transferAmount);
    }

    @GetMapping("/customers/transactions/{id}")
    public String getTransactions(@PathVariable("id") int id){
        return customerService.getTransactions(id);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @DeleteMapping("/customers/{id}")
    public void deleteCustomer(@PathVariable("id") int id){
        customerService.deleteCustomer(id);
    }

    @GetMapping("/customers/{id}/{status}")
    public String changeStatus(@PathVariable("id") int id, @PathVariable("status") String status){
        return customerService.changeStatus(id, status);
    }
}