
import java.util.Scanner;
public class Sort{
  public static void main(String[] args){
    Scanner myScanner = new Scanner(System.in);
    System.out.println("请输入第一个值a=");
    int a = myScanner.nextInt();
    System.out.println("a=" + a);
    System.out.println("请输入第二个值b=");
    int b = myScanner.nextInt();
    System.out.println("b=" + b);
    System.out.println("请输入第三个值c=");
    int c = myScanner.nextInt();
    System.out.println("c=" + c);

    if(a >= b){
      if(b >= c){
        System.out.println(a + "\t" + b + "\t" + c);
      }else if(a <= c){
        System.out.println(c + "\t" + a + "\t" + b);
      }else{
        System.out.println(a + "\t" + c + "\t" + b);
      }
    }else{
      if(a >= c){
        System.out.println(b + "\t" + a + "\t" + c);
      }else if(b >=c){
        System.out.println(b + "\t" + c + "\t" + a);
      }else{
        System.out.println(c + "\t" + b + "\t" + a);
      }
    }
  }
}