public class FamilyAccout{
  public static void main(String[] args){
    boolean loopFlag = true;
    String details = "收支\t 账户金额\t 收支金额\t 说 明\n";
    int balance = 1000;
    int currentAmmout1 = 0;
    int currentAmmout2 = 0;
    String amout1Info = "";
    String amout2Info = ""; 
    while(loopFlag){
      System.out.println("=======================家庭收支记账软件：=======================");
      System.out.println();
      System.out.println("                       1 收支明细：");
      System.out.println("                       2 登记收入：");
      System.out.println("                       3 登记支出：");
      System.out.println("                       4 退    出：");
      System.out.println();
      System.out.print("                       请选择（1-4）：");
      char key = Utility.readMenuSelection();
      switch(key){
        case '1':
          System.out.println(details); 
          break;
        case '2':
          System.out.print("请输入收入金额：");
          currentAmmout1 = Utility.readNumber();
          balance += currentAmmout1;
          System.out.print("请输入收入说明：");
          amout1Info = Utility.readString();
          details += "收入：\t" + balance + "\t\t" + currentAmmout1 + "\t\t" +  amout1Info + "\n";
          break;
        case '3':
          System.out.print("请输入支出金额：");
          currentAmmout2 = Utility.readNumber();
          balance -= currentAmmout2;
          System.out.print("请输入支出说明：");
          amout2Info = Utility.readString();
          details += "支出：\t" + balance + "\t\t" + currentAmmout2 + "\t\t" + amout2Info + "\n";
          break;
        case '4':
          System.out.println("确定退出吗？（Y/N）");
          char confirm = Utility.readConfirmSelection();
          if (confirm == 'Y'){
            loopFlag = false;
            break;
          }
      }
    }
  }
}