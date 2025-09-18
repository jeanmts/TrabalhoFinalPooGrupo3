package Entity;

import java.time.LocalDate;

public class FolhaPagamento{
    private int codigo;
    private Funcionario funcionario;
    private LocalDate dataPagamento;
    private double salarioLiquido;

    public FolhaPagamento(){
        super();
    }

    public FolhaPagamento(Integer codigo, Funcionario funcionario, LocalDate dataPagamento){
        super();
        this.codigo=codigo;
        this.funcionario=funcionario;
        this.dataPagamento=dataPagamento;
    }

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        return this.codigo;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataPagamento(){
        return dataPagamento;
    }

    public void setDataPagamentoo(LocalDate dataPagamento){
        return this.dataPagamento;
    }

    public double getSalarioLiquido(){
        return salarioLiquido;
    }

    public void setSalarioLiquido(double salarioLiquido){
        return this.salarioLiquido;
    }

    public double calcularSalarioLiquido(){

    }

    public double calcularINSS(){

    }

    public double calcularIR(){

    }

}