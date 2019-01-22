package com.sangame.utils;

import java.util.Random;
import java.util.UUID;

/**
 * Created by scq on 2018-01-09 10:19:41
 */
public class RandomUtils {
	public static String getRandomUUID() {
		return UUID.randomUUID().toString();
	}

	public static String getRandomUUID(boolean dash) {
		return dash ? getRandomUUID() : getRandomUUID().replace("-", "");
	}

	public static String getRandomString(String acceptChars, int length) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; ++i) {
			sb.append(getRandomCharacter(acceptChars));
		}
		return sb.toString();
	}

	public static char getRandomCharacter(String acceptChars) {
		int index = new Random().nextInt(acceptChars.length());
		return acceptChars.charAt(index);
	}

	private RandomUtils() {
	}
}
