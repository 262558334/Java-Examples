package com.examples.nio.server;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by JackZhang on 17/7/16.
 */
public class Processor {
    public static void process(Socket socket) {
        try {

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            int data;
            while ((data = inputStream.read()) != -1) {
                if (Character.isLetter(data)) {
                    outputStream.write(Character.toUpperCase(data));
                } else {
                    outputStream.write(data);
                }

            }
//            Thread.sleep(100000);
        }
        catch (Exception e){
            System.err.println("Processor error - "+e);
        }
    }
}
