package com.qs.p2p.date;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by scq on 2018-01-18 14:02:58
 */
public class DateTest {

	DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	static Date date = new Date();

	@Test
	public void testDate() throws ParseException {
		String dateStr = format.format(date);
		System.out.println(dateStr);
		Date dateTime = format.parse(dateStr);
		System.out.println(dateTime);
	}

	@Test
	public void testBefore() {
		Date now = new Date();
		try {
			Date time = format.parse("2017-01-01 00:00:00");
			Boolean res = now.after(time);
			System.out.println(res ? 1 : 0);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
}
