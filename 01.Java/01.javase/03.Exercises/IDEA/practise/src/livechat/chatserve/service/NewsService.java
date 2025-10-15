package livechat.chatserve.service;

import livechat.chatclient.utils.Utility;
import livechat.chatcommon.Message;
import livechat.chatcommon.MessgeType;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;

/**
 * @desc: 服务器给用户群发消息
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/16 15:40
 */
public class NewsService implements Runnable{
    private boolean flag = true;
    @Override
    public void run() {
        while (flag) {
            System.out.print("请输入推送内容，输入exit表示退出推送：");
            String news = Utility.readString(100);
            if(news.equals("exit")){
                flag = false;
                break;
            }
            Message message = new Message();
            message.setContent(news);
            message.setSender("服务器");
            message.setSendTime(new Date().toString());
            message.setMessType(MessgeType.MESSAGE_TO_ALL);
            System.out.println("服务器推送消息中");

            Hashtable<String, ServerConnectClientThread> hs = ManageServerConnectClientThead.getHs();
            Iterator<String> iterator = hs.keySet().iterator();
            while (iterator.hasNext()) {
                String userId = iterator.next();
                ServerConnectClientThread scct = hs.get(userId);
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(scct.getSocket().getOutputStream());
                    oos.writeObject(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
