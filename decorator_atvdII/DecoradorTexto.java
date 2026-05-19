

// 1. Interface Texto
interface Texto {
    String getTexto();
}

// 2. Classe base
class MensagemSimples implements Texto {

    @Override
    public String getTexto() {
        return "olá, mundo";
    }
}

// 3. Decorator abstrato
abstract class TextoDecorator implements Texto {

    protected Texto texto;

    public TextoDecorator(Texto texto) {
        this.texto = texto;
    }

    @Override
    public String getTexto() {
        return texto.getTexto();
    }
}

// 4. Decorador Maiúsculo
class MaiusculoDecorator extends TextoDecorator {

    public MaiusculoDecorator(Texto texto) {
        super(texto);
    }

    @Override
    public String getTexto() {
        return super.getTexto().toUpperCase();
    }
}

// 5. Decorador Aspas
class AspasDecorator extends TextoDecorator {

    public AspasDecorator(Texto texto) {
        super(texto);
    }

    @Override
    public String getTexto() {
        return "\"" + super.getTexto() + "\"";
    }
}

// 6. Decorador Ponto de exclamação
class PontoDecorator extends TextoDecorator {

    public PontoDecorator(Texto texto) {
        super(texto);
    }

    @Override
    public String getTexto() {
        return super.getTexto() + "!";
    }
}

// 7. Classe principal
public class DecoradorTexto {

    public static void main(String[] args) {

        Texto msg = new MensagemSimples();

        System.out.println("Base: " + msg.getTexto());

        msg = new MaiusculoDecorator(msg);
        System.out.println("Maiúsculo: " + msg.getTexto());

        msg = new AspasDecorator(msg);
        System.out.println("Aspas: " + msg.getTexto());

        msg = new PontoDecorator(msg);
        System.out.println("Final: " + msg.getTexto());
    }
}
