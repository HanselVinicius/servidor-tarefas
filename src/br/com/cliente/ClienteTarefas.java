package br.com.cliente;

import br.com.cliente.interface_grafica.InterfaceGrafica;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {


    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost",12345);


        System.out.println("conexao estabelecida");
//        InterfaceGrafica ig = new InterfaceGrafica(socket);
//        ig.montaTela();
        Thread threadEnviaComando = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    System.out.println("Pode enviar comandos!");
                PrintStream saida = new PrintStream(socket.getOutputStream());

                Scanner teclado = new Scanner(System.in);
                while (teclado.hasNextLine()){
                    String linha = teclado.nextLine();

                    if (linha.trim().equals("")){
                        break;
                    }
                    saida.println(linha);

                }
                    saida.close();
                    teclado.close();
            }catch (IOException ex){
                    throw  new RuntimeException(ex);
                }
            }
        }, "threadEnviaComando");

        System.out.println("------ Recebendo dados do servidor --------- ");

        Thread threadRecebeResposta = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner respostaServidor = null;
                try {
                    respostaServidor = new Scanner(socket.getInputStream());

                    while (respostaServidor.hasNextLine()) {
                        String linha = respostaServidor.nextLine();
                        System.out.println(linha);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                respostaServidor.close();
            }
        },"threadRecebeResposta");


        threadRecebeResposta.start();
        threadEnviaComando.start();

        threadEnviaComando.join();


        System.out.println("Fechando socket do cliente ");
        socket.close();

    }


}
