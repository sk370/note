import java.util.Scanner;
public class Scores{
  public static void main(String[] args){
    double[] scores;
    double maxScore = 0;
    Scanner myScanner = new Scanner(System.in);
    System.out.println("请输入参加考试的人数：");
    int nums = myScanner.nextInt();
    scores = new double[nums];
    System.out.println("请输入这" + nums + "个人的成绩：");
    for(int i = 0;i <scores.length;i++){
      scores[i] = myScanner.nextDouble();
    }
    for(int i = 0;i <scores.length;i++){
      if(maxScore <= scores[i]){
        maxScore = scores[i];
      }
    }
    System.out.println("最高分为" + maxScore);
    for(int i = 0;i <scores.length;i++){
      if(scores[i] >= maxScore - 10){
        System.out.println("第" + (i + 1) + "个学生的分数是" + scores[i] + ",成绩等级为：A" );
      }else if(scores[i] >= maxScore - 20){
        System.out.println("第" + (i + 1) + "个学生的分数是" + scores[i] + ",成绩等级为：B" );
      }else if(scores[i] >= maxScore - 30){
        System.out.println("第" + (i + 1) + "个学生的分数是" + scores[i] + ",成绩等级为：C" );
      }else{
        System.out.println("第" + (i + 1) + "个学生的分数是" + scores[i] + ",成绩等级为：D" );
      }
    }    
  }
}