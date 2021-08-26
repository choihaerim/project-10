/**
 * PROJECT : 놀이기구 사전 예약 프로그램
 * NAME : CustomerDAO.java
 * DESC : 고객(Customer) CRUD 로직
 * 
 * @author  이기환
 * @version 1.0
 */
package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Customer;
import util.PublicCommon;

public class CustomerDAO {
	
	/** Create */
	public static Customer addCustomer(String name, int height, String yn) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
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
			return null; //
		}finally {
			em.close();
			em = null;
		}

	}

	/** Select all */
	public static List<Customer> getAllCustomer() {
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

	/** Select */
	public static List<Customer> getOneCustomer(String name) {
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

	/** Update */
	public static void updateCustomerName(Long id, String name) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
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

	/** Delete */
	public static void deleteCustomer(Long id) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
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
	
}