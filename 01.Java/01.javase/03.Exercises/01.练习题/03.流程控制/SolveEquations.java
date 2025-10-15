import java.util.Scanner;
public class SolveEquations{
  public static void main(String[] args){
    Scanner myScanner = new Scanner(System.in);
    System.out.println("请输入a的值");
    double a = myScanner.nextDouble();
    System.out.println("请输入b的值：");
    double b = myScanner.nextDouble();
    System.out.println("请输入c的值：");
    double c = myScanner.nextDouble();
    double x1,x2;
    if(a == 0){
      x1 = x2 = -c / b;
      System.out.println("x1=" + x1 +",x2=" + x2);
    }else{
      if(b *b - 4 * a * c >= 0){
        x1 = (-b + Math.sqrt(b *b - 4 * a * c)) / (2 * a);
        x2 = (-b - Math.sqrt(b *b - 4 * a * c)) / (2 * a);
        System.out.println("x1=" + x1 +",x2=" + x2);
      }else if(b *b - 4 * a * c == 0){
        x1 = x2 = -b / (2 * a);
        System.out.println("x1=" + x1 +",x2=" + x2);
      }else{
        System.out.println("方程无解");
      }
    }
  }
}