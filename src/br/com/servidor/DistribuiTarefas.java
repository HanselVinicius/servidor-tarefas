package br.com.servidor;

import java.net.Socket;

public class DistribuiTarefas implements Runnable{

    private final Socket socket;
    public DistribuiTarefas(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        System.out.println("Distribuindo tarefas para " +socket);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
