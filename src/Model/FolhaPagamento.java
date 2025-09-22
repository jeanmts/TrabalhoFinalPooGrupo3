package Model;

import java.time.LocalDate;

import Interface.CalculosPagamento;

public class FolhaPagamento implements CalculosPagamento {
	private int id_funcionario;
	private Funcionario funcionario;
	private LocalDate dataPagamento;
	private double salarioLiquido;
	private int numDependentes;
	private int id_folha;

	public FolhaPagamento() {
		super();
	}

	public FolhaPagamento(Funcionario funcionario) {
		super();
		this.funcionario = funcionario;
	}

	public FolhaPagamento(Integer id_funcionario, Funcionario funcionario, LocalDate dataPagamento) {
		super();
		this.id_funcionario = id_funcionario;
		this.funcionario = funcionario;
		this.dataPagamento = dataPagamento;
	}

	public int getId_funcionario() {
		return id_funcionario;
	}

	public void setId_funcionario(int id_funcionario) {
		this.id_funcionario = id_funcionario;
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

	public int getNumDependentes() {
		return numDependentes;
	}

	public void setNumDependentes(int numDependentes) {
		this.numDependentes = numDependentes;
	}

	public int getId_folha() {
		return id_folha;
	}

	public void setId_folha(int id_folha) {
		this.id_folha = id_folha;
	}

	public double calcularINSS() {
		if (funcionario.getSalarioBruto() <= 1518.00) {
			return funcionario.getSalarioBruto() * 0.075;
		} else if (funcionario.getSalarioBruto() >= 1518.01 && funcionario.getSalarioBruto() <= 2793.88) {
			return (funcionario.getSalarioBruto() * 0.09) - 22.77;
		} else if (funcionario.getSalarioBruto() >= 2793.89 && funcionario.getSalarioBruto() <= 4190.83) {
			return (funcionario.getSalarioBruto() * 0.12) - 106.60;
		} else if (funcionario.getSalarioBruto() >= 4190.84 && funcionario.getSalarioBruto() <= 8157.41) {
			return (funcionario.getSalarioBruto() * 0.14) - 190.42;
		} else {
			return 951.62;
		}
	}

	public double calcularIR() {

		double salarioMaisDependentes = funcionario.getSalarioBruto();

		if (salarioMaisDependentes >= 2259.21 && salarioMaisDependentes <= 2826.65) {
			double valorADeduzir = ((funcionario.getSalarioBruto() - (numDependentes * 189.59) - calcularINSS())
					* 0.075) - 169.44;
			return valorADeduzir;

		} else if (salarioMaisDependentes >= 2826.66 && salarioMaisDependentes <= 3751.05) {
			double valorADeduzir = ((funcionario.getSalarioBruto() - (numDependentes * 189.59) - calcularINSS()) * 0.15)
					- 381.44;
			return valorADeduzir;

		} else if (salarioMaisDependentes >= 3751.06 && salarioMaisDependentes < 4664.68) {
			double valorADeduzir = ((funcionario.getSalarioBruto() - (numDependentes * 189.59) - calcularINSS())
					* 0.225) - 662.77;
			return valorADeduzir;
		} else if (salarioMaisDependentes > 4664.68) {
			double valorADeduzir = ((funcionario.getSalarioBruto() - (numDependentes * 189.59) - calcularINSS())
					* 0.275) - 896.00;
			return valorADeduzir;
		} else {
			return 0.0;
		}
	}

	public double calcularSalarioLiquido(double salarioBruto, int numeroDeDependentes) {
		double descontoINSS = calcularINSS();
		double descontoIR = calcularIR();
		this.salarioLiquido = salarioBruto - descontoINSS - descontoIR;
		return this.salarioLiquido;
	}

	@Override
	public String toString() {
		return "---------FOLHA DE PAGAMENTO---------" + "\nID Folha: " + id_folha + "\nFuncionario ID: "
				+ id_funcionario + "\nDesconto INSS: R$" + funcionario.getDescontoInss() + "\nDesconto IR: R$"
				+ funcionario.getDescontoIR() + "\nSalário Líquido: R$" + salarioLiquido + "\n";
	}

}