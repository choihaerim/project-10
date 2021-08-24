package project210823;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import util.PublicCommon;

public class test {
	
//	@Test
	void runTest() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Attraction att1 = new Attraction();
		Customer cus1 = new Customer();
		Reservation res1 = new Reservation();
		
		res1.setMemberCnt(3);
		res1.setCancelYN("y");
		res1.setTime("15:30");
		
		cus1.setHeight(100);
		cus1.setName("기환");
		cus1.setAlarmYN("y");
		
		att1.setLocation("A1");
		att1.setHeightLimit(173);
		att1.setParentYN("n");
		res1.setCustomer(cus1);
		res1.setAttraction(att1);
		att1.getReservations().add(res1);
		cus1.getReservations().add(res1);
		
		em.persist(res1);
		em.persist(cus1);
		em.persist(att1);
		
		tx.commit();
		em.close();
		em=null;
	}

}
