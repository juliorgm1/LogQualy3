package com.example.logqualy.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String nameProduct;
    private String descriptionProduct;
    private String date;
    private String photoProduct;

    public Product(String nameProduct, String descriptionProduct, String date, String photoProduct) {
        this.nameProduct = nameProduct;
        this.descriptionProduct = descriptionProduct;
        this.date = date;
        this.photoProduct = photoProduct;
    }


}
