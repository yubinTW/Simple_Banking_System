package banking;

import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.util.Random;

public class Bank {

    private Connection connection;
    private Random random;

    public Bank() {
        random = new Random();
    }

    public Bank(String dbName) {
        this();
        if (dbName == null) {
            dbName = "db.s3db";
        }
        String dbURL = "jdbc:sqlite:" + dbName;
        SQLiteDataSource dataSource = new SQLiteDataSource();
        dataSource.setUrl(dbURL);
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }
        if (!checkConnection(connection) || !initDB(connection)) {
            System.out.println("db error");
        }
    }

    private boolean checkConnection(Connection connection) {
        try {
            return connection.isValid(3);
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean initDB(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS card (" +
                    "id INTEGER, " +
                    "number TEXT, " +
                    "pin TEXT, " +
                    "balance INTEGER DEFAULT 0" +
                    ")");
            return true;
        } catch (SQLException e) {
            return false;
        }

    }


    public void createAccount() {
        String cardNumber = generateCardNumber();
        String pin = generatePIN();

        System.out.println("Your card has been created\n" +
                "Your card number:\n" +
                cardNumber + "\n" +
                "Your card PIN:\n" +
                pin);

        saveIntoDB(cardNumber, pin);
    }

    private void saveIntoDB(String cardNumber, String pin) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO card (number, pin) VALUES (" +
                    cardNumber + ", " + pin + ");";
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean loginAccount(String cardNumber, String pin) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM card WHERE number = " + cardNumber +
                    " AND pin = " + pin + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void addIncome(String cardNumber, int n) {
        String sql = "UPDATE card SET balance = balance + ? WHERE number = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, n);
            preparedStatement.setString(2, cardNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getBalance(String cardNumber, String pin) {
        try {
            Statement statement = connection.createStatement();
            String sql = "SELECT * FROM card WHERE number = " + cardNumber +
                    " AND pin = " + pin + ";";
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                int balance = resultSet.getInt("balance");
                return balance;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }

    public void transferBalance(String fromNumber, String toNumber, int balance) {

        try {
            String sql = "UPDATE card SET balance = balance - ? WHERE number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, balance);
            preparedStatement.setString(2, fromNumber);
            preparedStatement.executeUpdate();
            sql = "UPDATE card SET balance = balance + ? WHERE number = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, balance);
            preparedStatement.setString(2, toNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    private String generateCardNumber() {
        String ranStr = String.format("400000%05d%04d", random.nextInt(100000), random.nextInt(10000));
        int sum = getCheckSumWithoutLastNumber(ranStr + "0");
        int lastNumber = 0;
        if (sum % 10 != 0) {
            lastNumber = (sum / 10 + 1) * 10 - sum;
        }
        return String.format("%s%01d", ranStr, lastNumber);
    }

    private int getCheckSumWithoutLastNumber(String num) {
        int[] tmp = new int[num.length() - 1];
        int sum = 0;
        for (int i = 0; i < tmp.length; i++) {
            tmp[i] = num.charAt(i) - '0';
            if (i % 2 == 0) {
                tmp[i] *= 2;
            }
            if (tmp[i] > 9) {
                tmp[i] -= 9;
            }
            sum += tmp[i];
        }
        return sum;
    }

    public boolean isValidCard(String cardNumber) {
        int csum = getCheckSumWithoutLastNumber(cardNumber);
        int lastDigit = cardNumber.charAt(cardNumber.length() - 1) - '0';
        return (csum + lastDigit) % 10 == 0;
    }

    public boolean isExistCard(String cardNumber) {
        String sql = "SELECT * FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cardNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public void deleteAccount(String cardNumber) {
        String sql = "DELETE FROM card WHERE number = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cardNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void closeBank() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    private String generatePIN() {
        String pin = String.format("%04d", random.nextInt(10000));
        return pin;
    }
}
