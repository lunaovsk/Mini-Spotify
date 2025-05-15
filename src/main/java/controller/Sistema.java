package controller;

import java.util.*;

import javax.persistence.EntityManager;

import midia.*;
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
	private PlayList list;

	public Sistema(EntityManager em) {
		this.usuarioDao = new UsuarioDao(em);
		this.listDao = new PlayListDao(em);
		this.midiasDao = new MidiasDao(em);
	}
	public Sistema (Usuario usuario, PlayList list) {
		this.usuario = usuario;
		this.list = list;
	}


	public void addUser() {
		System.out.print("\nDigite o seu nome completo: ");
		String nome = scan.nextLine().trim();
		System.out.print("Digite seu e-mail: ");
		String email = scan.nextLine().trim().toLowerCase();
		if (nome.isEmpty() || email.isEmpty()) {
			System.out.println("Nome e e-mail são obrigatórios.");
			return;
		}
		Usuario userExiste = usuarioDao.findByEmail(email);
		if (userExiste == null) {
			Usuario novoUsuario = new Usuario(nome, email, new ArrayList<>());
			usuarioDao.registerUser(novoUsuario);
			System.out.println("Usuário cadastrado com sucesso!");
		} else {
			System.out.println("E-mail já utilizado no sistema. Tente novamente.");
		}
	}
	public void criarPlaylist() {
		System.out.print("Digite seu e-mail para criar a playlist: ");
		String email = scan.nextLine().toLowerCase();
		Usuario usuario = usuarioDao.findByEmail(email);
		if (usuario == null) {
			usuario = testeUser();
			if (usuario == null) return;
		}
		System.out.print("\nQual o nome que deseja para a PlayList? ");
		String nome = scan.nextLine().trim();
		PlayList novaPlaylist = new PlayList(nome, usuario, new ArrayList<>());
		listDao.registerPlayList(usuario, novaPlaylist);
		System.out.println("Playlist criada com sucesso!");
		while (true) {
			System.out.print("Deseja adicionar músicas à sua PlayList? (sim/não): ");
			String p = scan.nextLine().trim().toLowerCase();
			if (p.equals("sim")) {
				System.out.println("Transferindo para nosso catálogo de mídias...");
				System.out.println("==========================================");
				listarMusicas();
				adicionarMidias();
			} else if (p.equals("não") || p.equals("nao")) {
				System.out.println("Retornando ao menu inicial...");
				break;
			} else {
				System.out.println("Entrada inválida. Digite apenas \"sim\" ou \"não\".");
			}
		}
	}
	public void adicionarMidias() {
		System.out.print("Digite seu e-mail para criar a playlist: ");
		String email = scan.nextLine().toLowerCase();
		Usuario usuario = usuarioDao.findByEmail(email);
		if (usuario == null) {
			usuario = testeUser();
			if (usuario == null) return;
		}
		List<PlayList> list = findPlayListsByUsuario(usuario);
		if (list.isEmpty()) {
			System.out.println("Não há playlist criadas em seu usuario.");
			return;
		}
		PlayList list1 = escolherPlayListId(usuario);


		System.out.print("\nSelecione um filtro para adicionar a sua playlist:"
				+ "\n1. Artista" + "\n2. Título"
				+ "\nSelecione a opção desejada: ");
			try {
				int tipo = scan.nextInt();
				scan.nextLine();
				List<Midias> m = new ArrayList<>();
				switch (tipo) {
					case 1 -> {
						System.out.print("Digite o nome do artista: ");
						String artista = scan.nextLine().toLowerCase();

					}
					case 2 -> {
						System.out.print("Digite o título da música: ");
						String titulo = scan.nextLine().toLowerCase();

					}
					default -> {
						System.out.println("Opção inválida.");
						return;
					}
				}
				for (Midias midia : m) {
					System.out.println(midia.toString());
				}
			} catch (InputMismatchException e) {
				System.out.println("Digite apenas números válidos.");
				scan.nextLine();
			}
	}


	public void encerrarSistema() {
		usuarioDao.encerrarSistema();
		System.out.println("Sistema encerrado.");
	}

	public Usuario login() {
		System.out.print("Antes de prosseguir, faça login com seu e-mail: ");
		String email = scan.nextLine().toLowerCase();
		Usuario usuario  = usuarioDao.findByEmail(email);
		if (usuario == null) {
			return testeUser();
		}

		this.usuario = usuario;
		System.out.println("Login realizado com sucesso! Bem-vindo(a), " + this.usuario.getNome());
		return this.usuario;
	}

	public void viewPlayList() {
		System.out.print("Digite seu e-mail para visualizar suas PlayList: ");
		String email = scan.nextLine().toLowerCase(Locale.ROOT);
		Usuario usuario = usuarioDao.findByEmail(email);
		if (usuario == null) {
			testeUser();
			return;
		}
		findPlayListsByUsuario(usuario);
	}

	public List<PlayList> findPlayListsByUsuario(Usuario usuario) {
		Long id = usuario.getId();
		List<PlayList> playlist = listDao.findAll(id);
		if (playlist.isEmpty()) {
			System.out.println("Não há PlayList's criadas...\n");
			System.out.println("Retornando ao menu inicial...");
			return null;

		}
		System.out.println("Suas PlayList cadastradas: ");
		for(PlayList pl : playlist) {
			System.out.println("ID: " + pl.getId() + "| Nome: " + pl.getNome());
		}
		System.out.println("ID inválido. Retornando ao menu...");
		return null;
	}
	public PlayList escolherPlayListId(Usuario usuario) {
		List<PlayList> playlist = findPlayListsByUsuario(usuario);
		System.out.print("Escolha sua PlayList através do ID: ");
		Long escolha = scan.nextLong();
		scan.nextLine();
		for (PlayList pl : playlist) {
			if (pl.getId().equals(escolha)) {
				return pl;
			}
		}
		System.out.println("ID inválido. Retornando ao menu...");
		return null;
	}



	public void addMusicaPlayList() {

	}

	public void listarMusicas() {
		System.out.print("1. Visualizar todo o catálogo. \n" +
				"2. Visualizar por tipo.\n" +
				"3. Filtrar.\n");
		System.out.print("Qual a opção desejada? ");
		int escolha = scan.nextInt();
		scan.nextLine();
		if (escolha == 1) {
			List<Midias> m = midiasDao.catalogo();
			for (Midias midia : m) {
				System.out.println(midia);
			}
		} else if (escolha == 2) {
			System.out.println("Tipos disponíveis:");
			System.out.println("1. Música");
			System.out.println("2. Podcast");
			System.out.println("3. Audiobook");
			System.out.print("Escolha o tipo de mídia: ");

			int tipoEscolhido = scan.nextInt();
			scan.nextLine();

			Class<? extends Midias> tipo;
			switch (tipoEscolhido) {
				case 1:
					tipo = Musica.class;
					break;
				case 2:
					tipo = Podcast.class;
					break;
				case 3:
					tipo = Audiobook.class;
					break;
				default:
					System.out.println("Tipo inválido.");
					return;
			}
			List<Midias> m = midiasDao.buscarPorTipo(tipo);
			for(Midias midia : m) {
				System.out.println(midia);
			}
		} else if (escolha == 3) {
			List<Midias> m = midiasDao.catalogo();
			for (Midias midia : m) {
				System.out.println(midia);
			}
			System.out.print("\nSelecione um filtro para adicionar à sua playlist:"
					+ "\n- Artista" + "\n- Título"
					+ "\nDigite o filtro desejado: ");
			String filtro = scan.nextLine().toLowerCase().trim();

			if (filtro.equals("artista")) {
				System.out.print("Digite o nome do artista: ");
				String nomeArtista = scan.nextLine();
				m = midiasDao.buscarPorArtista(nomeArtista);
				if (m.isEmpty()) {
					System.out.println("Artista não encontrado.");
				} else {
					for (Midias midia : m) {
						System.out.println(midia);
					}
				}

			} else if (filtro.equals("titulo")) {
				System.out.print("Digite o título da música: ");
				String titulo = scan.nextLine();
				m = midiasDao.buscarPorTitulo(titulo);
				if (m.isEmpty()) {
					System.out.println("Titulo não encontrado.");
				}
				else {
					for (Midias midia : m) {
						System.out.println(midia);
					}
				}

			} else {
				System.out.println("Escolha uma opção válida");
			}
		}
	}

	public int menu() {
		int selecao = 0;

		try {
			System.out.println("===============Screen Sound===============");
			System.out.println(
					"\n1. Catálogo de Mídias. \n"
					+ "2. Pesquisa por tipo. \n"
					+ "3. Cadastrar usuario.\n"
					+ "4. PlayList.\n"
					+ "5. Visite suas PlayLists.\n"
					+ "6. Sair");
			System.out.print("Qual opção deseja? ");
			selecao = scan.nextInt();
			System.out.println("==========================================");
		} catch (NumberFormatException e) {
			System.out.println("Insira apenas números.");
		}
		scan.nextLine();
		return selecao;

	}

	public Usuario testeUser() {
		System.out.println("Usuário não encontrado.");
		System.out.println("Não possui conta? Digite \"sim\" para ir para o cadastro ou pressione Enter para voltar ao menu:");
		String resposta = scan.nextLine().trim().toLowerCase();

		if (resposta.equals("sim")) {
			addUser();
			return login();
		}

		System.out.println("Retornando ao menu...");
		return null;
	}

	public int opcao() {

		int selecao = 0;
		try {
			System.out.println("===============Screen Sound===============");
			System.out.println("\n1. Criar PlayList. \n" + "2. Adicionar músicas. \n" + "3. Sair");
			System.out.print("Qual opção deseja? ");
			selecao = scan.nextInt();
			System.out.println("==========================================");
		}catch (NumberFormatException e) {
			System.out.println("Insira apenas números.");
		}
		scan.nextLine();
		return selecao;

	}
}



