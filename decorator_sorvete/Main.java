// Exercício - Padrão Decorator
// Todos os códigos em um único arquivo
interface Sorvete {
    String getDescricao();
    double getPreco();
}

// Sorvete base
class SorveteSimples implements Sorvete {

    @Override
    public String getDescricao() {
        return "Sorvete de baunilha";
    }

    @Override
    public double getPreco() {
        return 5.00;
    }
}

// Classe abstrata Decorator
abstract class DecoradorSorvete implements Sorvete {
    protected Sorvete sorvete;

    public DecoradorSorvete(Sorvete sorvete) {
        this.sorvete = sorvete;
    }
}

// Decorador Chocolate
class CoberturaChocolate extends DecoradorSorvete {

    public CoberturaChocolate(Sorvete sorvete) {
        super(sorvete);
    }

    @Override
    public String getDescricao() {
        return sorvete.getDescricao() + " + cobertura de chocolate";
    }

    @Override
    public double getPreco() {
        return sorvete.getPreco() + 1.50;
    }
}

// Decorador Caramelo
class CoberturaCaramelo extends DecoradorSorvete {

    public CoberturaCaramelo(Sorvete sorvete) {
        super(sorvete);
    }

    @Override
    public String getDescricao() {
        return sorvete.getDescricao() + " + cobertura de caramelo";
    }

    @Override
    public double getPreco() {
        return sorvete.getPreco() + 1.20;
    }
}

// Decorador Granulado
class GranuladoColorido extends DecoradorSorvete {

    public GranuladoColorido(Sorvete sorvete) {
        super(sorvete);
    }

    @Override
    public String getDescricao() {
        return sorvete.getDescricao() + " + granulado colorido";
    }

    @Override
    public double getPreco() {
        return sorvete.getPreco() + 0.80;
    }
}

// Decorador Chantilly
class ChantillyExtra extends DecoradorSorvete {

    public ChantillyExtra(Sorvete sorvete) {
        super(sorvete);
    }

    @Override
    public String getDescricao() {
        return sorvete.getDescricao() + " + chantilly extra";
    }

    @Override
    public double getPreco() {
        return sorvete.getPreco() + 1.00;
    }
}

// Classe principal
public class Main {

    public static void main(String[] args) {

        // Combinação 1
        Sorvete pedido1 = new SorveteSimples();
        pedido1 = new CoberturaChocolate(pedido1);
        pedido1 = new GranuladoColorido(pedido1);
        pedido1 = new ChantillyExtra(pedido1);

        System.out.println(
            pedido1.getDescricao() +
            " - R$" + String.format("%.2f", pedido1.getPreco())
        );

        // Combinação 2
        Sorvete pedido2 = new SorveteSimples();
        pedido2 = new CoberturaCaramelo(pedido2);
        pedido2 = new ChantillyExtra(pedido2);
        pedido2 = new CoberturaChocolate(pedido2);

        System.out.println(
            pedido2.getDescricao() +
            " - R$" + String.format("%.2f", pedido2.getPreco())
        );
    }
}