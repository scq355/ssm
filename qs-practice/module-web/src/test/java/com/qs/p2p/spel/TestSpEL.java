package com.qs.p2p.spel;

import com.qs.p2p.model.User;
import org.junit.Test;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

/**
 * Created by scq on 2018-03-22 11:52:11
 */
public class TestSpEL {
	@Test
	public void spELTest1() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello World'");
		String message = (String) exp.getValue();
		System.out.println(message);
		exp = parser.parseExpression("'Hello World'.concat('!')");
		message = (String) exp.getValue();
		System.out.println(message);
		exp = parser.parseExpression("'Hello World'.bytes");
		byte[] bytes = (byte[]) exp.getValue();
		System.out.println(bytes);
		exp = parser.parseExpression("'Hello World'.bytes.length");
		Integer length = (Integer) exp.getValue();
		System.out.println(length);
		exp = parser.parseExpression("new String('hello world').toUpperCase()");
		message = exp.getValue(String.class);
		System.out.println(message);

		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(1856, 7, 9);
		User user = new User(123, "scq", calendar.getTime());

		exp = parser.parseExpression("userName");
		EvaluationContext context = new StandardEvaluationContext(user);
		String userName = (String) exp.getValue(context);
		System.out.println(userName);

		String userName_1 = (String) exp.getValue(user);
		System.out.println(userName_1);

		exp = parser.parseExpression("userName == 'scq'");
		boolean result = exp.getValue(context, Boolean.class);
		System.out.println(result);
	}

	@Test
	public void spELTest2() {
		ExpressionParser parser = new SpelExpressionParser();
		String helloWorld = (String) parser.parseExpression("'Hello World'").getValue();
		double avogadrosNumber = (double) parser.parseExpression("6.0221415E+23").getValue();
		System.out.println(helloWorld + "  " + avogadrosNumber);

		int maxValue = (int) parser.parseExpression("0x7FFFFFFF").getValue();
		boolean trueValue = (boolean) parser.parseExpression("true").getValue();
		Object nullValue = parser.parseExpression("null").getValue();
		System.out.println(maxValue + "  " + trueValue + "  " + nullValue);

		//TODO Properties, Arrays, Lists, Maps, Indexers
		EvaluationContext context = new StandardEvaluationContext();
		List numbers = (List) parser.parseExpression("{1,2,3,4,5}").getValue(context);
		List listOfLists = (List) parser.parseExpression("{{'a','b'},{'x','y'}}").getValue(context);
		Map inventorInfo = (Map) parser.parseExpression("{name:'Nikola',dob:'10-July-1856'}").getValue(context);
		Map mapOfMaps = (Map) parser.parseExpression("{name:{first:'Nikola',last:'Tesla'},dob:{day:10,month:'July',year:1856}}").getValue(context);
		System.out.println(numbers.toString() + "  " + listOfLists.toString() + " " + inventorInfo.toString() + "  " + mapOfMaps.toString());

		int[] numbers1 = (int[]) parser.parseExpression("new int[4]").getValue(context);
		int[] numbers2 = (int[]) parser.parseExpression("new int[]{1,2,3}").getValue(context);
		int[][] numbers3 = (int[][]) parser.parseExpression("new int[4][5]").getValue(context);

		System.out.println(numbers1.toString() + " " + numbers2.toString() + " " + numbers3.toString());


		String c = parser.parseExpression("'abc'.substring(2, 3)").getValue(String.class);
		boolean trueValue1 = parser.parseExpression("2 == 2").getValue(Boolean.class);
		boolean falseValue = parser.parseExpression("2 < -5.0").getValue(Boolean.class);
		boolean trueValue2 = parser.parseExpression("'black' < 'block'").getValue(Boolean.class);
		System.out.println(c + " " + trueValue1 + "  " + falseValue + "  " + trueValue2);

		boolean falseValue3 = parser.parseExpression("'xyz' instanceof T(Integer)").getValue(Boolean.class);

		System.out.println(falseValue3);

		//TODO Logical operators

		//Mathematical operators
		// Addition
		int two = parser.parseExpression("1 + 1").getValue(Integer.class); // 2
		String testString = parser.parseExpression("'test' + ' ' + 'string'").getValue(String.class); // 'test string'

		// Subtraction
		int four = parser.parseExpression("1 - -3").getValue(Integer.class); // 4
		double d = parser.parseExpression("1000.00 - 1e4").getValue(Double.class); // -9000

		// Multiplication
		int six = parser.parseExpression("-2 * -3").getValue(Integer.class); // 6
		double twentyFour = parser.parseExpression("2.0 * 3e0 * 4").getValue(Double.class); // 24.0

		// Division
		int minusTwo = parser.parseExpression("6 / -3").getValue(Integer.class); // -2
		double one = parser.parseExpression("8.0 / 4e0 / 2").getValue(Double.class); // 1.0

		// Modulus
		int three = parser.parseExpression("7 % 4").getValue(Integer.class); // 3
		int oneModulus = parser.parseExpression("8 / 5 % 2").getValue(Integer.class); // 1

		// Operator precedence
		int minusTwentyOne = parser.parseExpression("1+2-3*8").getValue(Integer.class); // -21

		System.out.println(two + "  " + testString + "  " + four + "  " + d + "  " + six + " " + twentyFour + " " +
				minusTwo + " " + one + "  " + three + "  " + oneModulus + "  " + minusTwentyOne);

	}




}
