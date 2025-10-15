public class InsertSort{
  public static void main(String[] args){
    int[] nums = {10, 12, 45, 90};
    int[] temp = new int[nums.length + 1];
    int num = 32, x = 0;
    temp[0] = num;
    for(int i = 1; i < temp.length; i++){
      temp[i] = nums[i - 1];
    }
    for(int i = 0; i < temp.length - 1; i++){
        for(int j = 0; j < temp.length - 1 - i; j++){
          if(temp[j] >= temp[j+1]){
            x = temp[j];
            temp[j] = temp[j+1];
            temp[j+1] = x;
          }
      }
    }
    for(int i = 0; i < temp.length; i++){
      System.out.print(temp[i] + "\t"); 
    }
  }
}