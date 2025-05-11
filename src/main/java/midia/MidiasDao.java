package midia;

import playlist.PlayList;
import usuario.Usuario;

import javax.persistence.EntityManager;
import java.util.List;

public class MidiasDao {
    private EntityManager em;

    public MidiasDao(EntityManager em) {
        this.em = em;
    }
    public void registerPlayList(Usuario usuario, PlayList playlist) {
        em.getTransaction().begin();
        em.persist(playlist);
        em.getTransaction().commit();
    }
    public List<Midias> buscarMusicas(TipoMidia tipo) {
        var sql = em.createQuery("SELECT m FROM Midias m WHERE m.tipo = :tipo", Midias.class)
                .setParameter("tipo", tipo);
        return sql.getResultList();
    }
}

