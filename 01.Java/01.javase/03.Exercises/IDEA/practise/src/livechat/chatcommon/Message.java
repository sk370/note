package livechat.chatcommon;

import java.io.Serializable;

/**
 * @desc: 客户端与服务端通信时的消息对象，这里采用广域网的形式，客户端与客户端不进行通信
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/15 15:13
 */
public class Message implements Serializable {//由于需要传输对象流，所以要求该对象可序列化
    private static final long serialVersionUID = 1L;
    private String sender;//客户端的请求者 / 客户端的发送者
    private String receiver;//客户端的接收者
    private String content;
    private String sendTime;
    private String messType;
    //文件传输相关的变量
    private byte[] fileBytes;
    private int fileLen = 0;
    private String srcPath;
    private String destPath;

    public byte[] getFileBytes() {
        return fileBytes;
    }

    public void setFileBytes(byte[] fileBytes) {
        this.fileBytes = fileBytes;
    }

    public int getFileLen() {
        return fileLen;
    }

    public void setFileLen(int fileLen) {
        this.fileLen = fileLen;
    }

    public String getSrcPath() {
        return srcPath;
    }

    public void setSrcPath(String srcPath) {
        this.srcPath = srcPath;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public String getMessType() {
        return messType;
    }

    public void setMessType(String messType) {
        this.messType = messType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSendTime() {
        return sendTime;
    }

    public void setSendTime(String sendTime) {
        this.sendTime = sendTime;
    }
}
