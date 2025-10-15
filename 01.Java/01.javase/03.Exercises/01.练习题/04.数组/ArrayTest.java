public class ArrayTest{
  public static void main(String[] args){
    // int[][] arr1 = new int[3][];
    int[] arr1[];
    arr1 = new int[3][];
    arr1[0] = new int[]{1};
    arr1[1] = new int[]{2,2};
    arr1[2] = new int[]{3,3};

    for(int i = 0;i <arr1.length;i++){
      for(int j = 0;j <arr1[i].length;j++){
        System.out.print(arr1[i][j] + "\t");
      }
    }
  }
}