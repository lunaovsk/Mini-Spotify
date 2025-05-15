package playlist;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import midia.Midias;
import usuario.Usuario;

@Entity
@Table(name = "ss_playlist")
public class PlayList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
<<<<<<< Updated upstream
	private Duration duracaoPlay;
=======

>>>>>>> Stashed changes
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToMany
	@JoinTable(name = "ss_playlist_midia",
			joinColumns = @JoinColumn(name = "playlist_id"),
			inverseJoinColumns = @JoinColumn(name = "midia_id"))
	private List<Midias> midias = new ArrayList<>();

	public PlayList() {}

	public PlayList(String nome, Usuario usuario, List<Midias> midias) {
		this.nome = nome;
		this.usuario = usuario;
		this.midias = midias;
	}
	public PlayList(String nome, Usuario usuario, Duration duracaoPlay, List<Midias> midias) {
		this.nome = nome;
		this.usuario = usuario;
		this.duracaoPlay = duracaoPlay;
		this.midias = midias;
	}

	// Getters e setters

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Midias> getMidias() {
		return midias;
	}

	public void setMidias(List<Midias> midias) {
		this.midias = midias;
	}
<<<<<<< Updated upstream

	public Duration getDuracaoPlay() {
		return duracaoPlay;
	}

	public void setDuracaoPlay(Duration duracaoPlay) {
		this.duracaoPlay = duracaoPlay;
	}
	
	
	
	
	
=======
>>>>>>> Stashed changes

	public int getDuracaoTotal() {
		return midias.stream().mapToInt(Midias::getDuracao).sum();
	}

	@Override
	public String toString() {
		return "PlayList{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", usuario=" + (usuario != null ? usuario.getNome() : "null") +
				", duracaoTotal=" + getDuracaoTotal() +
				", midias=" + midias +
				'}';
	}
}
