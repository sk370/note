import java.util.Scanner;
public class ArrayReduce{
  public static void main(String[] args){
    int[] arr1 = {1, 2, 3, 4, 5};
    char flag = 'y';
    Scanner myScanner = new Scanner(System.in);
    int[] newArr = new int[arr1.length - 1];
    System.out.println("��ʼ����Ԫ��Ϊ��");
    for(int i = 0;i <arr1.length;i++){
      System.out.print(arr1[i] + "\t");
    }      
    System.out.println();
    while(true){
      System.out.println("�Ƿ�ȷ��Ҫɾ�����һ������y/n");
      flag = myScanner.next().charAt(0);
      if(flag == 'n'){
        System.out.println("�������");
        break;
      }else if(flag != 'y'){
        System.out.println("������������������");
        continue;
      }else{
        for(int i = 0;i <newArr.length;i++){
          newArr[i] = arr1[i];
        }
        arr1 = newArr;
        System.out.println("��ǰ����Ԫ��Ϊ��");
        for(int i = 0;i <arr1.length;i++){
          System.out.print(arr1[i] + "\t");
        }      
        System.out.println();
        newArr = new int[arr1.length - 1];
        if(arr1.length <= 1){
          System.out.println("���鲻���ټ�С��");
          break;
        }
      }
    }
  }
}