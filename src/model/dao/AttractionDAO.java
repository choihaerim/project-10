package model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import util.PublicCommon;

public class AttractionDAO {

	// Insert 놀이기구 이름, 놀이기구 위치, 키 제한, 보호자 동반 여부
	public static Attraction insertAttraction(String name, String location, int heightLimit, String yn) {

		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {

			tx.begin();

			Attraction attraction = new Attraction();
			attraction.setName(name);
			attraction.setLocation(location);
			attraction.setHeightLimit(heightLimit);
			attraction.setParentYN(yn);

			em.persist(attraction);
			tx.commit();

			em.close();
			em = null;

			return attraction;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null;
		} finally {
			em.close();
			em = null;
		}
	}

	// Select (Read)
	public static List<Attraction> selectAllAttraction() {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			List<Attraction> all = em.createQuery("select a from Attraction a", Attraction.class).getResultList();
			return all;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
			em = null;
		}
	}

	public static List<Attraction> findByName(String name) {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			List<Attraction> all = em.createNamedQuery("Attraction.findByName").setParameter("name", name)
					.getResultList();
			return all;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			em.close();
			em = null;
		}
	}

	// Update 놀이기구 이름, 키 제한, 보호자 동반 여부
	public static void updateName(Long id, String name, String location, int heightLimit, String parentYN) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {

			tx.begin();
			Attraction attraction = em.find(Attraction.class, id);
			attraction.setName(name);
			attraction.setLocation(location);
			attraction.setHeightLimit(heightLimit);
			attraction.setParentYN(parentYN);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			em = null;
		}
	}

	// Delete
	public static void deleteById(Long id) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Attraction attraction = em.find(Attraction.class, id);
			em.remove(attraction);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			em = null;
		}
	}

	@Test
	void runTest() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Attraction att1 = new Attraction();

		att1.setName("범퍼카");
		att1.setLocation("B1");
		att1.setHeightLimit(120);
		att1.setParentYN("y");

		em.persist(att1);

		tx.commit();
		em.close();
		em = null;
	}
}
