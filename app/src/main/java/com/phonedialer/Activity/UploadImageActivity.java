package com.phonedialer.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
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
import com.facebook.drawee.view.SimpleDraweeView;
import com.phonedialer.Models.UploadImageResponse;
import com.phonedialer.R;
import com.phonedialer.RestApi.ApiClient;
import com.phonedialer.RestApi.ApiInterface;
import com.phonedialer.Utils.AlertDialogUtility;
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

public class UploadImageActivity extends AppCompatActivity {

    private Uri uriPhoto;
    private final int PHOTO_PICK_FROM_GALLERY = 1, PHOTO_CLICK_FROM_CAMERA = 2;
    private File filepath, cropfilepath;
    private final int PIC_CROP = 3;
    private SimpleDraweeView ivUpload;
    private TextView tvDate, tvTime;
    private EditText etDesc;
    private Button btnSend;
    private String strDate, strTime;
    private Calendar calendar;
    private int year, month, day, hour, min;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_upload_image);

        progressDialog = new ProgressDialog(UploadImageActivity.this);
        progressDialog.setMessage("Please wait...");

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        ivUpload = findViewById(R.id.ivUpload);
        tvDate = findViewById(R.id.tvDate);
        tvTime = findViewById(R.id.tvTime);
        etDesc = findViewById(R.id.etDesc);
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
        ivUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCameraAndGalleryDialog();
            }
        });
    }

    private void openCameraAndGalleryDialog() {
        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(UploadImageActivity.this);
            builder.setTitle("");
            builder.setMessage("Choose Profile Pic");
            builder.setPositiveButton("Use Gallery", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    createCropFolder();
                    openGallery();
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("Use Camera", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    createCropFolder();
                    openCamera();
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openCamera() {
        try {
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            uriPhoto = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "myFile.jpg"));
            Intent cameraIntent = new Intent();
            cameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uriPhoto);
            cameraIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(cameraIntent, PHOTO_CLICK_FROM_CAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openGallery() {
        try {
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            uriPhoto = Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "myFile.jpg"));
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            if (android.os.Build.VERSION.SDK_INT > 10) {
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
            }
            startActivityForResult(intent, PHOTO_PICK_FROM_GALLERY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createCropFolder() {
        File folder1 = new File(Environment.getExternalStorageDirectory() + "/Pictures");
        if (!folder1.exists()) {
            folder1.mkdir();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PHOTO_PICK_FROM_GALLERY) {
            uriPhoto = data.getData();
            if (uriPhoto.toString().contains("content://com.google.android.apps.photos.content/0/")) {
                filepath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/sent_" + System.currentTimeMillis() + ".png");
            } else if (uriPhoto.toString().contains("content://com.google.android.apps.photos.content/1/")) {
                filepath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/sent_" + System.currentTimeMillis() + ".mp4");
            }
            if (filepath != null) {
                uriPhoto = getImageContentUri(UploadImageActivity.this, filepath);
                if (uriPhoto != null) {
                    performCrop(uriPhoto);
                } else {
                }
            } else {
                if (uriPhoto != null) {
                    performCrop(uriPhoto);
                } else {
                }
            }
        } else if (resultCode == RESULT_OK && requestCode == PHOTO_CLICK_FROM_CAMERA) {
            if (uriPhoto != null) {
                performCrop(uriPhoto);
            }
        } else if (requestCode == PIC_CROP && resultCode == RESULT_OK) {
            try {
                if (data != null) {
                    Bitmap thePic = getBitmapFromFile(cropfilepath);
                    String strBAse64 = GlobalMethods.bitmapToBase64(thePic);
                    ivUpload.setImageURI(Uri.fromFile(cropfilepath));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void performCrop(Uri UriCamera) {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(UriCamera, "image/*");
//            cropIntent.putExtra("crop", "true");
//            cropIntent.putExtra("aspectX", 1);
//            cropIntent.putExtra("aspectY", 1);
//            cropIntent.putExtra("outputX", 256);
//            cropIntent.putExtra("outputY", 256);
            createCropFolder();
            cropfilepath = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/sent_" + System.currentTimeMillis() + ".jpg");
            cropIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cropfilepath));
            cropIntent.putExtra("output", Uri.fromFile(cropfilepath));
            cropIntent.putExtra("return-data", false);
            // start the activity - we handle returning in onActivityResult
            startActivityForResult(cropIntent, PIC_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "Whoops - your device doesn't support the crop action!";
        }
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            cursor.close();
            return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }

    private Bitmap getBitmapFromFile(File photoPath) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            return BitmapFactory.decodeFile(photoPath.getAbsolutePath(), options);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        File file = new File(String.valueOf(cropfilepath));
        RequestBody mFile = RequestBody.create(MediaType.parse("image*/"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("Image", file.getName(), mFile);
        RequestBody desc = RequestBody.create(MediaType.parse("text/plain"), etDesc.getText().toString());
        RequestBody datetime = RequestBody.create(MediaType.parse("text/plain"), strDate + " " + strTime);
        RequestBody method = RequestBody.create(MediaType.parse("text/plain"), "sendpost");
        Call<UploadImageResponse> call3 = apiInterface.imageUpload(method, desc, datetime, fileToUpload);
        call3.enqueue(new Callback<UploadImageResponse>() {
            @Override
            public void onResponse(Call<UploadImageResponse> call, Response<UploadImageResponse> response) {
                progressDialog.dismiss();
                Toast.makeText(UploadImageActivity.this, "Image Upload Successfully", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UploadImageResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(UploadImageActivity.this, "Failed " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
