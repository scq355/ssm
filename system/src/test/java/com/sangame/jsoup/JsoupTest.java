package com.sangame.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by admin on 2017/5/10.
 */
public class JsoupTest {
    public static void main(String[] args) throws Exception{
        /*String html1 = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc1 = Jsoup.parse(html1);
        System.out.printf("doc " + doc1);
        System.out.println("");
        String html2 = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        Document doc2 = Jsoup.parse(html2);
        System.out.printf("doc2" + doc2);
        System.out.println("");
        String html = "<div><p>Lorem ipsum.</p>";
        Document doc3 = Jsoup.parseBodyFragment(html);
        Element body3 = doc3.body();
        System.out.printf("body3" + body3);

        System.out.println("");
        Document doc4 = null;
        doc4 = Jsoup.connect("https://www.baidu.com/").get();
        String title4 = doc4.title();
        System.out.printf("title4 " + title4);

        Document doc5 = Jsoup.connect("http://example.com")
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();
        System.out.println("doc5 " + doc5);
        File input = new File("D:\\system\\src\\main\\resources\\input.html");
        Document doc6 = Jsoup.parse(input, "UTF-8", "http://www.baidu.com/");


        File inputa = new File("/tmp/input.html");
        Document doc = Jsoup.parse(inputa, "UTF-8", "http://example.com/");

        Element content = doc.getElementById("content");
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            String linkHref = link.attr("href");
            String linkText = link.text();
        }*/
        /*String html = "<p>An <a href='http://www.nwpu.edu.cn/'><b>nwpu</b></a> link.</p>";
        Document doc = Jsoup.parse(html);//解析HTML字符串返回一个Document实现
        Element link = doc.select("a").first();//查找第一个a元素

        String text = doc.body().text(); // "An example link"//取得字符串中的文本
        String linkHref = link.attr("href"); // "http://example.com/"//取得链接地址
        String linkText = link.text(); // "example""//取得链接地址中的文本

        String linkOuterH = link.outerHtml();
        // "<a href="http://example.com"><b>example</b></a>"
        String linkInnerH = link.html(); // "<b>example</b>"//取得链接内的html内容

        System.out.printf(doc + " == " + link + "==" + text + "==" + linkHref + "==" + linkText + "==" + linkOuterH + "==" + linkInnerH);
*/
        Document docss = Jsoup.connect("http://www.baidu.com").get();

        Element linkss = docss.select("a").first();
        String relHref = linkss.attr("href"); // == "/"
        String absHref = linkss.attr("abs:href"); // "http://www.open-open.com/"
        System.out.printf(linkss + ":" + relHref + ":" + absHref);
    }
}
