package com.example.cointrading.manager;

import com.example.cointrading.model.CoinDataModel;
import com.example.cointrading.utils.CoinUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 코인 매니저 class
 * 코인의 데이터 관리만 처리
 * 코인 관련 데이터 가공 및 처리는 CoinUtil class 에서 처리
 */
public class CoinManager {

    // 코인 map
    private Map<Integer, CoinDataModel> coinMap = new HashMap<>();

    private CoinManager() {
    }

    public static CoinManager getInstance() {
        return LazyHolder.IT;
    }

    private static class LazyHolder {
        private static final CoinManager IT = new CoinManager();
    }

    public void init() {
        CoinUtil.createCoinData();
    }

    /**
     * @return 코인 리스트 반환
     */
    public List<CoinDataModel> getCoinDataList() {
        return new ArrayList<>(coinMap.values());
    }

    /**
     * @param coinId 찾을 코인 id
     * @return 코인 id로 특정 코인 검색후 반환
     */
    public CoinDataModel findCoinById(int coinId) {
        return coinMap.get(coinId);
    }

    public boolean isExitCoinById(int coinId) {
        return coinMap.containsKey(coinId);
    }

    public void setCoinMap(Map<Integer, CoinDataModel> dummyCoinMap) {
        if (dummyCoinMap == null) {
            return;
        }

        if (dummyCoinMap.size() == 0) {
            return;
        }

        coinMap = dummyCoinMap;
    }

    /**
     * 더미 코인 이름 리스트 반환
     */
    public List<String> getCoinNaneList() {
        List<String> coinNameList = new ArrayList<>();
        coinNameList.add("비트코인");
        coinNameList.add("이더리움");
        coinNameList.add("리플");
        coinNameList.add("아이큐");
        coinNameList.add("디카르고");
        coinNameList.add("앱토스");
        coinNameList.add("칠리즈");
        coinNameList.add("솔라나");
        coinNameList.add("카바");
        coinNameList.add("헌트");
        coinNameList.add("위믹스");
        coinNameList.add("샌드박스");
        coinNameList.add("에이다");
        coinNameList.add("트론");
        coinNameList.add("플로우");
        coinNameList.add("스팀");
        coinNameList.add("세럼");
        coinNameList.add("저스트");
        return coinNameList;
    }

}
