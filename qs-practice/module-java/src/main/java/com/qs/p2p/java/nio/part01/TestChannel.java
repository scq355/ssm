package com.qs.p2p.java.nio.part01;

import ch.qos.logback.core.db.dialect.SybaseSqlAnywhereDialect;
import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 * 通道（Channel）：用于源节点与目标节点的连接。在 Java NIO 中负责缓冲区中数据的传输。Channel 本身不存储数据，因此需要配合缓冲区进行传输。
 *
 * 通道的主要实现类
 * java.nio.channels.Channel 接口：
 * 		|--FileChannel
 * 		|--SocketChannel
 * 		|--ServerSocketChannel
 * 		|--DatagramChannel
 *
 *
 * 获取通道
 * Java 针对支持通道的类提供了 getChannel() 方法
 * 		本地 IO：
 * 		FileInputStream/FileOutputStream
 * 		RandomAccessFile
 *
 * 		网络IO：
 * 		Socket
 * 		ServerSocket
 * 		DatagramSocket
 *
 *
 * 在 JDK 1.7 中的 NIO.2 针对各个通道提供了静态方法 open()
 * 在 JDK 1.7 中的 NIO.2 的 Files 工具类的 newByteChannel()
 *
 *
 * 通道之间的数据传输
 * transferFrom()
 * transferTo()
 *
 *
 * 分散(Scatter)与聚集(Gather)
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中
 *
 *
 * 字符集：Charset
 * 编码：字符串 -> 字节数组
 * 解码：字节数组  -> 字符串
 *
 * Created by scq on 2018-07-27 16:55:09
 */
public class TestChannel {

	//利用通道完成文件的复制（非直接缓冲区）
	@Test
	public void test1() {
		long start = System.currentTimeMillis();

		FileInputStream fis = null;
		FileOutputStream fos = null;

		// 获取通道
		FileChannel inChannel = null;
		FileChannel outChannel = null;

		try {
			fis = new FileInputStream("D:/mytools/ideaIU-2018.2.win.zip");
			fos = new FileOutputStream("D:/data/ideaIU-2018.2.win.zip");

			inChannel = fis.getChannel();
			outChannel = fos.getChannel();

			ByteBuffer buffer = ByteBuffer.allocate(1024);
			//③将通道中的数据存入缓冲区中
			while (inChannel.read(buffer) != -1) {
				//切换读取数据的模式
				buffer.flip();
				//④将缓冲区中的数据写入通道中
				outChannel.write(buffer);
				//清空缓冲区
				buffer.clear();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (outChannel != null) {
				try {
					outChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inChannel != null) {
				try {
					inChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("耗费时间为：" + (end - start));
	}

	//使用直接缓冲区完成文件的复制(内存映射文件)
	@Test
	public void test2() {
		long start = System.currentTimeMillis();

		try {
			FileChannel inChannel = FileChannel.open(Paths.get("D:/mytools/ideaIU-2018.2.win.zip"), StandardOpenOption.READ);
			FileChannel outChannel = FileChannel.open(Paths.get("D:/mytools/ideaIU.zip"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

			//内存映射文件
			MappedByteBuffer inMappedBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
			MappedByteBuffer outMappedBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

			//直接对缓冲区进行数据的读写操作
			byte[] dst = new byte[inMappedBuf.limit()];
			inMappedBuf.get(dst);
			outMappedBuf.put(dst);

			inChannel.close();
			outChannel.close();

			long end = System.currentTimeMillis();
			System.out.println("耗费时间为：" + (end - start));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//通道之间的数据传输(直接缓冲区)
	@Test
	public void test3() {
		try {
			FileChannel inChannel = FileChannel.open(Paths.get("D:/mytools/ideaIU-2018.2.win.zip"), StandardOpenOption.READ);
			FileChannel outChannel = FileChannel.open(Paths.get("D:/mytools/ideaIU.zip"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE);

			inChannel.transferTo(0, inChannel.size(), outChannel);
			outChannel.transferFrom(inChannel, 0, inChannel.size());

			inChannel.close();
			outChannel.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//分散和聚集
	@Test
	public void test4() throws IOException {
		long start = System.currentTimeMillis();

		RandomAccessFile randomAccessFile = new RandomAccessFile("D:/mytools/ideaIU-2018.2.win.zip", "rw");

		//1. 获取通道
		FileChannel fileChannel = randomAccessFile.getChannel();

		//2. 分配指定大小的缓冲区
		ByteBuffer byteBuffer1 = ByteBuffer.allocate(100);
		ByteBuffer byteBuffer2 = ByteBuffer.allocate(1024);

		//3. 分散读取
		ByteBuffer[] bufs = {byteBuffer1, byteBuffer2};
		fileChannel.read(bufs);

		for (ByteBuffer byteBuffer : bufs) {
			byteBuffer.flip();
		}

		System.out.println(new String(bufs[0].array(), 0, bufs[0].limit()));
		System.out.println("-----------------");
		System.out.println(new String(bufs[1].array(), 0, bufs[1].limit()));

		//4. 聚集写入
		RandomAccessFile randomAccessFile1 = new RandomAccessFile("D:/mytools/2018.zip", "rw");
		FileChannel fileChannel1 = randomAccessFile1.getChannel();

		fileChannel1.write(bufs);

		long end = System.currentTimeMillis();

		System.out.println("耗费时间为：" + (end - start));
	}


	@Test
	public void test5() {
		Map<String, Charset> map = Charset.availableCharsets();

		Set<Map.Entry<String, Charset>> set = map.entrySet();

		for (Map.Entry<String, Charset> entry : set) {
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
	}

	//字符集
	@Test
	public void test6() throws CharacterCodingException {
		Charset charset1 = Charset.forName("GBK");

		CharsetEncoder encoder = charset1.newEncoder();

		CharsetDecoder decoder = charset1.newDecoder();

		CharBuffer charBuffer1 = CharBuffer.allocate(1024);
		charBuffer1.put("你好吗");
		charBuffer1.flip();

		//编码
		ByteBuffer byteBuffer = encoder.encode(charBuffer1);
		for (int i = 0; i < 6; i++) {
			System.out.println(byteBuffer.get());
		}

		//解码
		byteBuffer.flip();
		CharBuffer charBuffer2 = decoder.decode(byteBuffer);
		System.out.println(charBuffer2.toString());

		System.out.println("------------------------------------------------------");

		Charset charset2 = Charset.forName("GBK");
		byteBuffer.flip();
		CharBuffer charBuffer3 = charset2.decode(byteBuffer);
		System.out.println(charBuffer3.toString());
	}

}
