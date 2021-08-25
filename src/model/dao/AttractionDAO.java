package model.dao;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.jupiter.api.Test;

import model.dto.Attraction;
import util.PublicCommon;

public class AttractionDAO {

	@Test
	public void attractionInsert() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			Attraction att1 = new Attraction();
			att1.setLocation("A2");
			att1.setName("범퍼카");
			att1.setHeightLimit(120);
			att1.setParentYN("y");

			Attraction att2 = new Attraction();
			att2.setLocation("A3");
			att2.setName("매직스윙");
			att2.setHeightLimit(140);
			att2.setParentYN("y");

			Attraction att3 = new Attraction();
			att3.setLocation("B1");
			att3.setName("비룡열차");
			att3.setHeightLimit(160);
			att3.setParentYN("n");

			Attraction att4 = new Attraction();
			att4.setLocation("B2");
			att4.setName("우주전투기");
			att4.setHeightLimit(110);
			att4.setParentYN("n");

			em.persist(att1);
			em.persist(att2);
			em.persist(att3);
			em.persist(att4);

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
	 * attractionId 하나 불러오기
	 * 
	 * @param attractionId
	 * @return attraction
	 */
	public static Attraction getOneAttraction(Long attractionId) {
		EntityManager em = PublicCommon.getEntityManager();
		Attraction attraction = null;
		try {
			attraction = em.find(Attraction.class, attractionId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return attraction;
	}

//	@Test
	public void m1() throws SQLException {
		Attraction a = getOneAttraction(1l);
		System.out.println("놀이기구번호 : " + a.getAttractionId());
		System.out.println("놀이기구 위치 : " + a.getLocation());
		System.out.println("키 제한 : " + a.getHeightLimit());
		System.out.println("보호자 동반 여부 : " + a.getParentYN());
	}

	/**
	 * 모든 놀이기구 정보 가져오기
	 * 
	 * @return allattractionId
	 */
//	@Test
	public static List<Attraction> getAllAttraction() {
		EntityManager em = PublicCommon.getEntityManager();
		List<Attraction> allattraction = null;
		try {
			String jpql = "select a from Attraction a";
			allattraction = em.createQuery(jpql).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
		return allattraction;
	}

//	@Test
	public void m2() throws SQLException {
		List<Attraction> at = getAllAttraction();
		// 주소값..........
		at.stream().forEach(v -> System.out.println(v));
	}

	/**
	 * 놀이기구 정보 수정하기
	 */
	public static void attractionUpdate() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

//		@Test
	public void m3() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();

		System.out.println("수정 전 검색해보기");
		Attraction attraction = em.find(Attraction.class, 4l);
		System.out.println(attraction);

		attractionDelete();

		System.out.println("수정 후 검색해보기");
		Attraction attraction1 = em.find(Attraction.class, 4l);
		System.out.println(attraction1);
	}

	/**
	 * 놀이기구 정보 삭제하기
	 */
	public static void attractionDelete() {
		EntityManager em = PublicCommon.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		try {
			Attraction attraction = em.find(Attraction.class, 1l);

			em.remove(attraction);

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
			em = null;
		}
	}

//		@Test
	public void m4() throws SQLException {
		EntityManager em = PublicCommon.getEntityManager();

		System.out.println("삭제 전 검색해보기");
		Attraction attraction = em.find(Attraction.class, 1l);
		System.out.println(attraction);

		attractionDelete();

		System.out.println("삭제 후 남은 예약리스트 검색해보기");
		List<Attraction> rs = AttractionDAO.getAllAttraction();
		// 주소값..........
		rs.stream().forEach(v -> System.out.println(v));

	}

}
