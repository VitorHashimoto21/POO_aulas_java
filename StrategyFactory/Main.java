// INTERFACE STRATEGY
interface FormaPagamentoStrategy {
    String processarPagamento(double valor);
}

// ESTRATÉGIA - CARTÃO DE CRÉDITO
class CartaoCreditoStrategy implements FormaPagamentoStrategy {

    @Override
    public String processarPagamento(double valor) {
        return "Pagamento de R$ " + valor +
               " realizado com Cartão de Crédito.";
    }
}

// ESTRATÉGIA - PAYPAL
class PayPalStrategy implements FormaPagamentoStrategy {

    @Override
    public String processarPagamento(double valor) {
        return "Pagamento de R$ " + valor +
               " realizado via PayPal.";
    }
}

// ESTRATÉGIA - TRANSFERÊNCIA BANCÁRIA
class TransferenciaBancariaStrategy implements FormaPagamentoStrategy {

    @Override
    public String processarPagamento(double valor) {
        return "Pagamento de R$ " + valor +
               " realizado por Transferência Bancária.";
    }
}

// CONTEXTO
class LojaOnline {

    private FormaPagamentoStrategy estrategia;

    // CONSTRUTOR
    public LojaOnline(FormaPagamentoStrategy estrategia) {
        this.estrategia = estrategia;
    }

    // FINALIZAR COMPRA
    public void finalizarCompra(double valor) {
        String mensagem = estrategia.processarPagamento(valor);
        System.out.println(mensagem);
    }
}

// SIMPLE FACTORY
class FormaPagamentoFactory {

    public FormaPagamentoStrategy criarFormaPagamento(String tipoPagamento) {

        if (tipoPagamento.equalsIgnoreCase("cartao")) {
            return new CartaoCreditoStrategy();

        } else if (tipoPagamento.equalsIgnoreCase("paypal")) {
            return new PayPalStrategy();

        } else if (tipoPagamento.equalsIgnoreCase("transferencia")) {
            return new TransferenciaBancariaStrategy();

        } else {
            throw new IllegalArgumentException(
                "Tipo de pagamento inválido: " + tipoPagamento
            );
        }
    }
}

// MAIN
public class Main {

    public static void main(String[] args) {

        // CRIAÇÃO DA FACTORY
        FormaPagamentoFactory factory =
                new FormaPagamentoFactory();

        double valor = 350.0;

        // CARTÃO
        FormaPagamentoStrategy cartao =
                factory.criarFormaPagamento("cartao");

        LojaOnline lojaCartao =
                new LojaOnline(cartao);

        lojaCartao.finalizarCompra(valor);

        // PAYPAL
        FormaPagamentoStrategy paypal =
                factory.criarFormaPagamento("paypal");

        LojaOnline lojaPaypal =
                new LojaOnline(paypal);

        lojaPaypal.finalizarCompra(valor);

        // TRANSFERÊNCIA
        FormaPagamentoStrategy transferencia =
                factory.criarFormaPagamento("transferencia");

        LojaOnline lojaTransferencia =
                new LojaOnline(transferencia);

        lojaTransferencia.finalizarCompra(valor);

        // TESTE INVÁLIDO
        try {

            FormaPagamentoStrategy pix =
                    factory.criarFormaPagamento("pix");

            LojaOnline lojaPix =
                    new LojaOnline(pix);

            lojaPix.finalizarCompra(valor);

        } catch (IllegalArgumentException e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }
}