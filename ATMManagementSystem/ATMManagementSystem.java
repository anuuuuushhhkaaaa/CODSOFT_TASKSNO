
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Bank Account Class
class BankAccount {
    private String accountHolder;
    private String accountNumber;
    private double balance;
    private List<String> transactions;

    public BankAccount(String accountHolder, String accountNumber, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.balance = balance;
        transactions = new ArrayList<>();
    }

    // Deposit Money
    public boolean deposit(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            return false;
        }

        balance += amount;
        transactions.add("Deposited: Rs. " + String.format("%.2f", amount));
        return true;
    }

    // Withdraw Money
    public boolean withdraw(double amount) {
        if (!Double.isFinite(amount) || amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;
        transactions.add("Withdrawn: Rs. " + String.format("%.2f", amount));
        return true;
    }

    // Check Balance
    public double checkBalance() {
        return balance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public List<String> getTransactions() {
        return transactions;
    }
}

// ATM Class
class ATM {
    private BankAccount bankAccount;

    public ATM(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    // Withdraw Operation
    public void withdraw(double amount) {
        if (amount > bankAccount.checkBalance()) {
            System.out.println("Insufficient Balance!");
        } else if (bankAccount.withdraw(amount)) {
            System.out.println("\nWithdrawal Successful!");
            System.out.printf("Amount Withdrawn: Rs. %.2f%n", amount);
        } else {
            System.out.println("\nInvalid Withdrawal Amount!");
        }
    }

    // Deposit Operation
    public void deposit(double amount) {
        if (bankAccount.deposit(amount)) {
            System.out.println("\nDeposit Successful!");
            System.out.printf("Amount Deposited: Rs. %.2f%n", amount);
        } else {
            System.out.println("\nInvalid Deposit Amount!");
        }
    }

    // Balance Operation
    public void checkBalance() {
        System.out.printf("%nYour Current Balance: Rs. %.2f%n",
                bankAccount.checkBalance());
    }

    // Transaction History
    public void showTransactionHistory() {
        System.out.println("\n========== TRANSACTION HISTORY ==========");

        List<String> history = bankAccount.getTransactions();

        if (history.isEmpty()) {
            System.out.println("No Transactions Available.");
        } else {
            for (int i = 0; i < history.size(); i++) {
                System.out.println((i + 1) + ". " + history.get(i));
            }
        }

        System.out.println("=========================================");
    }
}

// Main Class
public class ATMManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Create Bank Account
        BankAccount account = new BankAccount(
                "John Doe",
                "1234567890",
                10000
        );

        // Connect ATM with Bank Account
        ATM atm = new ATM(account);

        int choice = 0;

        System.out.println("\n======================================");
        System.out.println("          ATM MANAGEMENT SYSTEM");
        System.out.println("======================================");
        System.out.println("Welcome, " + account.getAccountHolder());
        System.out.println("Account Number: " + account.getAccountNumber());

        do {
            System.out.println("\n============= ATM MENU =============");
            System.out.println("1. Withdraw Money");
            System.out.println("2. Deposit Money");
            System.out.println("3. Check Balance");
            System.out.println("4. Transaction History");
            System.out.println("5. Exit");
            System.out.println("====================================");

            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid Input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Withdrawal Amount: Rs. ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid Amount!");
                        sc.nextLine();
                        break;
                    }

                    double withdrawAmount = sc.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;

                case 2:
                    System.out.print("Enter Deposit Amount: Rs. ");

                    if (!sc.hasNextDouble()) {
                        System.out.println("Invalid Amount!");
                        sc.nextLine();
                        break;
                    }

                    double depositAmount = sc.nextDouble();
                    atm.deposit(depositAmount);
                    break;

                case 3:
                    atm.checkBalance();
                    break;

                case 4:
                    atm.showTransactionHistory();
                    break;

                case 5:
                    System.out.println("\nThank You for Using Our ATM!");
                    System.out.println("Have a Nice Day!");
                    break;

                default:
                    System.out.println("Invalid Choice! Please Try Again.");
            }

        } while (choice != 5);

        sc.close();
    }
}
