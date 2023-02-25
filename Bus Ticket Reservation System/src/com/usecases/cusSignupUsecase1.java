package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.custom.ConsoleColors;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;

public class cusSignupUsecase1 {

	public static boolean cusSignup() {

		boolean flag = false;

		try {
			Scanner sc = new Scanner(System.in);
			System.out.println(ConsoleColors.ORANGE+ "Enter Username"+ConsoleColors.RESET);
			String username = sc.next();
			System.out.println(ConsoleColors.ORANGE+ "Enter Password"+ConsoleColors.RESET);
			String password = sc.next();
			System.out.println(ConsoleColors.ORANGE+ "Enter FirstName"+ConsoleColors.RESET);
			String firstName = sc.next();
			System.out.println(ConsoleColors.ORANGE+ "Enter LastName"+ConsoleColors.RESET);
			String lastName = sc.next();
			sc.nextLine();
			System.out.println(ConsoleColors.ORANGE+ "Enter Address"+ConsoleColors.RESET);
			String address = sc.nextLine();
			System.out.println(ConsoleColors.ORANGE+ "Enter Mobile"+ConsoleColors.RESET);
			String mobile = sc.next();

			CustomerDao cdao = new CustomerDaoImpl();
			String result = cdao.cusSignUp(username, password, firstName, lastName, address, mobile);
			if (result == "Signup Successfully") {
				System.out.println(ConsoleColors.LIGHT_GREEN_BACKGROUND+ result +ConsoleColors.RESET);
				flag = true;
			} else {
				System.out.println(ConsoleColors.RED_BACKGROUND+ result +ConsoleColors.RESET);
			}
		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED+"Invalid Input"+ConsoleColors.RESET);
		}
		return flag;
	}
}
