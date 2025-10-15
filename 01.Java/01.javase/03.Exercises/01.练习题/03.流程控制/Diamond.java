public class Diamond02{
  public static void main(String[] args){
    int lines = 9;
    for(int i = 1; i <= lines; i++){
      if(i <= lines / 2 + 1){
        for(int k =1 ; k <= lines / 2 + 1 - i; k++){
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