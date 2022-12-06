package com.example.cointrading.utils;

import android.content.Context;

import com.example.cointrading.global.AppConstants;

import java.util.Optional;

/**
 * 캐쉬 유틸
 */
public class CashUtil {

    public static int getCash(Context context) {

        if (context == null) {
            return 0;
        }

        return SharedPreferencesUtil.getIntData(context, AppConstants.SHARED_KEY_MY_CASH);
    }

    /**
     * 캐쉬 저장
     *
     * @param cash 저장할 금액
     */
    public static void saveCash(int cash, Context context) {
        if (context == null) {
            return;
        }

        SharedPreferencesUtil.saveIntData(context, AppConstants.SHARED_KEY_MY_CASH, cash);
    }

    /**
     * 캐시 차감
     *
     * @param deductionCash 차감할 캐쉬
     */
    public static boolean deductionCash(int deductionCash, Context context) {
        if (context == null) {
            return false;
        }

        int nowCash = getCash(context);

        if (deductionCash > nowCash) {
            return false;
        }

        saveCash((nowCash - deductionCash), context);

        return true;
    }

    /**
     * 캐쉬 충전
     *
     * @param chargeCash 충전할 캐쉬
     */
    public static int chargeCash(int chargeCash, Context context) {
        if (context == null) {
            return 0;
        }

        final int nowCash = getCash(context);
        final int newCash = nowCash + chargeCash;

        saveCash(newCash, context);

        return newCash;
    }

}
