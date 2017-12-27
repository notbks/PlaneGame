package com.bks.plane;

import java.awt.Graphics;
import java.awt.Image;

import com.bks.util.GameUtil;

/*
 * 爆炸类
 */
public class Explode {
	double x,y;
	/**
	 * 用static仅加载一次爆炸图片，然后共享
	 * 爆炸就像连环画，需要很多张图片连续播放
	 * 用静态代码块初始化静态属性
	 */
	static Image[] imgs = new Image[16];
	static {
		for(int i=0;i<16;i++){
			imgs[i] = GameUtil.getImage("images/explode/e"+(i+1)+".gif");
			//因为懒加载，图片第一次加载不出来，所以调用随便一个方法打破懒加载
			imgs[i].getWidth(null);
		}
	}
	
	int count;
	
	//爆炸效果
	public void draw(Graphics g){
		if(count<=15){
			g.drawImage(imgs[count], (int)x, (int)y, null);
			count++;
		}
	}
	
	public Explode(double x,double y){
		this.x = x;
		this.y = y;
	}
}
