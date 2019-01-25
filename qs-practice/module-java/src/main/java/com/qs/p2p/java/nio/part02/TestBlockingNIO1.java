package com.qs.p2p.java.nio.part02;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用 NIO 完成网络通信的三个核心：
 *
 *   1. 通道（Channel）：负责连接
 *   	   java.nio.channels.Channel 接口：
 *   			|--SelectableChannel
 *  				|--SocketChannel
 *  				|--ServerSocketChannel
 *  				|--DatagramChannel
 *
 *  				|--Pipe.SinkChannel
 *  				|--Pipe.SourceChannel
 *
 *  2. 缓冲区（Buffer）：负责数据的存取
 *
 *  3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 */
public class TestBlockingNIO1 {


    @Test
    public void client() throws IOException {
        //1. 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/lion_scq/Desktop/TestPipe.java"), StandardOpenOption.READ);

        //2. 分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //3. 读取本地文件，并发送到服务端
        while (fileChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        fileChannel.close();
        socketChannel.close();

    }


    @Test
    public void server() throws IOException {
        //1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        FileChannel fileChannel = FileChannel.open(Paths.get("/Users/lion_scq/Desktop/TestNIO.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //2. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8899));
        //3. 获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();

        //4. 分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //5. 接收客户端的数据，并保存到本地
        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        //6. 关闭通道
        socketChannel.close();
        fileChannel.close();
        serverSocketChannel.close();
    }
}
