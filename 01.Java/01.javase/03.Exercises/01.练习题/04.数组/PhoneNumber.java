public class PhoneNumber{
  public static void main(String[] args){
    int[] order = new int[]{8, 2, 1, 0, 3};
    int[] value = {2, 0, 3, 2, 4, 0, 1, 3, 2, 3, 3};
    String tel = "";
    for(int i = 0;i <value.length;i++){
      tel += order[value[i]];
    }
    System.out.println("联系方式是：" + tel);
  }
}