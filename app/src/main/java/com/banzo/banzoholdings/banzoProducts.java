package com.banzo.banzoholdings;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class banzoProducts implements Parcelable{

    private String desc,name;
    private String image;
    private boolean isCombo;
    private String price;

    public banzoProducts() {
    }

    public banzoProducts(String desc, String name, String image, boolean isCombo, String price) {
        this.desc = desc;
        this.name = name;
        this.image = image;
        this.isCombo = isCombo;
        this.price = price;
    }

    protected banzoProducts(Parcel in) {
        desc = in.readString();
        name = in.readString();
        image = in.readString();
        isCombo = in.readByte() != 0;
        price = in.readString();
    }

    public static final Creator<banzoProducts> CREATOR = new Creator<banzoProducts>() {
        @Override
        public banzoProducts createFromParcel(Parcel in) {
            return new banzoProducts(in);
        }

        @Override
        public banzoProducts[] newArray(int size) {
            return new banzoProducts[size];
        }
    };

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isCombo() {
        return isCombo;
    }

    public void setCombo(boolean combo) {
        isCombo = combo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    @Override
    public void writeToParcel(Parcel parcel, int i) {

        try{
            parcel.writeInt(Integer.parseInt(desc));
            parcel.writeInt(Integer.parseInt(name));
            parcel.writeInt(Integer.parseInt(image));
            parcel.writeInt(Integer.parseInt(price));

        }catch(NumberFormatException ex){ // handle your exception
            Log.i("SentianceDefintions", ex.toString());
        }
    }
}
