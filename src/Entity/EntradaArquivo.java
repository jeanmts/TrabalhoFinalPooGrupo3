package Entity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EntradaArquivo {
	
	public void lerArquivo(String path) {
		try {
			List<Funcionario> listaFuncionarios = new ArrayList<>();
			BufferedReader ler = new BufferedReader(new FileReader(path));

			while (ler.ready()) {
				String linha = ler.readLine();
				String[] a = linha.split(";", 4);

				Funcionario func = new Funcionario(a[0], a[1], LocalDate.parse(a[2]), Double.parseDouble(a[3]));
				listaFuncionarios.add(func);

			}
			
			ler.close();
			
		} catch (IOException e) {
			System.out.println("Erro na leitura do arquivo\n" + e.getMessage());
		}
	}
}
