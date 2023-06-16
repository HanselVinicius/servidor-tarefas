package br.com.servidor.runnables;

import java.io.PrintStream;

public class ComandoC2 implements Runnable {
    private final PrintStream saidaCliente;

    public ComandoC2(PrintStream saidaCliente) {
        this.saidaCliente = saidaCliente;
    }

    @Override
    public void run() {
        System.out.println("Executando comando c2");

        try{
            Thread.sleep(20000);
        }catch (InterruptedException ex){
            throw new RuntimeException();
        }


        saidaCliente.println("Comando c2 executado com sucesso");
    }
}
