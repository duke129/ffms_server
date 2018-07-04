/**
 * 
 */
package com.hm.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hm.util.model.AddressVo;

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
	
	public static AddressVo addressParserToObject(String address)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		AddressVo addressVo;
		try {
			
			addressVo = mapper.readValue(address, AddressVo.class);
			return addressVo;
			
		} catch (JsonParseException e) {
			
			e.printStackTrace();
			return new AddressVo();
			
		} catch (JsonMappingException e) {
			
			e.printStackTrace();
			return new AddressVo();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			return new AddressVo();
		}
		
		
	}
	
	public static String addressParserObjectToString(AddressVo addressVo)
	{
		ObjectMapper mapper = new ObjectMapper();
		
		String objectToString;
		try {
			
			objectToString = mapper.writeValueAsString(addressVo);
			return objectToString;
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

}
