package com.entri.utsresepmakanan;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import java.util.List;


public class RecyclerViewM extends RecyclerView.Adapter<RecyclerViewM.ViewHolder> {

    List<ImageUploadInfo> MainImageUploadInfoList;
    Context context;

    public  RecyclerViewM(List<ImageUploadInfo> MainimageUploadInfoList, Context context) {
        this.MainImageUploadInfoList = MainimageUploadInfoList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview1, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUploadInfo data = MainImageUploadInfoList.get(position);

        holder.tv_imagename.setText(data.getName());

        holder.cardView1.setTag(position);
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView tv_imagename;
        public CardView cardView1;


        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            tv_imagename = (TextView) itemView.findViewById(R.id.tv_imagename);
            cardView1 = (CardView) itemView.findViewById(R.id.cardview1);
        }
    }
}