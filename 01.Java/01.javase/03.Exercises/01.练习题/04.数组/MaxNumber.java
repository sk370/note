public class MaxNumber{
  public static void main(String[] args){
    int[] nums = new int[]{4,-1,9, 10,23};
    int maxMum = 0, index = 0;
    for(int i = 0;i <nums.length;i++){
      if(maxMum <= nums[i]){
        maxMum = nums[i];
        index = i;
      }
    }
    System.out.print("最大值为：" + nums[index] + "，下标为" + index);
  }
}