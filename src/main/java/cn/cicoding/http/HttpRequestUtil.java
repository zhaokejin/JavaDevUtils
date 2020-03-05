package cn.cicoding.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

public class HttpRequestUtil {
	private static final Logger logger = Logger.getLogger(HttpRequestUtil.class.getName());  

	public final static String HTTP_REQUEST_KEY = "a0ec166da8ea698b5b71e6e2fe4ffdd7";

	public static String sendHttpRequest(String urlStr){
		String result = null;
		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			InputStreamReader in = new InputStreamReader(con.getInputStream(),"utf-8");

			BufferedReader bfreader = new BufferedReader(in);

			StringBuffer sb = new StringBuffer();

			String line = null;

			while ((line = bfreader.readLine()) != null) {
				sb.append(line);
			}
			result = sb.toString();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			result = "error";
		} catch (IOException e) {
			e.printStackTrace();
			result = "error";
		}
		return result;
	}

	public String sendGet(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			String urlNameString = url;
			URL realUrl = new URL(urlNameString);
			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent","Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			// 建立实际的连接
			connection.connect();
			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}


}
