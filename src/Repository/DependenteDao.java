package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.Dependente;
import Enum.EnumParentesco;
import Exception.DependenteException;
import Utils.ConnectionFactory;

public class DependenteDao {
	private Connection connection;

	private DependenteDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void inserirDependente(Dependente dependente, Integer id_funcionario)  {
		LocalDate data = LocalDate.now();
		int idade = Period.between(dependente.getDataDeNascimento(), data).getYears();
		if (idade >=18){			
			throw new DependenteException("Erro: Depedente não pode ser cadastrado pois é maior de idade."); 
		}
		
		if (id_funcionario==null) {
			throw new DependenteException("O ID do funcionário precisa ser informado.");
		}
		String sql = "insert into dependente(nome_dependente, " + "cpf_dependente, "
				+ "data_de_nascimento_dependente, " + "parentesco_dependente, "+" id_funcionario_func") values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, dependente.getNome());
			stmt.setString(2, dependente.getCpf());
			stmt.setString(3, dependente.getDataDeNascimento().toString());
			stmt.setString(4, dependente.getParentesco().toString());
			stmt.setInt(5, id_funcionario);
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Dependente criado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar dependente!");
		}
	}

	public List<Dependente> listarDependentes() {
		String sql = "select * from dependente";
		List<Dependente> dependentes = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				EnumParentesco parentesco = EnumParentesco.valueOf(rs.getString("parentesco"));
				Dependente dependente = new Dependente(rs.getString("nome"), 
						rs.getString("cpf"),
						rs.getDate("data_nascimento").toLocalDate(), 
						parentesco);
				dependentes.add(dependente);
			}
			stmt.close();
			rs.close();
			connection.close();
			System.out.println("Listagem de dependente.");

		} catch (SQLException e) {
			System.out.println("Erro ao listar Dependente");
		}
		return dependentes;
	}
	
	public void atualizarDependente(Dependente dependente) {
		String sql = "update funcionario set nome_funcionario=?, "
				+ "cpf_funcionario=?, "
				+ "data_de_nascimento_funcionario=?, "
				+ "salarioBruto=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getDataDeNascimento().toString());
			stmt.setDouble(4, funcionario.getSalarioBruto());
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Funcionario atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Funcionario!");
		}
	}
		public void removerFuncionario(int id_funcionario) {
			String sql = "delete from funcionario where id_funcionario=?";
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				stmt.setInt(1, id_funcionario);
				stmt.execute();
				stmt.close();
				connection.close();
				System.out.println("Funcionario deletado com sucesso!");
			} catch (SQLException e) {
				System.err.println("Erro ao remover!");
			}
		
	}

	

}
