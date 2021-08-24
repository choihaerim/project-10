package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.transaction.Transaction;

import org.junit.jupiter.api.Test;

import util.PublicCommon;

public class RunTest {
	
	@Test
	void runTest() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	}

}
