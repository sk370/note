package reflect_;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/17 8:13
 */
public class Car {
    public String brand = "宝马";
    public String price = "50000";
    public String color = "red";

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price='" + price + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
