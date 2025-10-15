public class YanghuiTriangle{
  public static void main(String[] args){
    int num = 10;
    int[][] yanghuiTriangle = new int[10][];
    for(int i = 0;i <yanghuiTriangle.length;i++){
      yanghuiTriangle[i] = new int[i + 1];
      for(int j = 0;j <yanghuiTriangle[i].length;j++){
        if(j == 0 || j == i){
          yanghuiTriangle[i][j] = 1;
        }else{
          yanghuiTriangle[i][j] = yanghuiTriangle[i-1][j-1] + yanghuiTriangle[i - 1][j];
        }
        System.out.print(yanghuiTriangle[i][j] + "\t");
      }
      System.out.println();
    }
  }
}