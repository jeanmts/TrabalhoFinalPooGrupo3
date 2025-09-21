package Main;

import java.time.LocalDate;
import java.util.Scanner;

import Enum.EnumParentesco;
import Exception.DependenteException;
import Model.Dependente;
import Model.EntradaArquivo;
import Model.FolhaPagamento;
import Model.Funcionario;
import Repository.DependenteDao;
import Repository.FolhaPagamentoDao;
import Repository.FuncionarioDao;

public class Aplicacao {
	public static void main(String[] args) throws DependenteException {
		Scanner sc = new Scanner(System.in);

		System.out.println("===== Nome Empresa =====");
		System.out.println("Informe o endereço e o nome do arquivo: (com a extensão) ");
		try {
			
			EntradaArquivo entrada = new EntradaArquivo();

			entrada.lerArquivo(sc.nextLine());

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			DependenteDao dependenteDao = new DependenteDao();
			FolhaPagamentoDao folhaPagamentoDao = new FolhaPagamentoDao();
			FolhaPagamento folha = new FolhaPagamento();

			int opcoesFunc = -1;
			while (opcoesFunc != 0) {			
				System.out.println("Opções:\n " + "1- Listar funcionários\n" + "2 - Atualizar Funcionário\n"
						+ "3- Remover funcionário\n");
				opcoesFunc = sc.nextInt();
				sc.nextLine();

				switch (opcoesFunc) {
				case 1:
					System.out.println("Listando todos os funcionários.");
					funcionarioDao.listarFuncionarios();
					break;

				case 2:
					System.out.println("Atualização de funcionários");
					System.out.print("Digite o nome do funcionario: ");
					String nome = sc.nextLine();
					System.out.print("Digite o CPF do funcionario: ");
					String cpf = sc.nextLine();
					System.out.print("Digite a data de nascimento do funcionario: ");
					LocalDate dataDeNascimento = LocalDate.parse(sc.nextLine());
					System.out.print("Digite o salário bruto do funcionario: ");
					Double salarioBruto = sc.nextDouble();
					System.out.print("Digite o ID do funcionario: ");
					Integer idFuncionario = sc.nextInt();
					sc.nextLine();
					Funcionario funcionario = new Funcionario(nome, cpf, dataDeNascimento, salarioBruto);
					funcionarioDao.atualizarFuncionario(funcionario, idFuncionario);
					break;

				case 3:
					System.out.println("Excluindo funcionário");
					System.out.print("Digite o ID que quer excluir: ");
					Integer id = sc.nextInt();
					sc.nextLine();
					funcionarioDao.removerFuncionario(id);
				default:
					System.out.println("Opção inválida");
				}
			}

			int opcoesDep = -1;
			while (opcoesDep != 0) {
				System.out.println("Opções:\n " + "1- Listar Dependentes\n" + "2 - Atualizar Dependente\n"
						+ "3- Remover Dependente\n" + "0- Sair");
				opcoesDep = sc.nextInt();
				sc.nextLine();
				
				switch (opcoesDep) {
				case 1:
					System.out.println("Listando todos os Dependentes.");
					funcionarioDao.listarFuncionarios();
					break;
				case 2:
					System.out.println("Atualização de Dependentes");
					System.out.print("Digite o nome do dependente: ");
					String nome = sc.nextLine();
					System.out.print("Digite o CPF do dependentes: ");
					String cpf = sc.nextLine();
					System.out.print("Digite a data de nascimento do dependentes: ");
					LocalDate dataDeNascimento = LocalDate.parse(sc.nextLine());
					System.out.print("Digite o seu parentesco com o funcionario: (FILHO, SOBRINHO ou OUTROS)");
					String parentesco = sc.nextLine();
					System.out.print("Digite o ID do dependente: ");
					Integer id_dependente = sc.nextInt();
					sc.nextLine();
					Dependente dependente = new Dependente(nome, cpf, dataDeNascimento,
							EnumParentesco.valueOf(parentesco));
					dependenteDao.atualizarDependente(dependente, id_dependente);
					;
					break;
				case 3:
					System.out.println("Excluindo Dependente");
					System.out.print("Digite o ID do dependente que quer excluir: ");
					Integer id = sc.nextInt();
					sc.nextLine();
					dependenteDao.removerDependente(id);
				case 5: //eu
					System.out.println("Atualização de Folha de Pagamento");
		            System.out.print("Digite o ID da folha que irá atualizar: ");
		            int idFolha = sc.nextInt();
		            sc.nextLine();
		            folhaPagamentoDao.atualizarFolhaPagamento(folha, idFolha);
		            System.out.println("Folha de pagamento atualizada com sucesso!");
		            break;
				case 6:
					System.out.println("Remover Folha de Pagamento");
					System.out.print("Digite o ID da folha que deseja remover: ");
				    int idFolhaRemover = sc.nextInt();
				    sc.nextLine();
				    folhaPagamentoDao.removerFolhaPagamento(idFolhaRemover);
				    System.out.println("Folha de pagamento removida com sucesso!");
				    break;
				default:
					System.out.println("Opção inválida");
				}
			}
			
			int opcoesFolha = -1;
			while (opcoesFolha != 0) {
				System.out.println("Opções:\n " + "1- Listar Folha de Pagamento\n" + "2 - Atualizar Folha de Pagamento\n"
						+ "3- Remover Folha de Pagamento\n" + "0- Sair");
				
				opcoesFolha = sc.nextInt();
				sc.nextLine();
				
				switch (opcoesFolha) {
				case 1:
					System.out.println("Listagem de Folhas de Pagamento");
				    System.out.println(folhaPagamentoDao.listarFolhasPagamento());
				    break;
				case 2:
					System.out.println("Atualização de Folha de Pagamento");
		            System.out.print("Digite o ID da folha que irá atualizar: ");
		            int idFolha = sc.nextInt();
		            sc.nextLine();
		            folhaPagamentoDao.atualizarFolhaPagamento(folha, idFolha);
		            System.out.println("Folha de pagamento atualizada com sucesso!");
		            break;
				case 3:
					System.out.println("Remover Folha de Pagamento");
					System.out.print("Digite o ID da folha que deseja remover: ");
				    int idFolhaRemover = sc.nextInt();
				    sc.nextLine();
				    folhaPagamentoDao.removerFolhaPagamento(idFolhaRemover);
				    System.out.println("Folha de pagamento removida com sucesso!");
				    break;
				default:
					System.out.println("Opção inválida");
				}
			}
			sc.close();
		} catch (DependenteException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println("Erro inesperado! " + e.getMessage());
		}

	}
}
