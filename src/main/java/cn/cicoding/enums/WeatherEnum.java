package cn.cicoding.enums;

import cn.cicoding.other.PropertyUtil;


/**
* @Description: 天气枚举数据
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class WeatherEnum {
	
	/**
	 * 天气信息服务器地址
	 */
	public static final String WEATHER_URL = "http://218.107.133.101/weather/WeatherService";
	/**
	 * 需要获取天气信息的城市编码，多个以"|"隔开
	 */
	public static final String AREA_CODE = PropertyUtil.getProperty("weather_code");
	/**
	 * 数据类型
	 * 3 天常规预报(24 小时):forecast_f(基础)、forecast_v(常规)
	 * 指数:index_f(基础)、 index_v(常规)
	 */
	public static final String TYPE = "forecast_f";

	/**
	 * 天气现象编码枚举
	 * @date 2015-5-8 上午10:54:39
	 * @author WangPan
	 */
	public enum WeatherCodeEnum{
		W00,W01,W02,W03,W04,W05,W06,W07,W08,W09,W10,W11,W12,W13,W14,W15,W16,W17,W18,W19,W20,W21,W22,W23,W24,W25,W26,W27,W28,W29,W30,W31,W53,W99;
		public static WeatherCodeEnum getWeatherCodeEnum(String weatherCode) {
			weatherCode = "W" + weatherCode;
			return valueOf(weatherCode);
		}
	}
	
	/**
	 * 获取天气名称
	 * @author WangPan
	 * @date 2015-5-8 上午11:07:29
	 * @param weatherCode	气象数据JSON中fa(白天天气现象编号)和fb(晚上天气现象编号)
	 * @return
	 */
	public static String getWeatherName(String weatherCode){
		switch(WeatherCodeEnum.getWeatherCodeEnum(weatherCode)){
		case W00:
			return "晴";
		case W01:
			return "多云";
		case W02:
			return "阴";
		case W03:
			return "阵雨";
		case W04:
			return "雷阵雨";
		case W05:
			return "雷阵雨伴有冰雹";
		case W06:
			return "雨夹雪";
		case W07:
			return "小雨";
		case W08:
			return "中雨";
		case W09:
			return "大雨";
		case W10:
			return "暴雨";
		case W11:
			return "大暴雨";
		case W12:
			return "特大暴雨";
		case W13:
			return "阵雪";
		case W14:
			return "小雪";
		case W15:
			return "中雪";
		case W16:
			return "大雪";
		case W17:
			return "暴雪";
		case W18:
			return "雾";
		case W19:
			return "冻雨";
		case W20:
			return "沙尘暴";
		case W21:
			return "小到中雨";
		case W22:
			return "中到大雨";
		case W23:
			return "大到暴雨";
		case W24:
			return "暴雨到大暴雨";
		case W25:
			return "大暴雨到特大暴雨";
		case W26:
			return "小到中雪";
		case W27:
			return "中到大雪";
		case W28:
			return "大到暴雪";
		case W29:
			return "浮尘";
		case W30:
			return "扬沙";
		case W31:
			return "强沙尘暴";
		case W53:
			return "霾";
		case W99:
			return "无";
		default:
			return "-";
		}
	}
	

	/**
	 * 天气图标坐标值枚举
	 * @author WangPan
	 * @date 2015-5-19 下午05:08:28
	 */
	public enum WeatherImgEnum{
		晴,多云,阴,阵雨,雷阵雨,雷阵雨伴有冰雹,雨夹雪,小雨,中雨,大雨,暴雨,大暴雨,特大暴雨,阵雪,小雪,中雪,大雪,暴雪,雾,冻雨,沙尘暴,小到中雨,中到大雨,大到暴雨,暴雨到大暴雨,大暴雨到特大暴雨,小到中雪,中到大雪,大到暴雪,浮尘,扬沙,强沙尘暴,霾;
		public static WeatherImgEnum getWeatherImgEnum(String weatherName) {
			return valueOf(weatherName);
		}
	}
	
	/**
	 * 获取天气图标坐标
	 * @author WangPan
	 * @date 2015-5-19 下午05:08:28
	 * @param weatherName	气象数据JSON中fa(白天天气现象编号)和fb(晚上天气现象编号)
	 * @return
	 */
	public static String getWeatherImgLocation(String weatherName){
		switch(WeatherImgEnum.getWeatherImgEnum(weatherName)){
		case 晴:
			return "0-0";
		case 多云:
			return "48-0";
		case 阴:
			return "96-0";
		case 阵雨:
			return "143-0";
		case 雷阵雨:
			return "188-0";
		case 雷阵雨伴有冰雹:
			return "235-0";
		case 雨夹雪:
			return "282-0";
		case 小雨:
			return "329-0";
		case 中雨:
			return "376-0";
		case 大雨:
			return "0-48";
		case 暴雨:
			return "48-48";
		case 大暴雨:
			return "96-48";
		case 特大暴雨:
			return "143-48";
		case 阵雪:
			return "188-48";
		case 小雪:
			return "235-48";
		case 中雪:
			return "282-48";
		case 大雪:
			return "329-48";
		case 暴雪:
			return "376-48";
		case 雾:
			return "0-92";
		case 冻雨:
			return "143-92";
		case 沙尘暴:
			return "96-92";
		case 小到中雨:
			return "376-0";
		case 中到大雨:
			return "0-48";
		case 大到暴雨:
			return "48-48";
		case 暴雨到大暴雨:
			return "96-48";
		case 大暴雨到特大暴雨:
			return "143-48";
		case 小到中雪:
			return "282-48";
		case 中到大雪:
			return "329-48";
		case 大到暴雪:
			return "376-48";
		case 浮尘:
			return "96-140";
		case 扬沙:
			return "143-140";
		case 强沙尘暴:
			return "96-92";
		case 霾:
			return "329-140";
		default:
			return "0-0";
		}
	}
	
	/**
	 * 风向编码枚举
	 * @author WangPan
	 * @date 2015-5-8 上午11:21:53
	 */
	public enum WindDirectionEnum{
		WD0,WD1,WD2,WD3,WD4,WD5,WD6,WD7,WD8,WD9;
		public static WindDirectionEnum getWindDirectionEnum(String windDirectionCode) {
			windDirectionCode = "WD" + windDirectionCode;
			return valueOf(windDirectionCode);
		}
	}
	
	/**
	 * 获取风向
	 * @author WangPan
	 * @date 2015-5-8 上午11:21:53
	 * @param weatherCode	气象数据JSON中fe(白天风向编号)和ff(晚上风向编号)
	 * @return
	 */
	public static String getWindDirectionName(String windDirectionCode){
		switch(WindDirectionEnum.getWindDirectionEnum(windDirectionCode)){
		case WD0:
			return "无持续风向";
		case WD1:
			return "东北风";
		case WD2:
			return "东风";
		case WD3:
			return "东南风";
		case WD4:
			return "南风";
		case WD5:
			return "西南风";
		case WD6:
			return "西风";
		case WD7:
			return "西北风";
		case WD8:
			return "北风";
		case WD9:
			return "旋转风";
		default:
			return "-";
		}
	}
	
	/**
	 * 风力编码枚举
	 * @author WangPan
	 * @date 2015-5-8 上午11:21:53
	 */
	public enum WindPowerEnum{
		WP0,WP1,WP2,WP3,WP4,WP5,WP6,WP7,WP8,WP9;
		public static WindPowerEnum getWindPowerEnum(String windPowerCode) {
			windPowerCode = "WP" + windPowerCode;
			return valueOf(windPowerCode);
		}
	}
	
	/**
	 * 获取风向
	 * @author WangPan
	 * @date 2015-5-8 上午11:21:53
	 * @param weatherCode	气象数据JSON中fg(白天风力编号)和fh(晚上风力编号)
	 * @return
	 */
	public static String getWindPowerName(String windPowerCode){
		switch(WindPowerEnum.getWindPowerEnum(windPowerCode)){
		case WP0:
			return "微风";
		case WP1:
			return "3-4级";
		case WP2:
			return "4-5级";
		case WP3:
			return "5-6级";
		case WP4:
			return "6-7级";
		case WP5:
			return "7-8级";
		case WP6:
			return "8-9级";
		case WP7:
			return "9-10级";
		case WP8:
			return "10-11级";
		case WP9:
			return "11-12级";
		default:
			return "-";
		}
	}
	
}
