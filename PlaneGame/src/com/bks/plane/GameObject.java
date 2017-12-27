package com.bks.plane;

import java.awt.Image;
import java.awt.Rectangle;
/**
 * 游戏中对象的父类
 * 是子弹，飞机共同的属性抽象出来的
 */
public class GameObject {
	Image img;
	double x,y;
	int speed = 3;
	
	int width,height;
	
	/**
	 * 碰撞检测
	 * 平面游戏基于长方体
	 * 3D游戏基于立方体
	 * @return
	 */
	public Rectangle getRect(){
		return  new Rectangle((int)x,(int) y, width, height);
	}

	public GameObject(Image img, double x, double y, int speed, int width, int height) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.width = width;
		this.height = height;
	}
	
	public GameObject() {
	}
	
	public void setSpeed(int speed) {
		this.speed =speed;
	}
}
