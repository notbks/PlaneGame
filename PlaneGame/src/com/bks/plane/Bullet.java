package com.bks.plane;

import java.awt.Color;
import java.awt.Graphics;

import com.bks.util.Constant;

public class Bullet extends GameObject {
	double degree;	//角度
	
	public Bullet(){
		degree = Math.random()*Math.PI*2;	//[0,1]*2π 取得任意角度
		//将子弹初始位置放到界面中间
		x = Constant.GAME_WIDTH/2;
		y = Constant.GAME_WIDTH/2;
		//子弹大小
		width = 10;
		height = 10;
	}
	
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.fillOval((int)x, (int)y, width, height);
		
		x += speed*Math.cos(degree);
		y += speed*Math.sin(degree);
		
		/**
		 * 遇到边界反弹
		 */
		if(y>Constant.GAME_HEIGHT-height||y<30){
			degree = -degree;
		}
		if(x<0||x>Constant.GAME_WIDTH-width){
			degree = Math.PI-degree;
		}
		
		
		g.setColor(c);
	}
	
}
