package com.example.administrator.wxgridviewpic;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Environment;

public class AllUtill {
	
	/**
     * 判断SD卡是否存在
     * 
     * @return
     */
    public static boolean sdCardIsExit() {
	return Environment.getExternalStorageState().equals(
		Environment.MEDIA_MOUNTED);
    }
	
	 /**
     * 获取SD卡路径
     * 
     * @return /sdcard/
     */
    public static String getSDCardPath() {
		if (sdCardIsExit()) {
		    return Environment.getExternalStorageDirectory().toString() + "/";
		}
		return null;
    }
    

    
    /**
     * 创建文件夹
     * 
     * @param dirPath
     */
    public static String creatDir2SDCard(String dirPath) {
		File file = new File(dirPath);
		if (!file.exists()) {//判断文件是否存在
		    file.mkdirs();
		}
		return dirPath;
    }
   
    /**
     * 检验字符串是否为空；包括：null，""," "。
     * @param 	str		检验字符串
     * @return	boolean	true为空字符串；false为非空字符串。
     */
    public static boolean isBlank(String str) {
    	int strLen;
    	if (str == null || (strLen = str.length()) == 0) {
    	    return true;
    	}
    	for (int i = 0; i < strLen; i++) {
    	    if ((Character.isWhitespace(str.charAt(i)) == false)) {
    		return false;
    	    }
    	}
    	return true;
    }
    
    /**
     * 创建文件
     * 
     * 如果是/sdcard/download/123.doc则只需传入filePath=download/123.doc
     * 
     * @param filePath
     *            文件路径
     * @return 创建文件的路径
     * @throws IOException
     */
    public static String creatFile2SDCard(String filePath) throws IOException {
	// 无论传入什么值 都是从根目录开始 即/sdcard/+filePath
	//创建文件路径包含的文件夹
	String filedir = creatDir2SDCard(getFileDir(filePath));
	String fileFinalPath = filedir+getFileName(filePath);
	File file = new File(fileFinalPath);
	if (!file.exists()) {
	    file.createNewFile();
	}
	return fileFinalPath;
    }
    
    /**
     * 获取文件目录路径
     * 
     * @param filePath
     * @return
     */
    private static String getFileDir(String filePath) {
	if(filePath.startsWith(getSDCardPath())){
	    return filePath.replace(getFileName(filePath),"");
	}
	return getSDCardPath()+filePath.replace(getFileName(filePath),"");
    }
    
    /**
     * 获取文件名
     * 
     * @param filePath
     * @return
     */
    private static String getFileName(String filePath) {
	int index = 0;
	String tempName = "";
	if ((index = filePath.lastIndexOf("/")) != -1) {
	    // 如果有后缀名才
		tempName = filePath.substring(index + 1);
	}
	return tempName.contains(".")?tempName:"";
    }
    /** 
     * 用于动态时间格式化
     * 将日期时间格式为 今天  昨天  前天
     * @param time
     * @return 
     */
    public static String getFormatTime(String time){
    	String reTime = "";
      SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar= Calendar.getInstance();
        calendar.roll(Calendar.DAY_OF_YEAR,-2);
        String beforeTwo = df.format(calendar.getTime());
        
        calendar.roll(Calendar.DAY_OF_YEAR,1);

        String yesterDay = df.format(calendar.getTime());
        calendar= Calendar.getInstance();
        String toDay = df.format(calendar.getTime());
        
        //取年份
        calendar= Calendar.getInstance();
        String  strYear=String.valueOf(calendar.get(Calendar.YEAR));//获取当前年时间并转为字符串型
        if(time!=null&&time.length()>16){
    	 reTime=time.substring(0, 16);
    	String pubTime = time.substring(0, 10);//取天数
    	String yearTime = time.substring(0, 4);//取年份
    	
    	if(toDay.equals(pubTime)){
    		reTime="今天  "+time.substring(11, 16);
    	}else if(yesterDay.equals(pubTime)){
    		reTime="昨天  "+time.substring(11, 16);
    	}else if(beforeTwo.equals(pubTime)){
    		reTime="前天  "+time.substring(11, 16);
    	}else if(strYear.equals(yearTime)){
    		reTime=time.substring(0, 11);//前天以上的只显示天
    	}
        }
		return reTime;
    }
    
   
}
