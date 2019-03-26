package com.phonedialer.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.phonedialer.Models.UploadImageResponse;
import com.phonedialer.Models.VideoUploadResponse;
import com.phonedialer.R;
import com.phonedialer.RestApi.ApiClient;
import com.phonedialer.RestApi.ApiInterface;
import com.phonedialer.Utils.GlobalMethods;

import java.io.File;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadVideoActivity extends AppCompatActivity {

    private final int VIDEO_PICK_FROM_GALLERY = 1;
    private TextView tvDate, tvTime, tvVideo;
    private Button btnSend;
    private String strDate, strTime;
    private Calendar calendar;
    private int year, month, day, hour, min;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    private File fileVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_upload_video);

        progressDialog = new ProgressDialog(UploadVideoActivity.this);
        progressDialog.setMessage("Please wait...");

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        tvVideo = findViewById(R.id.tvVideo);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        btnSend = findViewById(R.id.btnSend);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UploadImage();
            }
        });
        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(111);
            }
        });

        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(222);
            }
        });
        tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectVideo();
            }
        });
    }

    private void SelectVideo() {
        try {
            Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, VIDEO_PICK_FROM_GALLERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == VIDEO_PICK_FROM_GALLERY && resultCode == RESULT_OK && null != data) {
                // Get the video from data
                Uri selectedVideo = data.getData();
                String[] filePathColumn = {MediaStore.Video.Media.DATA};
                Cursor cursor = getContentResolver().query(selectedVideo, filePathColumn, null, null, null);
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String decodableString = cursor.getString(columnIndex);
                tvVideo.setText(decodableString);
                cursor.close();
                fileVideo = new File(decodableString);
                Log.e("Log", "File choose done");
            } else {
                Toast.makeText(this, "You haven't picked any video", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        try {
            switch (id) {
                case 111:
                    DatePickerDialog dpd = new DatePickerDialog(this, myDateListener, year, month, day);
                    dpd.getDatePicker().setMinDate(calendar.getTimeInMillis());
                    return dpd;

                case 222:
                    TimePickerDialog tpd = new TimePickerDialog(this, timePickerListener, hour, min, false);
                    return tpd;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            month = selectedMonth;
            day = selectedDay;
            strDate = String.valueOf(new StringBuilder().append(year).append("-").append(month + 1).append("-").append(day));
            tvDate.setText(strDate);
        }
    };
    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {
            hour = hourOfDay;
            min = minutes;
            strTime = hour + ":" + min;
            tvTime.setText(strTime);
        }
    };

    private void UploadImage() {
        progressDialog.show();
        File file = new File(String.valueOf(fileVideo));
        RequestBody mFile = RequestBody.create(MediaType.parse("video*/"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("Story", file.getName(), mFile);
        RequestBody datetime = RequestBody.create(MediaType.parse("text/plain"), strDate + " " + strTime);
        RequestBody method = RequestBody.create(MediaType.parse("text/plain"), "sendstory");
        Call<VideoUploadResponse> call3 = apiInterface.videoUpload(method, datetime, fileToUpload);
        call3.enqueue(new Callback<VideoUploadResponse>() {
            @Override
            public void onResponse(Call<VideoUploadResponse> call, Response<VideoUploadResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(UploadVideoActivity.this, "Video Upload Successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<VideoUploadResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UploadVideoActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
