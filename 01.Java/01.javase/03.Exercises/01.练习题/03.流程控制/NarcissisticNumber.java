public class NarcissisticNumber{
  public static void main(String[] args){
    for(int i = 100; i < 1000; i++){
      int bit = i % 10;
      int ten = i / 10 % 10;
      int hundredth = i / 100;
      if(i == (bit * bit * bit) + (ten * ten *ten) + (hundredth * hundredth * hundredth)){
        System.out.print(i+ "\t");
      }
    }
  }
}