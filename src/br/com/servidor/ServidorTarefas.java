package br.com.servidor;

import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTarefas {


    public static void main(String[] args) throws Exception {
        System.out.println("-------- Iniciando Servidor  -------- ");
        ServerSocket servidor = new ServerSocket(12345);

        while(true) {
            Socket socket = servidor.accept();
            System.out.println(" Aceitando novo cliente na porta :" +socket.getPort());



            DistribuiTarefas distribuiTarefas = new DistribuiTarefas(socket);
            Thread threadCliente = new Thread(distribuiTarefas,"thread-distributora");
            threadCliente.start();

        }
    }

}
