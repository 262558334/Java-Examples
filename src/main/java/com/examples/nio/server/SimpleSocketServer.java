package com.examples.nio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by JackZhang on 17/7/16.
 */
public class SimpleSocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        while(true){
            Socket socket = serverSocket.accept();
            try {
                Processor.process(socket);
            } catch (Exception e) {
                System.out.println("Server error - "+e);
            }
        }

    }
}
