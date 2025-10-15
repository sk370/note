package iceriver.springsecurity.entity;

import java.util.Date;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className Admin
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/09/04 16:05
 */
public class Admin {
    private Integer id;
    private String loginAcct;
    private String userPswd;
    private String username;
    private Date createTime;
    private String email;

    public Admin() {
    }

    public Admin(Integer id, String loginAcct, String userPswd, String username, Date createTime, String email) {
        this.id = id;
        this.loginAcct = loginAcct;
        this.userPswd = userPswd;
        this.username = username;
        this.createTime = createTime;
        this.email = email;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", loginAcct='" + loginAcct + '\'' +
                ", userPswd='" + userPswd + '\'' +
                ", username='" + username + '\'' +
                ", createTime=" + createTime +
                ", email='" + email + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginAcct() {
        return loginAcct;
    }

    public void setLoginAcct(String loginAcct) {
        this.loginAcct = loginAcct;
    }

    public String getUserPswd() {
        return userPswd;
    }

    public void setUserPswd(String userPswd) {
        this.userPswd = userPswd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
