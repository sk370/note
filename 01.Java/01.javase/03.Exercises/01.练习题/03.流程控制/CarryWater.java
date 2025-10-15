public class CarryWater{
  public static void main(String[] args){
    int total = 50;
    int currentWater = 15;
    int weight = 5;
    int count = 0;
    do{
      currentWater += weight;
      count++;
    }while(currentWater <= total);
    System.out.print(count+ "\t");
  }
}