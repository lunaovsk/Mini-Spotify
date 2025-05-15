package playlist;

import usuario.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class PlayListDao {
	
	private EntityManager em;
	
	public PlayListDao(EntityManager em) {
		this.em = em;
	}
	public void registerPlayList(Usuario usuario, PlayList playlist) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.persist(playlist);
		em.getTransaction().commit();
	}
	public PlayList findByPlayName(String nome) {

		return em.find(PlayList.class, nome);
	}

	public List<PlayList> findAll (Long usuarioId) {
		List<PlayList> playLists = em.createQuery("SELECT p FROM PlayList p WHERE p.usuario.id = :id", PlayList.class)
				.setParameter("id", usuarioId)
				.getResultList();
		return playLists;
	}



	


}
