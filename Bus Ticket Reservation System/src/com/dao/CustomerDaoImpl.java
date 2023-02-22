package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Bean.Customer;
import com.exceptions.CustomerException;
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

	public String cusSignUp(Customer customer) {
		String message = "Signup Failed"; 
		try(Connection conn = DButil.ProvideConnection()) {
			PreparedStatement ps = conn.prepareStatement("Insert Into Customer(username,password,firstName,lastName,address,mobile) values(?,?,?,?,?,?)");
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setString(3, customer.getFirstName());
			ps.setString(4, customer.getLastName());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getMobile());
			
			int p = ps.executeUpdate();
			if(p>0) {
				message = "Signup Successfully";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = e.getMessage();
		}
		return message;
	}

	@Override
	public Customer cusLogin(String username, String password) throws CustomerException {
		Customer customer = null;
		Connection conn = DButil.ProvideConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from Customer where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int cusId = rs.getInt("cusId");
				String userrname = rs.getString("username");
				String passsword = rs.getString("password");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String address = rs.getString("address");
				String mobile = rs.getString("mobile");
				
				customer = new Customer(cusId,userrname,passsword,firstName,lastName,address,mobile);
			}else {
				throw new CustomerException("Invalid Username and Password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CustomerException(e.getMessage());
		}
		return customer;	
	}
}
