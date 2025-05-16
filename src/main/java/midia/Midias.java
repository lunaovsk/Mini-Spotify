package midia;

import javax.persistence.*;

@Entity
@Table(name = "ss_midia")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_midia", discriminatorType = DiscriminatorType.STRING)
public abstract class Midias {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	private String artista;
	private Long duracao;
	@Enumerated(EnumType.STRING)
	private Generos genero;

	public Midias(String titulo, String artista, Long duracao, Generos genero) {
		this.titulo = titulo;
		this.artista = artista;
		this.duracao = duracao;
		this.genero = genero;
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

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
		this.duracao = duracao;
	}

	public Generos getGenero() {
		return genero;
	}

	public void setGenero(Generos genero) {
		this.genero = genero;
	}


	@Override
	public String toString() {
		return "Biblioteca: ";
	}

}


