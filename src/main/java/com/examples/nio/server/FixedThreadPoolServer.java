package com.examples.nio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by JackZhang on 17/7/16.
 */
public class FixedThreadPoolServer {
    public static void main(String[] args) throws Exception {
        AtomicInteger counter = new AtomicInteger(0);
        ServerSocket serverSocket = new ServerSocket(8080);
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        while(true){
            Socket socket = serverSocket.accept();
            pool.submit(new Runnable() {
                @Override
                public void run() {

                    counter.incrementAndGet();
                    System.out.println("Server is processing -"+counter);
                    Processor.process(socket);
                    try {
                        Thread.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }
}
