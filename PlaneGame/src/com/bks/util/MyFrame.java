package com.bks.util;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MyFrame  extends Frame {
	
	/**
	 * 加载窗口
	 */
	public void launchFrame(){
		
		setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);	//窗口大小
		setLocation(100, 100);								//窗口位置
		setVisible(true);	
		
		new PaintThread().start();  //启动重画线程
		
		addWindowListener(new WindowAdapter() {	//	关闭窗口
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	/**
	 *双缓冲技术解决屏幕闪烁
	 *swing自己就解决了屏幕闪烁，这里用的是awt 
	 */
	private Image offScreenImage = null;
	public void update(Graphics g) {
		if(offScreenImage == null)
			offScreenImage = this.createImage(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
		
		Graphics gOff = offScreenImage.getGraphics();

		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
	
	/**
	 * 定义一个重画窗口的线程，是个内部类
	 * @author whj82
	 *
	 */
	class PaintThread extends Thread {
		
		public void run(){
			while(true){
				repaint();
				try {
					Thread.sleep(40); //1s = 1000ms		重绘界面，达到动态目的
				} catch (InterruptedException e) {
					e.printStackTrace();
				}   
			}
		}
		
	}
}
