package com.example.registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class SigninActivity<TextView> extends AppCompatActivity {

   ActivitySigninBinding binding;
   ProgressDialog progressDialog;
   FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(SigninActivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Login to your account");

        binding.signUpIdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.emailId.getText().toString() , binding.passwordId.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()){
                            Intent intent= new Intent(SigninActivity.this , MainActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(SigninActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        binding.signintextId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SigninActivity.this , SignUpActivity.class);
                startActivity(intent);
            }
        });

        if (auth.getCurrentUser()!= null){
            Intent intent = new Intent(SigninActivity.this , MainActivity.class);
            startActivity(intent);
        }
    }
}