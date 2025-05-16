package midia;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("AUDIOBOOK")
public class Audiobook extends Midias {
	
	public Audiobook(String titulo, String artista, Long duracao, Generos genero) {
		super(titulo, artista, duracao, genero);
	}
	public Audiobook () {

	}
	@Override
	public String toString() {
		return super.toString() + "(" +
				"Audiobook: " + getTitulo()
				+ ", Voz: " + getArtista()
				+ ", Duração: " + getDuracao()
				+ ", Gêneros Literário: " + getGenero().getDescricao() + ");";
	}
}
