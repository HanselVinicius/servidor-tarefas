package br.com.servidor;

import br.com.servidor.runnables.ComandoC1;
import br.com.servidor.runnables.ComandoC2AcessaBanco;
import br.com.servidor.runnables.ComandoC2ChamaWs;
import br.com.servidor.runnables.JuntaResultadosFutureWsFutureBanco;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class DistribuiTarefas implements Runnable{

    private final ExecutorService threadPool;
    private BlockingQueue<String> filaComandos;
    private final Socket socket;
    private final ServidorTarefas servidorTarefas;

    public DistribuiTarefas(ExecutorService threadPool, BlockingQueue<String> filaComandos, Socket socket, ServidorTarefas servidorTarefas) {
        this.threadPool = threadPool;
        this.filaComandos = filaComandos;
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
                        saidaCliente.println("Confirmação comando c2WS ");
                        ComandoC2ChamaWs c2WS = new ComandoC2ChamaWs(saidaCliente);
                        ComandoC2AcessaBanco c2Banco = new ComandoC2AcessaBanco(saidaCliente);
                        Future<String> futureWs = this.threadPool.submit(c2WS);
                        Future<String> futureBanco = this.threadPool.submit(c2Banco);

                        this.threadPool.submit(new JuntaResultadosFutureWsFutureBanco(futureWs,futureBanco,saidaCliente));

                        break;
                    } case "c3":{
                        this.filaComandos.put(comando); //bloqueia a execucao ate que chegue um novo comando
                        saidaCliente.println("Comando c3 adcionado na fila ");

                     }

                    case "fim":{
                        saidaCliente.println("Desligando o servidor");
                        servidorTarefas.parar();
                        break;
                    }
                    default:{
                        saidaCliente.println("comando nao encontrado");
                    }

                }

//                System.out.println(comando);
            }

            saidaCliente.close();
            entradaCliente.close();
        } catch (IOException | InterruptedException  e) {
            throw new RuntimeException(e);
        }

    }
}
