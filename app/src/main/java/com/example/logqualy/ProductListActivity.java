package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProductListActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    private FirebaseUser user;
    private FloatingActionButton fabFormProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        fabFormProduct = findViewById(R.id.fabFormProduct);

        fabFormProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductListActivity.this, FormProductActivity.class);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_logout:
                FirebaseAuth.getInstance().signOut();
                goToLoginActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
//        if (item.getItemId()==R.id.menu_item_logout){
//            FirebaseAuth.getInstance().signOut();
//            return true;
//        }else{
//            return super.onOptionsItemSelected(item);
//        }

    }

    private void goToLoginActivity() {
        startActivity(new Intent(
                this,
                MainActivity.class
        ));
        finish();
    }
}