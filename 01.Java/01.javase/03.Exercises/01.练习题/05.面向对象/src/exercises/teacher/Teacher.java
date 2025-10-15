/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日上午11:02:24
 */
package exercises.teacher;

public class Teacher {
	private String name;
	private int age;
	private String post;
	private double salary;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public Teacher() {
		super();
	}
	public Teacher(String name, int age, String post, double salary) {
		super();
		this.name = name;
		this.age = age;
		this.post = post;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "[name=" + name + ", age=" + age + ", post=" + post + ", salary=" + salary;
	}
	public void introduce() {
		System.out.println(toString());
	}
}
