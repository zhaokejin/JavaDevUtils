package cn.cicoding.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
* @Description: AES加密，解密
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class AesUtil {

	private static String password="aestest";

	/**  
	 * 加密  
	 * @param content 需要加密的内容  
	 * @return  
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws UnsupportedEncodingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */    
	public static byte[] encrypt(String content) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{    
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");  
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");  
		byte[] byteContent = content.getBytes("utf-8");  
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化   
		byte[] result = cipher.doFinal(byteContent);  
		return result; // 加密  
	}    

	/**解密  
	 * @param content  待解密内容  
	 * @return  
	 * @throws NoSuchAlgorithmException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */    
	public static byte[] decrypt(byte[] content) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{    
		SecretKeySpec key = new SecretKeySpec(password.getBytes(), "AES");  
		Cipher cipher = Cipher.getInstance("AES");  
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化   
		byte[] result = cipher.doFinal(content);  
		return result; //解密   
	}   
	/**
	 * @description:将二进制转换成16进制
	 * @param buf
	 * @return
	 * @time:2018年9月4日 上午10:18:46
	 * @user:WangPan
	 */
	public static String parseByte2HexStr(byte buf[]) {  
		StringBuffer sb = new StringBuffer();  
		for (int i = 0; i < buf.length; i++) {  
			String hex = Integer.toHexString(buf[i] & 0xFF);  
			if (hex.length() == 1) {  
				hex = '0' + hex;  
			}  
			sb.append(hex.toUpperCase());  
		}  
		return sb.toString();  
	}  


	/**
	 * @description:将16进制转换为二进制
	 * @param hexStr
	 * @return
	 * @time:2018年9月4日 上午10:18:53
	 * @user:WangPan
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {  

		if(hexStr.length() < 1){
			return null;
		}

		byte[] result = new byte[hexStr.length()/2];  
		for (int i = 0;i< hexStr.length()/2; i++) {  
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);  
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);  
			result[i] = (byte) (high * 16 + low);  
		}  
		return result;  
	}

	/**
	 * 因为加密后的byte数组是不能强制转换成字符串的，换言之：
	 * 字符串和byte数组在这种情况下不是互逆的；
	 * 要避免这种情况，我们需要做一些修订，可以考虑将二进制数据转换成十六进制表示
	 * 加密字符串
	 * @param content
	 * @return
	 * @user:WangPan
	 * create date:2014-8-26
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String encryptStr(String content) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException{
		byte[] bs=encrypt(content);
		return parseByte2HexStr(bs);
	}
	/**
	 * 解密字符串
	 * @param content
	 * @return
	 * @user:WangPan
	 * create date:2014-8-26
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String decryptStr(String content) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
		byte[] bs=parseHexStr2Byte(content);
		bs=decrypt(bs);
		return new String(bs);
	}
}
