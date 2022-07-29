package br.com.dio.desafio;

public abstract class Conta implements IConta{

    private static final int AGENCIA_PADRAO = 1;
    private static int sequencial = 1;

    //atributos:
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = AGENCIA_PADRAO;
        this.numero = sequencial++;
        this.cliente = cliente;
    }

    //implementação dos métodos vindo da interface IConta:


    @Override
    public boolean verificaSaque(double valor) {
        return !(valor > this.saldo);
    }

    @Override
    public void sacar(double valor) {
        if (this.verificaSaque(valor)) {
            this.saldo -= valor;
        }
    }

    @Override
    public void depositar(double valor) {
        this.saldo += valor;
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (this.verificaSaque(valor)){
            this.sacar(valor);
            contaDestino.depositar(valor);
        }
    }

    //getters:
    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    protected void imprimirInfosComuns(){
        System.out.println(String.format("Titular: %s", this.cliente.getNome()));
        System.out.println(String.format("Agência: %d", this.agencia));
        System.out.println(String.format("Conta: %d", this.numero));
        System.out.println(String.format("Saldo: R$ %.2f", this.saldo));
        System.out.println();
    }
}
