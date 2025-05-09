package usuario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

public class UsuarioDao {
	
	private EntityManager em;
	
	
	public UsuarioDao(EntityManager em) {
		this.em = em;
	}
	public void registerUser(Usuario usuario) {
		em.getTransaction().begin();
		em.persist(usuario);
		em.getTransaction().commit();
	}
	public Usuario buscarUser(Long id) {
		return em.find(Usuario.class, id);
	}
	public Usuario findByEmail(String email) {
        try {
            var sql = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class)
                     .setParameter("email", email)
                     .getSingleResult();
            return sql;
        } catch (NoResultException e) {
            return null; 
        }
    }
	public Boolean removerPlayList(Long usuarioId, Long playlistId) {
		Usuario usuario = buscarUser(usuarioId);
		
		if (usuario != null) {
			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public void encerrarSistema() {
		em.close();
	}
	

}
