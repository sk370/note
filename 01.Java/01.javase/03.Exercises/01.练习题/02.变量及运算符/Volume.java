import java.util.Scanner;
public class Volume{
  public static void main(String[] args){
    double PI = 3.14;
    Scanner myScanner = new Scanner(System.in);
    System.out.println("输入圆柱体半径：");
    double radius = myScanner.nextDouble();
    System.out.println("输入圆柱体高度：");
    double height = myScanner.nextDouble();
    double volume = PI * radius * radius * height;
    System.out.println("半径为" + radius + "，高为" + height + "的圆柱体体积为" + volume);
  }
}