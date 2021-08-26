/**
 * PROJECT : 놀이기구 사전 예약 프로그램
 * NAME : ReservationDAO.java
 * DESC : 예약(Reservation) CRUD 로직
 * 
 * @author  정은진(B), 방지원
 * @version 1.0
 */
package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import util.PublicCommon;

public class ReservationDAO {
	
	/** Create */
	public static void addReservation(Long aId, Long cId, String time, int cnt) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = new Reservation();
			
			reservation.setCustomer(em.find(Customer.class, cId));
			reservation.setAttraction(em.find(Attraction.class, aId));
			reservation.setMemberCnt(cnt);
			reservation.setTime(time);
			em.persist(reservation);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

	/** Select all */
	public static List<Reservation> getAllReservation() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Reservation> allreservations = null;
		try {
			String jpql = "select r from Reservation r";
			allreservations = em.createQuery(jpql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return allreservations; //
	}
	
	/** Select */
	public static Reservation getOneReservation(Long id) {
		EntityManager em = PublicCommon.getEntityManager();
		Reservation reservation = null;
		try {
			reservation = em.find(Reservation.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return reservation; //
	}

	/** Update */
	public static void updateReservationTime(Long id, String time) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
//		Reservation reservation = null;
		try {
			Reservation reservation = em.find(Reservation.class, id);
			reservation.setTime(time);
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

	/** Delete */
	public static void deleteReservation(Long id) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = em.find(Reservation.class, id);

			em.remove(reservation);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}
	
}
