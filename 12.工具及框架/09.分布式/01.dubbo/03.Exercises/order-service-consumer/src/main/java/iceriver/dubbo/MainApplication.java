package iceriver.dubbo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import iceriver.dubbo.service.OrderService;

public class MainApplication {

	public static void main(String[] args) throws IOException {
		// TODO 自动生成的方法存根
		ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("consumer.xml");
		OrderService orderService = ioc.getBean(OrderService.class);
		orderService.initOrder("1");
		System.in.read();
	}
}
