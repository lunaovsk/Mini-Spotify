import java.util.Scanner;

import javax.persistence.EntityManager;

import controller.Sistema;
import usuario.UsuarioDao;
import util.JPAUtil;

public class Main {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
        Sistema sistema = new Sistema(em);
		Scanner scan = new Scanner(System.in);
		var count = true;
		var menu = "===============Screen Sound===============";
		var footer = "==========================================";
		
		while (count) {
			
			menu();
			System.out.println("\n1. Cadastrar usuario. \n"
					+ "2. Criar PlayList. \n"
					+ "3. Visite suas PlayList.\n"
					+ "4. Todas as musicas disponíveis.\n"
					+ "5. Sair"  );
			System.out.print("Qual opção deseja adicionar? ");
			
			try {
				int selecao = scan.nextInt();
				switch (selecao) {
				
				case 1: 
					menu();
					sistema.addUser();
					break;
				case 2:  
					menu();
					var login = sistema.login();
					sistema.criarPlaylist(login);
					break;

				case 3: 
					menu();
					sistema.viewPlayList();
					break;
				case 4: 
					menu();
					sistema.listarMusicas();
					break;
				case 5: 
					menu();
					sistema.encerrarSistema();
					return;
				default: 
					System.out.println("Escolha um número das opções listas: ");
				}
				
			} catch (NumberFormatException e) {
				System.out.println("Insira apenas números.");
				
			}
			
			
		}
		

	}
	public static void menu() {
		System.out.println("===============Screen Sound===============");
	}
	public static void footer() {
		System.out.println("\n\n==========================================");
	}
	public static void errorNãoMapeado () {
		System.out.println("Contate o administrador.");
	}

}
