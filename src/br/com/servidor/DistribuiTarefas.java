package br.com.servidor;

import br.com.servidor.runnables.ComandoC1;
import br.com.servidor.runnables.ComandoC2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;

public class DistribuiTarefas implements Runnable{

    private final ExecutorService threadPool;
    private final Socket socket;
    private final ServidorTarefas servidorTarefas;

    public DistribuiTarefas(ExecutorService threadPool, Socket socket, ServidorTarefas servidorTarefas) {
        this.threadPool = threadPool;
        this.socket = socket;
        this.servidorTarefas = servidorTarefas;
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
                        ComandoC1 c1 = new ComandoC1(saidaCliente);
                        this.threadPool.execute(c1);
                        break;
                    }
                    case "c2":{
                        saidaCliente.println("Confirmação comando c2 ");
                        ComandoC2 c2 = new ComandoC2(saidaCliente);
                        this.threadPool.execute(c2);
                        break;
                    }case "fim":{
                        saidaCliente.println("Desligando o servidor");
                        servidorTarefas.parar();
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
