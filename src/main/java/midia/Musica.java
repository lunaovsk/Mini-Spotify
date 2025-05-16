package midia;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MUSICA")
public class Musica extends Midias {

	public Musica(String titulo, String artista, Long duracao, Generos genero) {
		super(titulo, artista, duracao, genero);
	}
	public Musica() {
    }


	@Override
	public String toString() {
		return super.toString() + "(" +
				"Música: " + getTitulo()
				+ ", Artista: " + getArtista()
				+ ", Duração: " + getDuracao()
				+ ", Genero: " + getGenero().getDescricao() + ");";
	}
}
