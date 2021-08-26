package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Attraction;
import model.dto.Customer;
import model.dto.Reservation;
import util.PublicCommon;

public class ReservationDAO {
	/**
	 * 새로운 예약 저장하기
	 * 
	 * @param attractionId, customerId, time, cnt
	 */
	public static boolean addReservation(Long aId, Long cId, String time, int cnt) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Customer cus = em.find(Customer.class, cId);
		Attraction att = em.find(Attraction.class, aId);

		try {
			if (cus.getHeight() >= att.getHeightLimit()) {
				Reservation reservation = new Reservation();

				reservation.setCustomer(cus);
				reservation.setAttraction(att);
				reservation.setMemberCnt(cnt);
				reservation.setTime(time);

				em.persist(reservation);

				tx.commit();
				
				return true;
				
			} 
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		} return false;
	}

	/**
	 * 예약정보 하나 불러오기
	 * 
	 * @param reservationId
	 * @return reservation
	 */

	public static Reservation getOneReservation(Long reservationId) {
		EntityManager em = PublicCommon.getEntityManager();
		Reservation reservation = null;
		try {
			reservation = em.find(Reservation.class, reservationId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return reservation;
	}

	/**
	 * 모든 예약정보 가져오기
	 */
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
		return allreservations;
	}

	/**
	 * Reservation id로 예약 삭제하기
	 * 
	 * @param reservationId
	 */
	public static void deleteReservation(Long reservationId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = em.find(Reservation.class, reservationId);

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

	/**
	 * reservation id로 예약시간 수정
	 * 
	 * @param reservationId, time
	 */
	public static void updateReservation(Long reservationId, String time) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();

		Reservation reservation = null;

		try {
			reservation = em.find(Reservation.class, reservationId);
			reservation.setTime(time);

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
