package com.entri.utsresepmakanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    EditText etusername, etpass;
    Button btnlogin;
    TextView btndaftar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etusername = findViewById(R.id.etusername);
        etpass = findViewById(R.id.etpass);

        btnlogin = findViewById(R.id.btnlogin);
        btndaftar = findViewById(R.id.btndaftar);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {
                    return;
                } else {
                    isUser();
                }
            }
        });

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(i);
            }
        });
    }

    private Boolean validateUsername() {
        String val = etusername.getText().toString();
        if (val.isEmpty()) {
            etusername.setError("Username tidak boleh kosong");
            return false;
        } else {
            etusername.setError(null);
            return true;
        }
    }

    private Boolean validatePassword() {
        String val = etpass.getText().toString();
        if (val.isEmpty()) {
            etpass.setError("Password tidak boleh kosong");
            return false;
        } else {
            etpass.setError(null);
            return true;
        }
    }

    private void isUser() {
        final String username = etusername.getText().toString().trim();
        final String password = etpass.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("tb_user");
        Query checkUser = reference.orderByChild("username").equalTo(username);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String passwordFromDB = snapshot.child(username).child("password").getValue(String.class);

                    if (password.equals(passwordFromDB)) {
                        String name = snapshot.child(username).child("name").getValue(String.class);
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);

                        i.putExtra("name", name);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Password salah!", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(LoginActivity.this, "Akun tidak ditemukan!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}