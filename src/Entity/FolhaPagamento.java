package Entity;

import java.time.LocalDate;

public class FolhaPagamento {
	private int codigo;
	private Funcionario funcionario;
	private LocalDate dataPagamento;
	private double salarioLiquido;

	
	
	public FolhaPagamento() {
		super();
	}

	public FolhaPagamento(Integer codigo, Funcionario funcionario, LocalDate dataPagamento) {
		super();
		this.codigo = codigo;
		this.funcionario = funcionario;
		this.dataPagamento = dataPagamento;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamentoo(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public double getSalarioLiquido() {
		return salarioLiquido;
	}

	public void setSalarioLiquido(double salarioLiquido) {
		this.salarioLiquido = salarioLiquido;
	}

	public double calcularSalarioLiquido() {
		return 0.0;

	}

	public double calcularINSS() {

		return 0.0;
	}

	public double calcularIR(double salarioBruto, int numeroDeDependentes) {

		double salarioMaisDependentes = salarioBruto + (numeroDeDependentes * 189.59);

		if (salarioMaisDependentes >= 2259.21 && salarioMaisDependentes <= 2826.65) {
			double valorADeduzir = salarioMaisDependentes - (salarioMaisDependentes * 1.075);
			return valorADeduzir;
		
		} else if (salarioMaisDependentes >= 2826.66 && salarioMaisDependentes <= 3751.05) {
			double valorADeduzir = salarioMaisDependentes - (salarioMaisDependentes * 1.15);
			return valorADeduzir;
		
		} else if(salarioMaisDependentes >= 3751.06 && salarioMaisDependentes < 4664.68) {
			double valorADeduzir = salarioMaisDependentes - (salarioMaisDependentes * 1.225);
			return valorADeduzir;
		} else {
			double valorADeduzir = salarioMaisDependentes - (salarioMaisDependentes * 1.275);
			return valorADeduzir;
		}
	}
}