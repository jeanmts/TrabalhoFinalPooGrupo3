package Main;

import java.time.LocalDate;
import java.util.Scanner;

import Exception.DependenteException;
import Model.EntradaArquivo;
import Model.Funcionario;
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

			System.out.println("Opções:\n " + "1- Listar funcionários\n" + "2 - Atualizar Funcionário\n"
					+ "3- Remover funcionário\n" + "0- Sair");
			int opcoes = sc.nextInt();

			while (opcoes != 0) {

				switch (opcoes) {
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
				default: System.out.println("Opção inválida");	
				}
			}
			sc.close();
		} catch (DependenteException e) {
			System.out.println(e.getMessage());
		}

	}

}
