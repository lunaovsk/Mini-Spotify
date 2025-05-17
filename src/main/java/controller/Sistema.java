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
	private Usuario usuario;
	private UsuarioDao usuarioDao;
	private PlayListDao listDao;
	private MidiasDao midiasDao;
	private EntityManager em;

	public Sistema(EntityManager em) {
		this.usuarioDao = new UsuarioDao(em);
		this.listDao = new PlayListDao(em);
		this.midiasDao = new MidiasDao(em);
		this.em = em;
	}

	public void catalogo() {
		List<Midias> m = midiasDao.catalogo();
		for (Midias midia : m) {
			System.out.println(midia);
		}
	}
	public void listarMusicas() {
		System.out.print("1. Visualizar todo o catálogo. \n" +
				"2. Visualizar por tipo.\n" +
				"3. Filtrar.\n" +
				"4. Sair.");
		System.out.print("Qual a opção desejada? ");
		int escolha = scan.nextInt();
		scan.nextLine();
		if (escolha == 1) {
			catalogo();
		} else if (escolha == 2) {
			listarPorTipo(true);
		} else if (escolha == 3) {
			filtroMusica();
		} else if (escolha == 4) {
			System.out.println("Retornando ao menu...");
		} else {
			System.out.println("Escolha uma opção válida");
			}
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
			Usuario novoUsuario = new Usuario(nome, email, new HashSet<>());
			usuarioDao.registerUser(novoUsuario);
			System.out.println("Usuário cadastrado com sucesso!");
		} else {
			System.out.println("E-mail já utilizado no sistema. Tente novamente.");
		}
	}
	public void criarPlaylist() {
		Usuario email = login();
		if (email == null) {
			System.out.println("Login cancelado. Retornando ao menu...");
			return;
		}
		em.clear();
		Usuario usuario = usuarioDao.findByEmail(email.getEmail());
		if (usuario == null) {
			return;
		}
		System.out.print("\nQual o nome que deseja para a PlayList? ");
		String nome = scan.nextLine().trim();
		PlayList novaPlaylist = new PlayList(nome,0L, usuario, new HashSet<>());
		listDao.registerPlayList(usuario, novaPlaylist);
		System.out.println("Playlist criada com sucesso!");
	}
	public void adicionarMidias() {
		Usuario email = login();
		if (email == null) {
			System.out.println("Login cancelado. Retornando ao menu...");
			return;
		}
		em.clear();
		Usuario usuario = usuarioDao.findByEmail(email.getEmail());
		List<PlayList> list = findPlayListsByUsuario(usuario);
		if (list.isEmpty()) {
			System.out.println("Não há playlist criadas em seu usuario.");
			return;
		}
		Long playListId = escolherPlayListId(list);
		if (playListId == null) {
			System.out.println("Nenhuma playlist selecionada.");
			return;
		}
		boolean continuarAdd = true;
		do {
			Long midia = escolherMidias();
			if (midia == null) {
				System.out.println("Nenhuma música encontrada.");
				continue;
			}
			midiasDao.addMidia(playListId, midia);
			System.out.println("Mídia adicionada com sucesso!");
			System.out.print("1. Adicionar mídia\n2. Sair\n" +
					"Qual opção desejada? ");
			int add = scan.nextInt();
			if (add == 2) {
				continuarAdd = false;
			}

		} while (continuarAdd);
		System.out.println("Retornando ao menu!");

	}
	public void viewPlayList() {
		Usuario email = login();
		if (email == null) {
			System.out.println("Login cancelado. Retornando ao menu...");
			return;
		}
		em.clear();
		List<Usuario> list = usuarioDao.findByYourPlayList(email);
		for (Usuario dto : list) {
			System.out.println(dto);
		}
	}
	public void removerMidia () {
		Usuario email = login();
		if (email == null) {
			System.out.println("Login cancelado. Retornando ao menu...");
			return;
		}
		em.clear();
		Usuario usuario = usuarioDao.findByEmail(email.getEmail());
		List<PlayList> list = findPlayListsByUsuario(usuario);
		if (list.isEmpty()) {
			System.out.println("Não há playlist criadas em seu usuario.");
			return;
		}
		Long playListId = escolherPlayListId(list);
		if (playListId == null) {
			System.out.println("Nenhuma playlist selecionada.");
			return;
		}
		boolean continuarAdd = true;
		do {
			viewPlayList();
			System.out.print("Digite o ID da mídia que deseja remover: ");
			Long midiaId = scan.nextLong();
			if (midiaId == null) {
				System.out.println("Nenhuma música encontrada.");
				continue;
			}
			midiasDao.removeMidia(playListId, midiaId);
			System.out.println("Mídia removida com sucesso!");
			System.out.print("1. Remover mídia\n2. Sair\n" +
					"Qual opção desejada? ");
			int add = scan.nextInt();
			if (add == 2) {
				continuarAdd = false;
			}

		} while (continuarAdd);
		System.out.println("Retornando ao menu!");
	}
	public List<PlayList> findPlayListsByUsuario(Usuario usuario) {
		Long id = usuario.getId();
		List<PlayList> playlist = listDao.findAll(id);
		if (playlist.isEmpty()) {
			System.out.println("Não há PlayList's criadas...\n");
			System.out.println("Retornando ao menu inicial...");
			return null;
		}
		for (PlayList pl : playlist) {
			System.out.println("ID: "+ pl.getId() + " PlayList: " + pl.getNome() + " Criada por: " + pl.getUsuario().getNome());
		}
		return playlist;
	}
	public Long escolherPlayListId(List<PlayList> list) {
		System.out.print("Escolha sua PlayList através do ID: ");
		Long escolha = scan.nextLong();
		scan.nextLine();
		for (PlayList pl : list) {
			if (pl.getId().equals(escolha)) {
				return escolha;
			}
		}
		System.out.println("ID inválido. Retornando ao menu...");
		return null;
	}
	public Long escolherMidias() {
		Class<? extends Midias> tipo = listarPorTipo(false);
		List<Midias> tipoMidia = midiasDao.buscarPorTipo(tipo);
		for (Midias m : tipoMidia) {
			System.out.println("ID: " + m.getId() + "| Título: " + m.getTitulo() + "| Autor: " + m.getArtista());
		}
		System.out.print("\nSelecione a música pelo ID: ");
		Long escolhido = scan.nextLong();
		for (Midias m : tipoMidia) {
			if (escolhido.equals(m.getId())) {
				return m.getId();
			}
		}
		return null;
	}
	public void filtroMusica () {
		List<Midias> m = midiasDao.catalogo();
		for (Midias midia : m) {
			System.out.println(midia);
		}
		System.out.print("\nSelecione um filtro para pesquisar uma mídia: "
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
			System.out.print("Digite o título da mídia: ");
			String titulo = scan.nextLine();
			m = midiasDao.buscarPorTitulo(titulo);
			if (m.isEmpty()) {
				System.out.println("Titulo não encontrado.");
			} else {
				for (Midias midia : m) {
					System.out.println(midia);
				}
			}
		}
	}
	public Class<? extends Midias> listarPorTipo (boolean mostrarMidias) {
			System.out.println("Tipos disponíveis:");
			System.out.println("1. Música");
			System.out.println("2. Podcast");
			System.out.println("3. Audiobook");
			System.out.print("Escolha o tipo de mídia: ");
			int tipoEscolhido = scan.nextInt();
			scan.nextLine();
			Class<? extends Midias> tipo = null;
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
			}
			if (mostrarMidias && tipo != null) {
				List<Midias> m = midiasDao.buscarPorTipo(tipo);
				for (Midias midia : m) {
					System.out.println(midia);
				}
			}
			return tipo;
	}
	public void encerrarSistema() {
		usuarioDao.encerrarSistema();
		System.out.println("Sistema encerrado.");
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
					+ "6. Visualizar todas as Playlist.\n"
					+ "7. Sair.");
			System.out.print("Qual opção deseja? ");
			selecao = scan.nextInt();
			System.out.println("\n==========================================");
		} catch (NumberFormatException e) {
			System.out.println("Insira apenas números.");
		}
		scan.nextLine();
		return selecao;
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
	public Usuario testeUser() {
		System.out.println("Usuário não encontrado.");
		System.out.println("Pressione Enter para voltar ao menu:");
		scan.nextLine().trim();
		System.out.println("Retornando ao menu...");
		return null;
	}
	public int opcao() {

		int selecao = 0;
		try {
			System.out.println("===============Screen Sound===============");
			System.out.println("\n1. Criar PlayList. \n" + "2. Adicionar músicas. \n" + "3. Remover midia.\n"+"4. Sair.");
			System.out.print("\nQual opção deseja? ");
			selecao = scan.nextInt();
			System.out.println("\n==========================================");
		}catch (NumberFormatException e) {
			System.out.println("Insira apenas números.");
		}
		scan.nextLine();
		return selecao;

	}

	public void viewAll() {
		List<Usuario> list = listDao.findByPlayList();
		if (list.isEmpty()) {
			System.out.println("Não há PlayList cadastradas no sistema.");
			return;
		}
		for (Usuario pl : list) {
			System.out.println(pl);
		}
	}
}



