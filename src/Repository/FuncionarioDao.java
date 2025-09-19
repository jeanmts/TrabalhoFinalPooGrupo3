package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Entity.Funcionario;
import Utils.ConnectionFactory;

public class FuncionarioDao {
	private Connection connection;

	private FuncionarioDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void inserirFuncionario(Funcionario funcionario) {
		String sql = "insert into funcionario(nome_funcionario, " + "cpf_funcionario, "
				+ "data_de_nascimento_funcionario, " + "salarioBruto) values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getDataDeNascimento().toString());
			stmt.setDouble(4, funcionario.getSalarioBruto());
			stmt.execute();
			stmt.close();
			connection.close();
			System.out.println("Funcionario criado com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar funcionario!");
		}
	}

	public List<Funcionario> listarFuncionarios() {
		String sql = "select * from funcionario";
		List<Funcionario> funcionarios = new ArrayList<>();

		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario(rs.getString("nome"), rs.getString("cpf"),
						rs.getDate("data_nascimento").toLocalDate(), rs.getDouble("salario_bruto"));
				funcionarios.add(funcionario);
			}
			stmt.close();
			rs.close();
			connection.close();
			System.out.println("Listagem de funcionarios.");

		} catch (SQLException e) {
			System.out.println("Erro ao listar Funcionarios");
		}
		return funcionarios;
	}
	
	public void atualizarFuncionario(Funcionario funcionario) {
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
