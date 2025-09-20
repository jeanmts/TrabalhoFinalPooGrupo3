package Model;

import java.time.LocalDate;

import Interface.CalculosPagamento;

public class FolhaPagamento implements CalculosPagamento{
	private int codigo;
	private Funcionario funcionario;
	private LocalDate dataPagamento;
	private double salarioLiquido;

	
	public FolhaPagamento() {
		super();
	}
	
	public FolhaPagamento(Funcionario funcionario) {
		super();
		this.funcionario = funcionario;
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

	public double calcularSalarioLiquido(double salarioBruto, int numeroDeDependentes) {
		
		double descontoINSS = calcularINSS(salarioBruto);
		double descontoIR = calcularIR(salarioBruto, numeroDeDependentes);
		this.salarioLiquido = salarioBruto - descontoINSS - descontoIR;
		return this.salarioLiquido; 

	}

	public double calcularINSS(double salarioBruto) {
		if (salarioBruto <= 1518.00) {
			return salarioBruto * 0.075;
		} else if (salarioBruto >= 1518.01 && salarioBruto <= 2793.88) {
			return (salarioBruto * 0.09) - 22.77;
		} else if (salarioBruto >=2793.89 && salarioBruto <= 4190.83) {
			return (salarioBruto * 0.12) - 106.60;
		} else if (salarioBruto >= 4190.84 && salarioBruto <= 8157.41) {
			return (salarioBruto * 0.14) - 190.42;
		} else {
			return 951.62;
		}
	}

	public double calcularIR(double salarioBruto, int numeroDeDependentes) {

		double salarioMaisDependentes = salarioBruto + (numeroDeDependentes * 189.59);

		if (salarioMaisDependentes >= 2259.21 && salarioMaisDependentes <= 2826.65) {
			double valorADeduzir = (salarioMaisDependentes * 1.075) - salarioMaisDependentes ;
			return valorADeduzir;
		
		} else if (salarioMaisDependentes >= 2826.66 && salarioMaisDependentes <= 3751.05) {
			double valorADeduzir =(salarioMaisDependentes * 1.15) - salarioMaisDependentes ;
			return valorADeduzir;
		
		} else if(salarioMaisDependentes >= 3751.06 && salarioMaisDependentes < 4664.68) {
			double valorADeduzir =(salarioMaisDependentes * 1.225) - salarioMaisDependentes;
			return valorADeduzir;
		} else {
			double valorADeduzir = (salarioMaisDependentes * 1.275) - salarioMaisDependentes;
			return valorADeduzir;
		}
	}
}