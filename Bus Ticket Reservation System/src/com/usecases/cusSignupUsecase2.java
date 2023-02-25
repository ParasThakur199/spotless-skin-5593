package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Bean.Customer;
import com.custom.ConsoleColors;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;

public class cusSignupUsecase2 {
	public static boolean cusSignUp() {
		boolean flag = false;

		try {
			Scanner sc = new Scanner(System.in);

			System.out.println(ConsoleColors.ORANGE+ "Enter UserName"+ConsoleColors.RESET);
			String username = sc.next();

			System.out.println(ConsoleColors.ORANGE+ "Enter Password"+ConsoleColors.RESET);
			String password = sc.next();

			System.out.println(ConsoleColors.ORANGE+ "Enter FirstName"+ConsoleColors.RESET);
			String firstName = sc.next();

			System.out.print(ConsoleColors.ORANGE+ "Enter LastName"+ConsoleColors.RESET);
			String lastName = sc.next();

			sc.nextLine();
			System.out.println(ConsoleColors.ORANGE+ "Enter Address"+ConsoleColors.RESET);
			String address = sc.nextLine();

			System.out.println(ConsoleColors.ORANGE+ "Enter Mobile"+ConsoleColors.RESET);
			String mobile = sc.next();

			CustomerDao cdd = new CustomerDaoImpl();
			Customer customer = new Customer(username, password, firstName, lastName, address, mobile);
			String result = cdd.cusSignUp(customer);

			if (result == "signup Successfully") {
				flag = true;
				System.out.println(ConsoleColors.LIGHT_GREEN_BACKGROUND+ result +ConsoleColors.RESET);
			} else {
				System.out.println(ConsoleColors.RED_BACKGROUND + result +ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED+ "Invalid Input" +ConsoleColors.RESET);
		}
		return flag;
	}
}
