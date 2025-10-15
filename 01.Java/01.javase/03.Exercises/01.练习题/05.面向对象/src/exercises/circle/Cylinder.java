/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月26日下午6:27:26
 */
package exercises.circle;

public class Cylinder extends Circle{
	double height;

	public Cylinder() {
		super();
	}
	public Cylinder(double radius, double height) {
		super(radius);
		this.height = height;
	}
	@Override
	public double area() {
		// TODO Auto-generated method stub
		return super.perimeter() * height;
	}
	public double volume() {
		return super.area() * height;
	}
	
}
