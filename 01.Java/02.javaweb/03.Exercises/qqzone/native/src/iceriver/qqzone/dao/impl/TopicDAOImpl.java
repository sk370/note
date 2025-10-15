package iceriver.qqzone.dao.impl;

import iceriver.myssm.basedao.BaseDAO;
import iceriver.qqzone.dao.TopicDAO;
import iceriver.qqzone.pojo.Topic;
import iceriver.qqzone.pojo.UserBasic;

import java.util.List;

public class TopicDAOImpl extends BaseDAO<Topic> implements TopicDAO {
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) {
        return super.executeQuery("select * from t_topic where author = ? ", userBasic.getId());
    }

    @Override
    public void addTopic(Topic topic) {

    }

    @Override
    public void delTopic(Topic topic) {
        executeUpdate("delete from t_topic where id = ? ", topic.getId());
    }

    @Override
    public Topic getTopic(Integer id) {
        return load("select * from t_topic where id = ? ", id);
    }
}
