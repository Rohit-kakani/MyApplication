package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class PicsAdapter extends RecyclerView.Adapter<PicsAdapter.MyPicViewHolder> {


    private List<PicDataModel> picDataModels ;


    public PicsAdapter(List<PicDataModel> picDataModels) {
        this.picDataModels = picDataModels;
    }

    @NonNull
    @Override
    public PicsAdapter.MyPicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cell_view, parent,false);

        return new MyPicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PicsAdapter.MyPicViewHolder holder, int position) {

        /** Setting Data to each cell views */
        holder.mTV.setText(picDataModels.get(position).getName());




         holder.mImageView.setImageBitmap(picDataModels.get(position).getBitmap());

        

    }

    @Override
    public int getItemCount() {
        //return mimagesDataList.size();
        return picDataModels.size();
    }

    public class MyPicViewHolder extends RecyclerView.ViewHolder {

        TextView mTV;
        ImageView mImageView;

        public MyPicViewHolder(@NonNull View itemView) {
            super(itemView);
            /** Initialing views*/
            mTV = itemView.findViewById(R.id.author);
            mImageView = itemView.findViewById(R.id.imageView);
        }
    }
}
