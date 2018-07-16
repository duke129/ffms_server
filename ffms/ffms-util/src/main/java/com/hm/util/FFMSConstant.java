/**
 * 
 */
package com.hm.util;

import java.util.Arrays;
import java.util.List;

/**
 * @author kiran
 *
 */
public interface FFMSConstant {
	
	Integer ACTIVITY_COMPLETED = 101;
	Integer ACTIVITY_NOT_DONE = 102;
	
	Integer NEW_LEAD = 103;
	Integer IN_PROGRESS = 104;
	Integer REJECTED = 105;
	Integer COMPLETED = 106;
	
	Long SALES_REQUEST = 1l;
	Long SERVICE_REQUEST = 2l;
	
	List<Integer> ticketStatusList = Arrays.asList(new Integer[] { NEW_LEAD, IN_PROGRESS , REJECTED , COMPLETED});
	
	public interface ActivityConstant {
		
		Integer BASIC_INFO_UPDATE = 1;
		Integer DEMO = 2;
		Integer ORDER = 3;
	}

}
