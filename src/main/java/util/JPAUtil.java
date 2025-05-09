package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
private EntityManagerFactory factory;
	
	public JPAUtil() {
		this.factory = Persistence.createEntityManagerFactory("spotify");
	}
	public EntityManager getEntityManager() {
		return factory.createEntityManager();
	}

}
