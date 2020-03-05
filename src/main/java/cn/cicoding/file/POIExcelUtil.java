package cn.cicoding.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.cicoding.model.ExportFileVo;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.cicoding.date.DateUtil;

/**
 * 基于poi-ooxml3.14  3.15某些方法过时  3.16更新了
* @Description: 导出excel
* @author www.cicoding.cn
* @date 2018年9月4日
* @version V1.0
*/
public class POIExcelUtil {

	private static final DecimalFormat df = new DecimalFormat("#0");
	
	/**
	 * @description:导出excel文件
	 * @param list
	 * @param input
	 * @param out
	 * @param title
	 * @time:2018年9月3日 下午5:10:35
	 * @user:WangPan
	 */
	public void exportExcel(List<ExportFileVo> list, InputStream input, OutputStream out, String title){
		
		try {
			
			XSSFWorkbook workbook = new XSSFWorkbook(input);
			SXSSFWorkbook wb=new SXSSFWorkbook(workbook, 500);
			
			
			CellStyle style1 = wb.createCellStyle(); 
			CellStyle style2 = wb.createCellStyle();   
			CellStyle style3 = wb.createCellStyle();   
			//自动换行
			style1.setWrapText(true);
			style2.setWrapText(true);
			Font font1 = wb.createFont(); 
			Font font2 = wb.createFont(); 
			CellStyle titleStyle = getTitleStyle(style1, font1);
			CellStyle cellStyle = getCellStyle(style2, HSSFColor.WHITE.index,font2);
			CellStyle cellRed = getCellStyle(style3, HSSFColor.RED.index,font2);
			// 创建Excel的工作sheet,对应到一个excel文档的tab   
			Sheet sheet = wb.getSheet("人员名单");
			
			sheet.setColumnWidth(0, 10000);
			sheet.setColumnWidth(1, 10000);
			sheet.setColumnWidth(2, 10000);
			sheet.setColumnWidth(3, 10000);
			sheet.setColumnWidth(4, 10000);
			
			int totalColumn = 4;
			
			//第一行
			Row row0 = sheet.createRow(0);
			for(int i = 0; i <= totalColumn; i ++){
				Cell cell = row0.createCell(i);   
				cell.setCellStyle(titleStyle);
			}
			row0.setHeight((short) 800);
			createCell(wb,row0,0,title,titleStyle);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, totalColumn));
			
			//第二行
			Row row1 = sheet.createRow(1);
			for(int i = 0; i <= totalColumn; i ++){
				Cell cell = row1.createCell(i);   
				cell.setCellStyle(cellStyle);
			}
			
			row1.setHeight((short) 500);
			createCell(wb,row1,0,"序号",cellStyle);
			createCell(wb,row1,1,"姓名",cellStyle);
			createCell(wb,row1,2,"编号",cellStyle);
			createCell(wb,row1,3,"电话",cellStyle);
			createCell(wb,row1,4,"地址",cellStyle);
			
			int count = 3;
			for(int i = 0; i < list.size(); i ++){
				Row row = sheet.createRow((i + count));
				for(int k = 0; k <= totalColumn; k ++){
					Cell cell = row.createCell(k);   
					cell.setCellStyle(cellStyle);
				}
				row.setHeight((short) 400);
				ExportFileVo vo = list.get(i);
				
				if(vo != null){
					createCell(wb,row,0,vo.getId().toString(),cellStyle);
					createCell(wb,row,1,vo.getName(),cellStyle);
					createCell(wb,row,2,vo.getCode(),cellStyle);
					createCell(wb,row,3,vo.getPhone(),cellStyle);
					createCell(wb,row,4,vo.getAddress(),cellStyle);
				}else{
					createCell(wb,row,0,"",cellStyle);
					createCell(wb,row,1,"",cellStyle);
					createCell(wb,row,2,"",cellStyle);
					createCell(wb,row,3,"",cellStyle);
					createCell(wb,row,4,"",cellStyle);
				}
			}
			
			
			wb.write(out);
			out.flush();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				input.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
}
	
	/**
	 * 导入excel文件
	 * @param uploadFile
	 * @return
	 */
	public List<ExportFileVo> importItemExcelToList(File uploadFile){
		List<ExportFileVo> list = new ArrayList<ExportFileVo>();
		InputStream input = null;
		try {
			input = new BufferedInputStream(new FileInputStream(uploadFile));
			XSSFWorkbook wb = new XSSFWorkbook(input);
			/*XSSFWorkbook workbook = new XSSFWorkbook(input);
			SXSSFWorkbook wb = new SXSSFWorkbook(workbook, 500);*/
			
			//循环工作表Sheet 
			for(int i = 0;i < wb.getNumberOfSheets(); i ++){
				Sheet sheet = wb.getSheetAt(i); 
				//循环行Row ,从第三行开始
				for (int rowNum = 2; rowNum <= sheet.getLastRowNum(); rowNum ++) {
					
					ExportFileVo vo = new ExportFileVo();
					//工作表的行
					Row row = sheet.getRow(rowNum);
					//循环行的单元格
					
					for(int j = 0; j < 5; j ++){	
						 Cell cell = row.getCell(j);
						 if(cell != null){

							switch (j) {
							case 0:
								String id = getStringCellValue(cell);
								vo.setId(Long.parseLong(id));
								break;
							case 1:
								String name = getStringCellValue(cell);
								vo.setName(name);
								break;
							case 2:
								String code = getStringCellValue(cell);
								vo.setCode(code);
								break;
							case 3:
								String phone = getStringCellValue(cell);
								vo.setPhone(phone);
								break;
							case 4:
								String address = getStringCellValue(cell);
								vo.setAddress(address);
								break;
							default:
								break;
							}
							 
                		 }
					 }
					list.add(vo);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				if(input != null){
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	
	
	
	/**
	* @Description: TODO title样式
	* @param @param style
	* @param @param font
	* @param @return    
	* @return HSSFCellStyle
	* @date 2016-1-18 下午02:00:43 
	* @author wangp 
	*/ 
	private static CellStyle getTitleStyle(CellStyle style,Font font){
		font.setFontName("微软雅黑");
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		font.setFontHeightInPoints((short) 20);
		font.setColor(HSSFColor.BLACK.index);
		style.setFont(font);// 设置字体
		style.setAlignment(CellStyle.ALIGN_CENTER);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// 设置边框   
		style.setBorderBottom(CellStyle.BORDER_THIN); 
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		return style;
	}
	
	
	/**
	* @Description: TODO cell 样式
	* @param @param style
	* @param @param colorIndex
	* @param @param font
	* @param @return    
	* @return HSSFCellStyle
	* @date 2016-6-13 下午05:06:48 
	* @author wangp 
	*/ 
	public static CellStyle getCellStyle(CellStyle style,short colorIndex,Font font){
		font.setFontName("微软雅黑");   
		font.setBoldweight((short) 80);   
		font.setFontHeight((short) 240);   
		style.setAlignment(CellStyle.ALIGN_CENTER);   
		style.setFillForegroundColor(colorIndex);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);   
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
		// 设置边框   
		style.setBorderBottom(CellStyle.BORDER_THIN); 
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		style.setFont(font);// 设置字体   
		return style;
	}
	
	/**
	* @Description: TODO添加单元格并设置文本和样式
	* @param @param wb
	* @param @param header
	* @param @param column
	* @param @param text
	* @param @param style    
	* @return void
	* @date 2016-6-13 下午04:51:07 
	* @author wangp 
	*/ 
	public static void createCell(SXSSFWorkbook wb, Row header, int column, String text,CellStyle style) {
        Cell cell=header.createCell(column);  
        cell.setCellValue(text);  
        cell.setCellStyle(style);
    }
	
	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell Excel单元格
	 * @return String 单元格数据内容
	 */
	private static String getStringCellValue(Cell cell) {
		String strCell = "";
		if(cell != null){
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				double num = cell.getNumericCellValue();
				strCell = String.valueOf(df.format(num));
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(cell.getBooleanCellValue());
				break;
			default:
				strCell = "";
				break;
			}
		}
		return strCell.trim();
	}

	private CellStyle getDataStyle(Workbook wb){
		// 创建单元格样式   
		CellStyle style = wb.createCellStyle();   
		style.setAlignment(CellStyle.ALIGN_CENTER);   
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);   
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
		// 设置边框   
		style.setBorderBottom(CellStyle.BORDER_THIN); 
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);
		return style;
	}
	
	private CellStyle getDateStyle(Workbook wb){
		// 创建单元格样式   
		CellStyle style = wb.createCellStyle();   
		style.setAlignment(CellStyle.ALIGN_CENTER);   
		style.setFillForegroundColor(HSSFColor.WHITE.index);
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);   
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);   
		// 设置边框   
		style.setBorderBottom(CellStyle.BORDER_THIN); 
		style.setBorderLeft(CellStyle.BORDER_THIN);
		style.setBorderTop(CellStyle.BORDER_THIN);
		style.setBorderRight(CellStyle.BORDER_THIN);

		DataFormat format= wb.createDataFormat();  
		style.setDataFormat(format.getFormat("yyyy-m-d h:mm:ss"));

		return style;
	}

	/**
	 * 验证必填
	 */
	private static boolean validRequire(String str){
		boolean flag=false;

		if(str !=null && str.trim().length()>0){
			flag=true;
		}

		return flag;
	}
	/**
	 * 验证长度
	 */
	private static boolean validLength(String str,int length){
		boolean flag=false;

		if(str.length()<=length){
			flag=true;
		}

		return flag;
	}

	/**
	 * 验证物料状态
	 */
	private static boolean validBoolean(String str){
		boolean flag=false;

		if(str.equals("Y") || str.equals("N")){
			flag=true;
		}

		return flag;
	}
	/**
	 * 验证物料状态
	 */
	private static boolean validMaterialStatus(String str){
		boolean flag=false;

		if(str.equals("H") || str.equals("G") || str.equals("L")){
			flag=true;
		}

		return flag;
	}
	/**
	 * 验证数值
	 */
	private static boolean validDouble(String str){
		return str.matches("-?[0-9]+.?[0-9]*");
	}
	/**
	 * 验证日期 yyyy-MM-dd HH:mm:ss
	 */
	private static boolean validDate(String str){
		boolean flag=false;
		
		DateFormat df = new SimpleDateFormat(DateUtil.yyyy_MM_dd_HH_mm_ss);
		try {
			df.parse(str);
			flag=true;
		} catch (ParseException e) {
			flag=false;
		}
		
		return flag;
	}
}
