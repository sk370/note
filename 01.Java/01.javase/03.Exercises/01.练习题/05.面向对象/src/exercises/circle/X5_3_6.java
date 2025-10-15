/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月26日下午6:31:17
 */
package exercises.circle;

public class X5_3_6 {
	public static void main(String[] args) {
		Circle c1 = new Circle(3.6);
		Circle c2 = new Circle(2.4);
		System.out.println(c1.area());
		System.out.println(c2.area());
		System.out.println(c1.perimeter());
		System.out.println(c2.perimeter());
		Cylinder cy1 = new  Cylinder(3.6, 2.4);
		Cylinder cy2 = new  Cylinder(2.4, 3.6);
		System.out.println(cy1.area());
		System.out.println(cy2.area());
		System.out.println(cy1.volume());
		System.out.println(cy2.volume());
	}
}
