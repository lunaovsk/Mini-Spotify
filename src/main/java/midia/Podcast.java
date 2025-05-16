package midia;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PODCAST")
public class Podcast extends Midias {
	public Podcast(String titulo, String artista, Long duracao, Generos genero) {
		super(titulo, artista, duracao, genero);
	}
	public Podcast() {

	}
	@Override
	public String toString() {
		return super.toString() + "(" +
				"Podcast: " + getTitulo()
				+ ", Apresentador: " + getArtista()
				+ ", Duração: " + getDuracao()
				+ ", Assunto: " + getGenero().getDescricao() + ");";
	}
}
