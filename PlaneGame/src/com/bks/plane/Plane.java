package com.bks.plane;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.bks.util.GameUtil;

public class Plane extends GameObject {
	private boolean left,up,right,down;
	
	private boolean live = true;
	
	public void draw(Graphics g){	//绘制出飞机
		if(live){
			g.drawImage(img, (int)x, (int)y, null);
			move();
		}
	}
	
	//达到控制所有方向的目的
	public void move(){
		if(left){
			x -= speed;
		}
		if(right){
			x += speed;
		}
		if(up){
			y -= speed;
		}
		if(down){
			y += speed;
		}
		
	}
	
	public void addDirection(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		default:
			break;
		}
	}
	
	public void minusDirection(KeyEvent e){
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		default:
			break;
		}
	}
	
	
	
	
	public Plane(String imgpath, double x, double y) {
		this.img =  GameUtil.getImage(imgpath);
		System.out.println(img.getWidth(null));
		System.out.println(img.getWidth(null));
		this.width = img.getWidth(null);
		this.height = img.getWidth(null);
		this.x = x;
		this.y = y;
	}
	
	public Plane(){
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	
	
}
