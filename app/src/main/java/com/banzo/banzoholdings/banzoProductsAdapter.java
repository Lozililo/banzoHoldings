package com.banzo.banzoholdings;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

public class banzoProductsAdapter extends RecyclerView.Adapter<banzoProductsAdapter.MyViewHolder> {

    private List<banzoProducts> banzoProductsList;
    Context context;
    StorageReference imageReference= FirebaseStorage.getInstance().getReference();

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

    public banzoProductsAdapter(Context context,List<banzoProducts> banzoProductsList) {
            this.context=context;
        this.banzoProductsList = banzoProductsList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.products_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
            banzoProducts products = banzoProductsList.get(position);
       holder.productName.setText(products.getName());
       holder.productDescription.setText(products.getDesc());
       holder.productPrice.setText(products.getPrice());

        imageReference.child(products.getImage()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("Loz","image uri is" + uri);
                Glide.with(context).load(uri).into(holder.productImage);

            }

        });
    }

    @Override
    public int getItemCount() {
        return banzoProductsList.size();
    }
}

