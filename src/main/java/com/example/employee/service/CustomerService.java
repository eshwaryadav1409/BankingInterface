package com.example.employee.service;

import java.util.*;
import com.example.employee.model.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.example.employee.repository.*;

public class CustomerService implements CustomerRepository{

    private HashMap<Integer, Customer> customerList = new HashMap<>();
    
    public CustomerService(){
        
        customerList.put(1, new Customer(1, "Eshwar Yadav", "male", "ACTIVE", 100000, Arrays.asList(0)));
        customerList.put(2, new Customer(2, "Pavan","male", "ACTIVE", 10000, Arrays.asList(0)));
        customerList.put(3, new Customer(3, "Abhishek","male", "ACTIVE", 100000, Arrays.asList(0)));
        customerList.put(4, new Customer(4, "Shiva Prasad","male", "ACTIVE", 5000, Arrays.asList(0)));
        customerList.put(5, new Customer(5, "Vamsi","male", "INACTIVE", 1000, Arrays.asList(0)));
        customerList.put(6, new Customer(6, "Sanju", "female", "INACTIVE",200000, Arrays.asList(0)));
        customerList.put(7, new Customer(7, "Shiva", "male", "INACTIVE",16000, Arrays.asList(0)));
        customerList.put(8, new Customer(8, "Anand", "male", "ACTIVE",1500, Arrays.asList(0)));
        customerList.put(9, new Customer(9, "Sri", "male", "INACTIVE",2000, Arrays.asList(0)));
        customerList.put(10,new Customer(10, "Chinni", "female", "INACTIVE",1600, Arrays.asList(0)));
    }
    
    int customerId = 11;

    @Override
    public ArrayList<Customer> getCustomers(){
        Collection<Customer> customerCollection = customerList.values();
        ArrayList<Customer> customers = new ArrayList<>(customerCollection);
        return customers;
    }

    @Override
    public Customer getCustomerById(int id){
        Customer customer = customerList.get(id);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return customer;
    }

    @Override
    public String transferAmount(int id1, int id2, int transferAmount){

        try{
            Customer c1 = customerList.get(id1);
            Customer c2 = customerList.get(id2);

            int debitAccount = c2.getBalance();
            if ( c2.getAccountStatus().equals("ACTIVE") ) {
                if(c1.getAccountStatus().equals("ACTIVE") ){
                    if (debitAccount >= transferAmount){
                        String process="";

                        int debitAmount = c2.getBalance() - transferAmount;
                        int creditAmount = c1.getBalance() + transferAmount;

                        c1.setBalance(creditAmount);
                        c2.setBalance(debitAmount);

                        List<Integer> transfers1 = c1.getTransactions();
                        ArrayList<Integer> transfersList1 = new ArrayList<>(transfers1);
                        transfersList1.add(id2);
                        transfersList1.add(transferAmount);
                        transfersList1.add(creditAmount);
                        c1.setTransactions(transfersList1);

                        List<Integer> transfers2 = c2.getTransactions();
                        ArrayList<Integer> transfersList2 = new ArrayList<>(transfers2);
                        transfersList2.add(id1);
                        transfersList2.add((-1)*transferAmount);
                        transfersList2.add(debitAmount);
                        c2.setTransactions(transfersList2);


                        
                        process = " *** Transaction is Successful *** \rBeneficiary Account(XXXXX"+String.valueOf(id1)+") Available Balance: "+
                                    c1.getBalance() +"/-\rSender's Account(XXXXX"+String.valueOf(id2)+") Available Balance: "+
                                    c2.getBalance()+"/-";
                        return process;
                    }
                    return "Insufficient Balance in Sender's Account(XXXXX"+String.valueOf(id2)+")";
                }
                return "Beneficiary Account(XXXXX"+ String.valueOf(id1) +") is INACTIVE"; 
            }
            return "Sender's Account(XXXXX"+ String.valueOf(id2) +") is INACTIVE";

        }catch(Exception e){
            return "Account Not Found - Check your Account Number";
            //throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

    }

    @Override
    public String getTransactions(int id){
        String list="";

        Customer customer =  customerList.get(id);
        List<Integer> transfers = customer.getTransactions();

        ArrayList<Integer> transfersList = new ArrayList<>(transfers);

        int l = transfersList.size();
        int transactionCount = 1;
        String process = "";
        for (int i=1;i<l;i+=3){
            int acc = transfersList.get(i);
            int x = transfersList.get(i+1);
            int y = transfersList.get(i+2);
            if (x<0) process = " to ";
            else process = " from ";

            list += "Transaction-"+String.valueOf(transactionCount)+process+"(XXXXX"+String.valueOf(acc)+")         "+String.valueOf(x)+"/-                     "+String.valueOf(y)+"/- \r";

            transactionCount+=1;
            
        }
        list = "Transactions                       Credit/Debit                Available Balance\r"+list;
        String intro = "Name: "+customer.getCustomerName()+"                Account-Status: "+customer.getAccountStatus();
        list = intro +"\r\r"+ list;
        return list;

    }

    @Override
    public Customer addCustomer(Customer customer){
        customer.setId(customerId);
        customerList.put(customerId, customer);
        customerId +=1;

        return customer;
    }
    
    
    @Override
    public void deleteCustomer(int id){
        Customer customer = customerList.get(id);

        if (customer == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else{
            customerList.remove(id);
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public String changeStatus(int id, String status){
        Customer customer = customerList.get(id);

        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        else{
            customer.setAccountStatus(status);
            return "Account Status changed to " +status +" Successfully";
        }
    }
}