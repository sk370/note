package livechat.chatcommon;

import java.io.Serializable;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 15:11
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {
    }

    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
