import java.util.Scanner;
public class GuessNumber{
  public static void main(String[] args){
    int num = (int)(Math.random() * 100);
    Scanner myScanner = new Scanner(System.in);
    System.out.println("请输入你猜测的数：");
    int count = 1;
    do{
      int guessNum = myScanner.nextInt();
      if(guessNum > num){
        System.out.println("大了");
        count++;
        continue;
      }else if(guessNum < num){
        System.out.println("小了");
        count++;
        continue;
      }else{
        System.out.println("对了，共猜了" + count + "次");
        break;
      }
    }while(true);
  }
}