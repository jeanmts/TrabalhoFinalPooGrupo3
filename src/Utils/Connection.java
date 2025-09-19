package Utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {

	String url = "jdbc:postgresql://localhost:5432/empresaPoo";
	String usuario = "postgres";
	String senha = "postgres";
	private Connection connection;

	public Connection getConnection() {
		System.out.println("Testando conexão...");
		try {
			connection = (Connection) DriverManager.getConnection(url, usuario, senha);
			if (connection != null) {
				System.out.println("Conectado com sucesso!");
			} else {
				System.out.println("Erro nos dados da conexão! Verifique, porfavor e tente novamente.");
			}
		} catch (SQLException e) {
			System.err.println("Não foi possível conectar ao banco.\n" + e.getMessage());
		}
		return connection;
	}

}
