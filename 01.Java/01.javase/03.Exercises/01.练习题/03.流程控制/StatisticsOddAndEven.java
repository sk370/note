import java.util.Scanner;
public class StatisticsOddAndEven{
  public static void main(String[] args){
    Scanner myScanner = new Scanner(System.in);
    System.out.println("������һ����λ������");
    int num = myScanner.nextInt();
    int i = 1,oddSum = 0, evenSum = 0;
    do{
      if(num / i % 10 % 2 ==0){
        evenSum++;
        i *= 10;
      }else{
        oddSum++;
        i *= 10;
      }
    }while(i <= 1000);
    System.out.print(num + "����" + oddSum + "��������" + evenSum + "��ż��");
  }
}