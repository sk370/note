package iceriver.spring.pojo;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/15 8:45
 */
public class Emp {
    private String ename;
    private String gender;
    private Dept dept3;

    public void setDept3(Dept dept) {
        this.dept3 = dept;
    }

    public Dept getDept3() {
        return dept3;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    public void add(){
        System.out.println(ename + " " + gender + " " + dept3);
    }
}
