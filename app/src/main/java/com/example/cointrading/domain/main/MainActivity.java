package com.example.cointrading.domain.main;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cointrading.R;
import com.example.cointrading.databinding.ActivityMainBinding;
import com.example.cointrading.domain.base.BaseActivity;
import com.example.cointrading.domain.main.coinlist.CoinListFragment;
import com.example.cointrading.domain.main.setting.SettingFragment;
import com.example.cointrading.global.AppConstants;
import com.example.cointrading.manager.CoinManager;
import com.example.cointrading.utils.CashUtil;
import com.example.cointrading.utils.SharedPreferencesUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private MainPageAdapter mainPageAdapter;

    @Override
    protected void initDefaultData() {

        if (isFistApp()) {
            // 앱 첫실행 더미 데이터 세팅
            initDummyData();
            CoinManager.getInstance().init();
        } else {
            // sharedPreferences 에서 데이터 가져옴
            CoinManager.getInstance().init();
        }

//        CoinManager.getInstance().init();

        mainPageAdapter = new MainPageAdapter(this);
        mainPageAdapter.setFmList(getFmList());
    }

    @Override
    protected void initLayout() {

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);

        binding.vpMain.setAdapter(mainPageAdapter);
        binding.vpMain.setUserInputEnabled(false);


    }

    @Override
    protected void initListener() {
        tabSelectListener();
    }

    /**
     * 탭 선택 리스너
     */
    private void tabSelectListener() {
        binding.tlMenu.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.vpMain.setCurrentItem(binding.tlMenu.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private List<Fragment> getFmList() {
        List<Fragment> fmList = new ArrayList<>();
        fmList.add(new CoinListFragment());
        fmList.add(new SettingFragment());
        return fmList;
    }

    /**
     * 앱 첫 실행인지 유무 체크
     */
    private boolean isFistApp() {
        return !SharedPreferencesUtil.getBooleanData(this, AppConstants.SHARED_KEY_APP_FIRST_START);
    }

    /**
     * 더미 데이터 초기화
     */
    private void initDummyData() {
        // 보유 금액 초기화
        CashUtil.saveCash(AppConstants.DEFAULT_CASH, this);
        CoinManager.getInstance().init();
        SharedPreferencesUtil.saveBooleanData(this, AppConstants.SHARED_KEY_APP_FIRST_START, true);
    }

}