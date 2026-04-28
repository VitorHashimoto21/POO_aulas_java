import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Enum para representar os tipos de mensagem
enum TipoLog {
    ERROR, WARNING, INFO, DEBUG
}

// Classe Singleton para gerenciar logs
class LogManager {

    // Instância única do LogManager
    private static LogManager instancia;

    // Define onde o log será gravado: "arquivo" ou "console"
    private String destino = "console";

    // Nome do arquivo de log
    private String nomeArquivo = "logs.txt";

    // Formatter para a data e hora
    private DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Construtor privado para garantir Singleton
    private LogManager() {}

    // Método thread-safe para obter a instância
    public static synchronized LogManager getInstancia() {
        if (instancia == null) {
            instancia = new LogManager();
        }
        return instancia;
    }

    // Método para configurar o destino do log
    public void configurarDestino(String destino) {
        if (destino.equalsIgnoreCase("arquivo") ||
            destino.equalsIgnoreCase("console")) {
            this.destino = destino.toLowerCase();
        } else {
            System.out.println("Destino inválido. Usando console como padrão.");
        }
    }

    // Método para registrar mensagens de log
    public void registrar(String mensagem, TipoLog tipo) {
        String dataHora = LocalDateTime.now().format(formatter);
        String logFormatado =
                "[" + dataHora + "] [" + tipo + "] " + mensagem;

        if (destino.equals("arquivo")) {
            gravarEmArquivo(logFormatado);
        } else {
            System.out.println(logFormatado);
        }
    }

    // Método privado para gravar logs em arquivo
    private void gravarEmArquivo(String mensagem) {
        try (PrintWriter writer =
                     new PrintWriter(new FileWriter(nomeArquivo, true))) {
            writer.println(mensagem);
        } catch (IOException e) {
            System.out.println("Erro ao escrever no arquivo de log.");
        }
    }
}

// Classe de teste
public class Main {
    public static void main(String[] args) {

        LogManager logger = LogManager.getInstancia();

        // Teste no console
        logger.registrar("Aplicação iniciada", TipoLog.INFO);
        logger.registrar("Modo debug ativado", TipoLog.DEBUG);
        logger.registrar("Cuidado com memória", TipoLog.WARNING);
        logger.registrar("Erro ao conectar ao banco", TipoLog.ERROR);

        // Mudar para arquivo
        logger.configurarDestino("arquivo");

        logger.registrar("Agora salvando em arquivo", TipoLog.INFO);
        logger.registrar("Erro crítico no sistema", TipoLog.ERROR);
    }
}