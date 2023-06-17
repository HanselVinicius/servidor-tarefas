package br.com.test.testa_lidar_com_execoes;

import java.util.concurrent.ThreadFactory;

public class FactoryThread implements ThreadFactory {


    private static Integer number = 0;

    @Override
    public Thread newThread(Runnable r) {
        number++;
        Thread thread = new Thread(r, "thread " +number);

        thread.setUncaughtExceptionHandler(new LidaComExcecao());



        return thread;
    }
}
