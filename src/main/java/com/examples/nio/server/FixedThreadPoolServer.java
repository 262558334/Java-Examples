package com.examples.nio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by JackZhang on 17/7/16.
 */
public class FixedThreadPoolServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        ExecutorService pool = Executors.newFixedThreadPool(1000);
        while(true){
            Socket socket = serverSocket.accept();
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    Processor.process(socket);
                }
            });

        }
    }
}
