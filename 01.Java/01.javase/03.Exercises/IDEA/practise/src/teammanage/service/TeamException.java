package teammanage.service;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/27 10:49
 */
public class TeamException extends Exception{
    static final long serialVersionUID = -33875169924229948L;

    public TeamException() {
    }
    public TeamException(String message) {
        super(message);
    }
}
