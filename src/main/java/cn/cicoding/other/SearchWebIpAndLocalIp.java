package cn.cicoding.other;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
* @Description: 获取IP地址
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class SearchWebIpAndLocalIp {

	private static String weburl = "http://1212.ip138.com/ic.asp";

	public static void main(String[] args) {
		String[] Web = SearchWebIpAndLocalIp.getwebIp(weburl);
	}
	
	public static String[] getwebIp(String strUrl) {
		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"GB2312"));
			String s = "";
			StringBuffer sb = new StringBuffer("");
			String webContent = "";
			String[] web = { "IP", "Attribution" };
			while ((s = br.readLine()) != null) {
				sb.append(s + "\r\n");
			}
			br.close();
			webContent = sb.toString();
			int start = webContent.indexOf("[") + 1;
			int end = webContent.indexOf("]");
			web[0] = webContent.substring(start, end);
			start = webContent.indexOf("来自：") + 3;
			end = webContent.indexOf("市");
			web[1] = webContent.substring(start, end);
			return web;
		} catch (Exception e) {
			e.printStackTrace();
			String[] NULL = { "获取天气信息失败,请检查网络连接!", "获取天气信息失败,请检查网络连接!" };
			return NULL;
		}
	}

	public static String getlocalIp() {
		/* 获取本机IP地址 */
		try {
			String LocallIp = "[";
			LocallIp += InetAddress.getLocalHost().getHostAddress();
			LocallIp += "]";
			return LocallIp;
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}
}
