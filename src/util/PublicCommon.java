//JPA사용하는 코드들이 공통적으로 사용하는 로직
//자원반환도 고려
/*
 * EntityManager는 메소드별 개별 생성 및 활용 후 자원반한
 * -Connection 공유 금지와 동일한 개념
 * 
 * EntityManagerFactory는 실행되는 서버 내부에 한개의 객체 생성 및 활용
 * 
 */

package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PublicCommon {
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracleDB");
	
	public static EntityManager getEntityManager() {
		//EntityManagerFactory 객체로 부터 EntityManager 객체 반환
		return emf.createEntityManager();
	}
	
	public static void close() {
		//EntityManagerFactory 자원반환
		emf.close();
		emf = null;
	}
}
