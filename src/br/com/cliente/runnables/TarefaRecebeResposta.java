package br.com.cliente.runnables;

import br.com.cliente.interface_grafica.InterfaceGrafica;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TarefaRecebeResposta implements Runnable{

    private final Socket socket;
    private final InterfaceGrafica interfaceGrafica;

    public TarefaRecebeResposta(Socket socket, InterfaceGrafica interfaceGrafica) {
        this.socket = socket;
        this.interfaceGrafica = interfaceGrafica;
    }


    @Override
    public void run() {
        try{
            interfaceGrafica.imprime("Recebendo dados do servidor");
            Scanner respostaServidor =  new Scanner(socket.getInputStream());

            while (respostaServidor.hasNextLine()){
                String linha = respostaServidor.nextLine();
                interfaceGrafica.imprime(linha);
            }

            respostaServidor.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
