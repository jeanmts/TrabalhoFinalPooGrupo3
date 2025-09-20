package Main;

import java.time.LocalDate;
import java.util.Scanner;

import Enum.EnumParentesco;
import Exception.DependenteException;
import Model.Dependente;
import Model.EntradaArquivo;
import Model.Funcionario;
import Repository.DependenteDao;
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

			System.out.println("Opções:\n " + "1- Listar funcionários\n" + "2 - Atualizar Funcionário\n"
					+ "3- Remover funcionário\n" + "0- Sair");
			int opcoesFunc = sc.nextInt();

			while (opcoesFunc != 0) {

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
					Funcionario funcionario = new Funcionario(nome, cpf, dataDeNascimento, salarioBruto);
					funcionarioDao.atualizarFuncionario(funcionario, idFuncionario);
					break;

				case 3:
					System.out.println("Excluindo funcionário");
					System.out.print("Digite o ID que quer excluir: ");
					Integer id = sc.nextInt();
					funcionarioDao.removerFuncionario(id);
				default:
					System.out.println("Opção inválida");
				}
			}

			System.out.println("Opções:\n " + "1- Listar Dependentes\n" + "2 - Atualizar Dependente\n"
					+ "3- Remover Dependente\n" + "0- Sair");
			int opcoesDep = sc.nextInt();

			while (opcoesDep != 0) {

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
					Dependente dependente = new Dependente(nome, cpf, dataDeNascimento,
							EnumParentesco.valueOf(parentesco));
					dependenteDao.atualizarDependente(dependente, id_dependente);
					;
					break;
				case 3:
					System.out.println("Excluindo Dependente");
					System.out.print("Digite o ID do dependente que quer excluir: ");
					Integer id = sc.nextInt();
					dependenteDao.removerDependente(id);
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
