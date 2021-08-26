/**
 * JPA 사용시 코드의 공통 로직
 */
package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PublicCommon {
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("oracleDB");
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void close() {
		emf.close();
		emf = null;
	}
	
}