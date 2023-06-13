package br.com.cliente;

import br.com.cliente.interface_grafica.InterfaceGrafica;
import br.com.cliente.runnables.TarefaRecebeResposta;

import java.io.IOException;
import java.net.Socket;

public class RodaAplicacao {
    public static void main(String[] args)  {
        try(Socket socket = new Socket("localhost",12345)){
            InterfaceGrafica ig = new InterfaceGrafica(socket);
            ig.montaTela();
            ig.imprime("Conexão estabelecida com sucesso");
            inicializaThreadRecebeResposta(socket,ig);
            ig.imprime("Servidor fechado");

        }catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }



    public static void inicializaThreadRecebeResposta(Socket socket, InterfaceGrafica ig) throws InterruptedException{
        Thread threadResposta = new Thread(new TarefaRecebeResposta(socket,ig),"thread_resposta");
        threadResposta.start();
        threadResposta.join();
    }


}
