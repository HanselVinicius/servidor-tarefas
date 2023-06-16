package br.com.test.testa_lidar_com_execoes;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Principal {

    public static void main(String[] args) {
        Properties properties = new Properties();
        ExecutorService threadPool = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread t, Throwable e) {
                        System.out.println("Erro na thread " +e  +" na thread  " +t);
                    }
                });
                thread.start();
                return thread;
            }
        });


        threadPool.execute(new LeitorPropriedades(properties, "arquivo1.txt"));
    }
}

