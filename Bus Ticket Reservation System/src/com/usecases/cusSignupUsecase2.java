package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;

public class cusSignupUsecase2 {
	public static boolean cusSignUp() {
		boolean flag = false;

		try {
			Scanner sc = new Scanner(System.in);

			System.out.println("Enter UserName");
			String username = sc.next();

			System.out.println("Enter Password");
			String password = sc.next();

			System.out.println("Enter FirstName");
			String firstName = sc.next();

			System.out.print("Enter LastName");
			String lastName = sc.next();

			sc.nextLine();
			System.out.println("Enter Address");
			String address = sc.nextLine();

			System.out.println("Enter Mobile");
			String mobile = sc.next();

			CustomerDao cdd = new CustomerDaoImpl();
			Customer customer = new Customer(username, password, firstName, lastName, address, mobile);
			String result = cdd.cusSignUp(customer);

			if (result == "signup Successfully") {
				flag = true;
				System.out.println(result);
			} else {
				System.out.println(result);
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		}
		return flag;
	}
}
