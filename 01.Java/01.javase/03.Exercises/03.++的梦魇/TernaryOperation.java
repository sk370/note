public class TernaryOperation{
  public static void main(String[] args){
  int a=3,b=8;

  int c=(a>b)?a++:b++;
  System.out.println("a="+a+"\tb="+b+"\tc="+c);

  int d=(a>b)?++a:++b;
  System.out.println("a="+a+"\tb="+b+"\td="+d);
  }
}