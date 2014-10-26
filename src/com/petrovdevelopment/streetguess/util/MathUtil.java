package com.petrovdevelopment.streetguess.util;

import com.google.android.gms.maps.model.LatLng;

public class MathUtil {
	// latitude is in [-85, 85], but for street view purposes [-48, 71];
	// longitude is in [-180, 180]. It would be nice to slice out all the ocean areas, but let's see if it is needed

	public static final double MIN_LAT = -48.0d;
	public static final double MAX_LAT = 71.0d;
	public static final double MIN_LNG = -180.0d;
	public static final double MAX_LNG = 180.0d;

	/**
	 * Generate a random double number within a given range
	 * @param low
	 * @param high
	 * @return
	 */
	public static Double generateRandomDoubleInRange(double low, double high) {
		double percentage = Math.random();
		double result = low + (high - low) * percentage;
		return result;
	}

	/**
	 * Generate a random latitude-longitude pair within the given min and max values
	 * @param lowLat
	 * @param highLat
	 * @param lowLng
	 * @param highLng
	 * @return LatLng object for google maps
	 */
	public static LatLng generateRandomLatLng(double lowLat, double highLat, double lowLng, double highLng) {
		return new LatLng(generateRandomDoubleInRange(lowLat, highLat), generateRandomDoubleInRange(lowLng, highLng));
	}

	/**
	 * Generate a random latitude-longitude pair
	 * @return LatLng object for google maps
	 */
	public static LatLng generateRandomLatLng() {
		return generateRandomLatLng(MIN_LAT, MAX_LAT, MIN_LNG, MAX_LNG);
	}
	// TODO: move this in a test class. To test the method's uniformity:
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
