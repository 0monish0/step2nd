import java.util.*;
class TestAccount{
    public void withdraw_successTest(){
        Account acc1=new Account(1001,"John Doe",25,1000.0,"Saving");

        if(acc1.withdraw(500)){
            System.out.println("SUCESSS withdrawal"+acc1abstract .getBalance());
        }
        else{
            System.out.println("Failure withdraw");
        }
    }
    public void depositsucess(){
        Account acc2 =new Account(1001,"John Doe",25,1000.0,"Saving");
        if(acc1.deposit(5000)){
            System.out.println("SUCESSS withdrawal" +acc2.getBalance());
        }
        else{
            System.out.println("Failure withdraw");
        }
    }
    public void withdrawlfailureTest(){
        Account acc3 = new TestAccount(1003,"Failer",01,34.0,"Admin");
        if(acc3.withdraw(5000)){
            System.out.println("Sucess withdrawal"+acc3.getBalance());

        }
        else{
            System.out.println("Failure withdraw");
        }}
    public void depositfailureTest(){
        Account acc4 =new Account(1004,"failer 2",25,1000.0,"Saving");
        if(acc1.deposit(-5000.0)){
            System.out.println("SUCESSS deposit" +acc3.getBalance());
        }
        else{
            System.out.println("Failure deposit");
        }
    }
    public static void main(){
        TestAccount obj = new TestAccount();
        obj.withdraw_successTest();
        obj.withdrawlfailureTest();
        obj.depositsucess();
        obj.depositfailureTest();
    }
    }