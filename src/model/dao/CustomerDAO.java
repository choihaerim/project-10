package model.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import util.PublicCommon;

public class CustomerDAO {
	/**
	 * Create(Insert)
	 */
	public static Customer insertCustomer(String name, int height, String yn) {

		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {

		tx.begin();

		Customer customer = new Customer();
		customer.setName(name);
		customer.setHeight(height);
		customer.setAlarmYN(yn);

		em.persist(customer);
		tx.commit();

		return customer;
		}catch(Exception e){
			tx.rollback();
			e.printStackTrace();
			return null;
		}finally {
			em.close();
			em = null;
		}

	}

	/**
	 * Read(Select)
	 */
	// select c from Customer c;
	public static List<Customer> selectAllCustomer() {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			List<Customer> all = em.createQuery("select c from Customer c", Customer.class).getResultList();
			return all;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
			em = null;
		}
	}

	// "select c from Customer c where c.name=:name", name="Customer.findByName"
	public static List<Customer> findByName(String name) {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			List<Customer> all = em.createNamedQuery("Customer.findByName").setParameter("name", name).getResultList();
			return all;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
			em = null;
		}
	}

	/**
	 * Update
	 */
	public static void updateName(Long id, String name) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Customer customer = em.find(Customer.class, id);
			customer.setName(name);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			em = null;
		}
	}

	/**
	 * Delete
	 */
	public static void deleteById(Long id) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
		tx.begin();
		Customer customer = em.find(Customer.class, id);
		em.remove(customer);
		tx.commit();
		}catch(Exception e) {
			e.printStackTrace();
			tx.rollback();
		}finally {
			em.close();
			em = null;
		}
	}

//	@Test
	void runTest() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Reservation res1 = new Reservation();
		Customer cus1 = em.find(Customer.class, 1l);
		Attraction att1 = em.find(Attraction.class, 1l);

		res1.setMemberCnt(3);
		res1.setTime("10:22");
		res1.setAttraction(att1);
		res1.setCustomer(cus1);
		em.persist(res1);

		tx.commit();
		em.close();
		em = null;
	}
}