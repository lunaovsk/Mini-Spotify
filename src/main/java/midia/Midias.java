package midia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import playlist.PlayList;

@Entity
@Table(name = "ss_midia")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_midia", discriminatorType = DiscriminatorType.STRING)
public class Midias {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String artista;
	private int duracao;
	@Enumerated(EnumType.STRING)
	private GenerosMusicais genero;
	@ManyToMany(mappedBy = "midias")
	private List<PlayList> playlist = new ArrayList<>();

	public Midias(String titulo, String artista, int duracao, GenerosMusicais genero, List<PlayList> playlist) {
		this.titulo = titulo;
		this.artista = artista;
		this.duracao = duracao;
		this.genero = genero;
		this.playlist = playlist;
	}

	public Midias() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getArtista() {
		return artista;
	}

	public void setArtista(String artista) {
		this.artista = artista;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

	public GenerosMusicais getGenero() {
		return genero;
	}

	public void setGenero(GenerosMusicais genero) {
		this.genero = genero;
	}

	public List<PlayList> getPlaylist() {
		return playlist;
	}

	public void setPlaylist(List<PlayList> playlist) {
		this.playlist = playlist;
	}

	@Override
	public String toString() {
		return "Midias{" +
				"titulo='" + titulo + '\'' +
				", artista='" + artista + '\'' +
				", duracao=" + duracao +
				", genero=" + genero;
	}
}


