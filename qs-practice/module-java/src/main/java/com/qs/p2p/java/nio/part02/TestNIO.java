package com.qs.p2p.java.nio.part02;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;


/**
 * Paths 提供的 get() 方法用来获取 Path 对象：
 * 			Path get(String first, String … more) : 用于将多个字符串串连成路径。
 * 		    Path 常用方法：
 * 			boolean endsWith(String path) : 判断是否以 path 路径结束
 * 			boolean startsWith(String path) : 判断是否以 path 路径开始
 * 			boolean isAbsolute() : 判断是否是绝对路径
 * 			Path getFileName() : 返回与调用 Path 对象关联的文件名
 * 			Path getName(int idx) : 返回的指定索引位置 idx 的路径名称
 * 			int getNameCount() : 返回Path 根目录后面元素的数量
 * 			Path getParent() ：返回Path对象包含整个路径，不包含 Path 对象指定的文件路径
 * 			Path getRoot() ：返回调用 Path 对象的根路径
 * 			Path resolve(Path p) :将相对路径解析为绝对路径
 * 			Path toAbsolutePath() : 作为绝对路径返回调用 Path 对象
 * 			String toString() ： 返回调用 Path 对象的字符串表示形式
 *
 *
 * 	Files常用方法：
 * 			Path copy(Path src, Path dest, CopyOption … how) : 文件的复制
 * 			Path createDirectory(Path path, FileAttribute<?> … attr) : 创建一个目录
 * 			Path createFile(Path path, FileAttribute<?> … arr) : 创建一个文件
 * 			void delete(Path path) : 删除一个文件
 * 			Path move(Path src, Path dest, CopyOption…how) : 将 src 移动到 dest 位置
 * 			long size(Path path) : 返回 path 指定文件的大小
 *
 *
 * 	Files常用方法：用于操作内容
 * 			SeekableByteChannel newByteChannel(Path path, OpenOption…how) : 获取与指定文件的连接，how 指定打开方式。
 * 			DirectoryStream newDirectoryStream(Path path) : 打开 path 指定的目录
 * 			InputStream newInputStream(Path path, OpenOption…how):获取 InputStream 对象
 * 			OutputStream newOutputStream(Path path, OpenOption…how) : 获取 OutputStream 对象
 */
public class TestNIO {

    @Test
    public void test1() {
        Path path = Paths.get("/Users/lion_scq/Desktop/", "nio/source/nio-day01/src/com/atguigu/nio/", "TestBuffer.java");
        System.out.println(path.endsWith("TestBuffer.java"));
        System.out.println(path.startsWith("/Users"));

        System.out.println(path.isAbsolute());
        System.out.println(path.getFileName());

        for (int i = 0; i < path.getNameCount(); i++) {
            System.out.print(path.getName(i) + " ");
        }
        System.out.println();
    }

    @Test
    public void test2() {
        Path path = Paths.get("/Users/lion_scq/Desktop/", "nio/source/nio-day01/src/com/atguigu/nio/", "TestBuffer.java");
        System.out.println(path.getParent());
        System.out.println(path.getRoot());
        System.out.println(path.toAbsolutePath());
        System.out.println(path.toString());
    }

    @Test
    public void test3() throws IOException {
        Path path1 = Paths.get("/Users/lion_scq/Desktop/nio/source/nio-day01/src/com/atguigu/nio/TestBuffer.java");
        Path path2 = Paths.get("/Users/lion_scq/Desktop/nio/source/nio-day01/src/com/atguigu/nio/t.java");

        Files.copy(path1, path2, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    public void test4() throws IOException {
        Path dir = Paths.get("/Users/lion_scq/Desktop/nio/source/nio-day01/src/com/atguigu/nio");
//        Files.createDirectory(dir);
        System.out.println(Files.size(dir));

        Path file = Paths.get("/Users/lion_scq/Desktop/nio/source/nio-day01/src/com/atguigu/nio/TestBuffer.java");
//        Files.deleteIfExists(file);

        BasicFileAttributes readAttribuates = Files.readAttributes(file, BasicFileAttributes.class, LinkOption.NOFOLLOW_LINKS);
        System.out.println(readAttribuates.creationTime());
        System.out.println(readAttribuates.lastModifiedTime());

        DosFileAttributeView fileAttributeView = Files.getFileAttributeView(file, DosFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
        fileAttributeView.setHidden(false);
    }


    @Test
    public void test5() throws IOException{
        SeekableByteChannel newByteChannel = Files.newByteChannel(Paths.get("1.jpg"), StandardOpenOption.READ);

        DirectoryStream<Path> newDirectoryStream = Files.newDirectoryStream(Paths.get("e:/"));

        for (Path path : newDirectoryStream) {
            System.out.println(path);
        }
    }

    //自动资源管理：自动关闭实现 AutoCloseable 接口的资源
    @Test
    public void test6(){
        try(FileChannel inChannel = FileChannel.open(Paths.get("1.jpg"), StandardOpenOption.READ);
            FileChannel outChannel = FileChannel.open(Paths.get("2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE)){

            ByteBuffer buf = ByteBuffer.allocate(1024);
            inChannel.read(buf);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
