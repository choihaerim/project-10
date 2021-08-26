/**
 * PROJECT : 놀이기구 사전 예약 프로그램
 * NAME : AttractionDAO.java
 * DESC : 놀이기구(Attraction) CRUD 로직
 * 
 * @author  최해림
 * @version 1.0
 */
package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Attraction;
import util.PublicCommon;

public class AttractionDAO {
	
	/** Create */
	public static Attraction addAttraction(String name, String location, int heightLimit, String yn) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Attraction attraction = new Attraction();
			attraction.setName(name);
			attraction.setLocation(location);
			attraction.setHeightLimit(heightLimit);
			attraction.setParentYN(yn);

			em.persist(attraction);
			
			tx.commit();
			return attraction;
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
			return null; //
		} finally {
			em.close();
			em = null;
		}
	}

	/** Select all */
	public static List<Attraction> getAllAttraction() {
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

	/** Select */
	public static List<Attraction> getOneAttraction(String name) {
		EntityManager em = PublicCommon.getEntityManager();
		try {
			List<Attraction> all = em.createNamedQuery("Attraction.findByName").setParameter("name", name).getResultList();
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
	public static void updateAttractionHeightLimit(Long id, int heightLimit) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Attraction attraction = em.find(Attraction.class, id);
			attraction.setHeightLimit(heightLimit);

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
	public static void deleteAttraction(Long id) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
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
	
}
