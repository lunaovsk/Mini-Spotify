package midia;

import playlist.PlayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("MUSICA")
public class Musica extends Midias {

	public Musica(String titulo, String artista, int duracao, GenerosMusicais genero, List<PlayList> playlist) {
		super(titulo, artista, duracao, genero, playlist);
	}
	public Musica() {
    }
}
