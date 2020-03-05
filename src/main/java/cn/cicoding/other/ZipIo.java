package cn.cicoding.other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
/**
* @Description: 文件的压缩与解压缩
* @author WangPan
* @date 2018年9月4日
* @version V1.0
*/
public class ZipIo {
	private File zipFile;
	static final int BUFFERSIZE = 4096;
	public ZipIo(String pathname){
		zipFile = new File(pathname);
	}
	/**
	 * 文件压缩方法
	 * @param filelist <文件列表>
	 * @throws IOException
	 */
	public void compressFilelist(String[] filelist) throws IOException{
		ZipOutputStream zipOutputStream = null;
		
		zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
//			Set<String> staffsSet = new HashSet<>(Arrays.asList(filelist));
		for(String file : filelist){
			String basedir = "";
			//判断文件类型：目录or文件
			compressByType(new File(file),zipOutputStream,basedir);
		}
		zipOutputStream.close();
	
	}
	
	/**
	 * 根据不同文件类型选择不同方法
	 * @param srcfFile	<待压缩的文件名>
	 * @param zipOutputStream <输出流>
	 * @param basedir <文件在压缩文件中的路径>
	 * @throws IOException
	 */
	private void compressByType(File srcfFile, ZipOutputStream zipOutputStream, String basedir) throws IOException {
		
		if(srcfFile.isDirectory()){
			compressDirectory(srcfFile,zipOutputStream,basedir);
		}else {
			compressFile(srcfFile,zipOutputStream,basedir);
		}
	}
	
	/**
	 * 文件的压缩方式
	 * @param srcfFile <待压缩的文件名>
	 * @param zipOutputStream <输出流>
	 * @param basedir <文件在压缩文件中的路径>
	 * @throws IOException
	 */
	private void compressFile(File srcfFile, ZipOutputStream zipOutputStream, String basedir) throws IOException {
		
		if(!srcfFile.exists()){
			return;
		}
		ZipEntry zipEntry = null;
		zipEntry = new ZipEntry(basedir+srcfFile.getName());
		
		FileInputStream fis = new FileInputStream(srcfFile);
		zipOutputStream.putNextEntry(zipEntry);
		int temp;
		byte[] b = new byte[BUFFERSIZE];
		while((temp = fis.read(b))!=-1){
			zipOutputStream.write(b,0,temp);
		}
		fis.close();
	}
	/**
	 * 文件夹的压缩方式
	 * @param srcfFile
	 * @param zipOutputStream
	 * @param basedir
	 * @throws IOException
	 */
	private void compressDirectory(File srcfFile, ZipOutputStream zipOutputStream, String basedir) throws IOException {
		if(!srcfFile.exists()){
			return;
		}
		File[] files = srcfFile.listFiles();
		//若为空文件夹
		if(files.length==0)
			zipOutputStream.putNextEntry(new ZipEntry(basedir+srcfFile.getName()+"/"));
	    //非空文件夹继续递归，判断下一级目录类型
		for(File file : files){
			compressByType(file, zipOutputStream, basedir+srcfFile.getName()+File.separator);
		}
		
	}
	/**
	 * 解压缩文件方法
	 * @param zipPath <压缩文件目录>
	 * @param outputPath <解压目录>
	 * @throws IOException
	 */
	public static void unzipFiles(String zipPath, String outputPath) throws IOException{
		ZipInputStream zis = null;
		ZipEntry zipEntry = null;
		
		zis = new ZipInputStream(new FileInputStream(zipPath));
		while((zipEntry = zis.getNextEntry())!=null){
			//获取每个zipentry的路径，创建相应的文件夹
			File path = new File(outputPath + File.separator + zipEntry.getName());
			File parpath = new File(path.getParentFile().getPath());
			if(zipEntry.isDirectory()){
				path.mkdirs();
				continue;
			}else {
				parpath.mkdirs();
			}
			//将文件解压到相应文件夹下
			FileOutputStream fod = new FileOutputStream(outputPath + File.separator + zipEntry.getName());
			int temp;
			byte[] b = new byte[BUFFERSIZE];
			while((temp=zis.read(b))!=-1){
				fod.write(b,0,temp);
			}
			fod.close();
		}
		zis.close();
		
	}

}
