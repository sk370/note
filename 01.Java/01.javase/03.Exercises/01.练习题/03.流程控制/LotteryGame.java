import java.util.Scanner;
public class LotteryGame{
  public static void main(String[] args){
    int target = (int)(Math.random()*90 + 10);
    System.out.println("本期的彩票中奖号码是：" + target);
    Scanner myScanner = new Scanner(System.in);
    System.out.println("请输入你的彩票号码");
    int num = myScanner.nextInt();
    if(num == target){
      System.out.println("恭喜你中奖了，奖励10000美元");
    }else if(num / 10 == target % 10 && num % 10 == target / 10){
      System.out.println("恭喜你中奖了,但号码反了，奖励3000美元");
    }else if((num / 10 == target / 10) || (num % 10 == target % 10)){
      System.out.println("恭喜你中奖了,但只有一个号码和位置正确，奖励1000美元");
    }else if((num / 10 == target % 10) || (num % 10 == target / 10)){
      System.out.println("恭喜你中奖了,但只有一个号码正确，而位置不正确，奖励500美元");
    }else{
      System.out.println("很遗憾没有中奖");
    }
  }
}