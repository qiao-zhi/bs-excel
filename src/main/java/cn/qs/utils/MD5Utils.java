package cn.qs.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class MD5Utils {

	private MD5Utils() {
		// ignore
	}

	public static String md5(String source) {
		return md5(source, "");
	}

	/**
	 * md5加密
	 * 
	 * @param source
	 *            原串
	 * @param salt
	 *            需要加的盐
	 * @return
	 */
	public static String md5(String source, String salt) {
		if (StringUtils.isBlank(source)) {
			throw new IllegalArgumentException();
		}

		if (StringUtils.isNotBlank(salt)) {
			source += salt;
		}

		return DigestUtils.md5Hex(source);
	}

}
