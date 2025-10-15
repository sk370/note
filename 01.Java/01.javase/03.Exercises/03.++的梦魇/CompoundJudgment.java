public class CompoundJudgment{
  public static void main (String[] args){
    int i = 2;
    int j = 3;
    System.out.println((i++ == 2) + " " + i);
    System.out.println((++j == 3) + " " + j);
  }
}