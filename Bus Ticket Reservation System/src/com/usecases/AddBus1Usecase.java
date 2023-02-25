package com.usecases;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.custom.ConsoleColors;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;

public class AddBus1Usecase {
public static void AddBus() {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			
			System.out.println(ConsoleColors.ORANGE + "Enter Bus number" + ConsoleColors.RESET);
			int busNo = sc.nextInt();
			
			System.out.println(ConsoleColors.ORANGE + "Enter bus name" + ConsoleColors.RESET);
			String bName = sc.next();
			
			System.out.println(ConsoleColors.ORANGE + "Enter Route from" + ConsoleColors.RESET);
			String routeFrom = sc.next();
			
			System.out.println(ConsoleColors.ORANGE + "Enter Routo To" + ConsoleColors.RESET);
			String routeTo = sc.next();
			
			System.out.println(ConsoleColors.ORANGE + "Enter Bus Type - AC / NonAC" + ConsoleColors.RESET);
			String bType = sc.next();
			
			sc.nextLine();
			System.out.println(ConsoleColors.ORANGE + "Enter Departure date and time in format (YYYY-MM-DD HH:MI:SS)" + ConsoleColors.RESET);
			String departure = sc.nextLine();
			
			System.out.println(ConsoleColors.ORANGE + "Enter Arrival date and time in format (YYYY-MM-DD HH:MI:SS)" + ConsoleColors.RESET);
			String arrival = sc.nextLine();
			
			System.out.println(ConsoleColors.ORANGE + "Enter Total Seats" + ConsoleColors.RESET);
			int totalSeats = sc.nextInt();
			
			System.out.println(ConsoleColors.ORANGE + "Enter Available Seats" + ConsoleColors.RESET);
			int availSeats = sc.nextInt();
			
			System.out.println(ConsoleColors.ORANGE + "Enter fare" + ConsoleColors.RESET);
			int fare = sc.nextInt();
			
			AdminDao dao = new AdminDaoImpl();
			
			String result = dao.addBus(busNo, bName, routeFrom, routeTo, bType, departure, arrival, totalSeats, availSeats, fare);
			
			if (result.equals("Bus added Successfully")) {
				System.out.println(ConsoleColors.LIGHT_GREEN_BACKGROUND+ result + ConsoleColors.RESET);
			}
			else {
				System.out.println(ConsoleColors.RED_BACKGROUND + result + ConsoleColors.RESET);
			}
		}
		catch (InputMismatchException e) {
			System.out.println(ConsoleColors.RED + "Invalid input" + ConsoleColors.RESET);
		}
		
	}
}
