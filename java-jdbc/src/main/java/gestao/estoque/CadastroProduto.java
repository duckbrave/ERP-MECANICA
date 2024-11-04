package gestao.estoque;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Scanner;
import modelo.dominio.dao.conexao.Conexao;

public class CadastroProduto {
    private Conexao conexao;
    private Scanner scanner;

    public CadastroProduto(Conexao conexao) {
        this.conexao = conexao;
        this.scanner = new Scanner(System.in);
    }

    private String solicitarEntrada(String mensagem) {
        System.out.print(mensagem + " (ou digite 'voltar' para retornar): ");
        String entrada = scanner.nextLine();
        if ("voltar".equalsIgnoreCase(entrada)) {
            System.out.println("Operação cancelada pelo usuário. Retornando ao menu principal...");
            return null;
        }
        return entrada;
    }

    public void coletarDadosProduto() {
        // Coletar código do produto
        String produtoInput = solicitarEntrada("Digite o código:");
        if (produtoInput == null) return;
        if (produtoInput.length() <= 3) {
            System.out.println("Código inválido, digite um código maior que 3.");
            return;
        }
        long codigo = Long.parseLong(produtoInput);

        // Nome do produto
        String produtoNome = solicitarEntrada("Digite o nome do produto:");
        if (produtoNome == null) return;

        // Quantidade
        String quantidadeStr = solicitarEntrada("Digite a quantidade:");
        if (quantidadeStr == null) return;
        long quantidade = Long.parseLong(quantidadeStr);

        // Preço de custo
        String prcCustoStr = solicitarEntrada("Preço de custo:");
        if (prcCustoStr == null) return;
        float prcCusto = Float.parseFloat(prcCustoStr);

        // Preço de venda
        String prcVendaStr = solicitarEntrada("Preço de venda:");
        if (prcVendaStr == null) return;
        float prcVenda = Float.parseFloat(prcVendaStr);

        // Embalagem
        String embalagem = solicitarEntrada("Qual modelo de embalagem:");
        if (embalagem == null) return;

        // Produto ou serviço
        String prodser = solicitarEntrada("Digite 'P' para produto ou 'S' para serviço:");
        if (prodser == null) return;
        prodser = prodser.toUpperCase();

        // Criar o objeto Produto com data e estado
        Produto produto = new Produto(codigo, produtoNome, quantidade, prcCusto, prcVenda, embalagem, LocalDateTime.now(), prodser);

        // Confirmar cadastro
        System.out.println("Você deseja: ");
        System.out.println("1. Cadastrar");
        System.out.println("2. Cancelar");
        System.out.print("Escolha uma opção (1 ou 2): ");
        int opcao = Integer.parseInt(scanner.nextLine());

        switch (opcao) {
            case 1:
                try (Connection conn = conexao.obterConexao()) {
                    inserirProduto(produto, conn);
                    System.out.println("Produto cadastrado com sucesso!");
                } catch (SQLException e) {
                    System.out.println("Erro ao cadastrar o produto: " + e.getMessage());
                }
                break;

            case 2:
                System.out.println("Cadastro cancelado.");
                break;

            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }

    public void inserirProduto(Produto produto, Connection conn) throws SQLException {
        // SQL de inserção, incluindo estado e data
        String inserirSQL = "INSERT INTO estoque (codigo_pro, nome_pro, quant_pro, prc_custo, prc_venda, tipo_emba, estado, data_cad, prod_ser) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(inserirSQL)) {
            // Configurar os parâmetros do PreparedStatement
            stmt.setLong(1, produto.getCodigo());
            stmt.setString(2, produto.getNome());
            stmt.setLong(3, produto.getQuantidade());
            stmt.setFloat(4, produto.getPrcCusto());
            stmt.setFloat(5, produto.getPrcVenda());
            stmt.setString(6, produto.getEmbalagem());
            stmt.setBoolean(7, produto.isEstado());
            stmt.setObject(8, produto.getData()); // Utilizando setObject para LocalDateTime
            stmt.setString(9, produto.getProdser());

            // Executa a inserção
            int rowsAffected = stmt.executeUpdate();
            conn.commit(); // Realiza o commit
            System.out.println("Linhas inseridas: " + rowsAffected);
        }
    }
}
