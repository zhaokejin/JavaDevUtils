package cn.cicoding.collection;

public class ArrayUtil {

    /**
     * @description:获取一个double类型小数点号后的长度
     * @param dd
     * @return
     * @time:2018年9月4日 上午9:37:38
     * @user:WangPan
     */
    public static int doueleBitCount(double dd){
        String temp = String.valueOf(dd);
        int i = temp.indexOf(".");
        if(i > -1){
            return temp.length()-i -1;
        }
        return 0;

    }

    /**
     * @description:获取一个double类型数组 小数点号后的长度
     * @param arr
     * @return
     * @time:2018年9月4日 上午9:38:08
     * @user:WangPan
     */
    public static Integer[] doubleBitCount(double[] arr){
        Integer[] len = new Integer[arr.length];
        for (int i = 0; i < arr.length; i++) {
            len[i] = doueleBitCount(arr[i]);
        }
        return len;
    }
}
