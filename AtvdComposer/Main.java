import java.util.ArrayList;
import java.util.List;

interface ComponenteGUI {
    void renderizar();
}

class Botao implements ComponenteGUI {

    private String nome;

    public Botao(String nome) {
        this.nome = nome;
    }

    @Override
    public void renderizar() {
        System.out.println("   Botão: " + nome);
    }
}

class Texto implements ComponenteGUI {

    private String conteudo;

    public Texto(String conteudo) {
        this.conteudo = conteudo;
    }

    @Override
    public void renderizar() {
        System.out.println("   Texto: " + conteudo);
    }
}

class Painel implements ComponenteGUI {

    private String nome;
    private List<ComponenteGUI> filhos = new ArrayList<>();

    public Painel(String nome) {
        this.nome = nome;
    }

    public void adicionar(ComponenteGUI componente) {
        filhos.add(componente);
    }

    @Override
    public void renderizar() {

        System.out.println(nome);

        for (ComponenteGUI componente : filhos) {
            componente.renderizar();
        }
    }
}

public class Main {

    public static void main(String[] args) {

        Botao botao1 = new Botao("Salvar");
        Texto texto1 = new Texto("Bem-vindo!");

        Botao botao2 = new Botao("Cancelar");
        Texto texto2 = new Texto("Mensagem interna");

        Painel painelInterno = new Painel("Painel Interno");
        painelInterno.adicionar(botao2);
        painelInterno.adicionar(texto2);

        Painel painelPrincipal = new Painel("Painel Principal");

        painelPrincipal.adicionar(botao1);
        painelPrincipal.adicionar(texto1);
        painelPrincipal.adicionar(painelInterno);

        painelPrincipal.renderizar();
    }
}