package Entity;

import java.time.LocalDate;

public class Funcionario extends Pessoa {

	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;

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

	@Override
	public String toString() {
		return "\nFuncionario:\nNome: " + getNome() + " CPF: " + getCpf() + " Data de nascimento: "
				+ getDataDeNascimento() + " Salario bruto: " + getSalarioBruto() + "\nDependentes:";

	}

}
