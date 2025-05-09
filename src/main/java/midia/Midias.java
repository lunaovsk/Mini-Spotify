package midia;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import playlist.PlayList;

@Entity
@Table(name = "ss_midia")
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
		
		

}
