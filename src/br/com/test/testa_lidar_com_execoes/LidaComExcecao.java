package br.com.test.testa_lidar_com_execoes;

public class LidaComExcecao implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("Erro na thread " +e  +" na thread  " +t.getName());
    }
}
