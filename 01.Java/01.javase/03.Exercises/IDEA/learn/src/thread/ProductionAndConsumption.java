package thread;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/1 10:12
 */
public class ProductionAndConsumption{
    private int production = 0;
    private int consumption = 0;


    public synchronized void production(){
        while (production < 50){
            notify();
            try {
                Thread.sleep(200);
                production++;
                System.out.println("正在生产第" + production +  "个。");
                if(production % 20 == 0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public synchronized void consumption(){
        while(production < 50){
            notify();
            try {
                Thread.sleep(1000);
                consumption++;
                System.out.println("正在消费第" + consumption + "个。");
                if(consumption % 3 == 0){
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public static void main(String[] args) {
        ProductionAndConsumption pc = new ProductionAndConsumption();
        Production production = new Production(pc);
        Consumption consumption = new Consumption(pc);
        production.start();
        consumption.start();
    }
}
class Production extends Thread{
    private ProductionAndConsumption pc;

    public Production(ProductionAndConsumption pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        pc.production();
    }
}
class Consumption extends Thread{
    private ProductionAndConsumption pc;

    public Consumption(ProductionAndConsumption pc) {
        this.pc = pc;
    }

    @Override
    public void run() {
        pc.consumption();
    }
}