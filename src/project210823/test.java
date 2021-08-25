package project210823;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import model.dao.ReservationDAO;
import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;

public class test {
	
	@Test
	void runTest() throws SQLException {
		Attraction att1 = new Attraction();
		Customer cus1 = new Customer();
		Reservation res1 = new Reservation();
		
		res1.setMemberCnt(3);
		res1.setCancelYN("y");
		res1.setTime("15:30");
		
		cus1.setHeight(100);
		cus1.setName("기환");
		cus1.setAlarmYN("y");
		
		att1.setName("후룸라이드");
		att1.setLocation("A1");
		att1.setHeightLimit(173);
		att1.setParentYN("n");
		res1.setCustomer(cus1);
		res1.setAttraction(att1);
		att1.getReservations().add(res1);
		cus1.getReservations().add(res1);
		
//		ReservationDAO.addReservation(res1);
		
		ReservationDAO.getAllReservations();
		
		Reservation res = ReservationDAO.getOneReservation(3l);
		System.out.println(res.getCustomer().getName());
		
		ReservationDAO.deleteReservation(2);
		
		ReservationDAO.getAllReservations();
		
		
	}

}