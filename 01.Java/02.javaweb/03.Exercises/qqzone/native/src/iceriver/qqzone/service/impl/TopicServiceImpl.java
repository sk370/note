package iceriver.qqzone.service.impl;

import iceriver.qqzone.dao.TopicDAO;
import iceriver.qqzone.pojo.Reply;
import iceriver.qqzone.pojo.Topic;
import iceriver.qqzone.pojo.UserBasic;
import iceriver.qqzone.service.ReplyService;
import iceriver.qqzone.service.TopicService;
import iceriver.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {

    private TopicDAO topicDAO = null;
    //此处引用的是replyService，而不是replyDAO
    private ReplyService replyService;
    private UserBasicService userBasicService;

    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return topicDAO.getTopicList(userBasic);
    }

    /**
     * 根据id获取指定的topic信息，包含这个topic关联的作者信息
     * @param id
     * @return
     */
    @Override
    public Topic getTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author = userBasicService.getUserBasicById(author.getId());
        topic.setAuthor(author);
        return topic;
    }

    @Override
    public void delTopic(Integer id) {
        Topic topic = topicDAO.getTopic(id);
        if (topic != null) {
            replyService.delReplyList(topic);
            topicDAO.delTopic(topic);
        }
    }

    @Override
    public Topic getTopicById(Integer id) {
        Topic topic = getTopic(id);
        List<Reply> replyList = replyService.getReplyListByTopicId(topic.getId());
        topic.setReplyList(replyList);

        return topic;
    }
}
