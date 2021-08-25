package model.dao;

import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dto.Customer;
import model.dto.Attraction;
import model.dto.Reservation;
import util.PublicCommon;

public class ReservationDAO {

//	private static ReservationDAO instance = new ReservationDAO();
//
//	private ReservationDAO() {}
//	
//	public static ReservationDAO getInstance() {
//		return instance;
//	}

	public static void addReservation(Reservation res) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = new Reservation();

			reservation.setMemberCnt(res.getMemberCnt());
			reservation.setCancelYN(res.getCancelYN());
			reservation.setTime(res.getTime());

//			Customer cus1 = new Customer();
//			cus1.setName("지원");
//			cus1.setHeight(120);
//			cus1.setAlarmYN("n");
//			
//			reservation.setCustomer(cus1);
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

//	@Test
	public void m0() {
//		Scanner scan = new Scanner(new InputStreamReader(System.in));
//		
//		System.out.println("예약시간을 입력하세요.");
//		String time = scan.next();
//		System.out.println("예약인원을 입력하세요.");
//		int count = Integer.parseInt(scan.next());
//		System.out.println("취소가능여부 입력하세요.");
//		String cancel = scan.next();

		Reservation reservation = new Reservation();
		reservation.setTime("17:00");
		reservation.setMemberCnt(4);
		addReservation(reservation);
	}

	/**
	 * 예약정보 하나 불러오기
	 * 
	 * @param reservationId
	 * @return reservation
	 */
	
	public static Reservation getReservation(int reservationId) {
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
	 * 
	 * @return allreservations
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

//	@Test
	public void m2() throws SQLException {
		List<Reservation> rs = getAllReservation();
		rs.stream().forEach(v -> System.out.println("||" + "예약번호 : " + v.getReservationId() + "||" + "예약시간 : "
				+ v.getTime() + "||" + "예약인원 : " + v.getMemberCnt() + "명" + "||" + "예약 취소 가능 여부 : " + v.getCancelYN()
				+ "||" + "예약 고객 이름 : " + v.getCustomer().getName() + "||" + "예약 고객 신장 : " + v.getCustomer().getHeight()
				+ "||" + "예약 고객 알림 허용 여부 : " + v.getCustomer().getAlarmYN() + "||"));
	}

	/**
	 * 예약정보 수정하기
	 */
	public static void updateReservation() {

	}

	public static List<Reservation> getAllReservations() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();

		String jpql = "select r from Reservation r";
		List<Reservation> all = em.createQuery(jpql).getResultList();
		all.forEach(v -> System.out.println("=============\n" + "- 예약 ID : " + v.getReservationId()
				+ "\n- 놀이기구 이름 : " + v.getAttraction().getName() + "\n- 인원수 : " + v.getMemberCnt()
				+ "\n- 취소 가능 여부 : " + v.getCancelYN() + "\n- 예약자 이름: " + v.getCustomer().getName()
				+ "\n- 예약 시간: " + v.getTime()));

		em.close();
		em = null;

		return all;
	}

	public static void deleteReservation(int reservationId) {
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
	
//	@Test
	public void m3() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();

		System.out.println("수정 전 검색해보기");
		Reservation reservation = em.find(Reservation.class, 4l);
		System.out.println(reservation);

		updateReservation();

		System.out.println("수정 후 검색해보기");
		Reservation reservation_a = em.find(Reservation.class, 4l);
		System.out.println(reservation_a);
	}

	/**
	 * 예약정보 취소하기
	 */
	public static void deleteReservation() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = em.find(Reservation.class, 3l);

			em.remove(reservation);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	// reservation id로 예약한 놀이기구 수정
	public static void updateReservation(int reservationId, Attraction attraction) {
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

//	@Test
	public void m4() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();

		System.out.println("삭제 전 검색해보기");
		Reservation reservation = em.find(Reservation.class, 3l);
		System.out.println(reservation);

		deleteReservation();

		System.out.println("삭제 후 남은 예약리스트 검색해보기");
		List<Reservation> rs = ReservationDAO.getAllReservation();
		rs.stream().forEach(v -> System.out.println(v)); // 주소값..........
	}
}
