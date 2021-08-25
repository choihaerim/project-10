package model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.dto.Attraction;
import model.dto.Reservation;
import util.PublicCommon;

public class ReservationDAO {
	public static void addReservation(Reservation res) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = new Reservation();

			reservation.setMemberCnt(res.getMemberCnt());
			reservation.setTime(res.getTime());

			reservation.setCustomer(res.getCustomer());
			reservation.setAttraction(res.getAttraction());

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

	public static List<Reservation> getAllReservations() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();

		String jpql = "select r from Reservation r";
		List<Reservation> all = em.createQuery(jpql).getResultList();
		all.forEach(v -> System.out.println("=============\n" + "- 예약 ID : " + v.getReservationId()
				+ "\n- 놀이기구 이름 : " + v.getAttraction().getName() + "\n- 인원수 : " + v.getMemberCnt()
				 + "\n- 예약자 이름: " + v.getCustomer().getName()
				+ "\n- 예약 시간: " + v.getTime()));

		em.close();
		em = null;

		return all;
	}

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

	// reservation id로 예약한 놀이기구 수정
	public static void updateReservation(Long reservationId, Attraction attraction) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Reservation reservation = null;
		
		try {
			reservation = em.find(Reservation.class, reservationId);
			reservation.setAttraction(attraction);
			
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

}
