package ua.nure.korkh.SummaryTask4.functional;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GenerateNum {
	
	public static String getNumber(){
		Date date = new Date();
		SimpleDateFormat datecreate = new SimpleDateFormat("ddMMyyyy-hh:mm:ss");
        String number = datecreate.format(date);
        return number; 
        
	}
}
