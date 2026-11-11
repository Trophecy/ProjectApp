package com.example.projectapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import static com.example.projectapp.FirebaseRef.refAuth;

import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button signUpButton = findViewById(R.id.signUpButton);
        Button loginButton = findViewById(R.id.loginButton);

        signUpButton.setOnClickListener(v -> {
            TextView username = findViewById(R.id.username_su);
            TextView password = findViewById(R.id.password_su);
            TextView remember = findViewById(R.id.remember_su);

            String strUsername = username.getText().toString();
            String strPassword = password.getText().toString();
            String strRemember = remember.getText().toString();

            refAuth.createUserWithEmailAndPassword(strUsername, strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = refAuth.getCurrentUser();
                        Toast.makeText(Login.this, "Registered successfully: " + user.getUid(), Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("strUsername", strUsername);
                        intent.putExtra("strPassword", strPassword);

                        SharedPreferences sharedPreferences = getSharedPreferences("MainActivityLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(strUsername, strRemember);
                        editor.apply();

                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Error. Please try again later.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        });

        loginButton.setOnClickListener(v -> {
            TextView username = findViewById(R.id.username_lg);
            TextView password = findViewById(R.id.password_lg);
            TextView remember = findViewById(R.id.remember_lg);

            String strUsername = username.getText().toString();
            String strPassword = password.getText().toString();
            String strRemember = remember.getText().toString();

            refAuth.signInWithEmailAndPassword(strUsername, strPassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = refAuth.getCurrentUser();
                        Toast.makeText(Login.this, "Logged in successfully", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Login.this, MainActivity.class);
                        intent.putExtra("strUsername", strUsername);
                        intent.putExtra("strPassword", strPassword);

                        SharedPreferences sharedPreferences = getSharedPreferences("MainActivityLogin", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(strUsername, strRemember);
                        editor.apply();

                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Error. Please try again.", Toast.LENGTH_LONG).show();
                    }
                }
            });
        });
    }
}