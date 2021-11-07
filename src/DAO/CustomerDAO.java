package DAO;

import main.CustomerCredentials;
import utils.DBUtil;

import java.sql.*;

public class CustomerDAO {

    public static int checkBalance(int customerID){
        Connection connect = null;
        int balance=0;
        try {

            connect = DBUtil.getConnection("checkBalance");
            String query = "SELECT account_balance FROM Account_Balance where account_id = ?";
            PreparedStatement pstmt = connect.prepareStatement(query);

            pstmt.setInt(1, customerID);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                balance=rs.getInt("account_balance");
            }
            return balance;
        } catch (SQLException e) {
            System.out.println("Something went wrong while fetching Account Balance");
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeConnection(connect,"checkBalance");
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return 0;
    }

    public static boolean depositMoney(int customerID, int depositMoney) {
        Connection connect = null;
        boolean flag = false;
        int customerBalance =0;
        try {

            connect = DBUtil.getConnection("depositMoney");
            String query = "SELECT account_balance FROM Account_Balance where account_id = ?";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.setInt(1,customerID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                customerBalance = rs.getInt(1);
            }

            customerBalance = customerBalance+depositMoney;
            query = "UPDATE Account_Balance SET account_balance = ? WHERE account_id = ?";
            pstmt = connect.prepareStatement(query);
            pstmt.setInt(1,customerBalance);
            pstmt.setInt(2,customerID);
            pstmt.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            System.out.println("Something went wrong while ");
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeConnection(connect,"depositMoney");
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return flag;
    }

    public static boolean withdrawMoney(int customerID, int amount) {
        Connection connect = null;
        boolean flag =false;
        CustomerCredentials cs = new CustomerCredentials();
        int customerBalance =0;
        try {
            cs.setUserID(customerID);
            connect = DBUtil.getConnection("withdrawMoney");
            String query = "SELECT account_balance FROM Account_Balance where account_id = ?";
            PreparedStatement pstmt = connect.prepareStatement(query);
            pstmt.setInt(1,customerID);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                customerBalance = rs.getInt(1);
            }

            if(amount < customerBalance){
                customerBalance = customerBalance-amount;
                query = "UPDATE Account_Balance SET account_balance = ? WHERE account_id = ?";
                pstmt = connect.prepareStatement(query);
                pstmt.setInt(1,customerBalance);
                pstmt.setInt(2,customerID);
                pstmt.executeUpdate();
                flag=true;
            }

        } catch (SQLException e) {
            System.out.println("Something went wrong while Withdrawing Money");
            e.printStackTrace();
        }finally {
            try {
                DBUtil.closeConnection(connect,"withdrawMoney");
            }catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return flag;
    }

    public static void transactionID(int userID, int amount, int parameter){
        Connection connection=null;

        try {
            connection = DBUtil.getConnection("transactionID");
            String query;
            if (parameter == 1) {
                query = "INSERT INTO Account_Transactions (Account_ID , tx_Type , tx_Amount , tx_timestamp ) VALUES(?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1,userID);
                pstmt.setString(2,"DEP");
                pstmt.setInt(3,amount);
                pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

                pstmt.executeUpdate();

            } else if (parameter == 2) {
                query = "INSERT INTO Account_Transactions (Account_ID , tx_Type , tx_Amount , tx_timestamp ) VALUES(?,?,?,?)";
                PreparedStatement pstmt = connection.prepareStatement(query);
                pstmt.setInt(1,userID);
                pstmt.setString(2,"WD");
                pstmt.setInt(3,amount);
                pstmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));

                pstmt.executeUpdate();

            } else
                System.out.println("Something went WRONG in updating transaction");
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.closeConnection(connection,"transactionID");
        }
    }
}
