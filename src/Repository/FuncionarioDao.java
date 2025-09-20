package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Funcionario;
import Service.ConnectionFactory;

public class FuncionarioDao {
	private Connection connection;

	public FuncionarioDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void inserirFuncionario(Funcionario funcionario) {
		String sql = "insert into funcionario(nome_funcionario, " + "cpf_funcionario, "
				+ "data_de_nascimento_funcionario, " + "salarioBruto) values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setDate(3, java.sql.Date.valueOf(funcionario.getDataDeNascimento()));
			stmt.setDouble(4, funcionario.getSalarioBruto());
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Funcionario criado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar funcionario!" + e.getMessage());
		}
	}

	public List<Funcionario> listarFuncionarios() {
		String sql = "select * from funcionario";
		List<Funcionario> funcionarios = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario(rs.getString("nome_funcionario"),
						rs.getString("cpf_funcionario"), rs.getDate("data_de_nascimento_funcionario").toLocalDate(),
						rs.getDouble("salarioBruto"));
				System.out.println(funcionario.toString());
				funcionarios.add(funcionario);
			}
			stmt.close();
			rs.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println("Erro ao listar Funcionarios" + e.getMessage());
		}
		return funcionarios;
	}

	public void atualizarFuncionario(Funcionario funcionario, Integer idFuncionario) {
		String sql = "update funcionario set nome_funcionario=?, " + "cpf_funcionario=?, "
				+ "data_de_nascimento_funcionario=?, " + "salarioBruto=?" + "where id_funcionario =?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setDate(3, java.sql.Date.valueOf(funcionario.getDataDeNascimento()));
			stmt.setDouble(4, funcionario.getSalarioBruto());
			stmt.setInt(5, idFuncionario);
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Funcionario atualizado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Funcionario!" + e.getMessage());
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
			System.err.println("Erro ao remover!" + e.getMessage());
		}

	}

}
