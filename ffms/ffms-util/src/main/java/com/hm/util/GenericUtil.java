/**
 * 
 */
package com.hm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kiran
 *
 */
public class GenericUtil {
	
	public static String convertDateToStringFromate(Date date) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		
		//to convert Date to String, use format method of SimpleDateFormat class.
		String strDate = dateFormat.format(date);
		
		return strDate;
	}

}
