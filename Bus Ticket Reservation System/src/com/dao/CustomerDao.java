package com.dao;

import com.Bean.Customer;
import com.exceptions.CustomerException;

public interface CustomerDao {
	public String cusSignUp(String username,String password,String firstName,String lastName,String address,String mobile);
	
	public String cusSignUp(Customer customer);
	
	public Customer cusLogin(String username,String password)throws CustomerException;
}
