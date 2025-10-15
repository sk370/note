package iceriver.redis.jedis;

import redis.clients.jedis.Jedis;

import java.util.Random;

/**
 * TODO
 * 1、输入手机号，点击发送后随机生成6位数字码，2分钟有效
 * 2、输入验证码，点击验证，返回成功或失败
 * 3、每个手机号每天只能输入3次
 * @author zhuyuqi
 * @version v0.0.1
 * @className PhoneCode
 * @description https://developer.aliyun.com/profile/sagwrxp2ua66w
 * @date 2022/08/23 20:12
 */
public class PhoneCode {
    public static void main(String[] args) {
        //模拟发送验证码
        verifyCode("123456");
        //校验
//        getRedisCode("123456","061171");

    }

    /**
     * 每次生成1个[0, 9]的数，循环6次进行拼接
     * @return
     */
    public static String getCode(){
        Random random = new Random();
        String code = "";
        for (int i = 0; i < 6; i++) {
            int rand = random.nextInt(10);
            code += rand;
        }
        return code;
    }

    /**
     * 每个手机号每天只能发3次、每次的验证码放到redis中设置过期时间
     * @param phone
     */
    public static void verifyCode(String phone){
        Jedis jedis = new Jedis("192.168.150.132", 6379);
        String countKey = "VerifyCode" + phone + "count";//保存验证码使用次数
        String codeKey = "VerifyCode" + phone + "code";//保存验证码
        // 每个手机发送3次
        String count = jedis.get(countKey);
        if(count == null){
            jedis.setex(countKey, 24*60*60, "1");
        }else if(Integer.parseInt(count) < 3){
            jedis.incr(countKey);
        }else {
            System.out.println("今天的验证机会（3次）已经用完");
            jedis.close();
            return;
        }

        String vCode = getCode();
        jedis.setex(codeKey, 120, vCode);
        jedis.close();
    }

    /**
     * 验证码校验
     * @param phone
     * @param code
     */
    public static void getRedisCode(String phone, String code){
        Jedis jedis = new Jedis("192.168.150.132", 6379);
        // 手机发送次数的String设计，保证key的唯一性
        String codeKey = "VerifyCode" + phone + "code";
        String codeValue = jedis.get(codeKey);
        if(codeValue.equals(code)){
            System.out.println("成功");
        }else{
            System.out.println("失败");
        }
        jedis.close();
    }
}
