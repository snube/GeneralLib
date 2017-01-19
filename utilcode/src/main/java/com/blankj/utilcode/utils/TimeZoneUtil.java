package com.blankj.utilcode.utils;

import java.util.Date;
import java.util.TimeZone;

/**
 *  时区工具 
 *  @author snubee
 *  @email snubee96@gmail.com
 *  @time 2016/12/8 13:57
 *
**/
public class TimeZoneUtil {

	/**
	 * 判断用户的设备时区是否为东八区（中国） 2014年7月31日
	 * @return
	 */
	public static boolean isInEasternEightZones() {
		boolean defaultVaule = true;
		if (TimeZone.getDefault() == TimeZone.getTimeZone("GMT+08"))
			defaultVaule = true;
		else
			defaultVaule = false;
		return defaultVaule;
	}

	/**
	 * 根据不同时区，转换时间 2014年7月31日
	 * @param
	 * @return
	 */
	public static Date transformTime(Date date, TimeZone oldZone, TimeZone newZone) {
		Date finalDate = null;
		if (date != null) {
			int timeOffset = oldZone.getOffset(date.getTime())
					- newZone.getOffset(date.getTime());
			finalDate = new Date(date.getTime() - timeOffset);
		}
		return finalDate;
	}
}
