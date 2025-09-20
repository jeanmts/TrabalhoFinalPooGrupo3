package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Enum.EnumParentesco;
import Repository.DependenteDao;
import Repository.FuncionarioDao;

public class EntradaArquivo {

	public void lerArquivo(String path) {
		try {
			List<Funcionario> listaFuncionarios = new ArrayList<>();
			List<Dependente> listaDependentes = new ArrayList<>();
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			DependenteDao dependenteDao = new DependenteDao();
			BufferedReader ler = new BufferedReader(new FileReader(path));

			Funcionario atual = null;
			boolean ehVazio = false;

			while (ler.ready()) {
				String linha = ler.readLine().trim();

				if (linha.isBlank()) {
					ehVazio = false;
					continue;
				}

				if (ehVazio == false) {
					String[] lerFuncionario = linha.split(";", -1); 
					
					Funcionario funcionario = new Funcionario(lerFuncionario[0], lerFuncionario[1], 
							LocalDate.parse(lerFuncionario[2]), Double.parseDouble(lerFuncionario[3]));
					
					listaFuncionarios.add(funcionario);
					funcionarioDao.inserirFuncionario(funcionario);
					atual = funcionario;
					ehVazio = true;
					
					
				} else {
					String[] lerDependente = linha.split(";", -1);

					Dependente dependentes = new Dependente(lerDependente[0], lerDependente[1], 
							LocalDate.parse(lerDependente[2]), EnumParentesco.valueOf(lerDependente[3]));
					
					listaDependentes.add(dependentes);
					dependenteDao.inserirDependente(dependentes, null);
					System.out.println(dependentes.toString());
				}
			}
			ler.close();
			
			System.out.println("Arquivo carregado com sucesso!");
			
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo\n" + e.getMessage());
		}
	}
}