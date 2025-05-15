package midia;

import playlist.PlayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("PODCAST")
public class Podcast extends Midias {
	public Podcast(String titulo, String artista, int duracao, GenerosMusicais genero, List<PlayList> playlist) {
		super(titulo, artista, duracao, genero, playlist);
	}
	public Podcast() {

	}
}
