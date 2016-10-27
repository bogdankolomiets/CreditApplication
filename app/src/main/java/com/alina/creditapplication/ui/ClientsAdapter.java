package com.alina.creditapplication.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alina.creditapplication.R;
import com.alina.creditapplication.entity.Client;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Bogdan Kolomiets
 * @version 1
 * @date 22.10.16
 */

public class ClientsAdapter extends RecyclerView.Adapter<ClientsAdapter.Holder> {
    private List<Client> mCLients;

    public ClientsAdapter() {
        mCLients = new ArrayList<>();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new Holder(inflater.inflate(R.layout.client_item, parent, false));
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.tvName.setText(mCLients.get(position).getName());
        holder.tvSurname.setText(mCLients.get(position).getSurname());
    }

    @Override
    public int getItemCount() {
        return mCLients.size();
    }

    public void addData(List<Client> clients) {
        mCLients.addAll(clients);
        notifyDataSetChanged();
    }

    public void clear() {
        mCLients.clear();
        notifyDataSetChanged();
    }

    public void addDataWithClear(List<Client> clients) {
        clear();
        addData(clients);
    }

    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvName)
        TextView tvName;
        @BindView(R.id.tvSurname)
        TextView tvSurname;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
