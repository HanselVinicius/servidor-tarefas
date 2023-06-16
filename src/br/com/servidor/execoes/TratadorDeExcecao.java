package br.com.servidor.execoes;

public class TratadorDeExcecao implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("ERRO NA THREAD " + t.getName() + " " + ", " +e.getMessage());
    }
}
