package com.qs.p2p.calendar;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by scq on 2018-02-01 11:02:02
 */
public class CalendarTest {

	@Test
	public void testDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		String firstDayOfMonth = dateFormat.format(calendar.getTime());
		System.out.println(firstDayOfMonth);
		calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		String lastDayOfMonth = dateFormat.format(calendar.getTime());
		System.out.println(lastDayOfMonth);
	}

	@Test
	public void testDateFormat() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		String dateStr = dateFormat.format(date);
		System.out.println(dateStr);
	}


	@Test
	public void testDateII() {
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date toDay = format.parse(format.format(new Date()));
			System.out.println(toDay);
			System.out.println(toDay.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
