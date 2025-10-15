public class HarmonicSeries{
  public static void main(String[] args){
    double sum = 0;
    int i = 1;
    while(sum < 10){
      sum += 1.0 / i;
      i++;
    }
    System.out.println(i);
  }
}