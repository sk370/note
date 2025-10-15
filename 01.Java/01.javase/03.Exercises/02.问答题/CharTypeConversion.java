public class CharTypeConversion{
  public static void main (String[] args){
    char x = 'a';
    int i = 65;
    System.out.println(true ? x : i);
    System.out.println(false ? x : i);
    
    System.out.println(true ? 'x' : i);
    System.out.println(false ? 'x' : i);

    System.out.println(true ? x : 65);
    System.out.println(false ? x : 65);

		System.out.println(true ? 'x' : 65);
		System.out.println(false ? 'x' : 65);
  }
}