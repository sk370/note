package reflect_;

import java.lang.reflect.Field;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        Car car1 = new Car();
        car1.brand = "奔驰";
        System.out.println(car1);//Car{brand='奔驰', price='50000', color='red'}
        System.out.println(car1.getClass());//class reflect_.Car

        System.out.println(car1.getClass().getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
//        System.out.println(Test.class.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(Car.class);//class reflect_.Car
        System.out.println(car1.getClass() == Car.class);//true
        System.out.println(Car.class.getClassLoader());//sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(car1.getClass().getClassLoader()==Test.class.getClassLoader());//true

        Person person = new Person();
        System.out.println(Person.class.getClassLoader()==person.getClass().getClassLoader());//true
        System.out.println(Person.class.getClassLoader()==car1.getClass().getClassLoader());//true

        System.out.println(Test.class);//class reflect_.Test
        System.out.println(Test.class.getClass());//class java.lang.Class

        String classPath = "reflect_.Car";
        Class<?> cls = Class.forName(classPath);
        System.out.println(cls);//class reflect_.Car
        System.out.println(cls.getClass());//class java.lang.Class
        System.out.println(cls.getName());//reflect_.Car

        System.out.println(cls.getPackage());//package reflect_
        System.out.println(cls.getPackage().getName());//reflect_

        Field brand = cls.getField("brand");
        System.out.println(brand);//public java.lang.String reflect_.Car.brand
        System.out.println(brand.getName());//brand

        Car car = (Car)cls.newInstance();
        System.out.println(car);//Car{brand='宝马', price='50000', color='red'}

        System.out.println(brand.get(car));//宝马
        System.out.println(brand.get(car1));//奔驰

        brand.set(car, "华晨宝马");
        brand.set(car1, "梅赛德斯奔驰");
        System.out.println(brand.get(car));//华晨宝马
        System.out.println(brand.get(car1));//梅赛德斯奔驰

        Field[] fields = cls.getFields();
        System.out.println(fields);//[Ljava.lang.reflect.Field;@74a14482
        for(Field field : fields){
            System.out.print(field.getName() + "\t");//brand	price	color
        }
    }
}
