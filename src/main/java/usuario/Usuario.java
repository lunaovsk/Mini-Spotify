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
		return this.playlist;
	}
	public String mostrarPlaylists() {
		if (playlist == null || playlist.isEmpty()) {
			return " Você não possuí PlayList";
		}

		StringBuilder sb = new StringBuilder();
		for (PlayList pl : playlist) {
			sb.append("\nID: ").append(pl.getId())
					.append(" | PlayList (").append(pl.getNome())
					.append("| Duração: ").append(pl.converter(pl.getDuracao()))
					.append(")\n\n");

			if (pl.getMidias() == null || pl.getMidias().isEmpty()) {
				sb.append("  (Nenhuma mídia adicionada ainda)\n");
			} else {
				for (Midias m : pl.getMidias()) {
					sb.append("  ID: ").append(m.getId())
							.append(" | Titulo: ").append(m.getTitulo())
							.append(" | Autor: ").append(m.getArtista())
							.append(" | Genero: ").append(m.getGenero().getDescricao())
							.append(".\n");
				}
			}
		}
		return sb.toString();
	}


	public void setPlaylist(Set<PlayList> playlist) {
		this.playlist = playlist;
	}



	@Override
	public String toString() {
		return "Usuario (Nome: " + nome + ")\n" + mostrarPlaylists();

	}
}
