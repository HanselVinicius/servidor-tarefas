package br.com.test.testa_lidar_com_execoes;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Principal {

    public static void main(String[] args) {
        Properties properties = new Properties();

        ExecutorService threadPool = Executors.newCachedThreadPool(new FactoryThread());




        threadPool.execute(new LeitorPropriedades(properties, "arquivo1.txt"));
    }
}

