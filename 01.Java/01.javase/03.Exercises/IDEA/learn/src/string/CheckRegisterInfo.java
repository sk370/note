package string;

import java.util.Scanner;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/4 21:13
 */
public class CheckRegisterInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name, password, email;
        while(true){
            System.out.print("请输入用户名：");
            name = scanner.next();
            if(name.length() == 2 || name.length() == 3 || name.length() == 4){
                break;
            }else {
                System.out.println("用户名长度不正确");
            }
        }
        while(true){
            System.out.print("请输入密码");
            password = scanner.next();
            if(password.length() == 6){
                char[] c = password.toCharArray();
                int i;
                for (i = 0; i < c.length; i++){
                    if(c[i] > '9' || c[i] < '0') {
                        break;
                    }
                }
                break;
            }else {
                System.out.println("密码长度不对或非数字");
            }
        }
        while(true){
            System.out.print("请输入邮件地址");
            email = scanner.next();
            if(email.contains("@") && email.contains(".") && email.indexOf("@") < email.indexOf(".")){
                break;
            }else {
                System.out.println("邮箱格式不正确");
            }
        }
        System.out.println("name = " + name + "，password = " + password + "，email = " + email);
    }
}
