package com.example.logqualy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateNewUserActivity extends AppCompatActivity {

    private static final String TAG = "CreateNewUserActivity";
    private EditText mEmail;
    private EditText mPassword;
    private Button mButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_user);

        mEmail = findViewById(R.id.editTextTextEmailAddressNewUser);
        mPassword = findViewById(R.id.editTextTextPasswordNewUser);
        mButton = findViewById(R.id.btnCreateNewUser);
        mAuth = FirebaseAuth.getInstance();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.

                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(CreateNewUserActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                    updateUI(null);
                                }
                            }
                        });
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            Intent intent = new Intent(this, ProductListActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this,"Erro ao cadastrar usuário",
                    Toast.LENGTH_SHORT).show();
        }
    }
}