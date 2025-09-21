package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import Enum.EnumParentesco;
import Exception.DependenteException;
import Model.Dependente;
import Model.Funcionario;
import Service.ConnectionFactory;

public class DependenteDao {
	private Connection connection;

	public DependenteDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void inserirDependente(Dependente dependente, String cpf_funcionario) {

		String sqlVerifica = "select * from funcionario where cpf_funcionario=?";
		Funcionario func = null;

		try {
			PreparedStatement stmt = connection.prepareStatement(sqlVerifica);
			stmt.setString(1, cpf_funcionario); // passando
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) { // verificar se achou
		        func = new Funcionario(rs.getInt("id_funcionario"));
		    }
			
			stmt.close();
			rs.close();

		} catch (SQLException e) {
			System.out.println("Funcionario não encontrado. " + e.getMessage());
		}

		LocalDate data = LocalDate.now();
		int idade = Period.between(dependente.getDataDeNascimento(), data).getYears();
		if (idade >= 18) {
			throw new DependenteException("Erro: Depedente não pode ser cadastrado pois é maior de idade.");
		}

		String sql = "insert into dependentes(nome_dependente, " + "cpf_dependente, "
				+ "data_de_nascimento_dependente, " + "parentesco_dependente, "
				+ " id_funcionario_func) values (?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, dependente.getNome());
			stmt.setString(2, dependente.getCpf());
			stmt.setDate(3, java.sql.Date.valueOf(dependente.getDataDeNascimento()));
			stmt.setString(4, dependente.getParentesco().toString());
			stmt.setInt(5, func.getId_funcionario());
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Dependente criado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar dependente!" + e.getMessage());
		}
	}

	public List<Dependente> listarDependentes() {
		String sql = "select * from dependentes";
		List<Dependente> dependentes = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				EnumParentesco parentesco = EnumParentesco.valueOf(rs.getString("parentesco_dependente"));
				Dependente dependente = new Dependente(rs.getString("nome_dependente"), rs.getString("cpf_dependente"),
						rs.getDate("data_de_nascimento_dependente").toLocalDate(), parentesco);
				dependentes.add(dependente);
			}
			stmt.close();
			rs.close();
			connection.close();
			System.out.println("Listagem de dependente.");

		} catch (SQLException e) {
			System.out.println("Erro ao listar Dependente" + e.getMessage());
		}
		return dependentes;
	}

	public void atualizarDependente(Dependente dependente, Integer id_dependente) {
		String sql = "update dependentes set nome_dependente=?, " + "cpf_dependente=?, "
				+ "data_de_nascimento_dependente=?, " + "parentesco_dependente=?" + "where id_dependente=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, dependente.getNome());
			stmt.setString(2, dependente.getCpf());
			stmt.setDate(3, java.sql.Date.valueOf(dependente.getDataDeNascimento()));
			stmt.setString(4, dependente.getParentesco().name());
			stmt.setInt(5, id_dependente);
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Dependente atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Dependente!" + e.getMessage());
		}
	}

	public void removerDependente(int id_dependente) {
		String sql = "delete from dependentes where id_dependente=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id_dependente);
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Dependente deletado com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao remover!" + e.getMessage());
		}

	}
}