package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Bean.Customer;
import com.custom.ConsoleColors;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exceptions.BusException;

public class BookTicketbNameUsecase {
	public static void BookTicketbName(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println(ConsoleColors.ORANGE+"Enter Bus Name"+ConsoleColors.RESET);
		String bName = sc.next();
		
		CustomerDao cdd = new CustomerDaoImpl();
		try {
			System.out.println(ConsoleColors.ORANGE +"Enter number of Tickets to Book"+ConsoleColors.RESET);
			int noOfSeats = sc.nextInt();
			
			int cusId = customer.getCusId();
			String res = cdd.bookticket(bName, cusId, noOfSeats);
			if(res.equals("Ticket Booked Successfully")) {
				System.out.println(ConsoleColors.LIGHT_GREEN_BACKGROUND+res+ConsoleColors.RESET);
			}else {
				System.out.println(ConsoleColors.RED_BACKGROUND+res+ConsoleColors.RESET);
			}
		}catch(BusException e) {
			System.out.println(ConsoleColors.RED +e.getMessage()+ConsoleColors.RESET);
		}
		catch(InputMismatchException e) {
			System.out.println(ConsoleColors.RED +"Invalid Input"+ConsoleColors.RESET);
		}
	}
}
