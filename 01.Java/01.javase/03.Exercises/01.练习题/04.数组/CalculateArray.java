public class CalculateArray{
  public static void main(String[] args){
    int nums = 10, maxNum, maxNumIndex = -1, target = 8, targetIndex = -1;
    double sum =0, avg = 0.0;
    int[] arr1 = new int[nums];
    for(int i = 0; i < arr1.length; i++){
      arr1[i] = (int)(Math.random()*100);
    }
    maxNum = arr1[0];
    System.out.print("原数组为：");
    for(int i = 0; i < arr1.length; i++){
      System.out.print(arr1[i] + "\t");
    }
    System.out.println();
    System.out.print("倒序输出为：");
    for(int i = arr1.length - 1; i >= 0; i--){
      System.out.print(arr1[i] + "\t");
    }
    System.out.println();
    for(int i = 0; i < arr1.length; i++){
      if(maxNum <= arr1[i]){
        maxNum = arr1[i];
        maxNumIndex = i;
      }
      sum += arr1[i];
      avg = sum / arr1.length;
      if(arr1[i] == target){
        targetIndex = i;
      }
    }
    System.out.print("最大值为：" + maxNum + "，是第" + (maxNumIndex + 1) + "个数；和为：" + sum + "，平均值为：" + avg);
    System.out.println();
    if(targetIndex != -1){
      System.out.print("目标值：" + target + "是第" + (targetIndex + 1) + "个数。");
    }else{
      System.out.print("目标值：" + target + "没找到");
    }
  }
}