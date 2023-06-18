package br.com.test.testa_fila;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestaFila {

    public static void main(String[] args) throws InterruptedException{
        // uma fila de comandos é criada aqui aonde quando não possui comandos espera
        // ate que um comando seja disponibilizado para ser consumido

        BlockingQueue<String> fila = new ArrayBlockingQueue<String>(3);

        fila.put("c1");
        fila.put("c2");
        fila.put("c3");
        //aguarda ate alguem tirar um elemento para colocar mais um na fila
        fila.put("c4");
        System.out.println(fila.take());
        System.out.println(fila.take());
        System.out.println(fila.take());


        System.out.println(fila.size());


    }

}
