package com.usecases;

import java.util.Scanner;

import com.Bean.Customer;
import com.custom.ConsoleColors;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exceptions.BusException;

public class cancelTicketbNameUsecase {
	public static void cancelTicket(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println(ConsoleColors.ORANGE+ "Enter Bus Name" +ConsoleColors.RESET);
		String bName = sc.nextLine();
		
		CustomerDao cdd = new CustomerDaoImpl();
		int cusId = customer.getCusId();
		try {
			String res = cdd.cancelTicket(bName, cusId);
			if(res.equals("Ticket Cancelled Successfully")) {
				System.out.println(ConsoleColors.LIGHT_GREEN_BACKGROUND + res + ConsoleColors.RESET);
			}else {
				System.out.println(ConsoleColors.RED_BACKGROUND + res + ConsoleColors.RESET);
			}
			
		} catch (BusException e) {
			// TODO Auto-generated catch block
			System.out.println(ConsoleColors.RED + e.getMessage() + ConsoleColors.RESET);
		}
		
	}
}
