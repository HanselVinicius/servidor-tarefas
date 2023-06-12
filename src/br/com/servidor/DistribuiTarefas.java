package br.com.servidor;

import java.io.IOException;
import java.io.PrintStream;
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


            Scanner entradaCliente = new Scanner(socket.getInputStream());
            PrintStream saidaCliente = new PrintStream(socket.getOutputStream());

            while (entradaCliente.hasNextLine()){
                String comando = entradaCliente.nextLine();
                System.out.println("Comando recebido " +comando);


                switch (comando){
                    case "c1": {
                        saidaCliente.println("Confirmação comando c1 ");
                        break;
                    }
                    case "c2":{
                        saidaCliente.println("Confirmação comando c2 ");
                        break;
                    }
                    default:{
                        saidaCliente.println("comando nao encontrado");
                    }

                }

                System.out.println(comando);
            }

            saidaCliente.close();
            entradaCliente.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
