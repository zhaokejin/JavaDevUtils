package cn.cicoding.collection;

import java.util.ArrayList;
import java.util.List;
/**
* @Description: List subList 分页
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class ListPageUtil {

	/**
	 * @description:List 分页
	 * @param list
	 * @param pageNum 页数
	 * @param pageSize 每页条数
	 * @return
	 * @time:2018年3月16日 下午5:36:46
	 * @user:WangPan
	 */
	public static List<Object> paging(List<Object> list, Integer pageNum,Integer pageSize) {
		
		List<Object> pageList = null;
		//总记录数
		int totalCount = list.size();
		//总页数
		int pageCount = 0;
		//求余，最后一页的记录条数
        int lastPageCount = totalCount % pageSize;
        
		if(pageNum < 1){
			return pageList;
		}
		
		if (lastPageCount > 0) {
			pageCount = totalCount / pageSize + 1;
		} else {
			pageCount = totalCount / pageSize;
		}
		
		if(pageNum > pageCount){
			return pageList;
		}
		
		if (lastPageCount == 0){
			pageList = list.subList((pageNum - 1) * pageSize,pageNum * pageSize);
		}else{
			if(pageCount == pageNum){
				pageList = list.subList((pageNum - 1) * pageSize,totalCount);
			}else{
				pageList = list.subList((pageNum - 1) * pageSize,pageNum * pageSize);
			}
		}
		
		return pageList;
	}

	public static void main(String[] args) {

		List<Object> list = new ArrayList<Object>();
		for (int i = 1; i < 35; i++) {
			list.add(i);
		}
		List<Object> pageList = paging(list,1 ,10);
		System.out.println(pageList);
	}
}
