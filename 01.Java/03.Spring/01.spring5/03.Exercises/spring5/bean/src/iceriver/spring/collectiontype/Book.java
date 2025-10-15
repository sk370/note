package iceriver.spring.collectiontype;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Book {
    private List<String> list;
    private Set<String> list2;
    private Map<String,String> list3;
    public void setList(List<String> list) {
        this.list = list;
    }

    public void setList2(Set<String> list2) {
        this.list2 = list2;
    }

    public void setList3(Map<String, String> list3) {
        this.list3 = list3;
    }

    public void test() {
        System.out.println(list + " " + list2 + " " + list3);
    }
}
