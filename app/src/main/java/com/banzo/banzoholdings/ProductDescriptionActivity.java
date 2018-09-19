package com.banzo.banzoholdings;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ProductDescriptionActivity extends AppCompatActivity {

    private TextView prodDescription, prodPrice;
    private ImageView productImage;
    private Button call, placeOrder;
    String description, name, image, price;
    StorageReference imageReference = FirebaseStorage.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        prodDescription = findViewById(R.id.description);
        prodPrice = findViewById(R.id.price);
        productImage = findViewById(R.id.productImage);
        call = findViewById(R.id.callUs);
        placeOrder=findViewById(R.id.order);

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

        name=extras.getString("name");
        image = extras.getString("image");
        description = extras.getString("description");
        price = extras.getString("price");

        prodDescription.setText(description);
        prodPrice.setText("R" + price + ".00");

        imageReference.child(image).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Log.d("Loz", "image uri is" + uri);
                Glide.with(ProductDescriptionActivity.this).load(uri).into(productImage);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeTheCall();
            }
        });

        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mEmail = new Intent(Intent.ACTION_SEND);
                mEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{ "bandzoholdings@outlook.com"});
                mEmail.putExtra(Intent.EXTRA_SUBJECT, "Product Order");
                mEmail.putExtra(Intent.EXTRA_TEXT, "I would like to order this "+name + " with these items "+ description);
                mEmail.setType("message/rfc822");
                startActivity(Intent.createChooser(mEmail, "Choose an email client to send your"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed

        super.onBackPressed();
        Intent intent = new Intent(ProductDescriptionActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void makeTheCall() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:0727106810"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(ProductDescriptionActivity.this,"call denied",Toast.LENGTH_LONG).show();
            return;
        }
            startActivity(callIntent);
    }

}


