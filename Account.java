import java.util.*;
class Account{
    private int accountNumber;
    private String name;
    private int age;
    private double balance;
    private String accountType;
    private String status;
    private double savings_minBalance=500;
    private double current_minBalance=1000;
    private int pin;
    private boolean haspin;
    Account(int accountNumber,String name,int age, double initialBalance,String accountType){
        this.accountNumber=accountNumber;
        //age enchanced
        this.name=name;
        if(age<18){
            age=18;
        }
        this.age=age;

        //acc type enchanced
        if(accountType!="Savings" || accountType!="Current"){
            accountType="Savings";
        }
        this.accountType=accountType;
        //blaance enchanced
        if(accountType=="Savings"){
            if(initialBalance<savings_minBalance){
                initialBalance=savings_minBalance;
            }
        }
        if(accountType=="Current"){
            if(initialBalance<current_minBalance){
                initialBalance=current_minBalance;
            }
        }
        this.balance=initialBalance;

        this.status="Active";
        haspin=false;

    }
    public boolean deposit(double amt){
        if(amt<=0)
            return false;
        else{
            balance+=amt;
            return true;
            }
    }
    public boolean withdraw(double amt,int pin){
        if()
        if(balance<=amt)
            return false;
        else{
            if(accountType=="Current" && (balance-amt)<current_minBalance){
                return false;
            }
            if(accountType=="Savings" && (balance-amt)<savings_minBalance){
                return false;
            }
            balance-=amt;
            return true;
            }
    }
    public boolean closeAccount(){
        if(status=="Inactive"){
            return false;
        }
        else{
            status="Inactive";
            return true;
        }
    }
    public boolean openAccount(){
        if(status=="Active"){
            return false;
        }
        else{
            status="Active";
            return true;
        }
    }
    public boolean setPin(int pin){
        String pinStr = String.valueOf(pin);
        if(pinStr.length()==4){
            this.pin=pin;
            haspin=true;
            return true;
        }
        else{
            return false;
        }
    }
    public boolean verifyPin(int pin ){
        if(this.pin == pin){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean hasPin(){
        return haspin;
    }

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
    public void setName(String n){
        name=n;
    }
    public void setAge(int g){
        age=g;
    }
        public static void main(String[] args){
    }


}