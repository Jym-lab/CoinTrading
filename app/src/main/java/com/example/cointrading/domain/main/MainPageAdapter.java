package com.example.cointrading.domain.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.cointrading.domain.main.coinlist.CoinListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 메인 화면 페이지 어댑터
 */
public class MainPageAdapter extends FragmentStateAdapter {

    // 화면 리스트
    private List<Fragment> fmList = new ArrayList<>();

    public MainPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        if (position >= fmList.size()) {
            return new CoinListFragment();
        }

        return fmList.get(position);
    }

    @Override
    public int getItemCount() {
        return fmList.size();
    }

    public void setFmList(List<Fragment> fmList) {
        if (fmList == null) {
            return;
        }
        this.fmList = fmList;
    }
}
