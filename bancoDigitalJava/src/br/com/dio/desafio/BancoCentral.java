package br.com.dio.desafio;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class BancoCentral {

    private ArrayList bancos = new ArrayList<Banco>();

    public void novoBanco(Banco banco){
        this.bancos.add(banco);
        //System.out.println(banco.getNome());
    }

    public ArrayList<Banco> getBancos() {
        return bancos;
    }

    public void setBancos(ArrayList<Banco> bancos) {
        this.bancos = bancos;
    }
}
