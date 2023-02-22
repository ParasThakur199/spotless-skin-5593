package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.utility.DButil;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public String cusSignUp(String username, String password, String firstName, String lastName, String address,String mobile) {
		String res = "SignUp Failed";
		try(Connection conn = DButil.ProvideConnection()) {
			PreparedStatement ps = conn.prepareStatement("Insert Into Customer(username,password,firstName,lastName,address,mobile) values(?,?,?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, address);
			ps.setString(6, mobile);
			
			int p = ps.executeUpdate();
			if(p>0) {
				res = "Signup Successfully";
			}
		} catch (SQLException e) {
			res = e.getMessage();
		}
		
		return res;
	}
	
}
