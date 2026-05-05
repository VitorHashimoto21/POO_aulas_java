
interface FreteStrategy {
    double calcularFrete(double peso, double valor);
}

// SEDEX
class SedexFrete implements FreteStrategy {
    @Override
    public double calcularFrete(double peso, double valor) {
        return peso * 1.45;
    }
}

// PAC
class PacFrete implements FreteStrategy {
    @Override
    public double calcularFrete(double peso, double valor) {
        return peso * 1.10;
    }
}

// FRETE GRÁTIS
class FreteGratis implements FreteStrategy {
    @Override
    public double calcularFrete(double peso, double valor) {
        if (valor > 200) {
            return 0;
        }
        return 0;
    }
}

// CALCULADORA
class CalculadoraFrete {
    private FreteStrategy estrategia;

    public void setEstrategia(FreteStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(double peso, double valor) {
        return estrategia.calcularFrete(peso, valor);
    }
}

// MAIN (classe principal)
public class CalcularFrete {
    public static void main(String[] args) {

        CalculadoraFrete calculadora = new CalculadoraFrete();

        double peso = 10;
        double valor = 250;

        // SEDEX
        calculadora.setEstrategia(new SedexFrete());
        System.out.println("SEDEX: " + calculadora.calcular(peso, valor));

        // PAC
        calculadora.setEstrategia(new PacFrete());
        System.out.println("PAC: " + calculadora.calcular(peso, valor));

        // FRETE GRÁTIS
        calculadora.setEstrategia(new FreteGratis());
        System.out.println("FRETE GRÁTIS: " + calculadora.calcular(peso, valor));
    }
}