package br.com.cliente;

import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {


    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost",12345);


        System.out.println("conexao estabelecida");

        PrintStream saida = new PrintStream(socket.getOutputStream());
        saida.println("c1");

        Scanner teclado = new Scanner(System.in);
        teclado.nextLine();

        saida.close();
        teclado.close();
        socket.close();

    }


}
