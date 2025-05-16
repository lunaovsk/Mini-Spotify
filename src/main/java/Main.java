import javax.persistence.EntityManager;
import controller.Sistema;
import util.JPAUtil;

public class Main {

	public static void main(String[] args) {
		EntityManager em = new JPAUtil().getEntityManager();
        Sistema sistema = new Sistema(em);
		try {
			while (true) {
				int selecao = sistema.menu();
				switch (selecao) {
					case 1:
						sistema.catalogo();
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
						sistema.viewAll();
						break;
					case 7:
						sistema.encerrarSistema();
						return;
					default:
						System.out.println("Escolha um número das opções listas: ");

				}
			}
		} catch (Exception e) {
			System.out.println("Erro inesperado: " + e.getMessage());
		}
	}
}
