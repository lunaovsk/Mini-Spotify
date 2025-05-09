package playlist;

import javax.persistence.EntityManager;

public class PlayListDao {
	
	private EntityManager em;
	
	public PlayListDao(EntityManager em) {
		this.em = em;
	}
	public void registerUser(PlayList playlist) {
		em.getTransaction().begin();
		em.persist(playlist);
		em.getTransaction().commit();
	}
	public PlayList buscarPlayList(Long id) {
		return em.find(PlayList.class, id);
	}
	


}
