package com.entri.utsresepmakanan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    DatabaseReference databaseReference;

    RecyclerView recyclerView;
    List<ImageUploadInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("masakanindo");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    IndoDetails indoDetails = snapshot1.getValue(IndoDetails.class);
                    ImageUploadInfo imageUploadInfo = new ImageUploadInfo();
                    String name = indoDetails.getName();

                    imageUploadInfo.setName(name);
                    list.add(imageUploadInfo);
                }
                RecyclerViewM recyclerViewM = new RecyclerViewM(list, getApplicationContext());
                recyclerView.setAdapter(recyclerViewM);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void detail(View view) {
        int position = (int) view.getTag();

        switch (position) {
            case 0 :
                Intent intent = new Intent(this, NasgorMawutActivity.class);
                startActivity(intent);
                break;
            case 1 :
                Intent intent1 = new Intent(this, AyamGorengLengkuasActivity.class);
                startActivity(intent1);
                break;
            case 2 :
                Intent intent2 = new Intent(this, AyamGeprekActivity.class);
                startActivity(intent2);
                break;
            case 3 :
                Intent intent3 = new Intent(this, SotoMaduraActivity.class);
                startActivity(intent3);
                break;
            case 4 :
                Intent intent4 = new Intent(this, SateMaranggiActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
