package Entity;

import java.time.LocalDate;

import Enum.EnumParentesco;

public class Dependente extends Pessoa {
	private EnumParentesco parentesco;

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
}
