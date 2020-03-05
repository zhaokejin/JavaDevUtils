package cn.cicoding.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.cicoding.constant.Contant;
import cn.cicoding.regex.RegUtil;
import cn.cicoding.valid.CheckValidUtil;
import org.apache.commons.lang.SystemUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	
	/**
	 * @Fields seq : 循环从0开始
	 * @Fields ROTATION : 循环到99999
	 * @Fields date : 时间
	 */ 
	private static int seq = 0;  
	private static final int ROTATION = 99999;
	private static Date date = new Date();
	/**
	 * 添加唯一标示，时间戳
	 */
	public static String onlyOneStr() {
		synchronized(date){
			if (seq > ROTATION){
				seq = 0;
			}
			date.setTime(System.currentTimeMillis());
			String str = String.format("%1$tY%1$tm%1$td%1$tk%1$tM%1$tS%2$05d",
					date, seq++);
			return str;
		}
	}
	/**
	 * @description:获取文件大小
	 * @param file
	 * @return
	 * @time:2018年9月4日 上午10:25:55
	 * @author:WangPan
	 */
	public static int getSize(File file) {
		if (file.exists() && file.isFile()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				return fis.available() / 1024;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (fis != null)
						fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return 0;
	}
	/**
	 * @description:文件删除
	 * @param path
	 * @return
	 * @time:2018年9月4日 上午10:26:00
	 * @author:WangPan
	 */
	public static boolean deleteFile(String path){
		boolean flag=false;
		if(path != null && !path.equals("")){
			try {
				// 文件真实路径
				File file = new File(Contant.IMAGE_PATH + path);
				if (file.isFile() && file.exists()) {
					// 原文件删除
					flag=file.delete();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

	/**
	 * @description:文件写入到磁盘
	 * @param rootPath
	 * @param uploadFileName
	 * @param uploadFile
	 * @return
	 * @time:2018年9月4日 上午10:26:08
	 * @author:WangPan
	 */
	public static String uploadFile(String rootPath,String uploadFileName,MultipartFile uploadFile){
		String fileName=null;

		if(uploadFileName!=null && uploadFileName.trim().length()>0){
			if(uploadFile!=null){
				//获取文件后缀名
				String url = Contant.IMAGE_PATH;
				String fileEndName =uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
				String onlyStr = onlyOneStr();
				String filePath = url + rootPath+"/"+onlyStr+"."+fileEndName;
				File direct = new File(url + rootPath);
				if(!direct.exists()){
					direct.mkdirs();
				}
				File file = new File(filePath);
				try {
					uploadFile.transferTo(file);
					fileName=rootPath + "/" + onlyStr + "." + fileEndName;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return fileName;
	}
	
	/**
	 * @description:获取文件的行数
	 * @param file
	 * @return
	 * @time:2018年9月3日 下午5:30:10
	 * @user:WangPan
	 */
	public final static int countLines(File file) {
        try(LineNumberReader rf = new LineNumberReader(new FileReader(file))){
            long fileLength = file.length();
            rf.skip(fileLength);
            return rf.getLineNumber();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
	
    /**
     * @description:在文件末尾追加一行
     * @param file
     * @param str
     * @return
     * @time:2018年9月3日 下午5:31:04
     * @user:WangPan
     */
    public final static boolean appendLine(File file, String str) {
        try (
                RandomAccessFile randomFile = new RandomAccessFile(file, "rw")
        ) {
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.writeBytes(SystemUtils.FILE_SEPARATOR + str);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * @description:获取文件后缀名
     * @param file
     * @return
     * @time:2018年9月3日 下午5:31:46
     * @user:WangPan
     */
    public final static String suffix(File file){
        String fileName=file.getName();
        return fileName.substring(fileName.indexOf(".")+1);
    }
    
    /**
     * @description:正则匹配文件
     * @param dirPath
     * @param reg
     * @return
     * @time:2018年9月4日 上午10:26:42
     * @author:WangPan
     */
    public final static List<File> searchFileReg(File dirPath, String reg) {
        List<File> list = new ArrayList<>();
        File[] files = dirPath.listFiles();
        if (CheckValidUtil.valid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, reg));
                } else {
                    String Name = file.getName();
                    if (RegUtil.isMatche(Name, reg)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }
    
    /**
     * @description:搜索文件
     * @param dirPath
     * @param fileName
     * @return
     * @time:2018年9月4日 上午10:27:03
     * @author:WangPan
     */
    public final static List<File> searchFile(File dirPath, String fileName) {
        List<File> list = new ArrayList<>();
        File[] files = dirPath.listFiles();
        if (CheckValidUtil.valid(files)) {
            for (File file : files) {
                if (file.isDirectory()) {
                    list.addAll(searchFile(file, fileName));
                } else {
                    String Name = file.getName();
                    if (Name.equals(fileName)) {
                        list.add(file);
                    }
                }
            }
        }
        return list;
    }
}
