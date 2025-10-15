package collection.list;

import java.util.ArrayList;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/8 14:33
 */
public class News {
    private String title;
    private String content;

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                '}';
    }
    public String processTitle(){
        String s = title;
        if(title.length() > 15){
            s = title.substring(0, 15) + "...";
        }
        return s;
    }
    public static void main(String[] args) {
        News news1 = new News("java-集合（练习）_鸵鸟在滑雪的博客-CSDN博客");
        News news2 = new News("Java入门集合之Set集合(重写equals（）和hashcode（）方法)");
        System.out.println(news1.processTitle());
        ArrayList arrayList = new ArrayList();
        arrayList.add(news1);
        arrayList.add(news2);
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            News news = (News)arrayList.get(i);
            System.out.println(news.processTitle());
        }
    }
}