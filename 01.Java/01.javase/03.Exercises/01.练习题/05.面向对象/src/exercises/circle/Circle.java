/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月26日下午6:25:04
 */
package exercises.circle;

public class Circle implements Shape{
	double radius;
	public Circle() {
		super();
	}
	public Circle(double radius) {
		super();
		this.radius = radius;
	}
	@Override
	public double area() {
		return PI * radius * radius;
	}
	public double perimeter() {
		return 2 * PI * radius;
	}
}
