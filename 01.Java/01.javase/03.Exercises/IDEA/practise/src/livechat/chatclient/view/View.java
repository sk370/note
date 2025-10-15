package livechat.chatclient.view;

import livechat.chatclient.service.ManageClientConnectServerThread;
import livechat.chatclient.service.UserClientService;
import livechat.chatclient.utils.Utility;
import livechat.chatcommon.Message;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 15:25
 */
public class View {
    private boolean loop = true;
    private String key;//接收用户输入
    private UserClientService userClientService = new UserClientService();

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        View view = new View();
        view.mainMenu();

    }

    private void mainMenu() throws IOException, ClassNotFoundException {
        while (loop) {
            System.out.println("===================欢迎登录网络通信系统===================");
            System.out.println("\t\t 1 登录系统");
            System.out.println("\t\t 9 退出系统");
            System.out.print("请选择登录类型：");
            System.out.println(ManageClientConnectServerThread.hm.size());
            key = Utility.readString(1);

            switch (key) {
                case "1":
                    System.out.print("请输入用户id：");
                    String userId = Utility.readString(50);
                    System.out.print("请输入密码：");
                    String password = Utility.readString(50);

                    if (userClientService.checekUser(userId, password)) {
                        System.out.println("欢迎" + userId);
                        while (loop) {
                            System.out.println("===================网络通信系统二级菜单(" + userId + ")===================");
                            System.out.println("\t\t 1 显示在线用户列表");
                            System.out.println("\t\t 2 群发消息");
                            System.out.println("\t\t 3 私聊消息");
                            System.out.println("\t\t 4 发送文件");
                            System.out.println("\t\t 9 退出系统");
                            System.out.print("请输入你的选择: ");
                            System.out.println(ManageClientConnectServerThread.hm.size());
                            key = Utility.readString(1);
                            switch (key) {
                                case "1":
                                    userClientService.onLineFriendList();
                                    break;
                                case "2":
                                    System.out.println("请输入想对大家说的话: ");
                                    String s = Utility.readString(100);
                                    userClientService.sendMessageToAll(s, userId);
                                    break;
                                case "3":
                                    System.out.print("请输入想聊天的用户号(在线): ");
                                    String getterId = Utility.readString(50);
                                    System.out.print("请输入想说的话: ");
                                    String content = Utility.readString(100);
                                    userClientService.secretChat(content, userId, getterId);

                                    break;
                                case "4":
                                    System.out.print("请输入你想把文件发送给的用户(在线用户): ");
                                    getterId = Utility.readString(50);
                                    System.out.print("请输入发送文件的路径(形式 d:\\xx.jpg)");
                                    String src = Utility.readString(100);
                                    System.out.print("请输入把文件发送到对应的路径(形式 d:\\yy.jpg)");
                                    String dest = Utility.readString(100);
                                    userClientService.sendFileToOne(src,dest,userId,getterId);
                                    break;
                                case "9":
                                    userClientService.logout();
                                    loop = false;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("登录失败");
                    }
                    break;
                case "9":
                    System.out.println("退出登录");
                    loop = false;
                    break;
            }
        }
    }
}
