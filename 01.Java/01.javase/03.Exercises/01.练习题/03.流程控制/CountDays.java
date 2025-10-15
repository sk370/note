public class CountDays{
  public static void main(String[] args){
    int rope = 3000;
    int days = 0;
    for(; rope >= 5; days++){
      rope /= 2;
    }
    System.out.println(days + "天后不超过5m");
  }
}