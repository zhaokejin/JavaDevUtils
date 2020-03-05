package cn.cicoding.other;

import cn.cicoding.string.StringUtil;


/**
* @Description: 比较版本号
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class VersionUtils {

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) {
    	int idx = 0;
        if (StringUtil.isEmpty(version1) || StringUtil.isEmpty(version2)) {
        	return 0;
        }
        // 切割点 "."；
        String[] versionArray1 = version1.split("\\.");
        String[] versionArray2 = version2.split("\\.");
        
        // 取最小长度值
        int minLength = Math.min(versionArray1.length, versionArray2.length);
        int diff = 0;
        // 先比较长度 再比较字符
        while (idx < minLength && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {
            ++idx;
        }
        // 如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    /**
     * 比较版本号 true
     *
     * @param version1
     * @param version2
     * @return boolean true v1>=v2 false相反
     */
    public static boolean compare(String version1, String version2) {
        int num = compareVersion(version1, version2);
        return num >= 0;
    }
    
    public static void main(String[] args) {
		String v1 = "5.1.3";
		String v2 = "5.8.2";
		System.out.println(compare(v2, v1));
	}
}
