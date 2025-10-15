public class ArrayTest{
  public static void main(String[] args){
    String[] strs = new String[5];
    strs[2] = "Tom";
    strs = new String[3];
    for (int i = 0; i < strs.length; i++) {
      System.out.print(strs[i] + "\t");
    }
  }
}