package com.phonedialer.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.phonedialer.Adapter.ImageListAdapter;
import com.phonedialer.Adapter.VideoListAdapter;
import com.phonedialer.Models.ImageListResponse;
import com.phonedialer.Models.VideoListResponse;
import com.phonedialer.R;
import com.phonedialer.RestApi.ApiClient;
import com.phonedialer.RestApi.ApiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VideoListActivity extends AppCompatActivity {
    private RecyclerView rcVideos;
    private VideoListAdapter videoListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<VideoListResponse.DataBean> alVideolist;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);

        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        rcVideos = findViewById(R.id.rcVideos);
        rcVideos.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(VideoListActivity.this);
        rcVideos.setLayoutManager(layoutManager);
        progressDialog = new ProgressDialog(VideoListActivity.this);
        progressDialog.setMessage("Please Wait...");
        VideoList();
    }

    private void VideoList() {
        progressDialog.show();
        Call<VideoListResponse> call3 = apiInterface.getVideoList("liststory");
        call3.enqueue(new Callback<VideoListResponse>() {
            @Override
            public void onResponse(Call<VideoListResponse> call, Response<VideoListResponse> response) {
                progressDialog.dismiss();
                VideoListResponse imageListResponse;
                imageListResponse = response.body();
                alVideolist = new ArrayList<>();
                alVideolist.addAll(imageListResponse.getData());
                if (!alVideolist.isEmpty()) {
                    videoListAdapter = new VideoListAdapter(VideoListActivity.this, alVideolist);
                    rcVideos.setAdapter(videoListAdapter);
                } else {
                    Toast.makeText(VideoListActivity.this, "No Video Found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<VideoListResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}