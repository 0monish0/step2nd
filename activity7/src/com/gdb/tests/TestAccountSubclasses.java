package com.gdb.tests;

import java.util.ArrayList;

import com.gdb.domain.Account;
import com.gdb.domain.CurrentAccount;
import com.gdb.domain.SavingsAccount;

public class TestAccountSubclasses {
    private static void printAccountInfo(Account acc) {
        String pinStatus = acc.hasPin() ? "Yes" : "No";
        System.out.println("Account #" + acc.getAccountNumber() + " | " + acc.getName()
            + " (" + acc.getAge() + " yrs) | "
            + acc.getAccountType() + " | "
            + acc.getBalance() + " | "
            + acc.getStatus() + " | PIN: "
            + pinStatus
        );
    }

    private static void printException(Exception e) {
        System.out.println("EXCEPTION: " + e.getMessage());
    }

    private static void printSavingsDetails(SavingsAccount acc) {
        System.out.println("Savings Account - Type: " + acc.getAccountType()
            + ", Minimum Balance: " + acc.getMinimumBalance()
        );
        System.out.println("Interest Rate: " + acc.getInterestRate() + "% per annum");
    }

    private static void printCurrentDetails(CurrentAccount acc) {
        System.out.println("Current Account - Type: " + acc.getAccountType()
            + ", Minimum Balance: " + acc.getMinimumBalance()
        );
        System.out.println("Overdraft Limit: " + acc.getOverdraftLimit());
        System.out.println("Available Overdraft: " + acc.getAvailableOverdraft());
        System.out.println("Overdraft Used: " + acc.getOverdraftUsed());
        System.out.println("Is Using Overdraft: " + acc.isUsingOverdraft());
    }

    public static void test1(ArrayList<Account> allAccounts) {
        System.out.println(">>> Test 1: Creating Accounts");
        try {
            Account acc1 = new SavingsAccount(1001, "John Doe", 25, 1000);
            allAccounts.add(acc1);
            System.out.print("Savings Account: ");
            printAccountInfo(acc1);
            
            Account acc2 = new CurrentAccount(1002, "Jane Smith", 30, 2000);
            allAccounts.add(acc2);
            System.out.print("Current Account: ");
            printAccountInfo(acc2);
        } catch (Exception e) {
            printException(e);
        }
    }

    public static void test2(SavingsAccount acc3, CurrentAccount acc4) {
        System.out.println(">>> Test 2: Account Type and Minimum Balance");
        printSavingsDetails(acc3);
        printCurrentDetails(acc4);
    }

    public static void test3(SavingsAccount acc) {
        System.out.println(">>> Test 3: Savings Account - Interest Calculation");
        System.out.print("Savings Account: ");
        printAccountInfo(acc);
        System.out.println("Interest Rate: " + acc.getInterestRate() + "% per annum");
        System.out.println("Interest for 1 year: " + acc.calculateInterest(1));
        System.out.println("Interest for 2 years: " + acc.calculateInterest(2));
        System.out.println("Interest for 5 years: " + acc.calculateInterest(5));
        
        double bal = acc.getBalance() + acc.calculateInterest(2);
        System.out.println("After 2 years with interest: Balance would be " + bal);
    }

    public static void test4(CurrentAccount acc) {
        System.out.println(">>> Test 4: Current Account - Overdraft Feature");
        
        try {
            acc.setPin(1234); 
            System.out.print("Current Account: ");
            printAccountInfo(acc);
            System.out.println("Overdraft Limit: " + acc.getOverdraftLimit());
            System.out.println("Available Overdraft: " + acc.getAvailableOverdraft());
            System.out.println("Overdraft Used: " + acc.getOverdraftUsed());
            System.out.println("Is Using Overdraft: " + acc.isUsingOverdraft());

            System.out.println("Withdrawing 1500.0 (goes below minimum balance of 1000)");
            System.out.println("Balance before: " + acc.getBalance());
            acc.withdraw(1500.0, 1234);
            System.out.println("Withdrawing: 1500.0 - SUCCESS");
            System.out.println("Balance after: " + acc.getBalance());
            System.out.println("Overdraft Used: " + acc.getOverdraftUsed());
            System.out.println("Available Overdraft: " + acc.getAvailableOverdraft());
            System.out.println("Is Using Overdraft: " + acc.isUsingOverdraft());
        } catch (Exception e) {
            printException(e);
        }

        System.out.println("Attempting to withdraw 4000.0 (would exceed overdraft)");
        double totalFunds = acc.getBalance() + acc.getAvailableOverdraft();
        System.out.println("Available funds: " + acc.getBalance() + " + " + acc.getAvailableOverdraft() + " = " + totalFunds);
        try {
            acc.withdraw(4000.0, 1234);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println("Repaying overdraft of 500.0");
        System.out.println("Balance before repayment: " + acc.getBalance());
        System.out.println("Overdraft Used before: " + acc.getOverdraftUsed());
        try {
            acc.repayOverdraft(500.0);
            System.out.println("Repaying 500.0 - SUCCESS");
            System.out.println("Balance after repayment: " + acc.getBalance());
            System.out.println("Overdraft Used after: " + acc.getOverdraftUsed());
            System.out.println("Is Using Overdraft: " + acc.isUsingOverdraft());
        } catch (Exception e) {
            printException(e);
        }
    }

    public static void test5(ArrayList<Account> allAccounts) {
        System.out.println(">>> Test 5: Polymorphism - Treating Accounts Uniformly");
        System.out.println("Processing accounts polymorphically:");
        
        try {
            allAccounts.add(new SavingsAccount(1003, "Bob Wilson", 35, 500.0));
            allAccounts.add(new CurrentAccount(1004, "Alice Brown", 28, 1500.0));
        } catch (Exception e) {
            printException(e);
        }

        double total = 0.0;
        for (Account acc : allAccounts) {
            System.out.println("Account #" + acc.getAccountNumber() + " | " + acc.getName() 
                + " (" + acc.getAge() + " yrs) | " + acc.getAccountType() + " | " + acc.getBalance() 
                + " | " + acc.getStatus() + " | Type: " + acc.getAccountType() + ", Min Balance: " + acc.getMinimumBalance());
            total += acc.getBalance();
        }
        System.out.println("Total accounts: " + allAccounts.size());
        System.out.println("Total balance across all accounts: " + total);
    }

    public static void test6() {
        System.out.println(">>> Test 6: Validation - Invalid Creation Attempts");
        
        System.out.println("Attempting to create SavingsAccount with 300 (below minimum)");
        try {
            Account acc1 = new SavingsAccount(1001, "John Doe", 25, 300);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println("Attempting to create CurrentAccount with 500 (below minimum)");
        try {
            Account acc2 = new CurrentAccount(1002, "Jane Smith", 30, 500);
        } catch (Exception e) {
            printException(e);
        }
        
        System.out.println("Attempting to create SavingsAccount with age 16");
        try {
            Account acc3 = new SavingsAccount(1001, "Young Boy", 16, 1000);
        } catch (Exception e) {
            printException(e);
        }
    }

    public static void test7(ArrayList<Account> allAccounts) {
        System.out.println(">>> Test 7: Savings Account - PIN and Operations");
        try {
            SavingsAccount acc = new SavingsAccount(1005, "Charlie Green", 40, 2000.0);
            allAccounts.add(acc);
            
            System.out.print("Savings Account: ");
            printAccountInfo(acc);
            
            acc.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            
            acc.deposit(500.0);
            System.out.println("Depositing 500.0: SUCCESS");
            System.out.println("Balance after deposit: " + acc.getBalance());
            
            acc.withdraw(300.0, 1234);
            System.out.println("Withdrawing 300.0 with correct PIN: SUCCESS");
            System.out.println("Balance after withdrawal: " + acc.getBalance());
            
            System.out.println("Attempting to withdraw 2000.0 (would violate minimum balance)");
            acc.withdraw(2000.0, 1234);
        } catch (Exception e) {
            printException(e);
        }
    }

    public static void test8(ArrayList<Account> allAccounts) {
        System.out.println(">>> Test 8: Current Account - Active Status Operations");
        try {
            CurrentAccount acc = new CurrentAccount(1006, "Diana Prince", 35, 3000.0);
            allAccounts.add(acc);
            
            System.out.print("Current Account: ");
            printAccountInfo(acc);
            
            acc.closeAccount();
            System.out.println("Closing account: SUCCESS");
            
            System.out.println("Attempting to deposit 100.0 on closed account");
            acc.deposit(100.0);
        } catch (Exception e) {
            printException(e);
        }
        
        try {
            CurrentAccount acc = (CurrentAccount) allAccounts.get(allAccounts.size() - 1);
            acc.reopenAccount();
            System.out.println("Reopening account: SUCCESS");
            
            acc.deposit(100.0);
            System.out.println("Depositing 100.0 after reopen: SUCCESS");
            System.out.println("Balance after deposit: " + acc.getBalance());
        } catch (Exception e) {
            printException(e);
        }
    }

    public static void test9(ArrayList<Account> allAccounts) {
        System.out.println(">>> Test 9: All Accounts Summary");
        for (Account acc : allAccounts) {
            printAccountInfo(acc);
        }
        System.out.println("============================================================\n" +
                           "TEST COMPLETED!\n" +
                           "============================================================");
    }

    public static void main(String[] args) {
        System.out.println("============================================================\n" + 
                           "ACCOUNT SUBCLASSES TEST (SAVINGS & CURRENT)\n" + 
                           "============================================================");
                           
        ArrayList<Account> allAccounts = new ArrayList<>();
        
        test1(allAccounts);
        test2((SavingsAccount)allAccounts.get(0), (CurrentAccount)allAccounts.get(1));
        test3((SavingsAccount)allAccounts.get(0));
        test4((CurrentAccount)allAccounts.get(1));
        test5(allAccounts);
        test6();
        test7(allAccounts);
        test8(allAccounts);
        test9(allAccounts);
    }
}