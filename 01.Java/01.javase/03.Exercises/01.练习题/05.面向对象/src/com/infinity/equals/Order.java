/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日上午9:41:04
 */
package com.infinity.equals;

public class Order {
	public static void main(String[] args) {
		Order order1 = new Order(18, "Smith");
		Order order2 = new Order(20, "Smith");
		Order order3 = new Order(20, "Smith");
		System.out.println(order1.equals(order3));
		System.out.println(order2.equals(order3));
	}
	private int orderId;
	private String name;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Order(int orderId, String name) {
		super();
		this.orderId = orderId;
		this.name = name;
	}
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj instanceof Order) {
			Order order = (Order)obj;
			return this.orderId == order.orderId && this.name.equals(order.name);
		}
		return false;
	}
	
}
