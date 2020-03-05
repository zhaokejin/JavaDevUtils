package cn.cicoding.other;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Scanner;

import cn.cicoding.string.StringUtil;

public class MacAddressUtil{

	/**
	 * 缓存mac地址 只在第一次获取
	 */
	private static String macAddressCache=null;

	private static String cpuCodeCache=null;

	public static String hexByte(byte b) {
		String s = "000000" + Integer.toHexString(b);
		return s.substring(s.length() - 2);
	}

	/**
	 * @description:验证当前机器mac地址是否可用
	 * @param macAddress
	 * @return
	 * @time:2018年9月4日 上午10:30:22
	 * @author:WangPan
	 */
	public static boolean validateMAC(String macAddress) {
		boolean flag=false;
		
		if(StringUtil.isEmpty(macAddress)){
			return false;
		}

		if(macAddressCache!=null && macAddressCache.equals(macAddress.toUpperCase())){
			return true;
		}

		Enumeration<NetworkInterface> el;
		try {
			el = NetworkInterface.getNetworkInterfaces();
			while (el.hasMoreElements()) {
				NetworkInterface netI = el.nextElement();

				// 网络接口是否已经开启并运行
				if (netI.isUp()) {
					byte[] mac = netI.getHardwareAddress();
					if (mac == null || mac.length == 0) {
						continue;
					}
					StringBuilder mac_s = new StringBuilder();
					mac_s = mac_s.append(hexByte(mac[0])).append("-")
					.append(hexByte(mac[1])).append("-")
					.append(hexByte(mac[2])).append("-")
					.append(hexByte(mac[3])).append("-")
					.append(hexByte(mac[4])).append("-")
					.append(hexByte(mac[5]));

					//有相等的mac地址则验证正确
					if(macAddress.toUpperCase().equals(mac_s.toString().toUpperCase())){
						macAddressCache=macAddress.toUpperCase();

						flag=true;
						break;
					}
				}
			}
		} catch (SocketException e1) {
			e1.printStackTrace();
		}
		return flag;
	}

	/**
	 * @description:验证当前机器cpu是否可用
	 * @param cpuCode
	 * @return
	 * @time:2018年9月4日 上午10:30:32
	 * @author:WangPan
	 */
	public static boolean validateCPU(String cpuCode){
		boolean flag=false;
		
		if(StringUtil.isEmpty(cpuCode)){
			return false;
		}
		
		try {
			if(cpuCodeCache!=null && cpuCodeCache.equals(cpuCode)){
				return true;
			}

			Process process = Runtime.getRuntime().exec(new String[] { "wmic", "cpu", "get", "ProcessorId" });
			process.getOutputStream().close();

			Scanner sc = new Scanner(process.getInputStream());
			while(sc.hasNext()){
				String property = sc.next();
				String serial = sc.next();

				if(property.equals("ProcessorId")){
					if(serial.equals(cpuCode)){
						cpuCodeCache=serial;
						
						flag=true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}
}