import java.util.Scanner;
public class Scores{
  public static void main(String[] args){
    double[] scores;
    double maxScore = 0;
    Scanner myScanner = new Scanner(System.in);
    System.out.println("������μӿ��Ե�������");
    int nums = myScanner.nextInt();
    scores = new double[nums];
    System.out.println("��������" + nums + "���˵ĳɼ���");
    for(int i = 0;i <scores.length;i++){
      scores[i] = myScanner.nextDouble();
    }
    for(int i = 0;i <scores.length;i++){
      if(maxScore <= scores[i]){
        maxScore = scores[i];
      }
    }
    System.out.println("��߷�Ϊ" + maxScore);
    for(int i = 0;i <scores.length;i++){
      if(scores[i] >= maxScore - 10){
        System.out.println("��" + (i + 1) + "��ѧ���ķ�����" + scores[i] + ",�ɼ��ȼ�Ϊ��A" );
      }else if(scores[i] >= maxScore - 20){
        System.out.println("��" + (i + 1) + "��ѧ���ķ�����" + scores[i] + ",�ɼ��ȼ�Ϊ��B" );
      }else if(scores[i] >= maxScore - 30){
        System.out.println("��" + (i + 1) + "��ѧ���ķ�����" + scores[i] + ",�ɼ��ȼ�Ϊ��C" );
      }else{
        System.out.println("��" + (i + 1) + "��ѧ���ķ�����" + scores[i] + ",�ɼ��ȼ�Ϊ��D" );
      }
    }    
  }
}