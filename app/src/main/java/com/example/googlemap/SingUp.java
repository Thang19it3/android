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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SingUp extends AppCompatActivity implements View.OnClickListener {

    private Button sign_up;
    private FirebaseAuth mAuth;
    private EditText editTextFullname , editTextAge, editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);

        mAuth = FirebaseAuth.getInstance();

        sign_up = (Button) findViewById(R.id.sign_up);
        sign_up.setOnClickListener(this);

        editTextFullname = (EditText)findViewById(R.id.fullname);
        editTextAge = (EditText)findViewById(R.id.age);
        editTextEmail = (EditText)findViewById(R.id.sig_email);
        editTextPassword = (EditText)findViewById(R.id.sig_pass);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.sign_up:
                registerUser();
                break;
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullname = editTextFullname.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        if (fullname.isEmpty())
        {
            editTextFullname.setError("Nhập tên");
            editTextFullname.requestFocus();
            return;
        }
        if (age.isEmpty())
        {
            editTextAge.setError("Nhập tuổi");
            editTextAge.requestFocus();
            return;
        }
        if (email.isEmpty())
        {
            editTextEmail.setError("Nhập email");
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

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            User user = new User(fullname, age, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull  Task<Void> task) {
                                    if (task.isSuccessful())
                                    {
                                        startActivity(new Intent(SingUp.this, Login_viethan.class));
                                    }
                                    else
                                    {
                                        Toast.makeText(SingUp.this,"Failed to register ! Try again !",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(SingUp.this,"Failed to register! ",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}