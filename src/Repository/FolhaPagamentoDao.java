package Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.FolhaPagamento;
import Model.Funcionario;
import Service.ConnectionFactory;

public class FolhaPagamentoDao {
	private Connection connection;

	public FolhaPagamentoDao() {
		connection = new ConnectionFactory().getConnection();
	}

	public void inserirFolhapagamento(int id_funcionario, FolhaPagamento folhaP, int qtdeDependentes) {
		String sql = "insert into FolhaPagamento(id_funcionario_func,"
				+ "data_pagamento, desconto_inss, desconto_ir, salarioLiquido) " + "VALUES (?,?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id_funcionario);
			stmt.setDate(2, java.sql.Date.valueOf(folhaP.getDataPagamento()));
			stmt.setDouble(3, folhaP.calcularINSS());
			stmt.setDouble(4, folhaP.calcularIR());
			stmt.setDouble(5, folhaP.calcularSalarioLiquido(folhaP.getFuncionario().getSalarioBruto(), qtdeDependentes));
			stmt.execute();
			stmt.close();
			System.out.println("Folha de pagamento criada com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao cadastrar Folha de pagamento!" + e.getMessage());
		}
	}

	public List<FolhaPagamento> listarFolhasPagamento() {
		String sql = "select * from FolhaPagamento";
		List<FolhaPagamento> folhasP = new ArrayList<>();
		try {
	        PreparedStatement stmt = connection.prepareStatement(sql);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            FolhaPagamento folhaP = new FolhaPagamento();

	            folhaP.setId_folha(rs.getInt("id_folha"));
	            folhaP.setId_funcionario(rs.getInt("id_funcionario_func"));
	            folhaP.setDataPagamentoo(rs.getDate("data_pagamento").toLocalDate());
	            folhaP.setSalarioLiquido(rs.getDouble("salarioLiquido"));

	            Funcionario f = new Funcionario();
	            f.setDescontoInss(rs.getDouble("desconto_inss"));
	            f.setDescontoIR(rs.getDouble("desconto_ir"));

	            folhaP.setFuncionario(f);

	            folhasP.add(folhaP);
	        }
	        rs.close();
	        stmt.close();
	        System.out.println("Listagem das Folhas de Pagamentos concluída.");
	    } catch (SQLException e) {
	        System.out.println("Erro ao listar Folhas de Pagamento: " + e.getMessage());
	    }
	    return folhasP;
	}

	public void atualizarFolhaPagamento(FolhaPagamento folhaP, int id_folha) {
		String sql = "update FolhaPagamento set data_pagamento=?, " + "desconto_INSS=?, " + "desconto_IR=?, "
				+ "salarioLiquido=? where id_folha=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setDate(1, java.sql.Date.valueOf(folhaP.getDataPagamento()));
			stmt.setDouble(2, folhaP.getFuncionario().getDescontoInss());
			stmt.setDouble(3, folhaP.getFuncionario().getDescontoIR());
			stmt.setDouble(4, folhaP.getSalarioLiquido());
			stmt.setDouble(5, id_folha);
			stmt.execute();
			stmt.close();
			System.out.println("Folha de Pagamento atualizado com sucesso!");
			System.out.println( folhaP.toString());
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Folha de Pagamento!");
		}
	}

	public void removerFolhaPagamento(int id_folha) {
		String sql = "delete from FolhaPagamento where id_folha=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id_folha);
			stmt.execute();
			stmt.close();
			System.out.println("Folha de pagamento removida com sucesso!");
		} catch (SQLException e) {
			System.err.println("Erro ao remover folha de pagamento!");
			e.printStackTrace();
		}
	}

}
