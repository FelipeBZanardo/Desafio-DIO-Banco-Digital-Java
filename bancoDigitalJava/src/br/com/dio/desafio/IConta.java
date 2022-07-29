package br.com.dio.desafio;

public interface IConta {

    //métodos:
    void sacar(double valor);

    boolean verificaSaque(double valor);

    void depositar(double valor);

    void transferir(double valor, Conta contaDestino);

    void imprimirExtrato();

}
