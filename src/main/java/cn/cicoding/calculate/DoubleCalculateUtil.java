package cn.cicoding.calculate;

import java.math.BigDecimal;

/**
* @Description: double 类型加减乘除，精度
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class DoubleCalculateUtil {
	
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * @description:提供精确的加法运算
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 * @time:2018年9月4日 上午9:35:09
	 * @user:WangPan
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * @description:提供精确的减法运算
	 * @param v1 被减数
	 * @param v2 减数
	 * @return 两个参数的差
	 * @time:2018年9月4日 上午9:35:32
	 * @user:WangPan
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * @description:提供精确的乘法运算
	 * @param v1 被乘数
	 * @param v2 乘数
	 * @return 两个参数的积
	 * @time:2018年9月4日 上午9:35:54
	 * @user:WangPan
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * @description:提供（相对）精确的除法运算，当发生除不尽的情况时，精确到小数点以后10位，以后的数字四舍五入。
	 * @param v1
	 * @param v2
	 * @return
	 * @time:2018年9月4日 上午9:36:17
	 * @user:WangPan
	 */
	public static double div(double v1, double v2) {
		if(v2 > 0){
			return div(v1, v2, DEF_DIV_SCALE);
		}else{
			return 0;
		}
	}

	/**
	 * @description:提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指定精度，以后的数字四舍五入。
	 * @param v1
	 * @param v2
	 * @param scale
	 * @return
	 * @time:2018年9月4日 上午9:36:35
	 * @user:WangPan
	 */
	public static double div(double v1, double v2, int scale) {
		if(v2 > 0){
			if (scale < 0) {
				throw new IllegalArgumentException("The scale must be a positive integer or zero");
			}
			BigDecimal b1 = new BigDecimal(Double.toString(v1));
			BigDecimal b2 = new BigDecimal(Double.toString(v2));
			return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
		}else{
			return 0;
		}
	}

	/**
	 * @description:提供精确的小数位四舍五入处理。
	 * @param v
	 * @param scale
	 * @return
	 * @time:2018年9月4日 上午9:36:44
	 * @user:WangPan
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
}
