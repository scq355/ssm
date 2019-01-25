package com.qs.p2p.java.nio.part02;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 使用 NIO 完成网络通信的三个核心：
 *
 * 1. 通道（Channel）：负责连接
 *
 * 	   java.nio.channels.Channel 接口：
 * 			|--SelectableChannel
 * 				|--SocketChannel
 * 				|--ServerSocketChannel
 * 				|--DatagramChannel
 *
 * 				|--Pipe.SinkChannel
 * 				|--Pipe.SourceChannel
 *
 * 2. 缓冲区（Buffer）：负责数据的存取
 *
 * 3. 选择器（Selector）：是 SelectableChannel 的多路复用器。用于监控 SelectableChannel 的 IO 状况
 *
 **/
public class TestNonBlockingNIO {

    @Test
    public void client() throws IOException {
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8899));
        socketChannel.configureBlocking(false);
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String string = scanner.next();
            byteBuffer.put((new Date().toString() + "\n" + string).getBytes());
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            byteBuffer.clear();
        }
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        //1. 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2. 切换非阻塞模式
        serverSocketChannel.configureBlocking(false);
        //3. 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(8899));
        //4. 获取选择器
        Selector selector = Selector.open();
        //5. 将通道注册到选择器上, 并且指定“监听接收事件”
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //6. 轮询式的获取选择器上已经“准备就绪”的事件
        while (selector.select() > 0) {
            //7. 获取当前选择器中所有注册的“选择键(已就绪的监听事件)”
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                //8. 获取准备“就绪”的是事件
                SelectionKey selectionKey = iterator.next();
                //9. 判断具体是什么事件准备就绪
                if (selectionKey.isAcceptable()) {
                    //10. 若“接收就绪”，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //11. 切换非阻塞模式
                    socketChannel.configureBlocking(false);
                    //12. 将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (selectionKey.isReadable()) {
                    //13. 获取当前选择器上“读就绪”状态的通道
                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                    //14. 读取数据
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int length = 0;
                    while ((length = socketChannel.read(byteBuffer)) != -1) {
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(), 0, length));
                        byteBuffer.clear();
                    }
                }
                //15. 取消选择键 SelectionKey
                iterator.remove();
            }

        }

    }
}
