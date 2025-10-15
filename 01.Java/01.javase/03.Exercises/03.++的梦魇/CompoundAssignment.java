public class CompoundAssignment{
  public static void main (String[] args){
    int i = 2;
    int j = 3;
    i *= i++; //i = i * i++
    j *= ++j; //j = j * ++j
    System.out.println("i = " + i);
    System.out.println("j = " + j);
  }
}