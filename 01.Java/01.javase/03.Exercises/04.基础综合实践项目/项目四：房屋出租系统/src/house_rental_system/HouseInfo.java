/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月23日下午4:13:33
 */
package house_rental_system;

public class HouseInfo {
	private String name;
	private String phone;
	private String address;
	private String money;
	private String status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public HouseInfo() {
		super();
	}
	public HouseInfo(String name, String phone, String address, String money, String status) {
		super();
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.money = money;
		this.status = status;
	}
}
