# Desafio-DIO-Banco-Digital-Java
Desafio de Projeto DIO Basecamp Santander - Criando um Banco Digital com Java

## O que foi modificado:
- Para o saque e consequentemente transferência, a operação era realizada independente do valor. Agora só há a possibilidade se existir saldo suficiente na conta;
- Todo tipo de operação, como criar conta, saque, transferência e depósito é feita através de um Menu.

## O que foi feito:
- Classe "Banco Central" que contém todos os Bancos criados;
- Por padrão, foi criado na Classe "Main", apenas dois Bancos (Banco1 e Banco2). Cada Banco possui suas contas e seus clientes;
- Para interface com o usuário, foi feito um Menu com 4 operações (Cadastrar um nova conta, mostrar a lista de clientes cadastrados, fazer alguma operação bancária e finalizar o programa);
- Digitar "0" finaliza o programa ou volta para o menu principal.

## Teste o programa comigo:
- Rode a aplicação;
- Crie uma conta em um dos dois bancos através do menu e faça uma operação(saque, depósito e transferência);

  Já criei antecipadamente uma conta com meu nome "Felipe" e depositei R$ 50,00 para testes. Transfira mais um trocadinho para mim :sunglasses:
  
## Melhorias futuras:
- A parte do menu pode ser melhorada, principalmente no retorno digitando "0";
- Há códigos repetidos, portanto a utilização de métodos deveria ser melhor utilizada;
- Criação de uma conta especial com limite, ou seja, será possível fazer saques ou transferências sem um saldo positivo, utilizando o limite da conta.

