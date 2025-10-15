public class Priority{
  public static void main(String[] args){
    int x=0,y=1;
    if(++x==y-- & x++==1||--y==0)
        System.out.println("x="+x+",y="+y);   //x = 2,y = 0;
      else
	      System.out.println("y="+y+",x="+x);
    int a=0,b=1;
    if(++a==b-- & a++==1&&--b==0)
        System.out.println("a="+a+",b="+b);   //x = 2,y = 0;
      else
	      System.out.println("b="+b+",a="+a);
  }
}