package gestao.venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import modelo.dominio.dao.conexao.Conexao;

public class VisualizarVendaNFE {
    private Conexao conexaoSQL;

    public VisualizarVendaNFE(Conexao conexaoSQL) {
        this.conexaoSQL = conexaoSQL;
    }

    public void exibirVendas() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = conexaoSQL.obterConexao();
            String sql = "SELECT cnpj, telefone, nome_cliente, nome_veiculo, marca_veiculo, "
                       + "forma_pagamento, peca, valor, defeito, data_venda, cod_peca FROM venda_nfe";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            System.out.println("Vendas NF-e:");
            System.out.printf("%-15s %-15s %-20s %-20s %-20s %-20s %-15s %-15s %-20s %-20s %-15s\n",
                    "CNPJ", "Telefone", "Nome Cliente", "Nome Veículo", "Marca Veículo", 
                    "Forma Pagamento", "Peça", "Valor", "Defeito", "Data Venda", "Código Peça");

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); // Formatação da data

            while (rs.next()) {
                String cnpj = rs.getString("cnpj");
                String telefone = rs.getString("telefone");
                String nomeCliente = rs.getString("nome_cliente");
                String nomeVeiculo = rs.getString("nome_veiculo");
                String marcaVeiculo = rs.getString("marca_veiculo");
                String formaPagamento = rs.getString("forma_pagamento");
                String peca = rs.getString("peca");
                double valor = rs.getDouble("valor");
                String defeito = rs.getString("defeito");
                java.sql.Timestamp dataVenda = rs.getTimestamp("data_venda");
                String codPeca = rs.getString("cod_peca");

                // Formatação da data para melhor legibilidade
                String dataVendaFormatada = dataVenda != null ? sdf.format(dataVenda) : "N/A";

                // Ajustando a formatação para que todos os campos fiquem alinhados
                System.out.printf("%-15s %-15s %-20s %-20s %-20s %-20s %-15s %.2f %-20s %-20s %-15s\n",
                        cnpj, telefone, nomeCliente, nomeVeiculo, marcaVeiculo,
                        formaPagamento, peca, valor, defeito, dataVendaFormatada, codPeca);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao visualizar vendas NF-e: " + e.getMessage());
        } finally {
            // Fechar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
