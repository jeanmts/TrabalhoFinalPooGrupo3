package Entity;

import java.time.LocalDate;

public class Funcionario extends Pessoa {

	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;

	public Funcionario(String nome, String cpf, LocalDate dataDeNascimento, double salarioBruto, double descontoInss,
			double descontoIR) {
		super(nome, cpf, dataDeNascimento);
		this.salarioBruto = salarioBruto;
		this.descontoInss = descontoInss;
		this.descontoIR = descontoIR;
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
	}

	public double getDescontoInss() {
		return descontoInss;
	}	

	public void setDescontoInss(double descontoInss) {
		this.descontoInss = descontoInss;
	}

	public double getDescontoIR() {
		return descontoIR;
	}

	public void setDescontoIR(double descontoIR) {
		this.descontoIR = descontoIR;
	}
}
