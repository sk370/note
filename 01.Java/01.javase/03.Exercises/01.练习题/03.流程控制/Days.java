import java.util.Scanner;
public class Days{
  public static void main(String[] args){
    Scanner myScanner = new Scanner(System.in);
    System.out.println("请输入年份：");
    int year = myScanner.nextInt();
    System.out.println("请输入月份：");
    int month = myScanner.nextInt();
    System.out.println("请输入日：");
    int day = myScanner.nextInt();
    switch(month){
      case 12:
        day += 30;
      case 11:
        day += 31;
      case 10:
        day += 30;
      case 9:
        day += 31;
      case 8:
        day += 31;
      case 7:
        day += 30;
      case 6:
        day += 31;
      case 5:
        day += 30;
      case 4:
        day += 31;
      case 3:
        if(year % 4 ==0 && year % 100 != 0 || year % 400 == 0){
          day += 29;
        }else{
          day +=28;
        }
      case 2:
        day +=31;
      case 1:
        System.out.println("全年的第" + day + "天");
        break;
      default:
        System.out.println("非法参数");
        break;
    }
  }
}