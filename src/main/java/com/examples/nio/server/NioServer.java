package com.examples.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by JackZhang on 17/7/18.
 */
public class NioServer
{
    private static Selector selector;

    public static void main(String[] args) {
        try {
            selector = Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server started, port - 8080");

            while(true){
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> it = keys.iterator();
                SelectionKey key = null;
                while (it.hasNext()){
                    key = it.next();
                    it.remove();

                    handleInput(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleInput(SelectionKey key) {
        try {
            if (key.isValid()){
                if (key.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector,SelectionKey.OP_READ);
                }
                if (key.isReadable()){
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    int readSize = sc.read(buffer);
                    if (readSize>0){
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        //convert to String
                        String result = new String(bytes,"UTF-8").toUpperCase();
                        //send response
                        doWrite(sc,result);
                    }
                }
            }
        }catch(Exception e){
            System.err.println("Handle input error -"+e);
        }

    }

    private static void doWrite(SocketChannel sc, String result) throws IOException {
        byte[] bytes = result.getBytes();
        ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
        writeBuffer.put(bytes);
        writeBuffer.flip();
        sc.write(writeBuffer);
    }

}
