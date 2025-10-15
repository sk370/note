public class VampireNumber{
  public static void main(String[] args){
    int bit = 0, ten = 0, hundredth = 0, kilobit = 0;
    for(int i = 1000; i < 10000;i++){
      bit = i % 10;
      ten = i / 10 % 10;
      hundredth = i / 100 % 10;
      kilobit = i / 1000;
      if(i == (bit * 10 + ten) * (hundredth * 10 + kilobit)){
        System.out.println(i);
      }else if(i == (bit * 10 + ten) * (kilobit * 10 + hundredth) ){
        System.out.println(i);
      }else if(i == (bit * 10 + hundredth) * (ten * 10 + kilobit) ){
        System.out.println(i);
      }else if(i == (bit * 10 + hundredth) * (kilobit * 10 + ten) ){
        System.out.println(i);
      }else if(i == (bit * 10 + kilobit) * (ten * 10 + hundredth) ){
        System.out.println(i);
      }else if(i == (bit * 10 + kilobit) * (hundredth * 10 + ten) ){
        System.out.println(i);
      }else if(i == (bit * 10 + kilobit) * (hundredth * 10 + ten) ){
        System.out.println(i);
      }else if(i == (ten * 10 + bit) * (hundredth * 10 + kilobit) ){
        System.out.println(i);
      }else if(i == (ten * 10 + bit) * (kilobit * 10 + hundredth) ){
        System.out.println(i);
      }else if(i == (ten * 10 + hundredth) * (kilobit * 10 + bit) ){
        System.out.println(i);
      }else if(i == (ten * 10 + kilobit) * (hundredth * 10 + bit) ){
        System.out.println(i);
      }else if(i == (hundredth * 10 + bit) * (kilobit * 10 + ten) ){
        System.out.println(i);
      }else if(i == (hundredth * 10 + ten) * (kilobit * 10 + bit) ){
        System.out.println(i);
      }
    }
  }
}