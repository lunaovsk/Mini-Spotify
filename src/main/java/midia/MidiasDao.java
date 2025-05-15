package midia;

import playlist.PlayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManager;
import java.util.List;

public class MidiasDao {
    private EntityManager em;

    public MidiasDao(EntityManager em) {
        this.em = em;
    }

    public List<Midias> buscarPorTipo(Class<? extends Midias> tipo) {
        var sql = em.createQuery("SELECT m FROM Midias m WHERE TYPE(m) = :tipo", Midias.class)
                .setParameter("tipo", tipo);
        return sql.getResultList();
    }

    public List<Midias> catalogo() {
        var sql = em.createQuery("SELECT m FROM Midias m", Midias.class);
        return sql.getResultList();
    }

    public List<Midias> buscarPorArtista(String artista) {
        var sql = em.createQuery("SELECT m FROM Midias m WHERE m.artista LIKE :artista", Midias.class)
                .setParameter("artista", "%" + artista + "%");
        return sql.getResultList();
    }

    public List<Midias> buscarPorTitulo(String titulo) {
        var sql = em.createQuery("SELECT m FROM Midias m WHERE m.titulo LIKE :titulo", Midias.class)
                .setParameter("titulo", "%" + titulo + "%");
        return sql.getResultList();
    }

    public void addMidia(Long idPlayList, Long idMidia) {
        em.getTransaction().begin();
        PlayList pl = em.find(PlayList.class, idPlayList);
        Midias m = em.find(Midias.class, idMidia);
        pl.getMidias().add(m);
        em.merge(pl);
        em.getTransaction().commit();
    }
}