package com.qs.p2p.java;

import com.qs.p2p.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * == 比较的是变量(栈)内存中存放的对象的(堆)内存地址，用来判断两个对象的地址是否相同，即是否是指相同一个对象。比较的是真正意义上的指针操作
 *
 * equals用来比较的是两个对象的内容是否相等，由于所有的类都是继承自java.lang.Object类的，所以适用于所有对象，
 * 如果没有对该方法进行覆盖的话，调用的仍然是Object类中的方法，而Object中的equals方法返回的却是==的判断。
 *
 *
 * equal()相等的两个对象他们的hashCode()肯定相等，hashCode()相等的两个对象他们的equal()不一定相等
 *
 * 所有对于需要大量并且快速的对比的话如果都用equal()去做显然效率太低，所以解决方式是，每当需要对比的时候，首先用hashCode()去对比，如果hashCode()不一样，
 * 则表示这两个对象肯定不相等（也就是不必再用equal()去再对比了）,如果hashCode()相同，此时再对比他们的equal()，如果equal()也相同，则表示这两个对象是真的相同了，这样既能大大提高了效率也保证了对比的绝对正确性！
 */
public class EqualsTest {

    @Test
    public void test1() {
        User user1 = new User();
        User user2 = new User();
        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1 == user2);

        User user3 = user1;
        System.out.println(user3.hashCode());
        System.out.println(user1 == user3);
    }

    /**
     * 唯一不需要new 就可以产生对象的途径。以String s="abce";形式赋值在java中叫直接量,它是在常量池中而不是象new一样放在压缩堆中
     *
     * 这种形式的字符串，在JVM内部发生字符串拘留，即当声明这样的一个字符串后，JVM会在常量池中先查找有有没有一个值为"abcd"的对象,如果有,就会把它赋给当前引用.
     * 即原来那个引用和现在这个引用指点向了同一对象,如果没有,则在常量池中新创建一个"abcd",下一次如果有String s1 = "abcd";又会将s1指向"abcd"这个对象,即以这形式声明的字符串,只要值相等,任何多个引用都指向同一对象.
     */
    @Test
    public void test2() {
        String s1 = "asd";
        String s2 = "asd";
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
        System.out.println(s1 == s2);

        String s3 = new String("asd");
        String s4 = new String("asd");
        System.out.println(s3.hashCode());
        System.out.println(s4.hashCode());
        System.out.println(s3 == s4);

        String s5 = new String("kkk");
        String s6 = new String("kkk");
        System.out.println(s5.hashCode());
        System.out.println(s6.hashCode());
        System.out.println(s5 == s6);
    }


    @Test
    public void test3() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUserName("scq");

        User user2 = new User();
        user2.setUserName("scq");

        userList.add(user1);
        userList.add(user2);

        System.out.println(user1.hashCode());
        System.out.println(user2.hashCode());
        System.out.println(user1.equals(user2));
        System.out.println(user1 == user2);

        Set<User> userSet = new HashSet<>();
        userSet.add(user1);
        userSet.add(user2);

        System.out.println(userList.size());
        System.out.println(userSet.size());
    }
}
