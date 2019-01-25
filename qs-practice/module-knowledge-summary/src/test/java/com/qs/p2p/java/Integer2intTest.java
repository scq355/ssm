package com.qs.p2p.java;


import org.junit.Test;

/**
 *  包装类跟基本类型
 *
 *  Integer && int
 * （1）Integer是int的包装类；int是基本数据类型；
 * （2）Integer变量必须实例化后才能使用；int变量不需要；
 * （3）Integer实际是对象的引用，指向此new的Integer对象；int是直接存储数据值 ；
 * （4）Integer的默认值是null；int的默认值是0。
 */
public class Integer2intTest {

    /**
     * 由于Integer变量实际上是对一个Integer对象的引用，所以两个通过new生成的Integer变量永远是不相等的（因为new生成的是两个对象，其内存地址不同）
     */
    @Test
    public void test1() {
        Integer i = new Integer(100);
        Integer j = new Integer(100);
        System.out.println(i == j);
        System.out.println(i.equals(j));
    }

    /**
     * Integer变量和int变量比较时，只要两个变量的值是向等的，则结果为true（因为包装类Integer和基本数据类型int比较时，java会自动拆包装为int，然后进行比较，实际上就变为两个int变量的比较）
     * 非new生成的Integer变量和new Integer()生成的变量比较时，结果为false。（因为非new生成的Integer变量指向的是java常量池中的对象，而new Integer()生成的变量指向堆中新建的对象，两者在内存中的地址不同）
     */
    @Test
    public void test2() {
        Integer i = new Integer(100);
        Integer j = 100;
        int k = 100;
        System.out.println(i == k);
        System.out.println(i == 100);
        System.out.println(i == j);
        System.out.println(i.equals(j));
    }

    /**
     * 对于两个非new生成的Integer对象，进行比较时，如果两个变量的值在区间-128到127之间，则比较结果为true，如果两个变量的值不在此区间，则比较结果为false
     *  java在编译Integer i = 100 ;时，会翻译成为Integer i = Integer.valueOf(100)。而java API中对Integer类型的valueOf的定义如下，对于-128到127之间的数，会进行缓存，Integer i = 127时，会将127进行缓存，下次再写Integer j = 127时，就会直接从缓存中取，就不会new了
     */
    @Test
    public void test3() {
        Integer i = 100;
        Integer j = 100;
        System.out.println(i == j);

        Integer a = 128;
        Integer b = 128;
        System.out.println(a == b);
    }

    @Test
    public void test4() {
        // 自动装箱：将基本数据类型转化为对象，Integer num1 = new Integer(9);
        Integer num1 = 9;
        // 自动拆箱：将对象重新转化为基本数据类型
        System.out.println(num1--);
        System.out.println(--num1);
    }

    /**
     * 归结于java对于Integer与int的自动装箱与拆箱的设计，是一种模式：叫享元模式（flyweight）。
     * 加大对简单数字的重利用，Java定义在自动装箱时对于值从–128到127之间的值，它们被装箱为Integer对象后，会存在内存中被重用，始终只存在一个对象。
     * 而如果超过了从–128到127之间的值，被装箱后的Integer对象并不会被重用，即相当于每次装箱时都新建一个 Integer对象。
     */
    @Test
    public void test5() {
        Integer num1 = 128;
        Integer num2 = 128;
        System.out.println(num1 == num2);

        Integer a = 127;
        Integer b = 127;
        System.out.println(a == b);
        System.out.println(a.hashCode());
    }

}
