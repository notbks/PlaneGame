package com.bks.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GameUtil {
	
	//工具类 一般将构造器私有
	private GameUtil(){}  
	
	//获取图片
	public static Image getImage(String path){
		BufferedImage bi=null;
		try {
			URL u = GameUtil.class.getClassLoader().getResource(path);
			bi = javax.imageio.ImageIO.read(u);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bi;
	}
}
