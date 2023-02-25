package com.usecases;

import java.util.Scanner;

import com.custom.ConsoleColors;
import com.dao.AdminDao;
import com.dao.AdminDaoImpl;

public class AdminLoginUsecase {
public static boolean AdminLogin() {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println(ConsoleColors.ORANGE +  "Enter username" + ConsoleColors.RESET);
		String username = sc.next();
		
		System.out.println(ConsoleColors.ORANGE + "Enter password" + ConsoleColors.RESET);
		String password = sc.next();
		
		AdminDao dao = new AdminDaoImpl();
		String result =  dao.adminLogin(username, password);
		
		if (result.equals("Login Successfully")){
			System.out.println(ConsoleColors.LIGHT_GREEN_BACKGROUND + result + ConsoleColors.RESET);
			return true;
		}
		else {
			System.out.println(ConsoleColors.RED_BACKGROUND + result + ConsoleColors.RESET);
			return false;
		}

	}
}
