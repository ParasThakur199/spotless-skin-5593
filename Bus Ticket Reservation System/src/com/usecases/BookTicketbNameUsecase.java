package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.Bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exceptions.BusException;

public class BookTicketbNameUsecase {
	public static void BookTicketbName(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Bus Name");
		String bName = sc.next();
		
		CustomerDao cdd = new CustomerDaoImpl();
		try {
			System.out.println("Enter number of Tickets to Book");
			int noOfSeats = sc.nextInt();
			
			int cusId = customer.getCusId();
			String res = cdd.bookticket(bName, cusId, noOfSeats);
			if(res.equals("Ticket Booked Successfully")) {
				System.out.println(res);
			}else {
				System.out.println(res);
			}
		}catch(BusException e) {
			System.out.println(e.getMessage());
		}
		catch(InputMismatchException e) {
			System.out.println("Invalid Input");
		}
	}
}
