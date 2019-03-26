package com.phonedialer.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.phonedialer.Models.ImageListResponse;
import com.phonedialer.Models.VideoListResponse;
import com.phonedialer.R;

import java.util.ArrayList;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.SimpleViewHolder> {
    private ArrayList<VideoListResponse.DataBean> alCategory;
    private Context context;

    public VideoListAdapter(Context context, ArrayList<VideoListResponse.DataBean> alCategory) {
        this.alCategory = alCategory;
        this.context = context;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_data_video, parent, false);
        return new SimpleViewHolder(view);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        viewHolder.wbvwVideo.setWebViewClient(new WebViewClient());
        viewHolder.wbvwVideo.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            viewHolder.wbvwVideo.getSettings().setMediaPlaybackRequiresUserGesture(true);
        }
        viewHolder.wbvwVideo.loadUrl(alCategory.get(position).getStory());
        viewHolder.tvDate.setText("" + alCategory.get(position).getDateTime());
    }

    @Override
    public int getItemCount() {
        return alCategory.size();
    }


    public class SimpleViewHolder extends RecyclerView.ViewHolder {
        public WebView wbvwVideo;
        public TextView tvDate;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            wbvwVideo = itemView.findViewById(R.id.wbVideo);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }

    @Override
    public void onViewAttachedToWindow(@NonNull SimpleViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.wbvwVideo.onResume();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull SimpleViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.wbvwVideo.onPause();
    }
}