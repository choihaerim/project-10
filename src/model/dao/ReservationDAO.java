package model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
			reservation.setCancelYN(res.getCancelYN());
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
//	@Test
	public void m0() {
		Reservation reservation = new Reservation();
		reservation.setTime("17:00");
		reservation.setMemberCnt(4);
		addReservation(reservation);
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
		System.out.println("||" + "예약번호 : " + r.getReservationId() + 
						   "||" + "예약인원 : " + r.getMemberCnt() + "명" +
						   "||" + "예약시간 : " + r.getTime() +
						   "||" + "예약 취소 가능 여부 : " + r.getCancelYN() + "||"
						   );
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
		rs.stream().forEach(v -> System.out.println("||" + "예약번호 : " + v.getReservationId() + 
													"||" + "예약시간 : " + v.getTime() + 
													"||" + "예약인원 : " + v.getMemberCnt() + "명" + 
													"||" + "예약 취소 가능 여부 : " + v.getCancelYN() + 
													"||" + "예약 고객 이름 : " + v.getCustomer().getName() + 
													"||" + "예약 고객 신장 : " + v.getCustomer().getHeight() + 
													"||" + "예약 고객 알림 허용 여부 : " + v.getCustomer().getAlarmYN()  + "||"
													));
	}
	
	
	
	/**
	 * 예약정보 수정하기
	 */
	public static void updateReservation(Long reservationId, int cnt) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = em.find(Reservation.class, reservationId);
			reservation.setMemberCnt(cnt);
			
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
		Reservation reservation = em.find(Reservation.class, 1l);
		System.out.println(reservation);
		
		updateReservation(1l, 3);
		
		System.out.println("수정 후 검색해보기");
		Reservation reservation_a = em.find(Reservation.class, 1l);
		System.out.println(reservation_a);
	}
	
	
	
	
	/**
	 * 예약정보 취소하기
	 */
	public static void deleteReservation(Long reservationId) {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Reservation reservation = em.find(Reservation.class, reservationId);
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
		
		deleteReservation(3l);
		
		System.out.println("삭제 후 남은 예약리스트 검색해보기");
		List<Reservation> rs = ReservationDAO.getAllReservation();
		rs.stream().forEach(v -> System.out.println(v)); //주소값..........
	}
	
}
