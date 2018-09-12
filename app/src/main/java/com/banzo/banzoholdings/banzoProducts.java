package com.banzo.banzoholdings;

public class banzoProducts {

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
}
