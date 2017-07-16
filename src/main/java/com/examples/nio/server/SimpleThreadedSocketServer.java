package com.examples.nio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by JackZhang on 17/7/16.
 */
public class SimpleThreadedSocketServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while(true){
            Socket socket = serverSocket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Processor.process(socket);
                }
            }).start();
        }
    }

}
