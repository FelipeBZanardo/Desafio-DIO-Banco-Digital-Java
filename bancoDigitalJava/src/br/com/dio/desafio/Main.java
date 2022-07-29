package br.com.dio.desafio;

public class Main {

    public static void main(String[] args) {
        /*Cliente felipe = new Cliente();
        felipe.setNome("Felipe");

        Conta cc = new ContaCorrente(felipe);
        Conta cp = new ContaPoupanca(felipe);

        cc.depositar(100);
        cc.transferir(50, cp);
        cc.sacar(25);

        cc.imprimirExtrato();
        cp.imprimirExtrato();*/


        BancoCentral bancoCentral = new BancoCentral();
        Banco banco1 = new Banco("Banco1");
        bancoCentral.novoBanco(banco1);
        Banco banco2 = new Banco("Banco2");
        bancoCentral.novoBanco(banco2);

        Cliente felipe = new Cliente();
        felipe.setNome("Felipe");

        Conta cc = new ContaPoupanca(felipe);
        cc.depositar(50);
        banco1.abreConta(cc);


        Menu menu = new Menu();
        menu.menuPrincipal(bancoCentral);
    }
}
