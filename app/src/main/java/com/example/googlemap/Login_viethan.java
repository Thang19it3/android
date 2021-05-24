package com.example.googlemap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.googlemap.ForgotPassword;
import com.example.googlemap.Home;
import com.example.googlemap.SingUp;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_viethan extends AppCompatActivity implements View.OnClickListener {

    private Button btn, resetpass;
    private Button btn1;
    private EditText editTextEmail, editTextPassword;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_viethan);
        btn = (Button)findViewById(R.id.login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
            }
        });

        btn1 = (Button)findViewById(R.id.regiter);
        btn1.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        resetpass = (Button)findViewById(R.id.resetpass);
        resetpass.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.regiter:
                startActivity(new Intent(this, SingUp.class));
                break;
            case R.id.login:
                userLogin();
                break;
            case R.id.resetpass:
                startActivity(new Intent(Login_viethan.this, ForgotPassword.class));
                break;
        }
    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(email.isEmpty())
        {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editTextEmail.setError("Sai email");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty())
        {
            editTextPassword.setError("Nhập pass");
            editTextPassword.requestFocus();
            return;
        }
        if (password.length() < 6)
        {
            editTextPassword.setError("Nhập pass hơn 6 kí tự");
            editTextPassword.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull  Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    startActivity(new Intent(Login_viethan.this, Home.class));
                }
                else
                {
                    Toast.makeText(Login_viethan.this,"Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}