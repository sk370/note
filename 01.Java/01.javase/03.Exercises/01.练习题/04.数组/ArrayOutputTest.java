public class ArrayOutputTest{
  public static void main(String[] args){
    // 3. char����һά����Ͷ�ά����
    char[] num1;
    // System.out.println("num1:" + num1); ���뱨��int1δ��ʼ��
    char[] num2 = new char[2];
    System.out.println("num2:" + num2);//�ڴ��ַ
    System.out.println("num2[0]:" + num2[0]);//    
    char[] num7 = {'a','b','c'};
    System.out.println("num7:" + num7);//�ڴ��ַ
    System.out.println("num7[0]:" + num7[0]);//   
    System.out.println(num7);// 
    
    char[][]num3;
    // System.out.println("num3:" + num3); ���뱨��int3δ��ʼ��
    char[][] num4 = new char[2][2];
    System.out.println("num4:" + num4);//�ڴ��ַ
    System.out.println("num4[0]:" + num4[0]);//�ڴ��ַ
    System.out.println("num4[0][0]:" + num4[0][0]);//
    num4[1] = new char[3];//�����ǲ��Ǹı��˶�ά�����е�һά�����С��
    System.out.println("num4[1]:" + num4[1]);//�ڴ��ַ
    System.out.println("num4[1][2]:" + num4[1][2]);//
    
    char[][]num6;
    // System.out.println("num6:" + num6); ���뱨��int6δ��ʼ��
    char[][] num5 = new char[2][];
    System.out.println("num5:" + num5);//�ڴ��ַ
    System.out.println("num5[0]:" + num5[0]);//null
    // System.out.println("num5[0][0]:" + num5[0][0]);//���벻�������б���
    num5[1] = new char[3];
    System.out.println("num5[1]:" + num5[1]);//�ڴ��ַ
    System.out.println("num5[1][2]:" + num5[1][2]);//
    num5[0] = new char[]{'1','2','3'};
    System.out.println("num5[0]:" + num5[0]);//�ڴ��ַ
    System.out.println("num5[0][1]:" + num5[0][1]);//
  }
}