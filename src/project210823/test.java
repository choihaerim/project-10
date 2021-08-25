package project210823;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dao.CustomerDAO;
import model.dao.ReservationDAO;
import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import util.PublicCommon;

public class test {
	
	@Test
	void runTest() throws SQLException {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Attraction att1 = em.find(Attraction.class, 1l);
		Customer cus1 = em.find(Customer.class, 2l);
		Reservation res1 = new Reservation();
		
		res1.setMemberCnt(3);
		res1.setTime("12:30");
		
		res1.setCustomer(cus1);
		res1.setAttraction(att1);
		att1.getReservations().add(res1);
		cus1.getReservations().add(res1);
		
		ReservationDAO.addReservation(res1);
		
		ReservationDAO.getAllReservations();
		
//		Reservation res = ReservationDAO.getOneReservation(3l);
		System.out.println(res1.getCustomer().getName());
		
//		ReservationDAO.deleteReservation(1l);
		
		ReservationDAO.getAllReservations();
		
		
	}

}