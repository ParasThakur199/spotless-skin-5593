package com.usecases;

import com.dao.AdminDao;
import com.dao.AdminDaoImpl;

public class ViewAllTicketsUsecase {
	public static void viewAllTicket() {
		
		AdminDao dao = new AdminDaoImpl();
		dao.viewAllTickets();
	}
}
