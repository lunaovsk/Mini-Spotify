package controller;

import java.util.*;

import javax.persistence.EntityManager;

import midia.GenerosMusicais;
import midia.Midias;
import midia.MidiasDao;
import midia.TipoMidia;
import playlist.PlayList;
import playlist.PlayListDao;
import usuario.Usuario;
import usuario.UsuarioDao;

public class Sistema {

	Scanner scan = new Scanner(System.in);
	private EntityManager manager;
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private PlayListDao listDao;
	private MidiasDao midiasDao;

	public Sistema(EntityManager em) {
		this.usuarioDao = new UsuarioDao(em);
		this.listDao = new PlayListDao(em);
		this.midiasDao = new MidiasDao(em);
	}

	public void addUser() {
		System.out.print("\nDigite o seu nome completo: ");
		String nome = scan.nextLine().toLowerCase();
		System.out.print("Digite seu e-mail: ");
		String email = scan.nextLine().toLowerCase();
		if (nome != null && email != null) {
			Usuario userExiste = usuarioDao.findByEmail(email);
			if (userExiste == null) {
				Usuario novoUsuario = new Usuario(nome, email, new ArrayList<>());
				usuarioDao.registerUser(novoUsuario);
				System.out.println("Usuário cadastrado com sucesso!");
			} else {
				System.out.println("E-mail já utilizado no sistema. Tente novamente.");
			}
		} else {
			System.out.println("Nome e email são requeridos.");
		}
	}

	public void criarPlaylist(Usuario usuario) {
		if (usuario != null) {
			System.out.print("Qual o nome que deseja para a PlayList? ");
			String nome = scan.nextLine().toLowerCase();
			PlayList novaPlaylist = new PlayList(nome, usuario, new ArrayList<>());
			listDao.registerPlayList(usuario, novaPlaylist);
			System.out.println("Playlist criada com sucesso!");
			try {
				while (true) {
					System.out.println("Deseja adicionar musicas a sua PlayList? (sim/não) ");
					String p = scan.nextLine().toLowerCase();
					if (p.equals("sim")) {
						System.out.println("Transferindo para nosso catálogo de mídias");
						listarMusicas();
					} else if (p.equals("não") || p.equals("nao")) {
						System.out.println("Retornando ao menu inicial...");
						break;
						}
					}
			} catch(Exception e) {
				System.out.println("Digite apenas \"sim\" ou \"não\"");
			}
		} else {
			login();
		}
	}



	public void encerrarSistema() {
		usuarioDao.encerrarSistema();
		System.out.println("Sistema encerrado.");
	}
	public Usuario login () {
		System.out.print("Antes de prosseguir, faça login com seu email: ");
		String email = scan.nextLine().toLowerCase(Locale.ROOT);
		Usuario usuario = usuarioDao.findByEmail(email);
		if (usuario == null) {
			System.out.println("Usuario não cadastrado ou credenciais inválidas");
			System.out.println("Não possuí conta? Digite \"sim\" para ir para aba de criação ou aperte enter para retornar ao Menu.");
			String sim = scan.nextLine().toLowerCase();
			if (sim.equals("sim")) {
				System.out.println("Transferindo para o cadastro...");
				addUser();
			}
			System.out.println("Retornando ao menu...");
		}
		return usuario;
	}
	public void viewPlayList() {
		System.out.print("Digite seu e-mail para visualizar suas PlayList: ");
		String email = scan.nextLine().toLowerCase(Locale.ROOT);
		Usuario usuario = usuarioDao.findByEmail(email);
		if (usuario == null){
			System.out.println("Usuario não cadastrado ou credenciais inválidas");
			System.out.println("Não possuí conta? Digite \"sim\" para ir para aba de criação ou aperte enter para retornar ao Menu.");
			String sim = scan.nextLine().toLowerCase();
			if (sim.equals("sim")) {
				System.out.println("Transferindo para o cadastro...");
				addUser();
			}
			System.out.println("Retornando ao menu...");
		} else  {
			System.out.println(usuario);
			if (usuario.getPlaylist().isEmpty()){
				System.out.print("Deseja criar uma PlayList? (sim/não)");
				String p = scan.nextLine().toLowerCase();
				try {
					if (p.equals("sim")) {
						System.out.println("Transferindo para criação de PlayList: ");
						criarPlaylist(usuario);
					} else if (p.equals("não") || p.equals("nao")) {
						System.out.println("Retornando ao menu inicial...");
					}
				} catch (Exception e) {
					System.out.println("Digite apenas \"sim\" ou \"não\"");
				}


			}
		}
	}
	public void addMusicaPlayList () {

	}

	public void listarMusicas() {


		for (TipoMidia t : TipoMidia.values()) {
			System.out.println(t.getIndice() + " - " + t.getDescricao());

		}
		System.out.print("Escolha o tipo de midia que deseja acessar: ");
		try {
			int i = scan.nextInt();
			scan.nextLine();

			TipoMidia tipoEscolhido = TipoMidia.porIndiceTipo(i);

			if (tipoEscolhido == null) {
				System.out.println("Tipo inválido.");
				return;
			}

//			if (tipoEscolhido == TipoMidia.MUSICA) {
//				System.out.print("Escolha o gênero de música que deseja acessar: ");
//				for (GenerosMusicais g : GenerosMusicais.values()) {
//					System.out.println(g.getIndice() + " - " + g.getDescricao());
//				}
//				int j = scan.nextInt();
//				scan.nextLine();
//				GenerosMusicais generosEscolhido = GenerosMusicais.porIndice(j);
//				List<Midias> m = midiasDao.buscarMusicas(generosEscolhido);
//				System.out.println("\n" + generosEscolhido.getDescricao() + " encontradas: ");
//				for (Midias midia : m) {
//					System.out.println(midia.toStringMusicas());
//				}}

			else {
				List<Midias> m = midiasDao.buscarMusicas(tipoEscolhido);
				System.out.println("\n" + tipoEscolhido.getDescricao() + " encontradas: ");
				for (Midias midia : m) {
					System.out.println(midia);
				}
			}

		} catch (InputMismatchException e) {
			System.out.println("Digite apenas números.");
			scan.nextLine();
		}
	}
}
