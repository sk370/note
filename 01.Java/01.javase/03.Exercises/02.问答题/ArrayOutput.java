public class ArrayOutput{
  public static void main(String[] args){
    char[] arr1 = {'a', 'b', 'c'};
    char[] arr2 = arr1;
    arr1[2] = 'Öì';
    for(int i = 0; i < arr2.length; i++){
        System.out.println(arr1[i] + ", " + arr2[i]);
    }
  }
}