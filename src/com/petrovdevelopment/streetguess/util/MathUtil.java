package com.petrovdevelopment.streetguess.util;

public class MathUtil {

	public static Double generateRandomFloatInRange(double low, double high) {
		double percentage = Math.random();
		double result = low + (high - low) * percentage;
		return result;
	}

	// to test the method's uniformity:
	// public static void testGenerateRandomFloatInRange() {
	// int[] counts = new int[10];// from 0 to 9
	//
	// for (int i = 0; i < 1000; i++) {
	// double randomNumber = MathUtil.generateRandomFloatInRange(-48, 10);
	// int digit = (int) randomNumber;
	// counts[digit]++;
	// }
	//
	// for (int i = 0; i < 10; i++) { // the counts should be relatively equal
	// U.log(new Object(), i + " count: " + counts[i]);
	// }
	// }

}
