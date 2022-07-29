package br.com.dio.desafio;

import javax.swing.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public void menuPrincipal(BancoCentral bc){
        System.out.println("****** MENU *****");
        System.out.println();
        System.out.println("[1] - Cadastrar conta");
        System.out.println("[2] - Verificar clientes");
        System.out.println("[3] - Sacar/Depositar/Transferir/Extrato");
        System.out.println("[0] - Sair");
        System.out.println();
        int opcao =  this.selecionaOpcao();
        if (opcao == 0){
            limpaConsole();
            System.out.println("Fim do programa!");
            System.exit(0);
        }
        if (opcao == 1) {
            limpaConsole();
            cadastraConta(bc);
        }
        if (opcao == 2){
            limpaConsole();
            verificaClientes(bc);
        }
        if (opcao == 3){
            escolhaTransacao(selecionaConta(bc), bc);
        }


    }

    public int selecionaOpcao(){
        Scanner sc = new Scanner(System.in);
        int opcao = -1;
        while(true){
            System.out.println("Digite o número da opção: ");
            String entrada = sc.next();
            try{
                opcao = Integer.parseInt(entrada);
                if (opcao >= 0 && opcao <= 3){
                    break;
                }
                System.out.println("Digite apenas uma das opções mostradas");
            }catch (Exception e){
                System.out.println("Digite apenas números");
            }
        }

        return opcao;

    }

    public void verificaClientes(BancoCentral bc){

        Scanner sc = new Scanner(System.in);

        int contador = 0;

        limpaConsole();

        for (Banco banco : bc.getBancos()) {
            System.out.println();
            System.out.println("***\t" + banco.getNome() + "\t***");
            System.out.println();
            for (Conta conta : banco.getContas()) {
                System.out.println((++contador) + " - " + conta.cliente.getNome());
            }
            contador = 0;
        }

        System.out.println();

        int opcao  = -1;

        while (true) {
            System.out.println("Digite 0 para voltar");
            String entrada = sc.next();
            try{
                opcao = Integer.parseInt(entrada);
            }catch (Exception e){
            }
            if (opcao == 0) menuPrincipal(bc);
        }

    }

    public void cadastraConta(BancoCentral bc){

        Banco b1 = escolheBanco(bc);

        limpaConsole();

        Scanner sc = new Scanner(System.in);
        boolean existe = false;

        while(true) {
            System.out.println("Digite seu nome: ");
            String nome = sc.next();

            for (Conta conta : b1.getContas()){
                if (conta.cliente.getNome().equals(nome)){
                    existe = true;
                }
            }

            if (!existe) {
                escolheConta(nome, b1, bc);
                System.out.println("Conta cadastrada com sucesso!");
                try { Thread.sleep (2000); } catch (InterruptedException ex) {}
                limpaConsole();
                menuPrincipal(bc);
                break;
            }
            System.out.println("Esse cliente já foi cadastrado anteriormente. Cadastre outro");
            existe = false;
        }
    }

    public Banco escolheBanco(BancoCentral bc){

        Scanner sc = new Scanner(System.in);

        System.out.println("Escolha um banco");
        System.out.println();
        System.out.println("[1] - Banco1");
        System.out.println("[2] - Banco2");
        System.out.println();

        int opcao=-1;
        while(true){
            System.out.println("Digite o número correspondente do banco ou 0 para sair");
            String escolha = sc.next();
            try{
                opcao = Integer.parseInt(escolha);
                if (opcao == 1 || opcao == 2 || opcao == 0){
                    break;
                }
                System.out.println("Digite apenas 0,1,2");
            }catch (Exception e){
                System.out.println("Digite apenas números");
            }
        }
        if (opcao == 0){
            limpaConsole();
            menuPrincipal(bc);
        }
        return bc.getBancos().get(opcao-1);
    }

    public void escolheConta(String nome, Banco b1, BancoCentral bc){

        Scanner sc = new Scanner(System.in);

        limpaConsole();

        System.out.println("Escolha um tipo de conta");
        System.out.println("[1] - Conta Corrente");
        System.out.println("[2] - Conta Poupança");
        System.out.println();

        int opcao=-1;
        while(true){
            System.out.println("Digite o número da opção ou 0 para sair");
            String escolha = sc.next();
            try{
                opcao = Integer.parseInt(escolha);
                if (opcao == 1 || opcao == 2 || opcao == 0){
                    break;
                }
                System.out.println("Digite apenas 0,1,2");
            }catch (Exception e){
                System.out.println("Digite apenas números");
            }
        }

        if (opcao == 0){
            limpaConsole();
            menuPrincipal(bc);
        } else if (opcao == 1){

           Cliente cliente = new Cliente();
           cliente.setNome(nome);
           Conta conta = new ContaCorrente(cliente);
           b1.abreConta(conta);

        } else {
            Cliente cliente = new Cliente();
            cliente.setNome(nome);
            Conta conta = new ContaPoupanca(cliente);
            b1.abreConta(conta);

        }

    }

    public Conta selecionaConta(BancoCentral bc){

        Scanner sc = new Scanner(System.in);

        Banco b1 = escolheBanco(bc);
        limpaConsole();
        int contador = 0;

        while (b1.getContas().isEmpty()) {
            System.out.println("Não existe conta cadastrada para esse banco");
            try { Thread.sleep (2000); } catch (InterruptedException ex) {}
            b1 = escolheBanco(bc);
        }

        for (Conta conta : b1.getContas()) {
            System.out.println("[" + (++contador) + "] - " + conta.cliente.getNome());
        }

        System.out.println();

        int opcao=-1;

        while(true){
            System.out.println("Digite o número corresponte a conta ou 0 para sair: ");
            String entrada = sc.next();
            try{
                opcao = Integer.parseInt(entrada);
                if (opcao >= 0 && opcao <= contador){
                    break;
                }
                System.out.println("Digite apenas o número da sua conta");
            }catch (Exception e){
                System.out.println("Digite apenas números");
            }
        }

        if (opcao == 0) menuPrincipal(bc);

        Conta conta = b1.getContas().get(opcao-1);
        return conta;

    }

    public void escolhaTransacao(Conta conta, BancoCentral bc){

        Scanner sc = new Scanner(System.in);

        limpaConsole();

        System.out.println("[1] Sacar");
        System.out.println("[2] Depositar");
        System.out.println("[3] Transferir");
        System.out.println("[4] Imprimir extrato");
        System.out.println();

        int opcao=-1;

        while(true){
            System.out.println("Digite o número corresponte a uma das opções ou 0 para sair: ");
            String entrada = sc.next();
            try{
                opcao = Integer.parseInt(entrada);
                if (opcao >= 0 && opcao <= 4){
                    break;
                }
                System.out.println("Digite apenas o número da opção");
            }catch (Exception e){
                System.out.println("Digite apenas números");
            }
        }

        if (opcao == 0) {
            menuPrincipal(bc);
        }
        else if (opcao == 1){
             limpaConsole();
             if (sacar(conta)) System.out.println("Saque realizado");
             else System.out.println("Saldo indisponível");
        } else if (opcao == 2) {
            depositar(conta);
            limpaConsole();
            System.out.println("Depósito realizado");
        } else if (opcao == 3) {
            limpaConsole();
            if (transferir(conta, bc)) System.out.println("Transferência realizada com sucesso");
            else System.out.println("Saldo indisponível para transferência");
        } else {
            extrato(conta, bc);
        }

        try { Thread.sleep (2000); } catch (InterruptedException ex) {}
        menuPrincipal(bc);

    }

    public boolean sacar(Conta conta){
        Scanner sc = new Scanner(System.in);

        double valor = 0.0;

        while (true){
            System.out.println("Digite o valor do saque: R$");
            String entrada = sc.next();
            try{
                valor = Double.parseDouble(entrada);
                break;
            }catch (Exception e){
                System.out.println("Digite apenas números");
            }
        }

        conta.sacar(valor);
        return conta.verificaSaque(valor);

    }

    public void depositar(Conta conta){
        Scanner sc = new Scanner(System.in);

        double valor = 0.0;

        while (true){
            System.out.println("Digite o valor do depósito: R$");
            String entrada = sc.next();
            try{
                valor = Double.parseDouble(entrada);;
                break;
            }catch (Exception e){
                System.out.println("Digite apenas números");
            }
        }

        conta.depositar(valor);

    }

    public boolean transferir(Conta conta, BancoCentral bc){

        Scanner sc = new Scanner(System.in);

        double valor = 0.0;

        Conta conta1 = selecionaConta(bc);
        while (conta == conta1) {
            limpaConsole();
            System.out.println("Não é possível transferir para a mesma conta");
            conta1 = selecionaConta(bc);
        }

        while (true){
            System.out.println("Digite o valor da transferência: R$");
            String entrada = sc.next();
            try{
                valor = Double.parseDouble(entrada);;
                break;
            }catch (Exception e){
                System.out.println("Digite apenas números");
            }
        }

        conta.transferir(valor, conta1);
        return conta.verificaSaque(valor);

    }

    public void extrato(Conta conta, BancoCentral bc){

        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        conta.imprimirExtrato();

        while (true){
            System.out.println("Digite 0 para voltar: ");
            String entrada = sc.next();
            try{
                opcao = Integer.parseInt(entrada);
            }catch (Exception e){

            }finally {
                if (opcao == 0) menuPrincipal(bc);
            }
        }


    }

    public void limpaConsole() {

        for (int i = 0; i < 100; i++) {
            System.out.println();
        }

    }
}
