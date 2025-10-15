public class Heart{
  public static void main(String[] args){
    int lines = 14;
    for(int i = 1; i <= lines; i++){
      if(i <= 3){
        for(int k =1 ; k <= 5 - ((i - 1) * 2); k++){
          System.out.print(" ");
        }
        for(int j = 1; j <= i; j++){
          System.out.print("* ");
        }
        System.out.println();
      }else{
        for(int k = 1; k <= i - (lines / 2 + 1) ;k++){
          System.out.print(" ");
        }
        for(int j = lines - i + 1; j >= 1; j--){
          System.out.print("* ");
        }
        System.out.println();
      }
    }
  }
}