/**
 * 
 */
package com.hm.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
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
	
	public static String DATE_FORMATE = "dd/MM/yyyy HH:mm:ss" ;
	
	public static String convertDateToStringFromate(Date date) {
		
		DateFormat dateFormat = new SimpleDateFormat(DATE_FORMATE);
		
		//to convert Date to String, use format method of SimpleDateFormat class.
		String strDate = dateFormat.format(date);
		
		return strDate;
	}
	
	public static Date convertStringToDateFromate(String date) {
		
		try {

			Date convertedDate = new SimpleDateFormat(DATE_FORMATE).parse(date);
			
			return convertedDate;
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
	
	public static String generateTicketNo()
	{
		return String.valueOf(System.currentTimeMillis());
	}

}
