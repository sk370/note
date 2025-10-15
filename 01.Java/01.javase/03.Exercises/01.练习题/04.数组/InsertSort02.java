public class InsertSort02{
  public static void main(String[] args){
    int[] nums = {10, 12, 45, 90};
    int[] temp = new int[nums.length + 1];
    int num = 32, x = 0;
    for(int i = 0; i < nums.length; i++){
      if(num <= nums[i]){
        x = i;
        break;
      }else{
        x = nums.length;
      }
    }
    for(int i = 0; i < temp.length; i++){
      if(i == x){
        temp[i] = num;
      }else if(i < x){
        temp[i] = nums[i];
      }else{
        temp[i] = nums[i - 1];
      }
    }
    for(int i = 0; i < temp.length; i++){
      System.out.print(temp[i] + "\t"); 
    }
  }
}