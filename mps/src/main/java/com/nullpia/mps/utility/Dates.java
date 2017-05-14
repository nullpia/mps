package com.nullpia.mps.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class Dates {

	/**
	 * 
	 * @param value
	 * @param format
	 * @return
	 */
	public static String format(String value, String format) {
		String l_value = Strings.empty(value);
		String l_return= "";
		try {
			SimpleDateFormat source = null;
			if( l_value.length() == 8 ) {
				source = new SimpleDateFormat("yyyyMMdd");
			} else {
				source = new SimpleDateFormat("yyyyMMddHHmmss");
			}
			java.util.Date sourceDate = source.parse(l_value);
			
			SimpleDateFormat target = new SimpleDateFormat(format);
			l_return = target.format(sourceDate);
		} catch(ParseException e) {
		}
		return l_return;
	}
}
