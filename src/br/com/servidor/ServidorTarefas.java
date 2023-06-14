package br.com.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServidorTarefas {

    private final ServerSocket servidor;
    private final ExecutorService threadPool;
    private boolean estaRodando;

    public ServidorTarefas() throws IOException {
        System.out.println("-------- Iniciando Servidor  -------- ");
         this.servidor = new ServerSocket(12345);
        this.threadPool = Executors.newCachedThreadPool();
        this.estaRodando = true;
    }

    public static void main(String[] args) throws Exception {

        ServidorTarefas servidor = new ServidorTarefas();
        servidor.rodar();
        servidor.parar();


    }

    public void parar() throws IOException {
        this.estaRodando = false;
        servidor.close();
        threadPool.shutdown();
    }

    public void rodar() throws IOException {
        while(this.estaRodando) {
            try {
                Socket socket = servidor.accept();
                System.out.println(" Aceitando novo cliente na porta :" + socket.getPort());


                DistribuiTarefas distribuiTarefas = new DistribuiTarefas(socket, this);
                threadPool.execute(distribuiTarefas);
            }catch (SocketException ex){
                System.out.println("SocketException, Está rodando? " + this.estaRodando);
            }

        }
    }

}
