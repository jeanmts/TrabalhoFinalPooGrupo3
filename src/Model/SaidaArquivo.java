package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

public class SaidaArquivo {

	public SaidaArquivo(String pathSaida, List<Funcionario> listaFuncionarios,
			List<FolhaPagamento> listaFolhaPagamentos) {
	}

	public SaidaArquivo() {
	}

	public void escritorArquivo(String path, List<Funcionario> funcionarios, List<FolhaPagamento> pgto) throws IOException {

		BufferedWriter escritor = new BufferedWriter(new FileWriter(path + "tabela.csv"));
		DecimalFormat df = new DecimalFormat("#.00");
		
		try {
			for (int i = 0; i < funcionarios.size(); i++) {
				escritor.append(funcionarios.get(i).getNome() + ";");
				escritor.append(funcionarios.get(i).getCpf() + ";");
				escritor.append(df.format(pgto.get(i).calcularINSS()) + ";");
				escritor.append(df.format(pgto.get(i).calcularIR()) + ";");
				escritor.append(df.format(pgto.get(i).getSalarioLiquido()) + ";");
				escritor.newLine();
			}
			System.out.println("Arquivo exportado com sucesso!");
			escritor.close();

	
		} catch (IOException e) {
			throw new IOException("Erro ao salvar o arquivo!");
		}
	}
}