package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Bean.Customer;
import com.custom.ConsoleColors;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exceptions.CustomerException;


public class cusLoginUsercase {
	public static Customer CusLogin() {
		Customer customer = null;
		try {
			Scanner sc = new Scanner(System.in);

			System.out.println(ConsoleColors.ORANGE+"Enter UserName"+ConsoleColors.RESET);
			String userName = sc.next();

			System.out.println(ConsoleColors.ORANGE+"Enter Password"+ConsoleColors.RESET);
			String password = sc.next();

			CustomerDao cdd = new CustomerDaoImpl();
			try {
				customer = cdd.cusLogin(userName, password);
				System.out.print(ConsoleColors.ROSY_PINK + "Welcome " + customer.getFirstName() +" "+ customer.getLastName()+ ConsoleColors.RESET);
			} catch (CustomerException e) {
				// TODO Auto-generated catch block
				System.out.println(ConsoleColors.RED+e.getMessage()+ ConsoleColors.RESET);
			}

		} catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED +e.getMessage()+ ConsoleColors.RESET);
		}
		return customer;
	}
}

