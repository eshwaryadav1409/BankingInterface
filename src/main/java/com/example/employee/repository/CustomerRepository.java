package com.example.employee.repository;

import com.example.employee.model.*;
import java.util.*;

public interface CustomerRepository{

    ArrayList<Customer> getCustomers();
    
    Customer getCustomerById(int id);

    String transferAmount(int id1, int id2, int transferAmount);

    String getTransactions(int id);

    Customer addCustomer(Customer customer);

    void deleteCustomer(int id);

    String changeStatus(int id, String status);

}