package iceriver.spring.pojo;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 8:45
 */
public class Dept {
    private String dept2;

    public void setDept2(String dept) {
        this.dept2 = dept;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dept='" + dept2 + '\'' +
                '}';
    }
}
