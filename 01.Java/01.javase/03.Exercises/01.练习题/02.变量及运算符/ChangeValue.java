public class ChangeValue{
  public static void main(String[] args){
    int a = 8, b = 3;
    int i = 8, j = 3;
    int m = 8, n = 3;

    int temp = a;
    a = b;
    b = temp;
    System.out.println("a=" + a + " ,b=" + b);

    i = i + j;
    j = i - j;
    i = i - j;
    System.out.println("i=" + i + " ,j=" + j);
    
    m = m ^ n;
    n = m ^ n;
    m = m ^ n;
    System.out.println("m=" + m + " ,n=" + n);
  }
}