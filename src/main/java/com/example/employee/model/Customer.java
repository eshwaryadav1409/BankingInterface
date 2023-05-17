
package com.example.employee.model;

import java.util.*;

public class Customer{
    private int id;
    private String customerName;
    private String gender;
    private String accountStatus;
    private int availableBalance;
    private List<Integer> transactions;

    public Customer(int id, String customerName, String gender, String accountStatus ,int availableBalance, List<Integer> transactions){
        this.id = id;
        this.customerName = customerName;
        this.gender = gender;
        this.accountStatus = accountStatus;
        this.availableBalance = availableBalance;
        this.transactions = transactions;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }

    public String getCustomerName(){
        return customerName;
    }

    public void setGender(String gender){
        this.gender = gender;
    }

    public String getGender(){
        return gender;
    }

    public void setAccountStatus(String accountStatus){
        this.accountStatus = accountStatus;
    }

    public String getAccountStatus(){
        return accountStatus;
    }

    public void setBalance(int availableBalance){
        this.availableBalance = availableBalance;
    }

    public int getBalance(){
        return availableBalance;
    }

    public void setTransactions(List<Integer> transactions){
        this.transactions = transactions;
    }

    public List<Integer> getTransactions(){
        return transactions;
    }
}