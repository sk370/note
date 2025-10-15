package iceriver.qqzone.service;

import iceriver.qqzone.pojo.HostReply;

public interface HostReplyService {
    HostReply getHostReplyByReplyId(Integer replyId);

    void delHostReply(Integer id);
}
