package com.example.cointrading.domain.main.coinlist;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cointrading.R;
import com.example.cointrading.domain.interfaces.OnItemCliCkListener;
import com.example.cointrading.model.CoinDataModel;
import com.example.cointrading.utils.CoinUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 코인 리스트 adapter
 */
public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.ViewHolder> {

    private List<CoinDataModel> coinList = new ArrayList<>();
    private OnItemCliCkListener<CoinDataModel> onItemCliCkListener;

    @NonNull
    @Override
    public CoinListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_coin_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CoinListAdapter.ViewHolder holder, int position) {
        // ui 처리
        final CoinDataModel coinDataModel = coinList.get(position);

        holder.tvCoinName.setText(coinDataModel.getCoinName());
        String percent = CoinUtil.getDayBefore(coinDataModel) + "%";
        holder.tvPercent.setText(percent);
        if (CoinUtil.getDayBefore(coinDataModel) >= 0) {
            holder.tvPercent.setTextColor(Color.RED);
        } else {
            holder.tvPercent.setTextColor(Color.CYAN);

        }
        holder.tvNow.setText(String.valueOf(coinDataModel.getNowPrice()));
        holder.tvTotalPrice.setText(String.valueOf(coinDataModel.getTotalPrice()));

        initListener(holder, position, coinDataModel);

    }

    @Override
    public int getItemCount() {
        return coinList.size();
    }

    private void initListener(CoinListAdapter.ViewHolder holder, int position, CoinDataModel coinDataModel) {
        holder.itemView.setOnClickListener(view -> onItemCliCkListener.onClick(coinDataModel));
    }

    /**
     * 뷰 초기화
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCoinName;
        private final TextView tvNow;
        private final TextView tvPercent;
        private final TextView tvTotalPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCoinName = itemView.findViewById(R.id.tv_coin_name);
            tvNow = itemView.findViewById(R.id.tv_coin_now);
            tvPercent = itemView.findViewById(R.id.tv_coin_percent);
            tvTotalPrice = itemView.findViewById(R.id.tv_coin_total_price);
        }

    }

    /**
     * 코인 리스트 세팅
     *
     * @param coinList 코인 리스트
     */
    @SuppressLint("NotifyDataSetChanged")
    public void setCoinList(List<CoinDataModel> coinList) {
        if (coinList == null) {
            return;
        }
        this.coinList = coinList;
        // 리스트 갱신
        notifyDataSetChanged();
    }

    public List<CoinDataModel> getCoinList() {
        return coinList;
    }

    public void setOnItemCliCkListener(OnItemCliCkListener<CoinDataModel> onItemCliCkListener) {
        this.onItemCliCkListener = onItemCliCkListener;
    }
}
