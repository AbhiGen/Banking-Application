
# Banking System Application

A simple banking system that allows customers to create accounts, check balances, deposit, withdraw, transfer money, view transaction history, and deactivate accounts. This system uses **MySQL** as the database and **Java** for the backend. The project demonstrates basic CRUD operations for a banking application.

## Features

- **Account Creation**: Users can create a new account by providing their name, username, and password.
- **Login**: Users can log in using their username and password to access their banking operations.
- **Check Balance**: Users can view their current account balance.
- **Deposit Money**: Users can deposit money into their account.
- **Withdraw Money**: Users can withdraw money from their account, provided they have sufficient balance.
- **Transfer Money**: Users can transfer money to another account, ensuring that the target account exists and the user has sufficient funds.
- **View Transaction History**: Users can view a list of their past transactions, including deposits, withdrawals, and transfers.
- **Deactivate Account**: Users can deactivate their account when no longer needed.
- **MySQL Database**: All account details, transaction records, and balances are stored in a MySQL database.

## Database Setup

### Steps to Set Up MySQL Database

1. **Install MySQL**: If you don’t have MySQL installed, download it from [MySQL official website](https://dev.mysql.com/downloads/installer/).

2. **Create Database and Tables**:
   After installing MySQL, open a MySQL client (e.g., MySQL Workbench or the command line) and create a new database with the following SQL queries:
   
   ```sql
   -- Create the BankSystem database
   CREATE DATABASE BankSystem;
   USE BankSystem;
   
   -- Create the customers table
   CREATE TABLE customers (
       account_id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100),
       username VARCHAR(50) UNIQUE NOT NULL,
       password VARCHAR(50) NOT NULL,
       balance DECIMAL(10, 2) DEFAULT 0.00,
       status ENUM('active', 'inactive') DEFAULT 'active'
   );
   
   -- Create the transactions table
   CREATE TABLE transactions (
       transaction_id INT AUTO_INCREMENT PRIMARY KEY,
       account_id INT,
       transaction_type ENUM('deposit', 'withdrawal', 'transfer') NOT NULL,
       amount DECIMAL(10, 2) NOT NULL,
       transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (account_id) REFERENCES customers(account_id)
   );
   ```

3. **Insert Sample Data**: You can populate the database with some initial data using the following queries (as previously provided):

   ```sql
   -- Inserting customer accounts
   INSERT INTO customers (name, username, password, balance, status)
   VALUES
   ('John Doe', 'johndoe', 'password123', 5000.00, 'active'),
   ('Jane Smith', 'janesmith', 'mypassword', 1500.00, 'active'),
   ('Sam Brown', 'sambrown', 'securepass', 3000.00, 'active'),
   ('Emily White', 'emilywhite', 'password321', 7000.00, 'inactive');
   
   -- Inserting sample transactions
   INSERT INTO transactions (account_id, transaction_type, amount)
   VALUES
   (1, 'deposit', 2000.00),
   (1, 'withdrawal', 500.00),
   (1, 'transfer', 1000.00);
   ```

## Connecting MySQL Database to Java in Eclipse

### Step 1: Install MySQL JDBC Driver

1. Download the MySQL JDBC driver from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/).
2. After downloading, extract the `.zip` file and locate the `mysql-connector-java-X.X.X.jar` file.

### Step 2: Add JDBC Driver to Eclipse Project

1. Open **Eclipse IDE**.
2. Right-click on your project in the **Project Explorer** and select **Properties**.
3. In the **Properties** window, click on **Java Build Path** and then go to the **Libraries** tab.
4. Click **Add External JARs** and browse to select the `mysql-connector-java-X.X.X.jar` file you downloaded.
5. Click **Apply and Close** to save changes.

### Step 3: Database Connection Code in Java

In your Java project, you can use the following code to connect to the database:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/BankSystem";
    private static final String USER = "root";  // Replace with your MySQL username
    private static final String PASSWORD = "password";  // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        return null;
    }
}
```

### Step 4: Test the Database Connection

You can test the connection by calling `DBConnection.getConnection()` within your application’s `main` method or other classes. If the connection is successful, the program will proceed; otherwise, it will throw an exception with an error message.

---

## How to Run the Application on Your Device

### Step 1: Set Up Your Development Environment

1. **Install Eclipse IDE**: If you don’t have Eclipse installed, download it from [Eclipse Downloads](https://www.eclipse.org/downloads/).
2. **Install MySQL**: Follow the steps mentioned earlier to install and set up MySQL on your local machine.
3. **Install MySQL Connector/J**: Download and add the MySQL JDBC driver to your project (as detailed above).

### Step 2: Clone the Repository

1. Clone this project from GitHub to your local machine using the following command:
   ```bash
   git clone https://github.com/yourusername/BankingSystem.git
   ```

### Step 3: Set Up the MySQL Database

1. Follow the steps outlined in the **Database Setup** section to create the database and insert sample data.

### Step 4: Import the Project in Eclipse

1. Open Eclipse.
2. Go to **File > Import**.
3. Select **Existing Projects into Workspace**.
4. Browse to the cloned project folder and select it.
5. Click **Finish**.

### Step 5: Run the Application

1. Right-click on the `BankingSystem.java` file in Eclipse.
2. Select **Run As > Java Application**.
3. The application will start in the console where you can interact with it through the menu-driven options.

---

## Notes

- Ensure that MySQL is running on your machine when you try to connect the application to the database.
- If you encounter any issues with the database connection (e.g., incorrect credentials or database not found), double-check your MySQL configuration and ensure that the username, password, and database name are correctly set in the `DBConnection` class.
- This banking system is a basic console application for demonstration purposes. For a production-level application, you may want to implement additional security features such as password hashing and encryption, error handling, and a more robust user interface.

---
