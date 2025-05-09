package controller;

import java.util.ArrayList;
import java.util.Scanner;

import javax.persistence.EntityManager;

import usuario.Usuario;
import usuario.UsuarioDao;

public class Sistema {

	Scanner scan = new Scanner(System.in);
	private EntityManager manager;
	private Usuario usuario;
	private UsuarioDao usuarioDao;

	public Sistema(UsuarioDao usuarioDao) {
		this.usuarioDao = usuarioDao;
	}

	public void addUser() {
		System.out.print("\nDigite o seu nome completo: ");
		String nome = scan.nextLine().toLowerCase();
		System.out.print("Digite seu e-mail: ");
		String email = scan.nextLine().toLowerCase();
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
		System.out.println("Função ainda não implementada.");
	}

	public void encerrarSistema() {
		manager.close();
		System.out.println("Sistema encerrado.");
	}

	public void login() {

	}

	public void listarMusicas() {

	}
}
