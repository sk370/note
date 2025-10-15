public class Multiple{
  public static void main(String[] args){
    for(int i = 14; i <= 100; i++){
      if(i % 13 == 0){
        continue;
      }      
      System.out.print(i + "\t");
    }
  }
}