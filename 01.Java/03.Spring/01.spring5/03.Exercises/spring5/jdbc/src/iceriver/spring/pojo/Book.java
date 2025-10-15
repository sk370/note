package iceriver.spring.pojo;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/13 18:16
 */
public class Book {
    private Integer userId;
    private String userName;
    private String ustatus;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUstatus() {
        return ustatus;
    }

    public void setUstatus(String ustatus) {
        this.ustatus = ustatus;
    }

    @Override
    public String toString() {
        return "Book{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", ustatus='" + ustatus + '\'' +
                '}';
    }
}
