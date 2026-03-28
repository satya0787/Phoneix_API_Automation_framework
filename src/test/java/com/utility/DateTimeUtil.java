package com.utility;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {
	
	private DateTimeUtil() {
		// Private constructor to prevent instantiation
	}

	public static String getTimewithdaysAgo(int days) {
		return Instant.now().minus(days, ChronoUnit.DAYS).toString();
	}

}
