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

    public void coletarDadosProduto() {
        // Coletar código do produto
        System.out.print("Digite o código: ");
        String produtoInput = scanner.nextLine();
        if (produtoInput.length() <= 3) {
            System.out.println("Código inválido, digite um código maior que 3.");
            return;
        }
        long codigo = Long.parseLong(produtoInput); // Convertendo o código para long

        // Nome do produto
        System.out.print("Digite o nome do produto: ");
        String produtoNome = scanner.nextLine();

        // Quantidade
        System.out.print("Digite a quantidade: ");
        long quantidade = Long.parseLong(scanner.nextLine()); // Mantendo como long

        // Preço de custo
        System.out.print("Preço de custo: ");
        float prcCusto = Float.parseFloat(scanner.nextLine()); // Convertendo para float

        // Preço de venda
        System.out.print("Preço de venda: ");
        float prcVenda = Float.parseFloat(scanner.nextLine());

        // Embalagem
        System.out.print("Qual modelo de embalagem: ");
        String embalagem = scanner.nextLine();

        // Produto ou serviço
        System.out.print("Digite 'P' para produto ou 'S' para serviço: ");
        String prodser = scanner.nextLine().toUpperCase();

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
