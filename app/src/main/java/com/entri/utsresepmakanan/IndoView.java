package com.entri.utsresepmakanan;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class IndoView extends RecyclerView.ViewHolder {
    TextView tv_imagename;

    public IndoView(@NonNull View itemView) {
        super(itemView);

        tv_imagename = itemView.findViewById(R.id.tv_imagename);
    }
}
