package modelo.agendamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.dominio.dao.conexao.Conexao;

public class VisualizarAgendamentos {
	private Conexao conexao;

	public VisualizarAgendamentos(Conexao conexao) {
		this.conexao = conexao;
	}

	public void listarAgendamentos() {
	    String sql = "SELECT a.id, c.nome, c.cnpj, c.telefone, a.nome_veiculo, a.data_entrada, a.status "
	               + "FROM agendamento a "
	               + "JOIN cliente c ON a.id_cliente = c.id";

	    try (Connection conn = conexao.obterConexao();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {

	        System.out.println("---- Lista de Agendamentos ----");
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String nomeCliente = rs.getString("nome");
	            String cnpj = rs.getString("cnpj");
	            String telefone = rs.getString("telefone");
	            String nomeVeiculo = rs.getString("nome_veiculo");
	            String dataEntrada = rs.getString("data_entrada");
	            String status = rs.getString("status");

	            System.out.println("ID Agendamento: " + id);
	            System.out.println("Cliente: " + nomeCliente);
	            System.out.println("CNPJ: " + cnpj);
	            System.out.println("Telefone: " + telefone);
	            System.out.println("Veículo: " + nomeVeiculo);
	            System.out.println("Data de Entrada: " + dataEntrada);

	            // Mensagem específica para status "A"
	            if ("A".equals(status)) {
	                System.out.println("Agendamento: Aberto");
	            }else if("P".equals(status)) {
	            	System.out.println("Agendamento: Pendente");
	            }else if("F".equals(status)) {
	            	System.out.println("Agendamento: Finalizado");
	            }


	            System.out.println("------------------------------");
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao listar agendamentos: " + e.getMessage());
	    }
	}


}
