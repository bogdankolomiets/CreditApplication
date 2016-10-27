package com.alina.creditapplication.ui;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alina.creditapplication.R;
import com.alina.creditapplication.entity.Client;
import com.alina.creditapplication.entity.CreditOffer;
import com.alina.creditapplication.ui.activity.AddCreditActivity;
import com.alina.creditapplication.ui.activity.ClientsListActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class CreditOffersAdapter extends RecyclerView.Adapter<CreditOffersAdapter.Holder> {
    private List<CreditOffer> mCreditOffers;
    private boolean mEditable = false;

    public CreditOffersAdapter() {
        mCreditOffers = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new Holder(inflater.inflate(R.layout.credit_offer_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvName.setText(mCreditOffers.get(position).getName());
        holder.tvPercent.setText(String.format("%d", mCreditOffers.get(position).getPercent()));
        holder.tvMaxPeriod.setText(String.format("%d міс", mCreditOffers.get(position).getMaxPeriod()));
        holder.tvCurrency.setText(mCreditOffers.get(position).getCurrency());
        if (mEditable) {
            holder.itemView.setOnClickListener(click -> {
                Intent intent = new Intent(holder.itemView.getContext(), AddCreditActivity.class);
                intent.putExtra(AddCreditActivity.EDIT_MODE, mCreditOffers.get(position));
                holder.itemView.getContext().startActivity(intent);
            });
        } else {
            holder.itemView.setOnClickListener(click -> {
                Intent intent = new Intent(holder.itemView.getContext(), ClientsListActivity.class);
                intent.putExtra(ClientsListActivity.CREDIT_OFFER_KEY, mCreditOffers.get(position).getId());
                intent.putExtra(ClientsListActivity.CREDIT_PERSENT_OFFER_KEY, mCreditOffers.get(position).getPercent());
                holder.itemView.getContext().startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCreditOffers.size();
    }

    public void addData(List<CreditOffer> creditOffers) {
        mCreditOffers.addAll(creditOffers);
        notifyDataSetChanged();
    }

    public void addDataWithClear(List<CreditOffer> creditOffers) {
        clear();
        addData(creditOffers);
    }

    public void clear() {
        mCreditOffers.clear();
        notifyDataSetChanged();
    }

    public void editable(boolean editable) {
        mEditable = editable;
        notifyDataSetChanged();
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvPercent)
        TextView tvPercent;
        @BindView(R.id.tvMaxPeriod)
        TextView tvMaxPeriod;
        @BindView(R.id.tvCurrency)
        TextView tvCurrency;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
