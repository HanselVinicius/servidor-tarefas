package br.com.servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

public class ServidorTarefas {

    private final ServerSocket servidor;
    private final ExecutorService threadPool;
    // volatile e atmoics são para permitir que as threads não salvem os valores destas variaveis em seu cache local
    private AtomicBoolean estaRodando;

    public ServidorTarefas() throws IOException {
        System.out.println("-------- Iniciando Servidor  -------- ");
         this.servidor = new ServerSocket(12345);
        this.threadPool = Executors.newCachedThreadPool();
        this.estaRodando = new AtomicBoolean(true);
    }

    public static void main(String[] args) throws Exception {

        ServidorTarefas servidor = new ServidorTarefas();
        servidor.rodar();
        servidor.parar();


    }

    public void parar() throws IOException {
        this.estaRodando.set(false);
        servidor.close();
        threadPool.shutdown();
    }

    public void rodar() throws IOException {
        while(this.estaRodando.get()) {
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
