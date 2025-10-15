package io;

import java.io.*;
import java.util.Properties;

/**
 * @author: INFINITY https://juejin.cn/user/2788017217999896/posts
 * @date: 2022/6/13 19:02
 */
public class PropertiesTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String filePath = "D:\\IDEA\\learn\\src\\io\\dog.properties";
        Properties properties = new Properties();
        properties.load(new FileReader(filePath));
        properties.list(System.out);
        String name = (String) properties.get("name");
        int age = Integer.parseInt(properties.getProperty("age"));
        String color = properties.get("color") + "";

        Dog dog = new Dog(name, age, color);
        System.out.println(dog);

        String serPath = "D:\\IDEA\\learn\\src\\io\\dog.dat";
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(serPath));
        objectOutputStream.writeObject(dog);
        objectOutputStream.close();
        System.out.println("序列化完成");

        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(serPath));
        Dog dog1 = (Dog)objectInputStream.readObject();
        System.out.println(dog1);


    }
}
class Dog implements Serializable{
    private String name;
    private int age;
    private String color;

    public Dog(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}