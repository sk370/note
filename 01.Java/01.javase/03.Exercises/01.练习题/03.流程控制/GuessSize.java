import java.util.Scanner;
public class GuessSize{
  public static void main(String[] args){
    int num1 = (int)(Math.random()*6 + 1);
    int num2 = (int)(Math.random()*6 + 1);
    int num3 = (int)(Math.random()*6 + 1);
    System.out.println(num1 + "\t" + num2 + "\t" + num3);
    int result;
    if(num1 == num2 && num1 == num3){
      result = 3;
    }else if((num1 + num2 +num3) > 9){
      result = 1;
    }else{
      result = 2;
    }

    System.out.println("开始猜数，你的选择是：");
    System.out.println("1.押大");
    System.out.println("2.押小");
    System.out.println("3.豹子");
    Scanner myScanner = new Scanner(System.in);
    int num = myScanner.nextInt();
    
    switch(num - result){
      case 0:
        System.out.println("赢");
        break;
      default:
        System.out.println("输");
        break;
    }
  }
}