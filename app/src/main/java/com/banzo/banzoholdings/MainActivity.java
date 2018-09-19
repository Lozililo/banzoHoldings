package com.banzo.banzoholdings;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements banzoProductsAdapter.banzoProductListener {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    banzoProductsAdapter banzoProductsAdapter;
    private List<banzoProducts> banzoProductsList = new ArrayList<>();
    RecyclerView recyclerView;
    String description,name,image,price;
    boolean isCombo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.my_recycler_view);

        db.collection("product").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {

                        banzoProducts products = document.toObject(banzoProducts.class);
                         description=products.getDesc();
                         name =products.getName();
                         image=products.getImage();
                         price= products.getPrice();
                         isCombo=products.isCombo();

                        banzoProducts banzoProducts=new banzoProducts(description,name,image,isCombo,price);
                        banzoProductsList.add(banzoProducts);
                        banzoProductsAdapter=new banzoProductsAdapter(MainActivity.this,banzoProductsList,MainActivity.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(banzoProductsAdapter);
                    }
                } else {
                    Log.d("naledi", "Error getting documents.", task.getException());
                }
            }
        });
    }

    @Override
    public void onProductSelected(banzoProducts product) {

        //Toast.makeText(getApplicationContext(), "Selected: " + product.getName() + ", " + product.getPrice(), Toast.LENGTH_LONG).show();
        Intent intent=new Intent(MainActivity.this,ProductDescriptionActivity.class);
        intent.putExtra("name",product.getName());
        intent.putExtra("description",product.getDesc());
        intent.putExtra("image",product.getImage());
        intent.putExtra("price",product.getPrice());
        startActivityForResult(intent, 10);
    }
}
