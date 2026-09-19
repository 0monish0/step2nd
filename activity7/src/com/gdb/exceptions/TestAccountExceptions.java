import java.util.ArrayList;

public class TestAccountExceptions {

    private static void printAccountInfo(Account acc) {
        String pinStatus = acc.hasPin() ? "Yes" : "No";

        System.out.println(
            "Account #" + acc.getAccountNumber() +
            " | " + acc.getName() +
            " (" + acc.getAge() + " yrs)" +
            " | " + acc.getAccountType() +
            " | ₹" + acc.getBalance() +
            " | " + acc.getStatus() +
            " | PIN: " + pinStatus
        );
    }

    private static void printException(Exception e) {
        System.out.println(
            "EXCEPTION: " +
            e.getClass().getSimpleName() +
            " - " +
            e.getMessage()
        );
    }

    public static void main() {
        ArrayList<Account> allAccounts = new ArrayList<>();

        System.out.println("===========================================\n \t\tTEST \n=======================================");
        
        System.out.println(">>> Test 1: Valid Account Creation");
        try {
            Account acc1 = new Account(1001, "Jhon Doe", 25, 1000, "Savings");
            allAccounts.add(acc1);
            System.out.print("SUCCESS: ");
            printAccountInfo(acc1);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 2: Invalid Age (under 18)");
        try {
            Account acc2 = new Account(1002, "Jhon Doe", 16, 1000, "Savings");
            System.out.print("SUCCESS: ");
            printAccountInfo(acc2);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 3: Invalid Account Type");
        try {
            Account acc3 = new Account(1003, "Jhon Doe", 25, 1000, "Invalid");
            System.out.print("SUCCESS: ");
            printAccountInfo(acc3);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 4: Minimum Balance on Creation");
        try {
            Account acc4 = new Account(1004, "Jhon Doe", 25, 300, "Savings");
            System.out.print("SUCCESS: ");
            printAccountInfo(acc4);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 5: Valid Deposit and Withdrawal");
        try {
            Account acc5 = new Account(1005, "Alice Brown", 30, 1000, "Savings");
            allAccounts.add(acc5);
            System.out.print("SUCCESS: ");
            printAccountInfo(acc5);

            acc5.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            
            acc5.deposit(500.0);
            System.out.println("Depositing 500.0: SUCCESS");
            System.out.println("Balance after deposit: " + acc5.getBalance());
            
            acc5.withdraw(200.0, 1234);
            System.out.println("Withdrawing 200.0: SUCCESS");
            System.out.println("Balance after withdrawal: " + acc5.getBalance());
            printAccountInfo(acc5);

        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 6: Invalid Deposit (Negative Amount)");
        System.out.println("Attempting to deposit -100.0");
        try {
            Account acc5 = allAccounts.get(1); 
            acc5.deposit(-100.0);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 7: Insufficient Balance");
        try {
            Account acc6 = new Account(1006, "Charlie Green", 35, 500.0, "Savings");
            acc6.setPin(4321);
            allAccounts.add(acc6);
            printAccountInfo(acc6);
            System.out.println("Attempting to withdraw 1000.0");
            acc6.withdraw(1000.0, 4321);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 8: Minimum Balance Violation");
        try {
            Account acc7 = new Account(1007, "Diana Prince", 28, 1000.0, "Savings");
            acc7.setPin(1111);
            allAccounts.add(acc7);
            printAccountInfo(acc7);
            System.out.println("Attempting to withdraw 600.0");
            acc7.withdraw(600.0, 1111);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 9: Inactive Account Operations");
        try {
            Account acc8 = new Account(1008, "Eve Wilson", 32, 2000.0, "Current");
            allAccounts.add(acc8);
            printAccountInfo(acc8);
            
            acc8.closeAccount();
            System.out.println("Closing account: SUCCESS");
            
            System.out.println("Attempting to deposit 100.0 on closed account");
            acc8.deposit(100.0);
        } catch (InactiveAccountException e) {
            printException(e);
            try {
                Account acc8 = allAccounts.get(allAccounts.size() - 1);
                acc8.reopenAccount();
                System.out.println("Reopening account: SUCCESS");
                acc8.deposit(100.0);
                System.out.println("Depositing 100.0 after reopen: SUCCESS");
                System.out.println("Balance after deposit: " + acc8.getBalance());
            } catch (Exception ex) {
                printException(ex);
            }
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 10: PIN Verification");
        try {
            Account acc9 = new Account(1009, "Frank Miller", 40, 1500.0, "Savings");
            allAccounts.add(acc9);
            printAccountInfo(acc9);
            
            acc9.setPin(1234);
            System.out.println("Setting PIN 1234: SUCCESS");
            
            acc9.withdraw(200.0, 1234);
            System.out.println("Withdrawing 200.0 with correct PIN: SUCCESS");
            System.out.println("Balance: " + acc9.getBalance());
            
            System.out.println("Attempting to withdraw 100.0 with incorrect PIN (9999)");
            acc9.withdraw(100.0, 9999);
        } catch (InvalidPinException e) {
            printException(e);
        } catch (Exception e) {
            printException(e);
        }

        System.out.println(">>> Test 11: All Accounts Summary");
        for (Account acc : allAccounts) {
            printAccountInfo(acc);
        }
        
        System.out.println("===========================================\n \t\tTEST COMPLETED \n=======================================");
    }
}