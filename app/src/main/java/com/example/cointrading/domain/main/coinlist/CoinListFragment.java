package com.example.cointrading.domain.main.coinlist;

import static android.content.Context.INPUT_METHOD_SERVICE;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.cointrading.R;
import com.example.cointrading.databinding.FragmentCoinlistBinding;
import com.example.cointrading.manager.CoinManager;
import com.example.cointrading.model.CoinBuyModel;
import com.example.cointrading.model.CoinDataModel;
import com.example.cointrading.utils.CashUtil;
import com.example.cointrading.utils.CoinUtil;
import com.example.cointrading.utils.StringUtil;

import java.util.List;

public class CoinListFragment extends Fragment {

    private FragmentCoinlistBinding binding;

    private CoinListAdapter coinListAdapter;

    private CoinDataModel selectCoinDataModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (getActivity() == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_coinlist, container, false);
        binding.setLifecycleOwner(this);

        initView();
        initListener();
        return binding.getRoot();
    }

    private void initView() {
        coinListAdapter = new CoinListAdapter();
        coinListAdapter.setCoinList(CoinUtil.getCoinList());
        binding.rvList.setAdapter(coinListAdapter);
    }

    private void initListener() {
        // 검색 버튼 클릭
        binding.btSearch.setOnClickListener(view -> searchCoin());

        coinListAdapter.setOnItemCliCkListener(coinDataModel -> {

            selectCoinDataModel = coinDataModel;
            int maxCount = CashUtil.getCash(getContext()) / coinDataModel.getNowPrice();

            binding.tvMyCash.setText(getString(R.string.fragment_coin_list_buy_group_my_cash, StringUtil.convertComma(CashUtil.getCash(getContext()))));
            binding.tvMaxCount.setText(getString(R.string.fragment_coin_list_buy_group_max_count, StringUtil.convertComma(maxCount)));
            binding.tvNowPrice.setText(getString(R.string.fragment_coin_list_buy_group_now, StringUtil.convertComma(coinDataModel.getNowPrice())));
            binding.tvHoldQuantity.setText(getString(R.string.fragment_coin_list_buy_group_hold_quantity, StringUtil.convertComma(CoinUtil.holdCoinQuantity(getContext(), coinDataModel.getId()))));
            binding.tvCoinName.setText(coinDataModel.getCoinName());
            binding.clBuyGroup.setVisibility(View.VISIBLE);
        });

        // 코인 매수
        binding.btnBuy.setOnClickListener(view -> {

            if (selectCoinDataModel == null) {
                return;
            }

            if (CoinUtil.holdCoinQuantity(getContext(), selectCoinDataModel.getId()) > 0) {
                Toast.makeText(getContext(), "해당 코인을 이미 보유중입니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            String tempQuantity = binding.etBuyQuantity.getText().toString();

            if (tempQuantity.length() == 0) {
                Toast.makeText(getContext(), "구매할 코인 개수를 입력해주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            int quantity = Integer.parseInt(tempQuantity);

            if ((quantity * selectCoinDataModel.getNowPrice()) > CashUtil.getCash(getContext())) {
                Toast.makeText(getContext(), "구매가 불가능 합니다.\n보유 금액을 확인해 주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            CashUtil.deductionCash((quantity * selectCoinDataModel.getNowPrice()), getContext());
            CoinBuyModel coinBuyModel = new CoinBuyModel();
            coinBuyModel.setId(selectCoinDataModel.getId());
            coinBuyModel.setBuyPrice(selectCoinDataModel.getNowPrice());
            coinBuyModel.setName(selectCoinDataModel.getCoinName());
            coinBuyModel.setQuantity(quantity);
            CoinUtil.buyCoin(getContext(), coinBuyModel);

            Toast.makeText(getContext(), "정상적으로 매수가 되었습니다.", Toast.LENGTH_SHORT).show();
            binding.etBuyQuantity.setText("");
            binding.clBuyGroup.setVisibility(View.INVISIBLE);

            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binding.etBuyQuantity.getWindowToken(), 0);

        });

        // 코인 매도
        binding.btnSell.setOnClickListener(view -> {
            if (selectCoinDataModel == null) {
                return;
            }

            if (CoinUtil.holdCoinQuantity(getContext(), selectCoinDataModel.getId()) <= 0) {
                Toast.makeText(getContext(), "매도가 불가능 합니다.\n보유 개수를 확인해 주세요.", Toast.LENGTH_SHORT).show();
                return;
            }

            CashUtil.chargeCash((CoinUtil.holdCoinQuantity(getContext(), selectCoinDataModel.getId()) * selectCoinDataModel.getNowPrice()), getContext());
            CoinUtil.sellCoin(getContext(), selectCoinDataModel.getId());

            Toast.makeText(getContext(), "정상적으로 매도가 되었습니다.", Toast.LENGTH_SHORT).show();
            binding.etBuyQuantity.setText("");
            binding.clBuyGroup.setVisibility(View.INVISIBLE);
            InputMethodManager imm = (InputMethodManager) getContext().getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(binding.etBuyQuantity.getWindowToken(), 0);

        });

        binding.ivRefresh.setOnClickListener(view -> {
            CoinManager.getInstance().init();
            coinListAdapter.setCoinList(CoinUtil.getCoinList());
        });
    }

    /**
     * 코인 검색
     */
    private void searchCoin() {
        final String filter = binding.etCoin.getText().toString();
        final List<CoinDataModel> filterList = CoinUtil.getFilterCoinList(filter);
        coinListAdapter.setCoinList(filterList);
    }
}
