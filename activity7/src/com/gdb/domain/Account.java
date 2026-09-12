package com.gdb.domain;

import com.gdb.exceptions.*;

public abstract class Account{
	//const
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
	public Account(int accountNumber, String name, int age,double initialBalance)
	throws IllegalArgumentException {// Validate age
	if (age < MIN_AGE) {
	throw new IllegalArgumentException("Customer must be at least " + MIN_AGE + " years old. Provided: " + age);
	}
	// Validate minimum balance (delegated to subclass)
	double minBalance = getMinimumBalance();
	if (initialBalance < minBalance) {
	throw new IllegalArgumentException(	getAccountType() + " account requires minimum balance of ₹" + minBalance +". Provided: ₹" + initialBalance);
	}
	// Initialize fields
	this.accountNumber = accountNumber;
	this.name = name;
	this.age = age;
	this.balance = initialBalance;
	this.status = "Active";
	this.pin = null;
	}
	// ===== Business Methods =====
	public void deposit(double amount) throws InvalidAmountException, InactiveAccountException {
	// TODO: Check if account is active
	// TODO: Check if amount is positive
	// TODO: Add amount to balance
		if(validateActive()){
			if(amount>0){
				balance+=amount;
			}
			else{
				throw new InvalidAmountException("Amount cannot be negative or 0");
			}
		}
		else{
			throw new InactiveAccountException("Account is inactive");
		}
	}

		public void withdraw(double amount, int pin)
	throws InvalidAmountException,
	InsufficientBalanceException,
	MinimumBalanceViolationException,
	InactiveAccountException,
	InvalidPinException {
	// TODO: Check if account is active
	// TODO: Check if PIN is set
	// TODO: Verify PIN
	// TODO: Check if amount is positive
	// TODO: Check if sufficient balance
	// TODO: Check minimum balance after withdrawal
	// TODO: Deduct amount from balance

	if(validateActive()){
		if(hasPin()){
			if(verifyPin()){
				if(amount>0){
					if(balance>=amount){
						if((balance-amount)>=getMinimumBalance()) {
							balance-=amount;
						}
						else{
							throw new InsufficientBalanceException("The amount violates the minimum balance");
						}
					}
					else{
							throw new InsufficientBalanceException("The amount violates the minimum balance");
						}
				}
				else{
					throw new InvalidAmountException("Invalid amount");
				}
			}
			else{
				throw new InvalidPinException("Wrong pin");
			}
		}
		else{
				throw new InvalidPinException("Pin not set");
			}
	}
	else{
		throw new InactiveAccountException("Accound Inactive");
	}
	}

		// ===== Account Status Management =====
	public void closeAccount() throws IllegalStateException {
	// TODO: Check if already closed
	// TODO: Set status to "Inactive"
		if(status=="Inactive"){
			throw new IllegalStateException("Alread Inactive");
		}
		else{
			status="Inactive";
		}
	}

		public void reopenAccount() throws IllegalStateException {
	// TODO: Check if already active
	// TODO: Set status to "Active"
			if(status=="Active"){
			throw new IllegalStateException("Alread Active");
		}
		else{
			status="Active";
		}
	}

		// ===== PIN Management =====
	public void setPin(int pin) throws IllegalArgumentException {
	// TODO: Validate PIN (4-digit number)
	// TODO: Set pin
		String pinStr= String.valueOf(pin);
		if(pinStr.length()==4){
			this.pin=pin;
		}
		else{
			throw new IllegalArgumentException("Enter a proper 4 digit pin");
		}
	}

		public boolean verifyPin(int pin) {
	// TODO: Return true if PIN matches, false otherwise
			if(this.pin==pin){
				return true;
			}
			else{
				return false;
			}
	}

		public boolean hasPin() {
	// TODO: Return true if PIN is set
			if(pin!=null){
				return true;
			}
			else{
				return false;
			}
	}
		// ===== Helper Methods =====
	private double getMinimumBalance() {
	// TODO: Return minimum balance based on account type
		if(accountType=="Savings"){
			return MIN_BALANCE_SAVINGS;
		}
		if(accountType=="Current"){
			return MIN_BALANCE_CURRENT;
		}
	}

	private void validateActive() throws InactiveAccountException {
	// TODO: Throw InactiveAccountException if not active
		if(status!="Active"){
			throw new IllegalArgumentException("Account Inactive");
		}
	}


	//======Getters======
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
    public String getAccountType(){
        return accountType;
    }
    public String getStatus(){
        return status;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setAge(int age){
        this.age=age;
    }
	

}