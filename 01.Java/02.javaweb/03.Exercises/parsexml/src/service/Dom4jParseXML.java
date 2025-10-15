package service;

import domain.Book;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.util.List;
//直接运行该方法，不需要启动服务器
/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/7/1 13:30
 */
public class Dom4jParseXML {
    public static void main(String[] args) throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read("file:\\D:\\实践练习\\07.JavaWeb\\parsexml\\src\\books.xml");
//        Document document = saxReader.read("src/books.xml");//这种方式找不到xml文件，为什么，视频里就这么写的。
        Element rootElement = document.getRootElement();
        List<Element> books = rootElement.elements();
        for(Element book : books){
            String sn = book.attributeValue("sn");
            Element nameElement = book.element("name");
            String nameElementText = nameElement.getText();
            Element priceElement = book.element("price");
            String priceElementText = priceElement.getText();
            String authorElement = book.elementText("author");

            System.out.println(new Book(sn,nameElementText,Double.parseDouble(priceElementText),authorElement));
        }
    }
}
