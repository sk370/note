import java.util.Scanner;
public class GreatestCommonDivisor{
  public static void main(String[] args){
    Scanner myScanner = new Scanner(System.in);
    System.out.println("������������");
    int a = myScanner.nextInt();
    int b = myScanner.nextInt();
    int min = (a < b) ? a : b;
    int greatestCommonDivisor = 0,leastCommonMultiple = 0;
    for(int i = 1;i <= min; i++){
      if(a % i == 0 && b % i == 0){
        greatestCommonDivisor = i;
      }
    }
    leastCommonMultiple = a * b / greatestCommonDivisor;
    System.out.println("���Լ��Ϊ��" + greatestCommonDivisor + "����С������Ϊ��" + leastCommonMultiple);
  }
}