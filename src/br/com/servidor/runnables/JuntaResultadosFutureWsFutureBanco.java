package br.com.servidor.runnables;

import java.io.PrintStream;
import java.util.concurrent.*;

public class JuntaResultadosFutureWsFutureBanco implements Callable<Void> {
    private Future<String> futureWs;
    private Future<String> futureBanco;
    private PrintStream saidaCliente;

    public JuntaResultadosFutureWsFutureBanco(Future<String> futureWs, Future<String> futureBanco, PrintStream saidaCliente) {
        this.futureWs = futureWs;
        this.futureBanco = futureBanco;
        this.saidaCliente = saidaCliente;
    }

    @Override
    public Void call()  {
        System.out.println("Aguardando resultados do future WS e Banco....");
        try {
            String numeroMagicoBanco = this.futureBanco.get(20, TimeUnit.SECONDS);
            String numeroMagicoWS = this.futureWs.get(20, TimeUnit.SECONDS);

            this.saidaCliente.println("Resultado comando c2 : banco: " + numeroMagicoBanco + " WS: " + numeroMagicoWS);

        }catch (TimeoutException | InterruptedException | ExecutionException ex){
            System.out.println("ERRO, TIMEOUT : , CANCELANDO EXECUCAO DO COMANDO C2 ");
            this.saidaCliente.println("Timeout:na Execucao do comando c2 -");
            this.futureWs.cancel(true);
            this.futureBanco.cancel(true);
        }
        System.out.println("finalizou JuntaResultadosFutureWsFutureBanco ");
        return null;
    }
}
