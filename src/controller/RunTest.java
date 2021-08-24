package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.Attraction;
import model.Customer;
import model.Reservation;
import util.PublicCommon;

public class RunTest {
	
	@Test
	void runTest() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Attraction att1 = new Attraction();
		Customer cus1 = new Customer();
		Reservation res1 = new Reservation();
		
		res1.setMember_cnt(3);
		res1.setCancel_yn("y");
		res1.setTime("15:30");
		
		cus1.setHeight(100);
		cus1.setName("기환");
		cus1.setAlarm_yn("y");
		
		att1.setLocation("A1");
		att1.setHeight_limit(173);
		att1.setParent_yn("n");
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
