package midia;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import playlist.PlayList;

@Entity
@Table(name = "ss_midia")
public class Midias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String artista;
	private LocalTime duracao;
	@Enumerated(EnumType.STRING)
	private GenerosMusicais genero;
	@ManyToMany(mappedBy = "midias")
	private List<PlayList> playlist = new ArrayList<>();
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_midia")
	private TipoMidia tipo;

	public Midias() {

	}

	public Midias(String titulo, String artista, LocalTime duracao, GenerosMusicais genero) {
		this.titulo = titulo;
		this.artista = artista;
		this.duracao = duracao;
		this.genero = genero;
	}

	public Midias(String titulo, String artista, LocalTime duracao, TipoMidia tipo) {
		this.titulo = titulo;
		this.artista = artista;
		this.duracao = duracao;
		this.tipo = tipo;
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

	public LocalTime getDuracao() {
		return duracao;
	}

	public void setDuracao(LocalTime duracao) {
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

	public TipoMidia getTipo() {
		return tipo;
	}

	public void setTipo(TipoMidia tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Midias{" +
				"titulo='" + titulo + '\'' +
				", artista='" + artista + '\'' +
				", duracao=" + duracao +
				", tipo=" + tipo +
				'}';
	}

	public String toStringMusicas() {
		return "Músicas {" +
				"titulo='" + titulo + '\'' +
				", artista='" + artista + '\'' +
				", duracao=" + duracao +
				", genero=" + genero +
				", tipo=" + tipo +
				'}';
	}
}
