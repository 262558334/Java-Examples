package com.examples.nio.client;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by JackZhang on 17/7/16.
 */
public class TroubleMaker {
    public static void main(String[] args) throws InterruptedException {
        for (int i=0;i<3000;i++){
            try {
                new Socket("localhost",8080);
                System.out.println(i);
            } catch (IOException e) {
                System.err.println("Could not connect to server - "+e);
            }
        }
    }
}
