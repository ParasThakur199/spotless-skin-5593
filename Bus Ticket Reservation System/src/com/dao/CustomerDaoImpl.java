package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Bean.Customer;
import com.exceptions.BusException;
import com.exceptions.CustomerException;
import com.utility.DButil;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public String cusSignUp(String username, String password, String firstName, String lastName, String address,
			String mobile) {
		String res = "SignUp Failed";
		try (Connection conn = DButil.ProvideConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"Insert Into Customer(username,password,firstName,lastName,address,mobile) values(?,?,?,?,?,?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.setString(3, firstName);
			ps.setString(4, lastName);
			ps.setString(5, address);
			ps.setString(6, mobile);

			int p = ps.executeUpdate();
			if (p > 0) {
				res = "Signup Successfully";
			}
		} catch (SQLException e) {
			res = e.getMessage();
		}

		return res;
	}

	public String cusSignUp(Customer customer) {
		String message = "Signup Failed";
		try (Connection conn = DButil.ProvideConnection()) {
			PreparedStatement ps = conn.prepareStatement(
					"Insert Into Customer(username,password,firstName,lastName,address,mobile) values(?,?,?,?,?,?)");
			ps.setString(1, customer.getUsername());
			ps.setString(2, customer.getPassword());
			ps.setString(3, customer.getFirstName());
			ps.setString(4, customer.getLastName());
			ps.setString(5, customer.getAddress());
			ps.setString(6, customer.getMobile());

			int p = ps.executeUpdate();
			if (p > 0) {
				message = "Signup Successfully";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			message = e.getMessage();
		}
		return message;
	}

	@Override
	public Customer cusLogin(String username, String password) throws CustomerException {
		Customer customer = null;
		Connection conn = DButil.ProvideConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("Select * from Customer where username = ? and password = ?");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int cusId = rs.getInt("cusId");
				String userrname = rs.getString("username");
				String passsword = rs.getString("password");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String address = rs.getString("address");
				String mobile = rs.getString("mobile");

				customer = new Customer(cusId, userrname, passsword, firstName, lastName, address, mobile);
			} else {
				throw new CustomerException("Invalid Username and Password");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new CustomerException(e.getMessage());
		}
		return customer;
	}

	@Override
	public String bookticket(String bName, int cusId, int noOfSeats) throws BusException {
		String res = "Ticket Booking Failed";

		try (Connection conn = DButil.ProvideConnection()) {
			PreparedStatement ps = conn.prepareStatement("Select * from Bus where bName = ?");

			ps.setString(1, bName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int busNo = rs.getInt("busNo");
				int totalSeats = rs.getInt("totalSeats");
				int availSeats = rs.getInt("availSeats");
				Date departure = rs.getDate("departure");
				int fare = rs.getInt("fare");

				PreparedStatement ps1 = conn.prepareStatement("Select dateDiff(?,current_date()) as date");
				ps1.setDate(1, departure);

				ResultSet rs1 = ps1.executeQuery();
				int days = 0;
				if (rs1.next()) {
					days = rs1.getInt("date");
				}
				if (days < 0) {
					throw new BusException("Booking is not Available in this date");
				} else if (availSeats >= noOfSeats) {
					int seatFrom = totalSeats - availSeats + 1;
					int seatTo = seatFrom + noOfSeats - 1;
					fare = fare * noOfSeats;

					PreparedStatement ps2 = conn
							.prepareStatement("Insert into booking(cusId, busNo, seatFrom, seatTo) values(?, ?, ?, ?)");
					ps2.setInt(1, cusId);
					ps2.setInt(2, busNo);
					ps2.setInt(3, seatFrom);
					ps2.setInt(4, seatTo);

					int p = ps2.executeUpdate();
					if (p > 0) {
						PreparedStatement ps3 = conn.prepareStatement("Update bus set availSeats = ? where busNo = ?");
						availSeats = availSeats - noOfSeats;
						ps3.setInt(1, availSeats);
						ps3.setInt(2, busNo);

						int y = ps3.executeUpdate();
						if (y <= 0) {
							throw new BusException("Available Seats are not Updated");
						}
						System.out.println("--------------------------------------------" + "\n" + "Customer Id is : "
								+ cusId + "\n" + "Bus No is : " + busNo + "\n" + "Seat No is from : " + seatFrom
								+ " to " + seatTo + "\n" + "Bus fare is : " + fare + "\n"
								+ "Booking yet to be confirm by Adminstrator" + "\n");

						res = "Ticket Booked Successfully";
					}
				}
			} else {
				throw new BusException("Bus with " + bName + " is not Available");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BusException(e.getMessage());
		}
		return res;

	}

	@Override
	public String cancelTicket(String bName, int cusId) throws BusException {
		String res = "Ticket Cancellation Failed";

		try (Connection conn = DButil.ProvideConnection()) {
			PreparedStatement ps = conn.prepareStatement("Select * from bus where bName = ?");
			ps.setString(1, bName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int busNo = rs.getInt("busNo");
				int availSeats = rs.getInt("availSeats");

				PreparedStatement ps1 = conn.prepareStatement("Select * from booking where busNo = ? and cusId = ?");
				ps1.setInt(1, busNo);
				ps1.setInt(2, cusId);

				ResultSet rs1 = ps1.executeQuery();
				boolean flag = false;
				int count = 0;
				while (rs1.next()) {
					flag = true;
					int seatFrom = rs1.getInt("seatFrom");
					int seatTo = rs1.getInt("seatTo");
					count += seatTo - seatFrom + 1;
				}
				if (flag) {
					PreparedStatement ps2 = conn.prepareStatement("Delete from booking where busNo = ? and cusId = ?");
					ps2.setInt(1, busNo);
					ps2.setInt(2, cusId);

					int x = ps2.executeUpdate();
					if (x > 0) {
						PreparedStatement ps3 = conn.prepareStatement("Update bus set availseats = ? where busNo = ?");
						availSeats = availSeats + count;
						ps3.setInt(1, availSeats);
						ps3.setInt(2, busNo);
						int y = ps3.executeUpdate();

						if (y <= 0) {
							throw new BusException("Available seats is not Updated");
						}
						res = "Ticket Cancelled Successfully";
					}
				} else {
					res = "No Booking Found";
				}
			}else {
				throw new BusException("Bus with "+bName+" is not Available");
			}
		} catch (SQLException e) {
			throw new BusException(e.getMessage());
		}
		return res;
	}
}
