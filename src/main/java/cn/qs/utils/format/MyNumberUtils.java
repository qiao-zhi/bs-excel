package cn.qs.utils.format;

import java.math.RoundingMode;
import java.text.NumberFormat;

import org.apache.commons.lang3.math.NumberUtils;

public class MyNumberUtils {

	public static String toFixedDecimal(String value, int scale) {
		return toFixedDecimal(NumberUtils.createNumber(value), scale);
	}

	public static String toFixedDecimal(Number value, int scale) {
		NumberFormat numberFormat = NumberFormat.getInstance();
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
		numberFormat.setMaximumFractionDigits(scale);

		return numberFormat.format(value);
	}

	public static String toFixedDecimalWithPercent(String value, int scale) {
		return toFixedDecimalWithPercent(NumberUtils.createNumber(value), scale);
	}

	public static String toFixedDecimalWithPercent(Number value, int scale) {
		NumberFormat numberFormat = NumberFormat.getPercentInstance();
		numberFormat.setRoundingMode(RoundingMode.HALF_UP);
		numberFormat.setMaximumFractionDigits(scale);

		return numberFormat.format(value);
	}

	public static void main(String[] args) {
		System.out.println(toFixedDecimalWithPercent("1.525445544", 2));
	}

}
