package Model;

import java.time.LocalDate;

import Enum.EnumParentesco;

public class Dependente extends Pessoa {
	private EnumParentesco parentesco;
	
    public Dependente() {
		super();
	}

    public Dependente(String nome, String cpf, LocalDate dataNascimento, EnumParentesco parentesco) {
        super(nome, cpf, dataNascimento);
        this.parentesco = parentesco;
	}

	public EnumParentesco getParentesco() {
		return parentesco;
	}

	public void setParentesco(EnumParentesco parentesco) {
		this.parentesco = parentesco;
	}

	@Override
	public String toString() {
		return "Nome dependente: " + getNome() + " CPF: " + getCpf()
		+" Data de nascimento: " + getDataDeNascimento() + " Parentesco: " + getParentesco();
	}
	
	
}
