package com.banzo.banzoholdings;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

public class banzoProductsAdapter extends RecyclerView.Adapter<banzoProductsAdapter.MyViewHolder> {

    private List<banzoProducts> banzoProductsList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView productName, productDescription, productPrice;
            public ImageView productImage;

            public MyViewHolder(View view) {
                super(view);
               productName = view.findViewById(R.id.productName);
               productDescription =  view.findViewById(R.id.productDescription);
               productPrice =  view.findViewById(R.id.productPrice);
               productImage=view.findViewById(R.id.productImage);
            }
        }

    public banzoProductsAdapter(List<banzoProducts> banzoProductsList) {
        this.banzoProductsList = banzoProductsList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
            banzoProducts products = banzoProductsList.get(position);
       holder.productName.setText(products.getName());
       holder.productDescription.setText(products.getDesc());
       holder.productPrice.setText(products.getPrice());
     

    }

    @Override
    public int getItemCount() {
        return banzoProductsList.size();
    }
}

