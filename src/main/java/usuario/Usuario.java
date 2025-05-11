package usuario;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import playlist.PlayList;



@Entity
@Table (name = "ss_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	@OneToMany(mappedBy="usuario")
	private List<PlayList> playlist;
	
	public Usuario () {

	}

	public Usuario(String nome, String email, List<PlayList> playlist) {
		this.nome = nome;
		this.email = email;
		this.playlist = new ArrayList<>();
	}


	public Long getId() {
		return id;
	}


	public void setId(Long idUsuario) {
		this.id = idUsuario;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	public List<PlayList> getPlaylist() {
		return playlist;
	}


	public void setPlaylist(List<PlayList> playlist) {
		this.playlist = playlist;
	}

	@Override
	public String toString() {
		return "Usuario{" +
				"nome='" + nome + '\'' +
				", email='" + email + '\'' +
				"\nplaylist=" + playlist +
				'}';
	}
}
