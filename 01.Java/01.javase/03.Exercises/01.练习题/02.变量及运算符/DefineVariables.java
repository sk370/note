public class DefineVariables{
  public static void main(String[] args){
    // 1.字符型：1种
    char char1 = 'a';
    char char2 = 64;
    char char3 = '\n';
    System.out.print(char1 + " " + char2 + " " + char3);
    System.out.println("===============");

    // 2.整形：4种
    byte byte1 = 28;
    short short1 = 28;
    int int1 = 28;
    // long long1 = 28; 写成这种形式时，代表将int类型的值赋给long。
    long long1 = 28L;
    System.out.println(byte1 + " " + short1 + " " + int1 + " " + long1);
    System.out.println("===============");
    
    // 3.浮点型：2种
    float float1 = 16.2F;
    double double1 = 16.2;
    System.out.println(float1 + " " + double1);
    System.out.println("===============");

    // 4.布尔型：1种
    boolean boolean1 = false;
    System.out.println(boolean1);
  }
}