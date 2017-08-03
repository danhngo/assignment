package test.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class DateUtil {

	public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	
	public static List<String> getDaysBetweenDates(String startDateStr, String endDateStr)
	{
	    List<String> result = new ArrayList<String>();
		Date startDate = getDate(startDateStr);
	    Date endDate = getDate(endDateStr);

	    Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startDate);
	    while (calendar.getTime().before(endDate))
	    {
	        Date date = calendar.getTime();
	        result.add(toDateString(date));
	        calendar.add(Calendar.DATE, 1);
	    }
	    result.add(toDateString(endDate));
	    return result;
	}
	
	
	public static Date getDate(String date) {
        try {
            if (!"".equals(date)) {
                return new SimpleDateFormat(DEFAULT_DATE_FORMAT).parse(date);
            }
        } catch (Exception ex) {
            //Invalid
        }
        return null;
    }
	public static String toDateString(Date date) {
		 try {
			if (date != null) {
	    	    SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	    	    return sdf.format(date);
	    	} 
		 } catch (Exception ex) {
	            //Invalid
	     }
    	return null;
    }
	
	public static int validateDate(String startDateStr, String endDateStr) {
    	if ("".equals(startDateStr) || "".equals(endDateStr)) {
    		return -1;
    	} 
		Date startDate = getDate(startDateStr);
		Date endDate = getDate(endDateStr);
		if (startDate == null || endDate == null) {
			return -2;
		} 
		if (endDate.before(startDate)) {
			return -3;
		}
    	return 0;
    }
	
	public static String getEndDateOfDuration(String startDateStr, int duration) {
    	if ("".equals(startDateStr)) {
    		return null;
    	} 
		Date startDate = getDate(startDateStr);
		if (startDate == null) return null;
		Calendar calendar = new GregorianCalendar();
	    calendar.setTime(startDate);
        calendar.add(Calendar.DATE, duration - 1);
	    return toDateString(calendar.getTime());
    }
	
}
