package iceriver.springsecurity.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * @author zhuyuqi
 * @version v0.0.1
 * @className PasswordEncoderService
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/09/04 18:45
 */
@Component
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return privateEncode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String formPassword = privateEncode(rawPassword);
        String databasePassword = encodedPassword;
        return Objects.equals(formPassword, databasePassword);
    }

    /**
     * 加密方法
     * @param rawPassword
     * @return
     */
    private String privateEncode(CharSequence rawPassword){
        //使用Javasecurity提供的md5加密工具类
        if (rawPassword == null || rawPassword.length() == 0) {
            throw new RuntimeException("传入字符不能为空");
        }
        String algorithm = "md5";
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);// Java提供的md5加密类
            byte[] bytes = ((String)rawPassword).getBytes();//将rawPassword转换为字符串后转换为字节数组
            byte[] digest = messageDigest.digest(bytes);// 执行加密
            int signum = 1;
            BigInteger bigInteger = new BigInteger(signum, digest);// 创建一个正大整数，signum为-1表示负数，为0表示0
            int radix = 16;
            String encoded = bigInteger.toString(radix).toUpperCase();// 将正大整数转换为16进制字符串
            return encoded;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
