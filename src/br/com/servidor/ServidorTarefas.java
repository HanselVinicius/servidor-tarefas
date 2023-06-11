package br.com.servidor;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {


    public static void main(String[] args) throws Exception {
        System.out.println("-------- Iniciando Servidor  -------- ");
        ServerSocket servidor = new ServerSocket(12345);

        ExecutorService threadPool = Executors.newCachedThreadPool();



        while(true) {
            Socket socket = servidor.accept();
            System.out.println(" Aceitando novo cliente na porta :" +socket.getPort());



            DistribuiTarefas distribuiTarefas = new DistribuiTarefas(socket);
            threadPool.execute(distribuiTarefas);

        }
    }

}
