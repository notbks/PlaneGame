package com.bks.plane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;

import com.bks.util.GameUtil;
import com.bks.util.MyFrame;
/**
 * 游戏主体
 * @author whj82
 *
 */
public class PlaneGameFrame extends MyFrame {
	
	//难度，表示子弹的数量和速度
	static int number =25;
	static int speed =3;
	
	Image bg = GameUtil.getImage("images/bg.jpg");
	Plane p = new Plane("images/plane.png",50,50);
	ArrayList<Bullet> bulletList = new ArrayList<>(); 
	
	Date startTime;
	Date endTime;
	
	Explode bao;
	
	public void paint(Graphics g){
		g.drawImage(bg, 0, 0, null);
		p.draw(g);
		
		//取出子弹
		for(int i=0;i<bulletList.size();i++){
			Bullet b = (Bullet) bulletList.get(i);
			b.draw(g);
			boolean peng = b.getRect().intersects(p.getRect());
			//飞机爆炸
			if(peng){
				p.setLive(false);  
				if(bao==null){
					endTime = new Date();
					bao = new Explode(p.x,p.y);
				}
				bao.draw(g);
				
				break;
			}
		}
		
		if(!p.isLive()){
			int period = (int)((endTime.getTime()-startTime.getTime())/1000);	//存活时间 秒
			printInfo(g, "GAME OVER  "+period+"秒", 20, 120, 260, Color.white);
			
			switch (period/5) {
			case 0:
			case 1:
				printInfo(g, "菜鸟", 50,100,200,Color.white);
				break;
			case 2:
				printInfo(g, "小鸟", 50,100,200,Color.white);
				break;
			case 3:
				printInfo(g, "大鸟", 50,100,200,Color.yellow);
				break;
			case 4:
				printInfo(g, "鸟王子", 50,100,200,Color.yellow);
				break;
			default:
				printInfo(g, "鸟人", 50,100,200,Color.yellow);
				break;
			}	
		}
	}
	
	//在窗口打印信息
	public static void printInfo(Graphics g,String str,int size,int x,int y,Color color){
		Color c = g.getColor();
		g.setColor(color);
		Font f = new Font("宋体",Font.BOLD,size);
		g.setFont(f);
		g.drawString(str,x,y);
		g.setColor(c);
	}
	
	//定义为内部类，可以方便的使用外部类的普通属性
	class KeyMonitor extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			p.addDirection(e);	//将移动飞机的方法放到plane中，做到低耦合
		}

		@Override
		public void keyReleased(KeyEvent e) {
			p.minusDirection(e);	//释放按键时，将Boolean上下左右修改回来
		}
		
	}
	
	//选择难度
	public static void chooseDifficulty(KeyEvent e, Graphics g) {
		printInfo(g, "选择难度", 100, 0, 0, Color.red);
		printInfo(g, "按下e键选择 简单 难度", 50, 25, 0, Color.red);
		printInfo(g, "按下h键选择 困难 难度", 50, 50, 0, Color.red);
		printInfo(g, "其他键选择 中等 难度", 50, 25, 0, Color.red);
		if(e.getKeyCode()==40) {
			number =20;
			speed =2;
		}else if(e.getKeyCode()==23) {
			number =30;
			speed =4;
		}else {
			number =25;
			speed =3;
		}
	}
	
	//运行游戏
	public void launchFrame(){
		//因为重写了launchFrame方法，所以不会调用父类的launchFrame方法。这里需要用super调用
		super.launchFrame();

		addKeyListener(new KeyMonitor());		//增加键盘的监听
		
		//chooseDifficulty(e,getGraphics());
		
		//根据难度生成子弹
		for(int i=0;i<number;i++){
			Bullet b = new Bullet();
			b.setSpeed(speed);
			bulletList.add(b);
		}
		
		startTime = new Date();
	}
	
	public static void main(String[] args) {
			new PlaneGameFrame().launchFrame();
		}
	}
