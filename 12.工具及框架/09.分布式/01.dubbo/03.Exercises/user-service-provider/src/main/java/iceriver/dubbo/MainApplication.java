package iceriver.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApplication {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("provider.xml");
		ioc.start();
		System.in.read();//按任意键退出，为了观察效果设置的阻塞
	}
}
