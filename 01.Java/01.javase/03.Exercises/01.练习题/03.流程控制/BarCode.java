import java.util.Scanner;
public class BarCode{
  public static void main(String[] args){
    Scanner myScanner = new Scanner(System.in);
    System.out.println("�������У��������룺");
    long barCode = myScanner.nextLong();
    long temp = barCode;
    int bit = 1;
    int target,evenSum = 0, oddSum = 0;
    while(bit <= 12){
      if(bit % 2 != 0){
        evenSum += temp % 10;
        System.out.println(evenSum);
        temp /= 10;
        System.out.println(temp);
        bit++;
      }else{
        oddSum += temp % 10;
        System.out.println(oddSum);
        temp /= 10;
        System.out.println(temp);
        bit++;
      }
    }
    target = 10 - (oddSum + evenSum * 3) % 10;
    System.out.println(oddSum + evenSum * 3);
    System.out.println("����" + barCode + "����֤��Ϊ��" + target + ",����֤�������Ϊ��" + barCode + target);
  }
}