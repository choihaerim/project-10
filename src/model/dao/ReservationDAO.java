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

public class ReservationDAO {
	
//	@Test
	public void reservationInsert() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation res1 = new Reservation();
			res1.setMemberCnt(3);
			res1.setCancelYN("y");
			res1.setTime("15:30");
			
			Attraction att1 = new Attraction();
			Customer cus1 = new Customer();
			
			res1.setCustomer(cus1);
			res1.setAttraction(att1);
			
			em.persist(res1);
			em.persist(cus1);
			em.persist(att1);
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
	}
	
	
	
	
	
	/**
	 * 예약정보 하나 불러오기
	 * @param reservationId
	 * @return reservation
	 */
	public static Reservation getOneReservation(Long reservationId) {
		EntityManager em = PublicCommon.getEntityManager();
		Reservation reservation = null;
		try {
			reservation = em.find(Reservation.class, reservationId);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		return reservation;
	}
//	@Test
	public void m1() throws SQLException {
		Reservation r = getOneReservation(2l);
		System.out.println("예약번호 : " + r.getReservationId());
		System.out.println("예약인원 : " + r.getMemberCnt());
		System.out.println("예약시간 : " + r.getTime());
		System.out.println("예약 취소 가능 여부 : " + r.getCancelYN());
	}
	
	
	
	
	/**
	 * 모든 예약정보 가져오기
	 * @return allreservations
	 */
	public static List<Reservation> getAllReservation() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Reservation> allreservations = null;
		try {
			String jpql = "select r from Reservation r";
			allreservations = em.createQuery(jpql).getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
		return allreservations;
	}
//	@Test
	public void m2() throws SQLException {
		List<Reservation> rs = getAllReservation();
		//주소값..........
		rs.stream().forEach(v -> System.out.println(v));
	}
	
	
	
	
	/**
	 * 예약정보 수정하기
	 */
	public static void reservationUpdate() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
	}
//	@Test
	public void m3() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		
		System.out.println("수정 전 검색해보기");
		Reservation reservation = em.find(Reservation.class, 4l);
		System.out.println(reservation);
		
		reservationDelete();
		
		System.out.println("수정 후 검색해보기");
		Reservation reservation_a = em.find(Reservation.class, 4l);
		System.out.println(reservation_a);
	}
	
	
	
	
	/**
	 * 예약정보 취소하기
	 */
	public static void reservationDelete() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = em.find(Reservation.class, 3l);
			
			em.remove(reservation);
			
			tx.commit();
		}catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		}finally {
			em.close();
			em = null;
		}
	}
//	@Test
	public void m4() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();
		
		System.out.println("삭제 전 검색해보기");
		Reservation reservation = em.find(Reservation.class, 3l);
		System.out.println(reservation);
		
		reservationDelete();
		
		System.out.println("삭제 후 남은 예약리스트 검색해보기");
		List<Reservation> rs = ReservationDAO.getAllReservation();
		//주소값..........
		rs.stream().forEach(v -> System.out.println(v));
		
	}
	
	
	
	
}
