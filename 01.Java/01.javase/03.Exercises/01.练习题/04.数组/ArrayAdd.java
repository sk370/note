import java.util.Scanner;
public class ArrayAdd{
  public static void main(String[] args){
    int[] arr1 = {1, 2, 3};
    Scanner myScanner = new Scanner(System.in);
    int[] newArr = new int[arr1.length + 1];
    char flag = 'y';
    do{
      System.out.println("请输入你要添加的数：");
      newArr[arr1.length] = myScanner.nextInt();
      for(int i = 0;i <arr1.length;i++){
        newArr[i] = arr1[i];
      }
      arr1 = newArr;
      for(int i = 0;i <arr1.length;i++){
        System.out.print(arr1[i] + "\t");
      }
      System.out.println("是否还要添加？y/n");
      flag = myScanner.next().charAt(0);
      if(flag == 'n') {
        break;
      }else{
        newArr = new int[arr1.length + 1];
      }
    }while(true);
  }
}