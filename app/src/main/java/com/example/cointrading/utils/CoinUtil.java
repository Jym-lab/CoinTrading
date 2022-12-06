package com.example.cointrading.utils;

import android.content.Context;

import com.example.cointrading.global.AppConstants;
import com.example.cointrading.manager.CoinManager;
import com.example.cointrading.model.CoinBuyModel;
import com.example.cointrading.model.CoinDataModel;
import com.example.cointrading.model.CoinModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 코인 유틸
 * 코인 가공 처리하는 class
 */
public class CoinUtil {

    public static List<CoinDataModel> getCoinList() {
        return CoinManager.getInstance().getCoinDataList();
    }

    /**
     * 더미 코인 생성
     */
    public static void createCoinData() {
        final List<String> coinNameList = CoinManager.getInstance().getCoinNaneList();
        Map<Integer, CoinDataModel> dummyCoinMap = new HashMap<>();
        for (int i = 0; i < coinNameList.size(); i++) {

            CoinDataModel coinDataModel = new CoinDataModel.Builder()
                    .setId(i)
                    .setCoinName(coinNameList.get(i))
                    .setNowPrice(RandomUtil.getRandomNumber(1, 1000))
                    .setYesterdayPrice(RandomUtil.getRandomNumber(1, 1000))
                    .setTotalPrice(RandomUtil.getRandomNumber(1000, 10000))
                    .build();

            dummyCoinMap.put(i, coinDataModel);
        }

        CoinManager.getInstance().setCoinMap(dummyCoinMap);

    }

    public static void createDummyCoinData() {
        final List<String> coinNameList = CoinManager.getInstance().getCoinNaneList();
        for (int i = 0; i < coinNameList.size(); i++) {

            // 20일치 코인 더미 데이터 생성
            for (int j = 0; j < 20; j++) {

                final String name = coinNameList.get(i);
                final int low = RandomUtil.getRandomNumber(1, 500);

//                CoinModel coinModel = new CoinModel.Builder()
//                        .setId(i)
//                        .setName()
//                        .setHigh()
//                        .setLow()
//                        .setNow()
//                        .setTransactionAmount()
//                        .build();
            }

        }
    }

    /**
     * 특정 코인의 객체 반환
     *
     * @param id 코인 아이디
     */
    public static CoinDataModel getCoinDataModel(int id) {
        return CoinManager.getInstance().findCoinById(id);
    }

    /**
     * 전일대비 퍼센트 반환
     *
     * @param coinDataModel 코인 객체
     */
    public static float getDayBefore(CoinDataModel coinDataModel) {
        return getDayBefore(coinDataModel.getNowPrice(), coinDataModel.getYesterdayPrice());
    }

    /**
     * 전일대비 퍼센트 반환
     *
     * @param now       오늘자 금액
     * @param yesterday 어제 금액
     */
    public static float getDayBefore(float now, float yesterday) {
        float percent = ((now - yesterday) / yesterday * 100);
        return (float) Math.round(percent * 100) / 100;
    }

    public static List<CoinDataModel> getFilterCoinList(String filter) {

        // 공백이 넘어올경우 전체 리스트를 반환
        if (filter.length() == 0) {
            return CoinManager.getInstance().getCoinDataList();
        }

        // 검색 조건식에 해당하는 코인 리스트
        final List<CoinDataModel> filterList = new ArrayList<>();

        // 코인 전체 리스트
        final List<CoinDataModel> coinDataModelList = CoinManager.getInstance().getCoinDataList();

        for (CoinDataModel coinDataModel : coinDataModelList) {
            if (coinDataModel.getCoinName().contains(filter)) {
                filterList.add(coinDataModel);
            }
        }

        return filterList;
    }

    public static List<CoinBuyModel> getBuyCoinList(Context context) {
        Type type = new TypeToken<List<CoinBuyModel>>() {
        }.getType();
        String json = SharedPreferencesUtil.getStringData(context, AppConstants.SHARED_KEY_BUY_COIN_LIST_TEMP);
        return new Gson().fromJson(json, type);
    }

    /**
     * @return 구매한 코인들의 평가 금액
     */
    public static int getTotalBuyAvgPrice(Context context) {
        List<CoinBuyModel> coinBuyModelList = getBuyCoinList(context);
        if (coinBuyModelList == null || coinBuyModelList.size() == 0) {
            return 0;
        }

        float avgPrice = 0;
        for (CoinBuyModel coinBuyModel : coinBuyModelList) {
            int nowPrice = CoinManager.getInstance().findCoinById(coinBuyModel.getId()).getNowPrice();
            avgPrice += (nowPrice * coinBuyModel.getQuantity());
        }

        return (int) avgPrice;
    }

    public static int holdCoinQuantity(Context context, int coinId) {
        List<CoinBuyModel> coinBuyModelList = getBuyCoinList(context);
        if (coinBuyModelList == null || coinBuyModelList.size() == 0) {
            return 0;
        }

        for (CoinBuyModel coinBuyModel : coinBuyModelList) {
            if (coinBuyModel.getId() == coinId) {
                return (int) coinBuyModel.getQuantity();
            }
        }

        return 0;

    }

    public static void buyCoin(Context context, CoinBuyModel coinBuyModel) {
        List<CoinBuyModel> coinBuyModelList = getBuyCoinList(context);
        if (coinBuyModelList == null) {
            coinBuyModelList = new ArrayList<>();
        }

        coinBuyModelList.add(coinBuyModel);

        SharedPreferencesUtil.saveStringData(context, AppConstants.SHARED_KEY_BUY_COIN_LIST_TEMP, new Gson().toJson(coinBuyModelList));
    }

    public static void sellCoin(Context context, int coinId) {
        List<CoinBuyModel> coinBuyModelList = getBuyCoinList(context);
        if (coinBuyModelList == null || coinBuyModelList.size() == 0) {
            return;
        }

        int selectId = -1;
        for (int i = 0; i < coinBuyModelList.size(); i++) {
            if (coinBuyModelList.get(i).getId() == coinId) {
                selectId = i;
                break;
            }
        }

        if (selectId != -1) {
            coinBuyModelList.remove(selectId);
            SharedPreferencesUtil.saveStringData(context, AppConstants.SHARED_KEY_BUY_COIN_LIST_TEMP, new Gson().toJson(coinBuyModelList));
        }
    }
}
