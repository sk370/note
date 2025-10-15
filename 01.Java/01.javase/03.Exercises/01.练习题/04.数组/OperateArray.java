public class OperateArray{
  public static void main(String[] args){
    int[] arr = new int[]{12,3,3,34,56,77,432}; 
    for(int i = arr.length - 1; i >= 0; i--){
      if(i > 0){
        arr[i] /= arr[0];
      }else{
        arr[0] /= arr[0];
      }
    }
    for(int i = 0; i < arr.length; i++){
      System.out.print(arr[i] + "\t");
    }
  }
}