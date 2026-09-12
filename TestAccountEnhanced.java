improt java.util.*;
class TestAccountEnchanced{
	private static void printAccountInfo(AccountEnhanced acc) {
		System.out.println("\nAccount #"+acc.getAccountNumber() +" | "+acc.getName()+" ("+acc.getAge()+") | "+acc.getAccountType()+ " | $"+ acc.getBalance()+ " | " acc.getStatus() +" | PIN:"+(acc.hasPin()==true?"YES":"NO")+"\n");
    }
    public static void valid_acc_creation(){
    	Account acc1 =new Account(1001,"John Doe",25,1000.0,"Saving");
    	printAccountInfo(acc1);
    }
    public static void invalidAge(){
    	System.out.println("creating account with age 16");
    	Account acc2 =new Account(1002,"Jhon kurik",16,1000.0,"Saving");
    	System.out.println("age auto correcting to 18");
    	printAccountInfo(acc2);
    }
    public static void invalidAccType(){
    	Account acc2 =new Account(1003,"Mallu kurik",16,1000.0,"invalidAge");
    	System.out.println("Creating account with type \"Invalid\"");
    	System.out.println("Account type defaulted to: Savings");
    	printAccountInfo(acc3);
    }
}