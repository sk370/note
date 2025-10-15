package iceriver.spring.logs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: INFINITY https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date: 2022/7/17 14:27
 */
public class UserLog {
    private static final Logger log = LoggerFactory.getLogger(UserLog.class);

    public static void main(String[] args) {
        log.info("hello logs");
        log.warn("hello logs");
    }
}
