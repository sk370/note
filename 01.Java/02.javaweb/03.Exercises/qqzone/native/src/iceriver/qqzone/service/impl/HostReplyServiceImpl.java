package iceriver.qqzone.service.impl;

import iceriver.qqzone.dao.HostReplyDAO;
import iceriver.qqzone.pojo.HostReply;
import iceriver.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {

    private HostReplyDAO hostReplyDAO;

    @Override
    public HostReply getHostReplyByReplyId(Integer replyId) {
        return hostReplyDAO.getHostReplyByReplyId(replyId);
    }

    @Override
    public void delHostReply(Integer id) {
        hostReplyDAO.delHostReply(id);
    }
}
