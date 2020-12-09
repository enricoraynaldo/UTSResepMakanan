package com.entri.utsresepmakanan;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class RegActivity extends AppCompatActivity {
    Button btnDaftar;
    EditText etname, etusername, etpass, etpass1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        etname = findViewById(R.id.etname);
        etusername = findViewById(R.id.etusername);
        etpass = findViewById(R.id.etpass);
        etpass1 = findViewById(R.id.etpass1);

        btnDaftar = findViewById(R.id.btndaftar);
        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etname.length() > 0 && etusername.length() > 0 && etpass.length() > 0 && etpass1.length() > 0) {
                    final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("tb_user");
                    final String username = etusername.getText().toString().trim();
                    Query checkUser = reference.orderByChild("username").equalTo(username);
                    checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                Toast.makeText(RegActivity.this, "Pendaftaran gagal, username sudah terdaftar!", Toast.LENGTH_LONG).show();
                            } else {
                                String password = etpass.getText().toString().trim();
                                String password1 = etpass1.getText().toString().trim();
                                if (!password.equals(password1)){
                                    Toast.makeText(RegActivity.this, "Pendaftaran gagal, password berbeda!", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    String name = etname.getText().toString().trim();

                                    UserHelper userHelper = new UserHelper(name, username, password);

                                    reference.child(username).setValue(userHelper);
                                    Toast.makeText(RegActivity.this, "Pendaftaran akun berhasil", Toast.LENGTH_LONG).show();
                                    finish();

                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
                else {
                    Toast.makeText(RegActivity.this, "Pendaftaran gagal, lengkapi form!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
