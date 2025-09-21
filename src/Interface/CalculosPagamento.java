package Interface;

public interface CalculosPagamento {
	    double calcularINSS();
	    double calcularIR();
	    double calcularSalarioLiquido(double salarioBruto, int numeroDependentes);
}