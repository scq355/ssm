package com.qs.p2p.java8.date;

import org.apache.ibatis.type.YearTypeHandler;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Set;

/**
 * Created by scq on 2018-07-26 15:07:20
 */
public class TestLocalDateTime {

	//1. LocalDate、LocalTime、LocalDateTime
	@Test
	public void test1() {
		LocalDateTime localDateTime = LocalDateTime.now();
		System.out.println(localDateTime);

		LocalDateTime localDateTime1 = LocalDateTime.of(2016, 11, 21, 10, 10, 10);
		System.out.println(localDateTime1);

		LocalDateTime ldt3 = localDateTime1.plusYears(20);
		System.out.println(ldt3);

		LocalDateTime ldt4 = localDateTime1.minusMonths(2);
		System.out.println(ldt4);

		System.out.println(localDateTime.getYear());
		System.out.println(localDateTime.getMonthValue());
		System.out.println(localDateTime.getDayOfMonth());
		System.out.println(localDateTime.getHour());
		System.out.println(localDateTime.getMinute());
		System.out.println(localDateTime.getSecond());
	}

	//2. Instant : 时间戳。 （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
	@Test
	public void test2() {
		Instant instant = Instant.now();
		System.out.println(instant);

		OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
		System.out.println(offsetDateTime);

		System.out.println(instant.getNano());

		Instant ins2 = Instant.ofEpochSecond(5);
		System.out.println(ins2);
	}

	//3.Duration : 用于计算两个“时间”间隔
	//Period : 用于计算两个“日期”间隔
	@Test
	public void test3() {
		Instant instant = Instant.now();
		System.out.println("--------------------");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}

		Instant instant1 = Instant.now();
		System.out.println("所耗费时间为：" + Duration.between(instant, instant1));

		System.out.println("----------------------------------");

		LocalDate ld1 = LocalDate.now();
		LocalDate ld2 = LocalDate.of(2011, 1, 1);

		Period period = Period.between(ld2, ld1);
		System.out.println(period.getYears());
		System.out.println(period.getMonths());
		System.out.println(period.getDays());
	}


	//4. TemporalAdjuster : 时间校正器
	@Test
	public void test4() {
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println(ldt);

		LocalDateTime ldt2 = ldt.withDayOfMonth(10);
		System.out.println(ldt2);

		LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
		System.out.println(ldt3);

		//自定义：下一个工作日
		LocalDateTime ldt5 = ldt.with((l) -> {
			LocalDateTime ldt4 = (LocalDateTime) l;

			DayOfWeek dow = ldt4.getDayOfWeek();

			if(dow.equals(DayOfWeek.FRIDAY)){
				return ldt4.plusDays(3);
			}else if(dow.equals(DayOfWeek.SATURDAY)){
				return ldt4.plusDays(2);
			}else{
				return ldt4.plusDays(1);
			}
		});

		System.out.println(ldt5);
	}

	//5. DateTimeFormatter : 解析和格式化日期或时间
	@Test
	public void test5() {
//		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

		LocalDateTime ldt = LocalDateTime.now();
		String strDate = ldt.format(dtf);

		System.out.println(strDate);

		LocalDateTime newLdt = ldt.parse(strDate, dtf);
		System.out.println(newLdt);
	}


	//6.ZonedDate、ZonedTime、ZonedDateTime ： 带时区的时间或日期
	@Test
	public void test7(){
		LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
		System.out.println(ldt);

		ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
		System.out.println(zdt);
	}

	@Test
	public void test6(){
		Set<String> set = ZoneId.getAvailableZoneIds();
		set.forEach(System.out::println);
	}


}
