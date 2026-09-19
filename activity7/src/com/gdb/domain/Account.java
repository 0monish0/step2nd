package com.gdb.domain;
import com.gdb.exceptions.*;
import com.gdb.tests.*;

public abstract class Account {
    // ===== Constants =====
    private static final int MIN_AGE = 18;
    private static final int MIN_PIN = 1000;
    private static final int MAX_PIN = 9999;

    // ===== Fields =====
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String status;
    private Integer pin;

    // ===== Abstract Methods =====
    public abstract double getMinimumBalance();
    public abstract String getAccountType();

    // ===== Constructor =====
    public Account(int accountNumber, String name, int age, double initialBalance) throws IllegalArgumentException {
        if(age < MIN_AGE){
            throw new IllegalArgumentException("Customer must be at least " + MIN_AGE + ". Provided: " + age);
        }
        
        double minBal = getMinimumBalance();
        if(initialBalance < minBal){
            throw new IllegalArgumentException(getAccountType() + " Accounts require minimum balance of: " + minBal);
        }

        //initialize fields
        this.accountNumber = accountNumber;
        this.name = name;
        this.age = age;
        this.balance = initialBalance;
        this.status = "Active";
        this.pin = null;
    }

    // ===== Business Methods =====
    public void deposit(double amount) throws InvalidAmountException, InactiveAccountException {
        validateActive(); // This will throw an exception and stop execution if inactive
        
        if(amount <= 0){
            throw new InvalidAmountException("Amount cannot be negative or 0");
        }
        balance += amount;
    }

    public void withdraw(double amount, int pin)
        throws InvalidAmountException,
        InsufficientBalanceException,
        MinimumBalanceViolationException,
        InactiveAccountException,
        InvalidPinException {
        
        validateActive();
        
        if(!hasPin()){
            throw new InvalidPinException("Pin not set");
        }
        if(!verifyPin(pin)){
            throw new InvalidPinException("Wrong pin");
        }
        if(amount <= 0){
            throw new InvalidAmountException("Invalid amount");
        }
        if(balance < amount){
            throw new InsufficientBalanceException("Insufficient funds");
        }
        if((balance - amount) < getMinimumBalance()) {
            throw new MinimumBalanceViolationException("The amount violates the minimum balance");
        }
        
        balance -= amount;
    }

    // ===== Account Status Management =====
    public void closeAccount() throws IllegalStateException {
        if(status.equals("Inactive")){
            throw new IllegalStateException("Already Inactive");
        } else {
            status = "Inactive";
        }
    }

    public void reopenAccount() throws IllegalStateException {
        if(status.equals("Active")){
            throw new IllegalStateException("Already Active");
        } else {
            status = "Active";
        }
    }

    // ===== PIN Management =====
    public void setPin(int pin) throws IllegalArgumentException {
        String pinStr = String.valueOf(pin);
        if(pinStr.length() == 4){
            this.pin = pin;
        } else {
            throw new IllegalArgumentException("Enter a proper 4 digit pin");
        }
    }

    public boolean verifyPin(int pin) {
        return this.pin != null && this.pin == pin;
    }

    public boolean hasPin() {
        return pin != null;
    }

    // ===== Helper Methods =====
    protected void validateActive() throws InactiveAccountException {
        if(!status.equals("Active")){
            throw new InactiveAccountException("Account Inactive");
        }
    }

    //====== Getters ======
    public int getAccountNumber(){
        return accountNumber;
    }
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public double getBalance(){
        return balance;
    }
    public String getStatus(){
        return status;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAge(int age){
        this.age = age;
    }

    protected void setBalance(double newBalance){
        this.balance = newBalance;
    }
	protected void validateAmount(double amount){
		if(amount<=0){
			throw new IllegalArgumentException("Amount must be a positive number.Provided: "+amount);
		}
	}

    public static void main(String[] args){
    }
}