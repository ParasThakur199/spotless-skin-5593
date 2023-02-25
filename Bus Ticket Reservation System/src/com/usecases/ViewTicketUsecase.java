package com.usecases;

import com.Bean.Customer;
import com.dao.CustomerDao;
import com.dao.CustomerDaoImpl;

public class ViewTicketUsecase {
	public static void viewTicket(Customer customer) {
		CustomerDao cdd = new CustomerDaoImpl();
		cdd.viewTicket(customer.getCusId());
	}
	
}
