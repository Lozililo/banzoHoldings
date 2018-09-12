package com.banzo.banzoholdings;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    StorageReference imageReference;
    banzoProductsAdapter banzoProductsAdapter;
    private List<banzoProducts> banzoProductsList = new ArrayList<>();
    RecyclerView recyclerView;

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
                        String description=products.getDesc();
                        String name =products.getName();
                        String image=products.getImage();
                        String price= "R "+products.getPrice();
                        boolean isCombo=products.isCombo();

                        banzoProducts banzoProducts=new banzoProducts(description,name,image,isCombo,price);
                        banzoProductsList.add(banzoProducts);
                        banzoProductsAdapter=new banzoProductsAdapter(MainActivity.this,banzoProductsList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(banzoProductsAdapter);

                        Log.d("naledi", document.getId() + " => name  of product is " + name + " this is what it does "+ description + " the price is R"+ price + " the combo is "+ isCombo + "and image url is " +image);

                    }
                } else {
                    Log.d("naledi", "Error getting documents.", task.getException());
                }
            }
        });


    }
}
