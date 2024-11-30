import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Bank System!");

        while (true) {
            System.out.println("\n1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            
            int choice = sc.nextInt();
            sc.nextLine();  // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter your name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter a username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter a password: ");
                    String password = sc.nextLine();
                    Customer.createAccount(name, username, password);
                    break;
                
                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = sc.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = sc.nextLine();
                    Customer customer = new Customer(loginUsername, loginPassword);
                    if (customer.login()) {
                        System.out.println("Login successful!");

                        // Account operations menu
                        while (true) {
                            System.out.println("\n1. Check Balance");
                            System.out.println("2. Deposit Money");
                            System.out.println("3. Withdraw Money");
                            System.out.println("4. Transfer Money");
                            System.out.println("5. View Transactions");
                            System.out.println("6. Deactivate Account");
                            System.out.println("7. Logout");
                            System.out.print("Choose an option: ");
                            int action = sc.nextInt();

                            switch (action) {
                                case 1:
                                    customer.checkBalance();
                                    break;
                                case 2:
                                    System.out.print("Enter amount to deposit: ");
                                    double depositAmount = sc.nextDouble();
                                    customer.deposit(depositAmount);
                                    break;
                                case 3:
                                    System.out.print("Enter amount to withdraw: ");
                                    double withdrawAmount = sc.nextDouble();
                                    customer.withdraw(withdrawAmount);
                                    break;
                                case 4:
                                    System.out.print("Enter amount to transfer: ");
                                    double transferAmount = sc.nextDouble();
                                    System.out.print("Enter target account ID: ");
                                    int targetAccountId = sc.nextInt();
                                    customer.transfer(transferAmount, targetAccountId);
                                    break;
                                case 5:
                                    customer.viewTransactionHistory();
                                    break;
                                case 6:
                                    customer.deactivateAccount();
                                    break;
                                case 7:
                                    System.out.println("Logging out...");
                                    break;
                            }

                            if (action == 7) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("Login failed. Try again.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
            }
        }
    }
}
