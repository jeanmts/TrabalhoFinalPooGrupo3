package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import Enum.EnumParentesco;
import Exception.DependenteException;
import Model.Dependente;
import Model.FolhaPagamento;
import Model.Funcionario;
import Model.SaidaArquivo;
import Repository.DependenteDao;
import Repository.FolhaPagamentoDao;
import Repository.FuncionarioDao;

public class Aplicacao {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		sc.useLocale(Locale.US);

		System.out.println("===== Nome Empresa =====");
		System.out.println("Informe o endereço e o nome do arquivo: (com a extensão) ");
		try {
			String path = sc.nextLine();
			BufferedReader ler = new BufferedReader(new FileReader(path));

			List<Funcionario> listaFuncionarios = new ArrayList<>();
			List<Dependente> listaDependentes = new ArrayList<>();
			List<FolhaPagamento> listaFolhaPagamentos = new ArrayList<>();

			FuncionarioDao funcionarioDao = new FuncionarioDao();
			DependenteDao dependenteDao = new DependenteDao();
			FolhaPagamento folha = new FolhaPagamento();

			Funcionario atual = null;
			boolean ehVazio = false;
			Funcionario funcionario = null;

			FolhaPagamento folhaPgto = null;
			FolhaPagamentoDao folhaPagamentoDao = null;

			while (ler.ready()) {
				String linha = ler.readLine().trim();

				if (linha.isBlank()) {
					ehVazio = false;
					continue;
				}

				if (ehVazio == false) {
					String[] lerFuncionario = linha.split(";", -1);

					funcionario = new Funcionario(lerFuncionario[0], lerFuncionario[1],
							LocalDate.parse(lerFuncionario[2]), Double.parseDouble(lerFuncionario[3]));

					listaFuncionarios.add(funcionario);
					funcionarioDao.inserirFuncionario(funcionario);

					folhaPgto = new FolhaPagamento(funcionarioDao.listarFuncionarios().get(0).getId_funcionario(),
							funcionario, LocalDate.now());

					for (int i = 0; i < funcionarioDao.listarFuncionarios().size(); i++) {
						if (funcionario.getCpf().trim()
								.equals(funcionarioDao.listarFuncionarios().get(i).getCpf().trim())) {
							folhaPagamentoDao = new FolhaPagamentoDao();
							folhaPagamentoDao.inserirFolhapagamento(
									funcionarioDao.listarFuncionarios().get(i).getId_funcionario(), folhaPgto,
									listaDependentes.size());

							listaFolhaPagamentos.add(folhaPgto);
						} else {
							System.out.println("Aguarde alguns instantes...");
						}
					}
					atual = funcionario;
					ehVazio = true;

				} else {
					String[] lerDependente = linha.split(";", -1);

					Dependente dependentes = new Dependente(lerDependente[0], lerDependente[1],
							LocalDate.parse(lerDependente[2]), EnumParentesco.valueOf(lerDependente[3]));

					listaDependentes.add(dependentes);
					dependenteDao.inserirDependente(dependentes, atual.getCpf());
				}
			}

			int opcoesFunc = -1;
			while (opcoesFunc != 0) {
				System.out.println("Opções:\n " + "1- Listar funcionários\n" + "2 - Atualizar Funcionário\n"
						+ "3- Remover funcionário\n" + "0- Sair");
				opcoesFunc = sc.nextInt();
				sc.nextLine();

				switch (opcoesFunc) {
				case 1:
					System.out.println("Listando todos os funcionários.");
					for (Funcionario fun : funcionarioDao.listarFuncionarios()) {
						System.out.println(fun);
					}
					// para cada funcionario, imprima ele na tela
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
					Funcionario func = new Funcionario(nome, cpf, dataDeNascimento, salarioBruto);
					funcionarioDao.atualizarFuncionario(func, idFuncionario);
					break;
				case 3:
					System.out.println("Excluindo funcionário");
					System.out.print("Digite o ID que quer excluir: ");
					Integer id = sc.nextInt();
					sc.nextLine();
					funcionarioDao.removerFuncionario(id);
					break;

				case 0:
					System.out.println("Voce será redirecionado para dependentes");
					break;

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
					for (Dependente depen : dependenteDao.listarDependentes()) {
						System.out.println(depen);
						// para cada dependente, imprima ele na tela
					}
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
					break;
				case 3:
					System.out.println("Excluindo Dependente");
					System.out.print("Digite o ID do dependente que quer excluir: ");
					Integer id = sc.nextInt();
					sc.nextLine();
					dependenteDao.removerDependente(id);
					break;
				default:
					System.out.println("Opção inválida");

				case 0:
					System.out.println("Voce será redirecionado para Folha de Pagamento");
					break;
				}
			}

			int opcoesFolha = -1;
			while (opcoesFolha != 0) {
				System.out.println("Opções:\n " + "1- Listar Folha de Pagamento\n"
						+ "2 - Atualizar Folha de Pagamento\n" + "3- Remover Folha de Pagamento\n" + "0- Sair");

				opcoesFolha = sc.nextInt();
				sc.nextLine();

				switch (opcoesFolha) {
				case 1:
					System.out.println("Listagem de Folhas de Pagamento");
					for (FolhaPagamento folhaPa : folhaPagamentoDao.listarFolhasPagamento()) {
						System.out.println(folhaPa);
					}
					break;
				case 2:
					System.out.println("Atualização de Folha de Pagamento");
					System.out.print("Digite o ID da folha que irá atualizar: ");
					int idFolha = sc.nextInt();
					sc.nextLine();

					System.out.print("Digite a nova data de pagamento (AAAA-MM-DD): ");
					LocalDate dataDePagamento = LocalDate.parse(sc.nextLine());
					folhaPagamentoDao.atualizarDataPagamento(idFolha, dataDePagamento);
					break;
				case 3:
					System.out.println("Remover Folha de Pagamento");
					System.out.print("Digite o ID da folha que deseja remover: ");
					int idFolhaRemover = sc.nextInt();
					sc.nextLine();
					folhaPagamentoDao.removerFolhaPagamento(idFolhaRemover);
					System.out.println("Folha de pagamento removida com sucesso!");
					break;
				case 0:
					System.out.println("Programa Encerrado");
					break;
				default:
					System.out.println("Opção inválida");
				}
			}

			System.out.println("Adicione o local para salvar o arquivo: ");
			String pathSaida = sc.nextLine();
			SaidaArquivo saidaArquivo = new SaidaArquivo();

			System.out.println(listaFuncionarios.get(0));
			saidaArquivo.escritorArquivo(pathSaida, listaFuncionarios, listaFolhaPagamentos);
//			funcionarioDao.fecharPrograma();
			sc.close();
		} catch (DependenteException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo\n" + e.getMessage());
		} catch (IndexOutOfBoundsException e) {
			System.out.println(e.getMessage());
		}

	}
}
