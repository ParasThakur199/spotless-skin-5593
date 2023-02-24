package com.usecases;

import java.util.Scanner;

import com.Bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;
import com.exceptions.BusException;

public class cancelTicketbNameUsecase {
	public static void cancelTicket(Customer customer) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Bus Name");
		String bName = sc.nextLine();
		
		CustomerDao cdd = new CustomerDaoImpl();
		int cusId = customer.getCusId();
		try {
			String res = cdd.cancelTicket(bName, cusId);
			if(res.equals("Ticket Cancelled Successfully")) {
				System.out.println(res);
			}else {
				System.out.println(res);
			}
			
		} catch (BusException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
	}
}
