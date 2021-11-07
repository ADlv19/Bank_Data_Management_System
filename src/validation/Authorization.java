package validation;

import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Authorization {

	public static int getPasswordFromDB(int userID,int parameter){
		Connection connect = null;
		int customerPassword=0;
		try {
			connect = DBUtil.getConnection("getPasswordFromDB");
			String query = "SELECT account_Password FROM Bank_Management where account_id = ?";
			PreparedStatement pstmt = connect.prepareStatement(query);
			pstmt.setInt(1,userID);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				customerPassword = rs.getInt("account_Password");
			}
			return customerPassword;

		} catch (SQLException e) {
			System.out.println("Something went wrong while fetching PASSWORD");
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeConnection(connect,"getPasswordFromDB");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return 0;
	}

	public static String getNameFromDB(int ID, int parameter){
		Connection connect = null;
		String firstName="";
		try {
			if(parameter==1){
				connect = DBUtil.getConnection("getCustomerNameFromDB");
				String query = "SELECT personfname FROM Bank_Management where account_id = ?";
				PreparedStatement pstmt = connect.prepareStatement(query);
				pstmt.setInt(1,ID);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					firstName = rs.getString("personfname");
				}
				return firstName;
			}
			else if(parameter==2){
				connect = DBUtil.getConnection("getAdminNameFromDB");
				String query = "SELECT adminName FROM AdminCredentials where adminID = ?";
				PreparedStatement pstmt = connect.prepareStatement(query);
				pstmt.setInt(1,ID);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					firstName = rs.getString("adminName");
				}
				return firstName;
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong while fetching NAME");
			e.printStackTrace();
		}finally {
			try {
				DBUtil.closeConnection(connect,"getNameFromDB");
			}catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	public static boolean checkValidUserID(int userID){
		boolean flag=true;
		if(userID<1000){
			System.out.println("Entered Invalid ID\n Try Again !!!");
			flag = false;
		}
		return flag;
	}
}
