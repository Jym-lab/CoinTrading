package com.example.cointrading.domain.main.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cointrading.R;
import com.example.cointrading.databinding.FragmentSettingBinding;
import com.example.cointrading.global.AppConstants;
import com.example.cointrading.manager.CoinManager;
import com.example.cointrading.model.CoinBuyModel;
import com.example.cointrading.utils.CashUtil;
import com.example.cointrading.utils.CoinUtil;
import com.example.cointrading.utils.SharedPreferencesUtil;
import com.example.cointrading.utils.StringUtil;

import java.util.List;

public class SettingFragment extends Fragment {

    private FragmentSettingBinding binding;
    private SettingAdapter settingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getActivity() == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false);
        binding.setLifecycleOwner(this);
        initListener();
        settingAdapter = new SettingAdapter();
        binding.setFragment(this);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }

    private void initView() {

        if (binding.rvList.getAdapter() == null) {
            binding.rvList.setAdapter(settingAdapter);
        }

        binding.etCharge.setText("");
        binding.setCash(getString(R.string.fragment_setting_my_cash, StringUtil.convertComma(CashUtil.getCash(getContext()))));
        binding.setTotalCash(getString(R.string.fragment_setting_total_my_cash, StringUtil.convertComma(CashUtil.getCash(getContext()) + CoinUtil.getTotalBuyAvgPrice(getContext()))));

        if (CoinUtil.getBuyCoinList(getContext()) == null || CoinUtil.getBuyCoinList(getContext()).size() == 0) {
            binding.tvEmpty.setVisibility(View.VISIBLE);
            binding.rvList.setVisibility(View.GONE);
        } else {
            binding.tvEmpty.setVisibility(View.GONE);
            binding.rvList.setVisibility(View.VISIBLE);

            settingAdapter.setCoinBuyModelList(CoinUtil.getBuyCoinList(getContext()));
        }

    }

    private void initListener() {

    }

    /**
     * 금액 충전
     */
    public void chargeCash() {
        final int chargeCash = getInputChargeCash();
        if (chargeCash == 0) {
            Toast.makeText(getContext(), R.string.fragment_setting_charge_fail, Toast.LENGTH_SHORT).show();
            return;
        }

        CashUtil.chargeCash(chargeCash, getContext());
        initView();

        Toast.makeText(getContext(), R.string.fragment_setting_charge_success, Toast.LENGTH_SHORT).show();
    }

    /**
     * @return 사용자가 입력한 충전할 금액
     */
    public int getInputChargeCash() {
        // 사용자가 입력한 캐쉬
        int inputCash;

        try {
            inputCash = Integer.parseInt(binding.etCharge.getText().toString());
        } catch (NumberFormatException e) {
            // int 형으로 변환 실패해서 금액 충전 불가
            inputCash = 0;
        }
        return inputCash;
    }

    /**
     * 초기화
     */
    public void reset() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("정말로 초기화 하시겠습니까?")
                .setTitle("초기화");

        builder.setPositiveButton("예", (dialogInterface, i) -> {
            SharedPreferencesUtil.saveStringData(binding.getRoot().getContext(), AppConstants.SHARED_KEY_BUY_COIN_LIST_TEMP, "");
            CashUtil.saveCash(AppConstants.DEFAULT_CASH, binding.getRoot().getContext());
            initView();
        });

        builder.setNegativeButton("아니요", (dialogInterface, i) -> {

        });

        builder.create().show();
    }

}
