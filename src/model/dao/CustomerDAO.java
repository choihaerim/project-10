package model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dto.Customer;
import util.PublicCommon;

public class CustomerDAO {
	/**
	 * Create(Insert)
	 */
	public static Customer insertCustomer(String name,int height,String yn) {
		
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		
		Customer customer = new Customer();
		customer.setName(name);
		customer.setHeight(height);
		customer.setAlarm_yn(yn);
		
		em.persist(customer);
		tx.commit();
		
		em.close();
		em=null;
		
		return customer;
	}
	/**
	 * Read(Select)
	 */
		//select c from Customer c;
	public static List<Customer> selectAllCustomer(){
		EntityManager em = PublicCommon.getEntityManager();
		
		List<Customer> all = em.createQuery("select c from Customer c",Customer.class).getResultList();
		
		em.close();
		em=null;
		return all;
	}
	
		//"select c from Customer c where c.name=:name", name="Customer.findByName"
	public static List<Customer> findByName(String name) {
		EntityManager em = PublicCommon.getEntityManager();
		
		List<Customer> all = em.createNamedQuery("Customer.findByName").setParameter("name", name).getResultList();
		
		em.close();
		em=null;
		return all;
	}
	/**
	 * Update
	 */
	public static void updateName(Long id,String name) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Customer customer = em.find(Customer.class, id);
		customer.setName(name);
		
		tx.commit();
		em.close();
		em=null;
	}
	/**
	 * Delete
	 */
	public static void deleteById(Long id) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		
		tx.begin();
		Customer customer = em.find(Customer.class, id);
		em.remove(customer);
		
		em.close();
		em=null;
	}
}
