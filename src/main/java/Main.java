import java.util.Scanner;

import javax.persistence.EntityManager;

import controller.Sistema;
import usuario.Usuario;
import usuario.UsuarioDao;
import util.JPAUtil;

public class Main {

	public static void main(String[] args) {
		
		EntityManager em = new JPAUtil().getEntityManager();
        Sistema sistema = new Sistema(em);
<<<<<<< Updated upstream
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
=======
		try {
			while (true) {
				int selecao = sistema.menu();
				switch (selecao) {
					case 1:

						break;
					case 2:
						sistema.listarMusicas();
						break;
					case 3:
						sistema.addUser();
						break;
					case 4:
						int opcao = sistema.opcao();
						switch (opcao) {
							case 1:
								sistema.criarPlaylist();
								break;
							case 2:
								sistema.adicionarMidias();
								break;
							case 3:
								break;
							default:
								System.out.println("Escolha um número das opções listas: ");
						}
						break;
					case 5:
						sistema.viewPlayList();
						break;
					case 6:
						sistema.encerrarSistema();
						return;
					default:
						System.out.println("Escolha um número das opções listas: ");
>>>>>>> Stashed changes
				}
			}
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
	}
}
