package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;

public class cusSignupUsecase1 {

	public static boolean cusSignup() {

		boolean flag = false;

		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter Username");
			String username = sc.next();
			System.out.println("Enter Password");
			String password = sc.next();
			System.out.println("Enter FirstName");
			String firstName = sc.next();
			System.out.println("Enter LastName");
			String lastName = sc.next();
			sc.nextLine();
			System.out.println("Enter Address");
			String address = sc.nextLine();
			System.out.println("Enter Mobile");
			String mobile = sc.next();

			CustomerDao cdao = new CustomerDaoImpl();
			String result = cdao.cusSignUp(username, password, firstName, lastName, address, mobile);
			if (result == "Signup Successfully") {
				System.out.println(result);
				flag = true;
			} else {
				System.out.println(result);
			}
		} catch (InputMismatchException e) {
			System.out.println("Invalid Input");
		}
		return flag;
	}
}
