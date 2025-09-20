package interfaceCalculos;

public interface CalculosPagamento {
	    double calcularINSS(double salarioBruto);
	    double calcularIR(double salarioBruto, int numeroDependentes);
	    double calcularSalarioLiquido(double salarioBruto, int numeroDependentes);
}