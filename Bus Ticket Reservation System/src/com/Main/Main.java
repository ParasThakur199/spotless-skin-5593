package com.Main;

import java.sql.Connection;

import com.Bean.Customer;
import com.usecases.cusLoginUsercase;
import com.usecases.cusSignupUsecase1;
import com.usecases.cusSignupUsecase2;
import com.utility.DButil;

public class Main {
	public static void main(String[] args) {
		Connection conn = DButil.ProvideConnection();
		if(conn != null) {
			System.out.println("connect");
		}else {
			System.out.println("not connect");
		}
		
//		boolean flag = cusSignupUsecase1.cusSignup();
//		System.out.print(flag);
		
//		boolean flag = cusSignupUsecase2.cusSignUp();
//		System.out.println(flag);
		
		Customer customer = cusLoginUsercase.CusLogin();
	}
}
