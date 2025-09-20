package Main;

import java.util.Scanner;

import Exception.DependenteException;
import Model.EntradaArquivo;
import Repository.FuncionarioDao;

public class Aplicacao {

	public static void main(String[] args) throws DependenteException{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("===== Nome Empresa =====");
		System.out.println("Informe o endereço e o nome do arquivo: (com a extensão) ");
		try {
		EntradaArquivo entrada = new EntradaArquivo();
		
		entrada.lerArquivo(sc.nextLine());
		
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		
		System.out.println("Listando todos os funcionários.");
		funcionarioDao.listarFuncionarios();
		
		}catch(DependenteException e){
			System.out.println(e.getMessage());
		}
	}

}
