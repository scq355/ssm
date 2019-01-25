package com.qs.p2p.java;

import com.qs.p2p.model.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据结构：JDK7中HashMap采用的是位桶+链表的方式，即我们常说的散列链表的方式，而JDK8中采用的是位桶+链表/红黑树的方式
 *
 * 线程安全：非线程安全
 *
 * 效率：如果成百上千个节点在hash时发生碰撞，存储一个链表中，要查找其中一个节点，不可避免的花费O(N)的查找时间。再最坏的情况下，链表查找的时间复杂度为O(n),而红黑树一直是O(logn),提高HashMap的效率
 */
public class HashMapTest {

    @Test
    public void test1() {
        Map<String, Object> map = new HashMap<>();

        System.out.println(1 << 4);

        System.out.println(1 << 30);

        // hash算法
        int h = 3;
        String key = "scq";
        map.put(key, new User());
        h = key.hashCode();
        System.out.println(h);
        Integer temp = h >>> 16;
        System.out.println(temp);
        Integer result = h ^ temp;
        System.out.println(result);
    }

    @Test
    public void test2() {
        Integer num1 = null;
        Integer num2 = null;
        System.out.println(num1 == num2);
        System.out.println(num1.equals(num2));
    }
}
