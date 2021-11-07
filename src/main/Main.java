package main;

import DAO.CustomerDAO;
import utils.DBUtil;
import validation.Authorization;

import java.util.Scanner;

public class Main {
        static Scanner sc = new Scanner(System.in);

        public static void main(String[] args) {
                System.out.println("Welcome to Banking App \n Enter 1 for Customer Login \n Enter 2 for Admin Login");
                int loginChoice=sc.nextInt();
                switch (loginChoice){
                        case 1:
                                customerLogin();
                                break;
                        case 2:
                                adminLogin();
                                break;
                }

                DBUtil.shutdownCPDS();
        }


        public static void customerLogin(){
                System.out.print("Enter userID: ");
                int userID = sc.nextInt();

                if(Authorization.checkValidUserID(userID)) {
                        System.out.print("Enter 4 Digit Password of YOUR account: ");
                        int password = sc.nextInt();
                        if(password == Authorization.getPasswordFromDB(userID,1)){
                                System.out.println("Welcome "+ Authorization.getNameFromDB(userID,1));
                                while (true){
                                        printCustomerMenu();
                                        int choice = sc.nextInt();
                                        if(choice==1){
                                                //                               CHECK BALANCE
                                                System.out.println("Your Balance is : "+ CustomerDAO.checkBalance(userID));
                                        }
                                        else if(choice==2){
                                                //                               WITHDRAW MONEY
                                                System.out.println("Enter how much you would like to withdraw : ");
                                                int amount = sc.nextInt();
                                                if(amount%100==0) {
                                                        boolean flag = CustomerDAO.withdrawMoney(userID, amount);
                                                        System.out.println("You have Successfully Withdrawn " + amount + " Rupees.");
                                                        if (flag){
                                                                CustomerDAO.transactionID(userID,amount,2);
                                                        }
                                                }
                                                else{
                                                        System.out.println("Insufficient Funds try checking your balance or Entered Invalid amount" +
                                                                " Please Enter amount in multiple of 100");
                                                }
                                        }
                                        else if(choice==3){
                                                //                                DEPOSIT MONEY
                                                System.out.println("Enter how much you would like to Deposit: ");
                                                int depositMoney = sc.nextInt();
                                                if(depositMoney%100==0){
                                                        //                                Add Money TO DB
                                                        CustomerDAO.depositMoney(userID,depositMoney);
                                                        System.out.println(depositMoney+" Rupees Successfully Deposited");
                                                        CustomerDAO.transactionID(userID,depositMoney,1);
                                                }

                                                else{
                                                        System.out.println("Unrecognized Currency, Please Enter Money and Amount" +
                                                                "which is multiple of 100....");
                                                        break;
                                                }
                                        }
                                        else if (choice==4){
                                                //                               EXIT APPLICATION
                                                System.out.println("Thank you For Visiting....\n" +
                                                        "See you soon...");
                                                break;
                                        }
                                        else if(choice==5){

                                        }
                                        else{}
                                }
                        }
                }
        }

        public static void adminLogin(){
                System.out.print("Enter Admin ID: ");
                int adminID = sc.nextInt();
                System.out.print("Enter Admin Password: ");
                int password = sc.nextInt();
                if(password== Authorization.getPasswordFromDB(adminID,2)){
                        System.out.println("Login Successfull");
                }else
                        System.out.println("Unsuccesfull Login Attempt");
        }

        public static void printCustomerMenu(){
                System.out.println("Enter 1 to Check Balance");
                System.out.println("Enter 2 to Withdraw Money");
                System.out.println("Enter 3 to Deposit Money");
                System.out.println("Enter 4 to Exit: ");
        }
        public static void printAdminMenu(){
                System.out.println("Enter 1 to Create Account");
                System.out.println("Enter 2 to Delete Account");
                System.out.println("Enter 3 to Show Account");
        }
}
