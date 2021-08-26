package project210823;

import java.sql.SQLException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

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
		
		Attraction att1 = new Attraction();
		Customer cus1 = new Customer();
		Reservation res1 = new Reservation();
		
		
		cus1.setHeight(100);
		cus1.setName("기환");
		cus1.setAlarmYN("y");
		
		att1.setName("후룸라이드");
		att1.setLocation("A1");
		att1.setHeightLimit(173);
		att1.setParentYN("n");

		att1.getReservations().add(res1);
		cus1.getReservations().add(res1);
		
		
		em.persist(att1);
		em.persist(cus1);
		
		tx.commit();
		em.close();
		em = null;
		
//		ReservationDAO.addReservation();
		
//		ReservationDAO.getAllReservation();
		
//		ReservationDAO.addReservation(1l, 1l, "09:00", 3);

		
//		ReservationDAO.deleteReservation(2);
		
		ReservationDAO.getAllReservation();
		
		
	}

}