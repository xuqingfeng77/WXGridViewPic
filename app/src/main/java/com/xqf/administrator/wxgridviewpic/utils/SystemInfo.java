package com.xqf.administrator.wxgridviewpic.utils;

import android.content.Context;

/**
 * 此类用于获取手机系统相关信息
 *
 */
public class SystemInfo
{
	private Context context = null;
	private static SystemInfo systemInfo = null;
	
	public SystemInfo(Context context)
	{
		this.context = context;
	}
	
	/**
	 * 获取SystemInfo
	 */
	public static SystemInfo getInstance(Context context)
	{
		
		if(systemInfo == null)
		{
			systemInfo = new SystemInfo(context);
		}
		
		return systemInfo;
	}
	
	/**
	 * 获取屏幕信息
	 */
	protected WindowInfo getWindowInfo()
	{
		return new WindowInfo(context);
	}
}
