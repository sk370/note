package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/1 13:53
 */
public class TortoiseRace {
    public int distance = 1000;

    public static void main(String[] args) {
        TortoiseRace tortoise1 = new TortoiseRace();
        Rabit rabit = new Rabit(tortoise1);
        Tortoise tortoise = new Tortoise(tortoise1);
        rabit.start();
        tortoise.start();
    }

}

class Rabit extends Thread{
    private int currentDis = 0;
    private String name = "兔子";
    private TortoiseRace race;

    public Rabit(TortoiseRace race) {
        this.race = race;
    }

    @Override
    public void run() {
        while (currentDis < race.distance){
            try {
                sleep(200);
                currentDis += 40;
                System.out.println(name + "跑了" + currentDis +"米");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        super.run();
    }
}
class Tortoise extends Thread{
    private int currentDis = 0;
    private String name = "乌龟";
    private TortoiseRace race;

    public Tortoise(TortoiseRace race) {
        this.race = race;
    }

    @Override
    public void run() {
        while (currentDis < race.distance){
            try {
                sleep(50);
                currentDis += 10;
                System.out.println(name + "跑了" + currentDis +"米");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}