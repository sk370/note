package tankbattle;

import java.io.*;
import java.util.Vector;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/14 9:47
 */
public class Recoder {
    private static int allEnemyTankNum = 0;
    private static BufferedReader br = null;
    private static BufferedWriter bw = null;
    private static String recordFile = "D:\\IDEA\\practise\\src\\tankbattle\\myRecord.txt";
//    保存上次游戏敌方坦克信息
    private static Vector<EnemyTank> enemyTanks = null;
//    开局时读取Recoder文件的敌方坦克、游戏成绩等信息
    private static Vector<Node> nodes = new Vector<>();

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recoder.allEnemyTankNum = allEnemyTankNum;
    }

//    记录击毁坦克数量
    public static void addEnemyTankNum(){
        allEnemyTankNum++;
    }
//  获取MyPanel类中的敌方坦克，用于退出时保存信息到文件
    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recoder.enemyTanks = enemyTanks;
    }

    public static String getRecordFile() {
        return recordFile;
    }

    //    获取Recoder文件中的坦克信息
    public static Vector<Node> getNodes(){

        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            String line = "";
            while ((line = br.readLine()) != null){
                String[] xyd = line.split(" ");//将坐标添加到文件中时是以空格分割的
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return nodes;
    }

    //    退出时保存游戏信息到文件
    public static void keepRecod(){

        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "");
            bw.newLine();

            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive){//理论上当前坦克isLive都是true，增加判断确保不会出错
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    //写入到文件
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
