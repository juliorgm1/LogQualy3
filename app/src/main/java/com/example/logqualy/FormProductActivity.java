package com.example.logqualy;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.logqualy.model.Product;

public class FormProductActivity extends AppCompatActivity {

    public static final String PRODUCT_SAVE = "PRODUCT_SAVE";
    private EditText mEditTextNameProduct;
    private EditText mEditTextDescriptionProduct;
    private EditText mEditTextDate;
    private ImageView mImageViewPhoto;
    private Button mButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_product);

        loadViews();
        clickButtons();
    }

    private void clickButtons() {
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Product product = getProductFromForm();
                Intent intent = new Intent(FormProductActivity.this, ProductListActivity.class);
                intent.putExtra(PRODUCT_SAVE, product);
                setResult(Activity.RESULT_OK, intent);
            }
        });
    }

    private Product getProductFromForm() {
        if(validateForm()) {
            String nameProduct = mEditTextNameProduct.getText().toString();
            String descriptionProduct = mEditTextDescriptionProduct.getText().toString();
            String date = mEditTextDate.getText().toString();
            String photoProduct = "adress image";

            return new Product(nameProduct, descriptionProduct, date, photoProduct);
        }

        return null;
    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(mEditTextNameProduct.getText())){
            mEditTextNameProduct.setError("Informe o nome do produto");
            mEditTextNameProduct.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(mEditTextDescriptionProduct.getText())){
            mEditTextDescriptionProduct.setError("Informe a descrição do produto");
            mEditTextNameProduct.requestFocus();
            return false;
        }

        return true;
    }

    private void loadViews() {
        mEditTextNameProduct =  findViewById(R.id.editTextProductName);
        mEditTextDescriptionProduct =  findViewById(R.id.editTextProductDescreption);
        mEditTextDate =  findViewById(R.id.editTextDate);
        mImageViewPhoto =  findViewById(R.id.imageViewPhotoProduct);
        mButtonSave =  findViewById(R.id.btnSaveProduct);
    }
}