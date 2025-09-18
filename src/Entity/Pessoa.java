package Entity;

	import java.time.LocalDate;

	public abstract class Pessoa {
		private String nome;
		private String cpf;
		private LocalDate dataDeNascimento;
		
		public Pessoa() {
			super();
		}
		
		public Pessoa(String nome, String cpf, LocalDate dataDeNascimento) {
			super();
			this.nome = nome;
			this.cpf = cpf;
			this.dataDeNascimento = dataDeNascimento;
		}
		
		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}
		
		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public LocalDate getDataDeNascimento() {
			return dataDeNascimento;
		}

		public void setDataDeNascimento(LocalDate dataDeNascimento) {
			this.dataDeNascimento = dataDeNascimento;
		}
	
	}

