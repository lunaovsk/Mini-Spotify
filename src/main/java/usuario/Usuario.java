package usuario;

import java.util.Set;
import javax.persistence.*;

import midia.Midias;
import playlist.PlayList;

@Entity
@Table (name = "ss_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	@OneToMany(mappedBy="usuario", fetch = FetchType.LAZY)
	private Set<PlayList> playlist;
	
	public Usuario () {

	}

	public Usuario(String nome, String email, Set<PlayList> playlist) {
		this.nome = nome;
		this.email = email;
		this.playlist = playlist;
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


	public Set<PlayList> getPlaylist() {
		for (PlayList pl : this.playlist) {
			Long duracaoPl = pl.getDuracao();
			System.out.println("PlayList (" + pl.getNome() + "| Duração: " + pl.converter(duracaoPl) + ")\n");
			for (Midias m : pl.getMidias()) {
				System.out.println("  Titulo: " + m.getTitulo() +
						" | Autor: " + m.getArtista() +
						" | Genero: " + m.getGenero());
			}
		}
		return this.playlist;
	}


	public void setPlaylist(Set<PlayList> playlist) {
		this.playlist = playlist;
	}



	@Override
	public String toString() {
		System.out.println("Usuario (Nome: " + nome + ")");
		getPlaylist();
		return "";
	}
}
