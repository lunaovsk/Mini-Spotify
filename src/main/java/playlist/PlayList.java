package playlist;


import java.util.Set;

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
	private Long duracao;
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@ManyToMany
	@JoinTable(name = "ss_playlist_midia",
			joinColumns = @JoinColumn(name = "playlist_id"),
			inverseJoinColumns = @JoinColumn(name = "midia_id"))
	private Set<Midias> midias;

	public PlayList() {}

	public PlayList(String nome, Long duracao, Usuario usuario, Set<Midias> midias) {
		this.nome = nome;
		this.duracao = 0L;
		this.usuario = usuario;
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

	public Set<Midias> getMidias() {
		return midias;
	}

	public void setMidias(Set<Midias> midias) {
		this.midias = midias;
	}

	public String converter(Long duracao) {
		Long horas = duracao / 3600;
		Long minutos = (duracao % 3600) / 60;
		Long segundos = duracao % 60;
		return String.format("%02d:%02d:%02d", horas, minutos, segundos);
	}

	public Long getDuracao() {
		return duracao;
	}

	public void setDuracao(Long duracao) {
		this.duracao = duracao;
	}
}
