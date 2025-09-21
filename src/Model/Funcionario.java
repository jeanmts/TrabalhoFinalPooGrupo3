package Model;

import java.time.LocalDate;

public class Funcionario extends Pessoa {

	private double salarioBruto;
	private double descontoInss;
	private double descontoIR;
	private int id_funcionario;
	private int qtde_dependentes;

	public Funcionario() {

	}

	public Funcionario(int id_funcionario) {
		super();
		this.id_funcionario = id_funcionario;

	}

	
	public Funcionario(String nome, String cpf, LocalDate dataDeNascimento,double salarioBruto) {
		super(nome, cpf, dataDeNascimento);
		this.salarioBruto = salarioBruto;
	}

	public Funcionario(int id_funcionario, String nome, String cpf, LocalDate dataDeNascimento, double salarioBruto) {
		super(nome, cpf, dataDeNascimento);
		this.id_funcionario = id_funcionario;
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

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	
	public int getQtde_dependentes() {
		return qtde_dependentes;
	}

	public void setQtde_dependentes(int qtde_dependentes) {
		this.qtde_dependentes = qtde_dependentes;
	}

	@Override
	public String toString() {
		return "Funcionario: Nome: " + getNome() + " CPF: " + getCpf() + " Data de nascimento: " + getDataDeNascimento()
				+ " Salario bruto: " + getSalarioBruto() + "ID: " + getId_funcionario();

	}

}
