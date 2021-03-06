package com.herokuapp.cristcc2.Uteis;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convercoes {

	public String convertDateUStoDataBR(String dataUS) {
		try {
			DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
			Date date = formatUS.parse(dataUS);
			DateFormat formatBR = new SimpleDateFormat("dd/mm/yyyy");
			String dateBR = formatBR.format(date);
			return dateBR;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataUS;
	}
	
	public String convertDateBRtoDataUS(String dataBR) {
		try {
			DateFormat formatUS = new SimpleDateFormat("dd/mm/yyyy");
			Date date = formatUS.parse(dataBR);
			DateFormat formatBR = new SimpleDateFormat("yyyy-mm-dd");
			String dateUS = formatBR.format(date);
			return dateUS;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataBR;
	}
	
	public boolean comparaDatas(String data1, String data2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
			Date date3 = sdf.parse(data1);
			Date date4 = sdf.parse(data2);
			
			if (date3.compareTo(date4) >= 0) {
				return true;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		return false;
	}
}
