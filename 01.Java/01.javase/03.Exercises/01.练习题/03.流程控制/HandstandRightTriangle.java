import java.util.Scanner;
public class HandstandRightTriangle{
  public static void main(String[] args){
    Scanner myScanner = new Scanner(System.in);
    System.out.println("请输入等腰直角三角形的边长：");
    int num = myScanner.nextInt();
    for(int i = num; i >= 1; i--){
      for(int j = 1; j <= i; j++){
        System.out.print("#");
      }
      System.out.println();
    }
  }
}