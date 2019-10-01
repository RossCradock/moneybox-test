package com.example.minimoneybox.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minimoneybox.Model.ProductResponse;
import com.example.minimoneybox.R;

import java.util.List;

public class ProductArrayAdapter extends RecyclerView.Adapter<ProductArrayAdapter.ViewHolder> {
    private List<ProductResponse> products;
    private LayoutInflater inflater;
    private ItemClickListener clickListener;

    public ProductArrayAdapter(Context context, List<ProductResponse> products) {
        this.inflater = LayoutInflater.from(context);
        this.products = products;
    }

    // inflates the row layout from xml
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductResponse product = products.get(position);

        holder.productName.setText(product.getProduct().getName());
        holder.planValue.setText("Plan Value: £" + product.getPlanValue().toString());
        holder.moneyBox.setText("Moneybox: £" + product.getMoneybox().toString());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return products.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView productName;
        TextView planValue;
        TextView moneyBox;

        ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tv_product_name);
            planValue = itemView.findViewById(R.id.tv_plan_value);
            moneyBox = itemView.findViewById(R.id.tv_money_box);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
