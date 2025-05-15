package midia;

import playlist.PlayList;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;

@Entity
@DiscriminatorValue("AUDIOBOOK")
public class Audiobook extends Midias {
	
	public Audiobook(String titulo, String artista, int duracao, GenerosMusicais genero, List<PlayList> playlist) {
		super(titulo, artista, duracao, genero, playlist);
	}
	public Audiobook () {

	}
}
