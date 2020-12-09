package com.entri.utsresepmakanan;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    public static final String Database_Path = "All_Image_Uploads_Database";

    // Creating DatabaseReference.
    DatabaseReference databaseReference;

    // Creating RecyclerView.
    RecyclerView recyclerView;

    // Creating RecyclerView.Adapter.
    RecyclerView.Adapter adapter;

    // Creating Progress dialog
    TextView tvname;

    // Creating List of ImageUploadInfo class.
    List<ImageUploadInfo> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvname = findViewById(R.id.tvname);

        tvname.setText(getIntent().getStringExtra("name"));
        // Assign id to RecyclerView.
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        // Setting RecyclerView size true.
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference = FirebaseDatabase.getInstance().getReference().child("kategori");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    UserDetails userDetails = snapshot1.getValue(UserDetails.class);
                    ImageUploadInfo imageUploadInfo = new ImageUploadInfo();
                    String name = userDetails.getName();
                    String imageUrl = userDetails.getImageUrl();

                    imageUploadInfo.setName(name);
                    imageUploadInfo.setImageUrl(imageUrl);
                    list.add(imageUploadInfo);
                }
                RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(list, getApplicationContext());
                recyclerView.setAdapter(recyclerViewAdapter);
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
                Intent intent = new Intent(this, FirstActivity.class);
                startActivity(intent);
                break;
            case 1 :
                Intent intent1 = new Intent(this, SecondActivity.class);
                startActivity(intent1);
                break;
            case 2 :
                Intent intent2 = new Intent(this, ThirdActivity.class);
                startActivity(intent2);
                break;
        }
    }
}