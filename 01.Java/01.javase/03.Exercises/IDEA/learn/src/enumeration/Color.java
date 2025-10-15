package enumeration;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/5/25 14:01
 */
public enum Color implements ShowColor {
    RED(255,0,0), BLUE(0,0,255), BLACK(0,0,0), YELLOW(255, 255,0), GREEN(0,255,0);
    private int redValue;
    private int greenValue;
    private int blueValue;
    Color(int redValue, int greenValue, int blueValue) {
        this.redValue = redValue;
        this.greenValue = greenValue;
        this.blueValue = blueValue;
    }

    @Override
    public String show() {
        return ":redValue=" + redValue +
                ", greenValue=" + greenValue +
                ", blueValue=" + blueValue;
    }

    public static void main(String[] args) {
        Color[] colors = Color.values();
        for(Color color : colors){
            System.out.print(color);
            System.out.println(color.show());
        }
    }
}
interface ShowColor{
    String show();
}
