package Entity;

import java.time.LocalDate;

public class Funcionario extends Pessoa {

	private double salarioBruto;

	public Funcionario() {
		super();
	}
	
	public Funcionario(String nome, String cpf, LocalDate dataDeNascimento, double salarioBruto) {
		super(nome, cpf, dataDeNascimento);
		this.salarioBruto = salarioBruto;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

}
