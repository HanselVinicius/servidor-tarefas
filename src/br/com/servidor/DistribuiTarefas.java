package br.com.servidor;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DistribuiTarefas implements Runnable{

    private final Socket socket;
    public DistribuiTarefas(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
        System.out.println("Distribuindo tarefas para " +socket);


            Scanner entradaCliiente = new Scanner(socket.getInputStream());

            while (entradaCliiente.hasNextLine()){
                String comando = entradaCliiente.nextLine();
                System.out.println(comando);
            }

            entradaCliiente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
