import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

class BancoLivros {
    private static final String URL = "jdbc:sqlite:biblioteca.db";

    public void criarTabela() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS livros(
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                     titulo TEXT NOT NULL, 
                     autor TEXT NOT NULL
                     );
                     """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void cadastrarLivro(String titulo, String autor) {
        String sql = "INSERT INTO livros (titulo, autor) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, titulo);
            pstmt.setString(2, autor);
            pstmt.executeUpdate();

            System.out.println("Livro cadastrado com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir livro: " + e.getMessage());
        }
    }

    public void listarLivros() {
        String sql = "SELECT * FROM livros ORDER BY id";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nLista de Livros:");
            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String autor = rs.getString("autor");

                System.out.println(id + " | " + titulo + " | " + autor);
            }

            if (!encontrou) {
                System.out.println("Nenhum livro encontrado!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar livros: " + e.getMessage());
        }
    }

    public void deletarLivro(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Livro deletado com sucesso!");
            } else {
                System.out.println("Livro não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar livro: " + e.getMessage());
        }
    }
}

public class CadastroDeLivros {
    public static void main(String[] args) {
        BancoLivros banco = new BancoLivros();
        banco.criarTabela();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n1 - Cadastrar Livro");
            System.out.println("2 - Listar Livros");
            System.out.println("3 - Deletar Livro");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o título: ");
                    String titulo = scanner.nextLine();

                    System.out.print("Digite o autor: ");
                    String autor = scanner.nextLine();

                    banco.cadastrarLivro(titulo, autor);
                    break;

                case 2:
                    banco.listarLivros();
                    break;

                case 3:
                    System.out.print("Digite o ID: ");
                    int id = scanner.nextInt();

                    banco.deletarLivro(id);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }
}