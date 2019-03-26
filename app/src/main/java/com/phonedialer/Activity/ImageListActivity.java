package com.phonedialer.Activity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.facebook.common.file.FileUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.phonedialer.Adapter.ImageListAdapter;
import com.phonedialer.Models.ImageListResponse;
import com.phonedialer.R;
import com.phonedialer.RestApi.ApiClient;
import com.phonedialer.RestApi.ApiInterface;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ImageListActivity extends AppCompatActivity {
    private RecyclerView rvImages;
    private ImageListAdapter imageListAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ImageListResponse.Datum> alCollegelist;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_image_list);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        rvImages = findViewById(R.id.rvImages);
        rvImages.setHasFixedSize(false);
        layoutManager = new LinearLayoutManager(ImageListActivity.this);
        rvImages.setLayoutManager(layoutManager);
        progressDialog = new ProgressDialog(ImageListActivity.this);
        progressDialog.setMessage("Please wait...");
        ImageList();
    }

    private void ImageList() {
        progressDialog.show();
        Call<ImageListResponse> call3 = apiInterface.getImageList("listpost");
        call3.enqueue(new Callback<ImageListResponse>() {
            @Override
            public void onResponse(Call<ImageListResponse> call, Response<ImageListResponse> response) {
                progressDialog.dismiss();
                ImageListResponse imageListResponse;
                imageListResponse = response.body();
                alCollegelist = new ArrayList<>();
                alCollegelist.addAll(imageListResponse.getData());
                if (!alCollegelist.isEmpty()) {
                    imageListAdapter = new ImageListAdapter(ImageListActivity.this, alCollegelist);
                    rvImages.setAdapter(imageListAdapter);
                } else {
                    Toast.makeText(ImageListActivity.this, "No Image Found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ImageListResponse> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
