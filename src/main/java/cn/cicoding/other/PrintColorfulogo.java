package cn.cicoding.other;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class PrintColorfulogo {
	
	/**
	 * 打印Logo
	 */
	public static String get() {

		String a =  "\r\n"+
				    "                   _ooOoo_"+"\n"+
				    "                  o8888888o"+"\n"+
				    "                  88\" . \"88"+"\n"+
				    "                  (| o_o |)"+"\n"+
				    "                  O\\  =  /O"+"\n"+
				    "               ____/'---'\\____"+"\n"+
				    "             .'  \\\\|     |//  '."+"\n"+
				    "            /  \\\\|||  :  |||//  \\"+"\n"+
				    "           /  _||||| -:- |||||-  \\"+"\n"+
				    "           |   | \\\\\\  -  /// |   |"+"\n"+
				    "           | \\_|  ''\\---/''  |   |"+"\n"+
				    "           \\  .-\\__  `-`  ___/-. /"+"\n"+
				    "         ___`. .'  /--.--\\  `. . __"+"\n"+
				    "      .\"\" '<  `.___\\_<|>_/___.'  >'\"\"."+"\n"+
				    "     | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |"+"\n"+
				    "     \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /"+"\n"+
				    "======`-.____`-.___\\_____/___.-`____.-'======"+"\n"+
				    "                   `=---='"+"\n"+
				    "^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^"+"\n"+
				    "              佛祖保佑       永无BUG";
		
		System.out.println(a);
		return a;
	}
	
	public static void pringLogo() throws IOException {
		StringBuffer logo = new StringBuffer();
		BufferedImage bi = (BufferedImage) ImageIO.read(PrintColorfulogo.class.getResourceAsStream("48-48.png"));

		// 获取图像的宽度和高度
		int width = bi.getWidth();
		int height = bi.getHeight();

		// 扫描图片
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {// 行扫描
				int dip = bi.getRGB(j, i);
				System.out.println("----------" + dip);
				if (dip < 0){
					logo.append(" ");
				}
				else{
					logo.append("*");
				}
			}
			logo.append("\n");// 换行
		}
		System.out.println(logo);
	}
	
	public static void main(String[] args) {
		try {
			PrintColorfulogo.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
