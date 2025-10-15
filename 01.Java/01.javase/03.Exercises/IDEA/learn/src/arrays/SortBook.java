package arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/4 17:42
 */
public class SortBook {
    public static void main(String[] args) {
        Book[] books = new Book[4];
        books[0] = new Book("红楼梦", 100);
        books[1] = new Book("金瓶梅新", 90);
        books[2] = new Book("青年文摘20年", 5);
        books[3] = new Book("java从入门到放弃", 300);
        System.out.println("按价格从高到低");
        Arrays.sort(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book)o1;
                Book book2 = (Book)o2;
                if(book1.price - book2.price > 0){
                    return 1;
                } else if (book1.price - book2.price < 0) {
                    return -1;
                }else{
                    return 0;
                }
            }
        });
        System.out.println(Arrays.toString(books));
        System.out.println("按书名从长到短");
        Arrays.sort(books, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Book book1 = (Book)o1;
                Book book2 = (Book)o2;
                return book2.name.length() - book1.name.length();
            }
        });
        System.out.println(Arrays.toString(books));
    }
}
class Book{
    public String name;
    public double price;
    public Book(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}