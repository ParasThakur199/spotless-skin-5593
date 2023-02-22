package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exceptions.CustomerException;

public class cusLoginUsercase {
	public static Customer CusLogin() {
		Customer customer = null;
		try {
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter UserName");
			String userName = sc.next();

			System.out.println("Enter Password");
			String password = sc.next();

			CustomerDao cdd = new CustomerDaoImpl();
			try {
				customer = cdd.cusLogin(userName, password);
				System.out.print("Welcome" + customer.getFirstName() + customer.getLastName());
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}

		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		return customer;
	}
}
