package br.com.test.testa_comandos;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {


    public static void main(String[] args) throws Exception {

        BlockingQueue<Comando> comandos = new PriorityBlockingQueue<>();

        comandos.put(new Comando("UPDATE", 3, "curso=threads2&dataCriacao=13/06/2016"));
        comandos.put(new Comando("REMOVE", 1, "id=3"));
        comandos.put(new Comando("ADD", 5, "curso=threads2&dataCriacao=12/06/2016&nivel=avancada"));
        comandos.put(new Comando("GET", 2, "id=4"));

        Comando comando = null;
        while ((comando = comandos.take()) != null){
            System.out.println(comando.getTipo() + " - " + comando.getPrioridade());

        }
    }

}
