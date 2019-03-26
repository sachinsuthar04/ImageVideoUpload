package com.phonedialer.RestApi;


import com.phonedialer.Models.ImageListResponse;
import com.phonedialer.Models.UploadImageResponse;
import com.phonedialer.Models.VideoListResponse;
import com.phonedialer.Models.VideoUploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {

    @FormUrlEncoded
    @POST("./")
    Call<ImageListResponse> getImageList(@Field("method") String method);

    @Multipart
    @POST("./")
    Call<UploadImageResponse> imageUpload(@Part("method") RequestBody method, @Part("Description") RequestBody desc,
                                          @Part("DateTime") RequestBody datetime, @Part MultipartBody.Part file);

    @FormUrlEncoded
    @POST("./")
    Call<VideoListResponse> getVideoList(@Field("method") String method);

    @Multipart
    @POST("./")
    Call<VideoUploadResponse> videoUpload(@Part("method") RequestBody method,
                                          @Part("DateTime") RequestBody datetime, @Part MultipartBody.Part file);

}

