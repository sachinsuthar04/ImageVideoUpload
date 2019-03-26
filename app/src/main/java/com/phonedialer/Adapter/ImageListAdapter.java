package com.phonedialer.Adapter;

import android.content.Context;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.phonedialer.Models.ImageListResponse;
import com.phonedialer.R;

import java.util.ArrayList;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.SimpleViewHolder> {
    private ArrayList<ImageListResponse.Datum> alCategory;
    private Context context;

    public ImageListAdapter(Context context, ArrayList<ImageListResponse.Datum> alCategory) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        this.alCategory = alCategory;
        this.context = context;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data_image, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, int position) {
        Uri uri = Uri.parse(alCategory.get(position).getImage());
        viewHolder.my_image_view.setImageURI(uri);
        viewHolder.tvDate.setText("" + alCategory.get(position).getDateTime());
        viewHolder.tvDesc.setText("" + alCategory.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return alCategory.size();
    }

    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public SimpleDraweeView my_image_view;
        public TextView tvDate, tvDesc;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            my_image_view = itemView.findViewById(R.id.my_image_view);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}