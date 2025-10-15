public class ArrayOutputTest{
  public static void main(String[] args){
    // 3. char类型一维数组和二维数组
    char[] num1;
    // System.out.println("num1:" + num1); 编译报错，int1未初始化
    char[] num2 = new char[2];
    System.out.println("num2:" + num2);//内存地址
    System.out.println("num2[0]:" + num2[0]);//    
    char[] num7 = {'a','b','c'};
    System.out.println("num7:" + num7);//内存地址
    System.out.println("num7[0]:" + num7[0]);//   
    System.out.println(num7);// 
    
    char[][]num3;
    // System.out.println("num3:" + num3); 编译报错，int3未初始化
    char[][] num4 = new char[2][2];
    System.out.println("num4:" + num4);//内存地址
    System.out.println("num4[0]:" + num4[0]);//内存地址
    System.out.println("num4[0][0]:" + num4[0][0]);//
    num4[1] = new char[3];//这里是不是改变了二维数组中的一维数组大小？
    System.out.println("num4[1]:" + num4[1]);//内存地址
    System.out.println("num4[1][2]:" + num4[1][2]);//
    
    char[][]num6;
    // System.out.println("num6:" + num6); 编译报错，int6未初始化
    char[][] num5 = new char[2][];
    System.out.println("num5:" + num5);//内存地址
    System.out.println("num5[0]:" + num5[0]);//null
    // System.out.println("num5[0][0]:" + num5[0][0]);//编译不报错，运行报错
    num5[1] = new char[3];
    System.out.println("num5[1]:" + num5[1]);//内存地址
    System.out.println("num5[1][2]:" + num5[1][2]);//
    num5[0] = new char[]{'1','2','3'};
    System.out.println("num5[0]:" + num5[0]);//内存地址
    System.out.println("num5[0][1]:" + num5[0][1]);//
  }
}