public class FamilyAccout{
  public static void main(String[] args){
    boolean loopFlag = true;
    String details = "��֧\t �˻����\t ��֧���\t ˵ ��\n";
    int balance = 1000;
    int currentAmmout1 = 0;
    int currentAmmout2 = 0;
    String amout1Info = "";
    String amout2Info = ""; 
    while(loopFlag){
      System.out.println("=======================��ͥ��֧���������=======================");
      System.out.println();
      System.out.println("                       1 ��֧��ϸ��");
      System.out.println("                       2 �Ǽ����룺");
      System.out.println("                       3 �Ǽ�֧����");
      System.out.println("                       4 ��    ����");
      System.out.println();
      System.out.print("                       ��ѡ��1-4����");
      char key = Utility.readMenuSelection();
      switch(key){
        case '1':
          System.out.println(details); 
          break;
        case '2':
          System.out.print("�����������");
          currentAmmout1 = Utility.readNumber();
          balance += currentAmmout1;
          System.out.print("����������˵����");
          amout1Info = Utility.readString();
          details += "���룺\t" + balance + "\t\t" + currentAmmout1 + "\t\t" +  amout1Info + "\n";
          break;
        case '3':
          System.out.print("������֧����");
          currentAmmout2 = Utility.readNumber();
          balance -= currentAmmout2;
          System.out.print("������֧��˵����");
          amout2Info = Utility.readString();
          details += "֧����\t" + balance + "\t\t" + currentAmmout2 + "\t\t" + amout2Info + "\n";
          break;
        case '4':
          System.out.println("ȷ���˳��𣿣�Y/N��");
          char confirm = Utility.readConfirmSelection();
          if (confirm == 'Y'){
            loopFlag = false;
            break;
          }
      }
    }
  }
}