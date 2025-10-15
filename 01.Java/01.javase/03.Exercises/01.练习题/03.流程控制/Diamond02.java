public class Diamond{
  public static void main(String[] args){
    int lines = 9;
    for(int i = 1; i <= lines; i++){
      if(i <= (lines + 1) /2){
        for(int j = 1;j <= 5 - i + 1; j++){
          System.out.print("*");
        }
        for(int j = 1;j <= 2 * (i - 1); j++){
          System.out.print(" ");
        }
        for(int j = 1;j <= 5 - i + 1; j++){
          System.out.print("*");
        }
        System.out.println();
      }else{
        for(int j = 1;j <= i - 5 + 1; j++){
          System.out.print("*");
        }
        for(int j = 1;j <= 2 * (lines - i); j++){
          System.out.print(" ");
        }
        for(int j = 1;j <= i - 5 + 1; j++){
          System.out.print("*");
        }
        System.out.println();
      }      
    }
  }
}