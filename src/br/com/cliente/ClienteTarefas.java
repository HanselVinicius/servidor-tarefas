package br.com.cliente;

import java.net.Socket;
import java.util.Scanner;

public class ClienteTarefas {


    public static void main(String[] args) throws Exception{

        Socket socket = new Socket("localhost",12345);


        System.out.println("conexao estabelecida");


        Scanner teclado = new Scanner(System.in);
        teclado.nextLine();

        socket.close();

    }


}
