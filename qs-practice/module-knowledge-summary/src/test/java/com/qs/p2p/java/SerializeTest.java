package com.qs.p2p.java;

import com.qs.p2p.model.User;
import org.junit.Test;

import java.io.*;

/**
 * 把对象的字节序列永久地保存到硬盘上，通常存放在一个文件中；
 *
 * 在网络上传送对象的字节序列
 */
public class SerializeTest {
    private static String file_name = "d://obj.bin";

    @Test
    public void test1() {
        //创建持久化对象
        User user = new User();
        user.setUserName("孙常青");
        user.setPhoneNumber("12345678912");
        writeObject(user);

        User userRead = (User) readObject();

        System.out.println(userRead.toString());
    }

    /**
     * 序列化
     */
    public void writeObject(Serializable serializable) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file_name));
            objectOutputStream.writeObject(serializable);
            System.out.println("序列化成功...");
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 反序列化
     */
    public Object readObject() {
        Object object = null;

        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file_name));
            object = objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return object;
    }


}
