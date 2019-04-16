package com.airlab.musicplayer.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

public class RandomUtils {
	
	/**
	 * 返回一个指定范围内的随机数
	 * @param minValue 返回的随机数的下界
	 * @param maxValue 返回的随机数的上界
	 * @return result
	 */
	public static int next(int minValue, int maxValue){
		Random r = new Random();
		int tempValue = maxValue - minValue;
		int result = 0;
		if (tempValue > 0) {
			result = r.nextInt(tempValue) + minValue;
		}
		return result;
	}
	
	public static int secureRandom(int minValue, int maxValue){
		SecureRandom secureRandom = new SecureRandom();
		byte[] seed = UUID.randomUUID().toString().getBytes();
		secureRandom.setSeed(seed);
		int random = secureRandom.nextInt(maxValue)%(maxValue-minValue+1) + minValue;
		return random;
	}
}

