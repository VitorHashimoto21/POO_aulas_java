import java.sql.*;
import java.util.Scanner;

class BancoTarefas {
    private static final String URL = "jdbc:sqlite:tarefas.db";

    public void criarTabela() {
        String sql = """
                     CREATE TABLE IF NOT EXISTS tarefas(
                     id INTEGER PRIMARY KEY AUTOINCREMENT,
                     descricao TEXT NOT NULL,
                     concluida INTEGER NOT NULL DEFAULT 0
                     );
                     """;

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(sql);

        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public void adicionarTarefa(String descricao) {
        String sql = "INSERT INTO tarefas (descricao) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, descricao);
            pstmt.executeUpdate();

            System.out.println("Tarefa adicionada com sucesso!");

        } catch (SQLException e) {
            System.out.println("Erro ao inserir tarefa: " + e.getMessage());
        }
    }

    public void listarTarefas() {
        String sql = "SELECT * FROM tarefas ORDER BY id";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nLista de Tarefas:");
            boolean encontrou = false;

            while (rs.next()) {
                encontrou = true;
                int id = rs.getInt("id");
                String descricao = rs.getString("descricao");
                int concluida = rs.getInt("concluida");

                String status = (concluida == 1) ? "✔ Concluída" : "✘ Pendente";

                System.out.println(id + " | " + descricao + " | " + status);
            }

            if (!encontrou) {
                System.out.println("Nenhuma tarefa encontrada!");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar tarefas: " + e.getMessage());
        }
    }

    public void concluirTarefa(int id) {
        String sql = "UPDATE tarefas SET concluida = 1 WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Tarefa marcada como concluída!");
            } else {
                System.out.println("Tarefa não encontrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar tarefa: " + e.getMessage());
        }
    }

    public void excluirTarefa(int id) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Tarefa excluída com sucesso!");
            } else {
                System.out.println("Tarefa não encontrada.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao excluir tarefa: " + e.getMessage());
        }
    }
}

public class GerenciadorTarefas {
    public static void main(String[] args) {
        BancoTarefas banco = new BancoTarefas();
        banco.criarTabela();

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Adicionar tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Concluir tarefa");
            System.out.println("4 - Excluir tarefa");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Digite a descrição da tarefa: ");
                    String descricao = scanner.nextLine();
                    banco.adicionarTarefa(descricao);
                    break;

                case 2:
                    banco.listarTarefas();
                    break;

                case 3:
                    System.out.print("Digite o ID da tarefa: ");
                    int idConcluir = scanner.nextInt();
                    banco.concluirTarefa(idConcluir);
                    break;

                case 4:
                    System.out.print("Digite o ID da tarefa: ");
                    int idExcluir = scanner.nextInt();
                    banco.excluirTarefa(idExcluir);
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