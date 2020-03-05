package cn.cicoding.other;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertyUtil {
	private static final String PROPERTIES_FILE_NAME = "common.properties";
	public static Logger logger = Logger.getLogger(PropertyUtil.class);
	private static Properties property = null;

	/**
	 * @description:获取属性
	 * @param key
	 * @return
	 * @time:2018年9月4日 上午10:32:06
	 * @author:WangPan
	 */
	public static String getProperty(String key) {
		if (property == null) {
			initProperty();
		}
		return property.getProperty(key);
	}

	/**
	 * @description:初始化
	 * @time:2018年9月4日 上午10:33:13
	 * @author:WangPan
	 */
	private static void initProperty() {
		String path = PropertyUtil.class.getClassLoader().getResource("").getPath() + PROPERTIES_FILE_NAME;
		try {
			path = URLDecoder.decode(path, "utf-8");// 处理路径中还有空格问题
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		InputStream in = null;
		try {
			in = new BufferedInputStream(new FileInputStream(path));
			Properties p = new Properties();
			p.load(in);
			property = p;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public final static Map<String, String> properties(InputStream in) {
		Map<String, String> map = new HashMap<>();
		Properties pps = new Properties();
		try {
			pps.load(in);
		} catch (IOException e) {
			logger.error("load properties error:" + e.getMessage());
		}
		Enumeration en = pps.propertyNames();
		while (en.hasMoreElements()) {
			String strKey = (String) en.nextElement();
			String strValue = pps.getProperty(strKey);
			map.put(strKey, strValue);
		}
		return map;
	}

	/**
	 * @description:读取Properties的全部信息
	 * @param filePath
	 * @return
	 * @throws IOException
	 * @time:2018年9月4日 上午10:33:25
	 * @author:WangPan
	 */
	public final static Map<String, String> GetAllProperties(String filePath) throws IOException {
		Map<String, String> map = new HashMap<>();
		Properties pps = new Properties();
		try (InputStream in = new BufferedInputStream(new FileInputStream(filePath))) {
			return properties(in);
		} catch (IOException e) {
			logger.error("load properties error");
		}
		return map;
	}

	/**
	 * @description:写入Properties信息
	 * @param filePath
	 * @param pKey
	 * @param pValue
	 * @throws IOException
	 * @time:2018年9月4日 上午10:33:33
	 * @author:WangPan
	 */
	public final static void WriteProperties(String filePath, String pKey,
			String pValue) throws IOException {
		Properties props = new Properties();

		props.load(new FileInputStream(filePath));
		// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
		// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
		OutputStream fos = new FileOutputStream(filePath);
		props.setProperty(pKey, pValue);
		// 以适合使用 load 方法加载到 Properties 表中的格式，
		// 将此 Properties 表中的属性列表（键和元素对）写入输出流
		props.store(fos, "Update '" + pKey + "' value");

	}
}
