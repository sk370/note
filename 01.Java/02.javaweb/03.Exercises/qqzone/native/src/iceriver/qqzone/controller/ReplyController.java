package iceriver.qqzone.controller;

import iceriver.qqzone.pojo.Reply;
import iceriver.qqzone.pojo.Topic;
import iceriver.qqzone.pojo.UserBasic;
import iceriver.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.util.Date;

public class ReplyController {

    private ReplyService replyService;

    public String addReply(String content, Integer topicId, HttpSession session) {
        UserBasic author = (UserBasic) session.getAttribute("userBasic");
        Reply reply = new Reply(content, new Date(), author, new Topic(topicId));
        replyService.addReply(reply);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
        // detail.html
    }

    public String delReply(Integer replyId, Integer topicId) {
        replyService.delReply(replyId);
        return "redirect:topic.do?operate=topicDetail&id=" + topicId;
    }
}
