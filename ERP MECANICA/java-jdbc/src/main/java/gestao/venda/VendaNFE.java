package gestao.venda;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import modelo.dominio.dao.conexao.Conexao;

public class VendaNFE {
    private Conexao conexaoSQL;
    private Scanner scanner;

    public VendaNFE(Conexao conexaoSQL, Scanner scanner) {
        this.conexaoSQL = conexaoSQL;
        this.scanner = scanner;
    }

    public boolean realizarVenda() {
        try {
            System.out.print("Informe o ID do Agendamento (ou digite 'voltar' para retornar): ");
            String entradaAgendamento = scanner.nextLine().trim();
            if (entradaAgendamento.equalsIgnoreCase("voltar")) {
                System.out.println("Operação de venda cancelada. Retornando ao menu...");
                return false; // Retorna ao menu principal
            }
            int idAgendamento = Integer.parseInt(entradaAgendamento);

            System.out.print("Informe a forma de pagamento (ou digite 'voltar' para retornar): ");
            String formaPagamento = scanner.nextLine().trim();
            if (formaPagamento.equalsIgnoreCase("voltar")) {
                System.out.println("Operação de venda cancelada. Retornando ao menu...");
                return false;
            }

            System.out.print("Descreva o defeito solucionado (ou digite 'voltar' para retornar): ");
            String defeito = scanner.nextLine().trim();
            if (defeito.equalsIgnoreCase("voltar")) {
                System.out.println("Operação de venda cancelada. Retornando ao menu...");
                return false;
            }

            // Perguntar se houve venda de peça
            System.out.print("Foi vendida alguma peça no serviço? (s/n ou digite 'voltar' para retornar): ");
            String vendaPeca = scanner.nextLine().trim();
            if (vendaPeca.equalsIgnoreCase("voltar")) {
                System.out.println("Operação de venda cancelada. Retornando ao menu...");
                return false;
            }

            String codPeca = null;
            String peca = null;
            double valor = 0.0;

            if (vendaPeca.equalsIgnoreCase("s")) {
                System.out.print("Informe o código da peça (ou digite 'voltar' para retornar): ");
                codPeca = scanner.nextLine().trim();
                if (codPeca.equalsIgnoreCase("voltar")) {
                    System.out.println("Operação de venda cancelada. Retornando ao menu...");
                    return false;
                }
                Object[] infoPeca = buscarInformacoesPeca(codPeca);
                if (infoPeca != null) {
                    peca = (String) infoPeca[0];
                    valor = (Double) infoPeca[1];
                } else {
                    System.out.println("Produto não encontrado no estoque.");
                    return false;
                }
            }

            System.out.println("\nResumo da venda:");
            System.out.println("ID do Agendamento: " + idAgendamento);
            System.out.println("Forma de Pagamento: " + formaPagamento);
            System.out.println("Defeito: " + defeito);

            if (peca != null) {
                System.out.printf("Peça: %s | Preço: R$ %.2f\n", peca, valor);
            } else {
                System.out.println("Nenhuma peça foi vendida.");
            }

            System.out.print("Deseja confirmar a venda? (s/n): ");
            String confirmacao = scanner.nextLine().trim();
            if (!confirmacao.equalsIgnoreCase("s")) {
                System.out.println("Venda cancelada.");
                return false;
            }

            // Conexão e transação
            try (Connection conn = conexaoSQL.obterConexao()) {
                if (conn == null) {
                    System.out.println("Erro ao obter conexão com o banco de dados.");
                    return false;
                }

                conn.setAutoCommit(false); // Iniciar a transação

                // 1. Buscar dados do agendamento
                String sqlAgendamento = "SELECT id_cliente, cnpj, telefone, nome_cliente, nome_veiculo, marca_veiculo "
                        + "FROM agendamento WHERE id = ?";
                try (PreparedStatement stmtAgendamento = conn.prepareStatement(sqlAgendamento)) {
                    stmtAgendamento.setInt(1, idAgendamento);
                    ResultSet rsAgendamento = stmtAgendamento.executeQuery();

                    if (!rsAgendamento.next()) {
                        System.out.println("Agendamento não encontrado.");
                        conn.rollback();
                        return false;
                    }

                    int idCliente = rsAgendamento.getInt("id_cliente");
                    String cnpj = rsAgendamento.getString("cnpj");
                    String telefone = rsAgendamento.getString("telefone");
                    String nomeCliente = rsAgendamento.getString("nome_cliente");
                    String nomeVeiculo = rsAgendamento.getString("nome_veiculo");
                    String marcaVeiculo = rsAgendamento.getString("marca_veiculo");

                    // 2. Inserir os dados na tabela venda_nfe
                    String sqlVendaNFE = "INSERT INTO venda_nfe (id_cliente, cnpj, telefone, nome_cliente, nome_veiculo, "
                            + "marca_veiculo, forma_pagamento, peca, valor, defeito, data_venda, cod_peca) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement stmtVendaNFE = conn.prepareStatement(sqlVendaNFE)) {
                        stmtVendaNFE.setInt(1, idCliente);
                        stmtVendaNFE.setString(2, cnpj);
                        stmtVendaNFE.setString(3, telefone);
                        stmtVendaNFE.setString(4, nomeCliente);
                        stmtVendaNFE.setString(5, nomeVeiculo);
                        stmtVendaNFE.setString(6, marcaVeiculo);
                        stmtVendaNFE.setString(7, formaPagamento);
                        stmtVendaNFE.setString(8, peca);
                        stmtVendaNFE.setDouble(9, valor);
                        stmtVendaNFE.setString(10, defeito);
                        stmtVendaNFE.setTimestamp(11, new java.sql.Timestamp(new Date().getTime()));
                        stmtVendaNFE.setString(12, codPeca);

                        stmtVendaNFE.executeUpdate();
                    }

                    // 3. Atualizar quantidade no estoque se houve venda de peça
                    if (codPeca != null) {
                        String sqlAtualizaEstoque = "UPDATE estoque SET quant_pro = quant_pro - 1 WHERE codigo_pro = ?";
                        try (PreparedStatement stmtAtualizaEstoque = conn.prepareStatement(sqlAtualizaEstoque)) {
                            stmtAtualizaEstoque.setString(1, codPeca);
                            stmtAtualizaEstoque.executeUpdate();
                        }
                    }

                    // 4. Atualizar o status do agendamento para 'F'
                    String sqlAtualizaStatus = "UPDATE agendamento SET status = 'F' WHERE id = ?";
                    try (PreparedStatement stmtAtualizaStatus = conn.prepareStatement(sqlAtualizaStatus)) {
                        stmtAtualizaStatus.setInt(1, idAgendamento);
                        stmtAtualizaStatus.executeUpdate();
                    }

                    conn.commit(); // Confirmar a transação
                    System.out.println("Venda NF-e realizada com sucesso!");
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("Erro ao realizar a venda NF-e: " + e.getMessage());
                e.printStackTrace(); // Adicione esta linha para imprimir a stack trace
                return false;
            }
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida. Tente novamente.");
            scanner.nextLine(); // Limpa a entrada inválida
            return false;
        }
    }

    private Object[] buscarInformacoesPeca(String codPeca) {
        try (Connection conn = conexaoSQL.obterConexao();
             PreparedStatement stmtEstoque = conn.prepareStatement(
                     "SELECT nome_pro, prc_venda FROM estoque WHERE codigo_pro = ?")) {
            stmtEstoque.setString(1, codPeca);
            try (ResultSet rsEstoque = stmtEstoque.executeQuery()) {
                if (rsEstoque.next()) {
                    return new Object[]{rsEstoque.getString("nome_pro"), rsEstoque.getDouble("prc_venda")};
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar informações da peça: " + e.getMessage());
        }
        return null;
    }
}
