package com.example.cointrading.domain.main.setting;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cointrading.R;
import com.example.cointrading.domain.main.coinlist.CoinListAdapter;
import com.example.cointrading.manager.CoinManager;
import com.example.cointrading.model.CoinBuyModel;
import com.example.cointrading.utils.CoinUtil;
import com.example.cointrading.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

public class SettingAdapter extends RecyclerView.Adapter<SettingAdapter.ViewHolder> {

    List<CoinBuyModel> coinBuyModelList = new ArrayList<>();

    @NonNull
    @Override
    public SettingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buy_coin_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SettingAdapter.ViewHolder holder, int position) {

        CoinBuyModel coinBuyModel = coinBuyModelList.get(position);
        holder.tvCoinName.setText(coinBuyModel.getName());
        holder.tvCoinNow.setText(StringUtil.convertComma(coinBuyModel.getBuyPrice()));
        holder.tvCoinPercent.setText(StringUtil.convertComma(CoinManager.getInstance().findCoinById(coinBuyModel.getId()).getNowPrice()));
        float percent = CoinUtil.getDayBefore(CoinManager.getInstance().findCoinById(coinBuyModel.getId()).getNowPrice(), coinBuyModel.getBuyPrice());

        if (percent >= 0) {
            holder.tvRateReturn.setTextColor(Color.RED);
        } else {
            holder.tvRateReturn.setTextColor(Color.CYAN);

        }
        holder.tvRateReturn.setText(percent + "%");

    }

    @Override
    public int getItemCount() {
        return coinBuyModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvCoinName;
        private final TextView tvCoinNow;
        private final TextView tvCoinPercent;
        private final TextView tvRateReturn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvCoinName = itemView.findViewById(R.id.tv_coin_name);
            tvCoinNow = itemView.findViewById(R.id.tv_coin_now);
            tvCoinPercent = itemView.findViewById(R.id.tv_coin_percent);
            tvRateReturn = itemView.findViewById(R.id.tv_rate_return);

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCoinBuyModelList(List<CoinBuyModel> coinBuyModelList) {
        this.coinBuyModelList = coinBuyModelList;
        notifyDataSetChanged();
    }
}
