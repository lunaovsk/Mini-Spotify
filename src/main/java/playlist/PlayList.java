package playlist;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import midia.Midias;
import usuario.Usuario;

@Entity
@Table(name = "ss_playlist")
public class PlayList {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Duration duracaoPlay;
	@ManyToOne
	@JoinColumn(name = "ss_usuario_id")
	private Usuario usuario;
	@ManyToMany
	@JoinTable(name = "ss_playlist_midia", 
	joinColumns = @JoinColumn(name = "playlist_id"),
	inverseJoinColumns = @JoinColumn(name = "midia_id"))
	private List<Midias> midias;
	
	public PlayList(String nome, Usuario usuario, List<Midias> midias) {
		this.nome = nome;
		this.usuario = usuario;
		this.midias = new ArrayList<>();
	}
	public PlayList(String nome, Usuario usuario, Duration duracaoPlay, List<Midias> midias) {
		this.nome = nome;
		this.usuario = usuario;
		this.duracaoPlay = duracaoPlay;
		this.midias = midias;
	}

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

	public Duration getDuracaoPlay() {
		return duracaoPlay;
	}

	public void setDuracaoPlay(Duration duracaoPlay) {
		this.duracaoPlay = duracaoPlay;
	}
	
	
	
	
	

}
