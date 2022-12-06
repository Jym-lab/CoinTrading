package com.example.cointrading.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.cointrading.global.AppConstants;

/**
 * SharedPreferencesUtil
 * 데이터 저장 util
 */
public class SharedPreferencesUtil {

    /**
     * string 데이터 저장
     *
     * @param context  context
     * @param key      저장할 키
     * @param saveData 저장할 데이터
     */
    public static void saveStringData(Context context, String key, String saveData) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppConstants.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, saveData);
        editor.apply();
    }

    /**
     * int 데이터 저장
     *
     * @param context  context
     * @param key      저장할 키
     * @param saveData 저장할 데이터
     */
    public static void saveIntData(Context context, String key, int saveData) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppConstants.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, saveData);
        editor.apply();
    }

    /**
     * int 데이터 저장
     *
     * @param context  context
     * @param key      저장할 키
     * @param saveData 저장할 데이터
     */
    public static void saveBooleanData(Context context, String key, boolean saveData) {
        SharedPreferences sharedPref = context.getSharedPreferences(AppConstants.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, saveData);
        editor.apply();
    }

    /**
     * string 데이터 불러오기
     *
     * @param context context
     * @param key     key 값
     */
    public static String getStringData(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }

    /**
     * string 데이터 불러오기
     *
     * @param context context
     * @param key     key 값
     */
    public static int getIntData(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, -1);
    }

    public static boolean getBooleanData(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.SHARED_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

}
