public class BubbleSort{
  public static void main(String[] args){
    int[] nums = {34, 5, 22, -98, 6, -76, 0, -3};
    int x = 0;
    for(int i = 0; i < nums.length - 1; i++){
        for(int j = 0; j < nums.length - 1 - i; j++){
          if(nums[j] >= nums[j+1]){
            x = nums[j];
            nums[j] = nums[j+1];
            nums[j+1] = x;
          }
      }
    }
    for(int i = 0; i < nums.length; i++){
      System.out.print(nums[i] + "\t"); 
    }
  }
}