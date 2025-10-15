/**
 * @author INFITNITY Juejin:https://juejin.cn/user/2788017217999896
 * @date 2022年5月19日下午6:04:35
 */
package com.infinity.equals;

import java.util.Objects;

public class JourneyToTheWest {
	private String name;
	private double height;
	private String weapon;
	private String race;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getWeapon() {
		return weapon;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public JourneyToTheWest(String name, String race) {
		super();
		this.name = name;
		this.race = race;
	}
	@Override
	public String toString() {
		return "姓名：" + this.name + "；种族：" + this.race;
	}
	@Override
	public boolean equals(Object obj) {
		JourneyToTheWest jtw = (JourneyToTheWest)obj;
		if (jtw.race.equals("人族") || jtw.race.equals("猴族"))
			return true;
		if(jtw.race.equals("妖族"))
			return false;
		return false;
	}
	void skill() {
		if(this.name.equals("孙悟空")) {
			System.out.println("七十二变！");
		}
		if(this.name.equals("唐僧")) {
			System.out.println("紧箍咒！");
		}
		if(this.name.equals("白骨精")) {
			System.out.println("九阴白骨爪！");
		}
		
	}
}
