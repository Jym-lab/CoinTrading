package com.example.cointrading.utils;

import java.util.Random;

/**
 * 랜덤 유틸
 */
public class RandomUtil {
    /**
     * @param minNumber 최소 숫자
     * @param maxNumber 최대 숫자
     * @return 최소 숫자 ~ 최대 숫자 사이의 랜덤값 반환
     */
    public static int getRandomNumber(int minNumber, int maxNumber) {
        Random random = new Random();
        return random.nextInt(maxNumber - minNumber + 1) + minNumber;
    }
}
