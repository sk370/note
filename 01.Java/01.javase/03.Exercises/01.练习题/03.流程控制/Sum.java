public class Sum{
  public static void main(String[] args){
    int sum = 0;
    for(int i = 1; i <= 100;){
      sum += i;
      i += 2;
    }
    System.out.print(sum);
  }
}