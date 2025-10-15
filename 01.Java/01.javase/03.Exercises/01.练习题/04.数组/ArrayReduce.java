import java.util.Scanner;
public class ArrayReduce{
  public static void main(String[] args){
    int[] arr1 = {1, 2, 3, 4, 5};
    char flag = 'y';
    Scanner myScanner = new Scanner(System.in);
    int[] newArr = new int[arr1.length - 1];
    System.out.println("初始数组元素为：");
    for(int i = 0;i <arr1.length;i++){
      System.out.print(arr1[i] + "\t");
    }      
    System.out.println();
    while(true){
      System.out.println("是否确定要删除最后一个数？y/n");
      flag = myScanner.next().charAt(0);
      if(flag == 'n'){
        System.out.println("程序结束");
        break;
      }else if(flag != 'y'){
        System.out.println("输入有误，请重新输入");
        continue;
      }else{
        for(int i = 0;i <newArr.length;i++){
          newArr[i] = arr1[i];
        }
        arr1 = newArr;
        System.out.println("当前数组元素为：");
        for(int i = 0;i <arr1.length;i++){
          System.out.print(arr1[i] + "\t");
        }      
        System.out.println();
        newArr = new int[arr1.length - 1];
        if(arr1.length <= 1){
          System.out.println("数组不能再减小了");
          break;
        }
      }
    }
  }
}