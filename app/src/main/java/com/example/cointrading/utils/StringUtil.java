package com.example.cointrading.utils;

import java.text.DecimalFormat;

/**
 * 문자 관련 유틸
 */
public class StringUtil {

    /**
     * 숫자 콤마 찍어서 반환
     *
     * @param cash 변경할 금액
     */
    public static String convertComma(int cash) {
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        return formatter.format(cash);
    }

}
