package usuario;

import playlist.PlayList;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

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

	public void encerrarSistema() {
		em.close();
	}

	public List<Usuario> findByYourPlayList(Usuario usuario) {
		var sql = em.createQuery("""
        SELECT DISTINCT u
        FROM Usuario u
        LEFT JOIN FETCH u.playlist pl
        LEFT JOIN FETCH pl.midias m
        WHERE u.email = :email
        """, Usuario.class)
				.setParameter("email", usuario.getEmail());
		return sql.getResultList();
	}

}
