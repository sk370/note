import java.util.Scanner;
public class Week{
  public static void main(String[] args){
    Scanner myScanner = new Scanner(System.in);
    System.out.println("请输入【1-7】中的任意值：");
    int week = myScanner.nextInt();
    switch(week){
      case 1:
        System.out.println("周一");
        break;
      case 2:
        System.out.println("周一");
        break;
      case 3:
        System.out.println("周一");
        break;
      case 4:
        System.out.println("周一");
        break;
      case 5:
        System.out.println("周一");
        break;
      case 6:
        System.out.println("周一");
        break;
      case 7:
        System.out.println("周一");
        break;
      default:
        System.out.println("非法参数");
        break;
    }
  }
}