package livechat.chatcommon;

/**
 * @desc: 消息类型
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 15:19
 */
public interface MessgeType {//是不是设置成枚举类更合适？
    String MESSAGE_LOGIN_SUCCEED = "1";//登录成功
    String MESSAGE_LOGIN_FAIL = "2";//登录失败
    String MESSAGE_COMM_MES = "3";//普通信息包
    String MESSAGE_GET_ONLINE_FRIEND = "4";//返回在线用户列表 客户端请求--->服务端
    String MESSAGE_RET_ONLINE_FRIEND = "5";//返回在线用户列表 客户端反馈--->服务端
    String MESSAGE_CLIENT_EXIT = "6";//客户端请求退出
    String MESSAGE_MESSAGE_SUCCEED = "7";//私聊消息发送成功
    String MESSAGE_MESSAGE_FAILD = "8";//私聊消息发送失败
    String MESSAGE_TO_ALL = "9";//群发消息
    String MESSAGE_FILE = "10";//传输文件
}
