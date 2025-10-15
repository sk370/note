public class Triangle01{
  public static void main(String[] args){
    int lines = 7;
    for(int i = 1; i <= lines; i++){
      if(i <= lines / 2 + 1){
        for(int j = 1; j <= i; j++){
          System.out.print("*");
        }
        System.out.println();
      }else{
        for(int j = lines - i + 1; j >= 1; j--){
          System.out.print("*");
        }
        System.out.println();
      }
    }
  }
}