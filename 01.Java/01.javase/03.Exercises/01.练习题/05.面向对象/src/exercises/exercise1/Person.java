/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日上午10:23:54
 */
package exercises.exercise1;

public class Person {
	private String name;
	private int age;
	private String job;
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
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Person() {
		super();
	}
	public Person(String name, int age, String job) {
		super();
		this.name = name;
		this.age = age;
		this.job = job;
	}
	
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", job=" + job + "]";
	}
	public void sort(Person[] person) {
		Person p = new Person();
		for(int i = 0; i < person.length - 1; i++) {
			for(int j = 0; j < person.length - 1 - i; j++) {
				if(person[j].getAge() < person[j + 1].getAge()) {
					p = person[j];
					person[j] = person[j + 1];
					person[j + 1] = p;
				}
			}
		}
	}
	public static void main(String[] args) {
		Person[] person = new Person[3];
		person[0] = new Person("张三", 19, "盗贼");
		person[1] = new Person("李四", 24, "法师");
		person[2] = new Person("王五", 18, "术士");
//		Person p = new Person();
		person[0].sort(person);
		for(int i = 0; i < person.length; i++) {
			System.out.println(person[i]);
		}		
	}
}
