package Model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaidaArquivo {
	
	public void escritorArquivo(String path, Funcionario funcionario,FolhaPagamento pgto) {
		
		try {
			BufferedWriter escritor = new BufferedWriter(new FileWriter(path + "tabela.csv"));
			escritor.append(funcionario.getNome() + ";");
			escritor.append(funcionario.getCpf() + ";");
			escritor.append(funcionario.getDescontoInss() + ";");
			escritor.append(funcionario.getDescontoIR() + ";");
			escritor.append(pgto.getSalarioLiquido() + ";");
			escritor.close();
		} catch (IOException e) {
			System.out.println("Erro na escritura do arquivo!");
		}
	}
}