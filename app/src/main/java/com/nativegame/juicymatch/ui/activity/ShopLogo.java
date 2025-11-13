package com.nativegame.juicymatch.ui.activity;

import static com.nativegame.juicymatch.ui.config.apicontroller.shop_logo_url;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.databinding.ActivityShopImageBinding;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.LoginModels;
import com.nativegame.juicymatch.ui.models.MessageModels;
import com.nativegame.juicymatch.ui.models.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopLogo extends AppCompatActivity {

    ActivityShopImageBinding binding;
    User user;
    String path;
    MultipartBody.Part body;
    List<LoginModels> data;
    KProgressHUD hud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShopImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user = new User(getApplicationContext());

        // Image click -> open picker
        binding.image1.setOnClickListener(view -> openImagePicker());

        binding.backarrow.setOnClickListener(view -> onBackPressed());

        binding.myprofilesubmit.setOnClickListener(view -> {
            if (path == null) {
                Toast.makeText(ShopLogo.this, "Please Select First Image", Toast.LENGTH_SHORT).show();
                binding.image.requestFocus();
                return;
            } else {
                createDemand();
            }
        });

        login();
    }

    // ✅ Photo Picker (no permission needed)
    private void openImagePicker() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
            pickImageLauncher.launch(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            pickImageLauncher.launch(intent);
        }
    }

    // ✅ Handle result from Photo Picker
    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    Uri uri = result.getData().getData();
                    path = getPathFromUri(this, uri);
                    Glide.with(this).load(uri).into(binding.image);
                } else {
                    Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
                }
            });

    // ✅ Get local path for upload
    private String getPathFromUri(Context context, Uri uri) {
        try {
            File file = new File(context.getCacheDir(), "upload_image.jpg");
            InputStream inputStream = context.getContentResolver().openInputStream(uri);
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            inputStream.close();
            outputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void login() {
        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().vendorProfile(user.getUserid());
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
                data = response.body();
                if (data == null || data.isEmpty()) return;

                if (Integer.parseInt(data.get(0).getMessage()) == 1) {
                    Glide.with(getApplicationContext())
                            .load(shop_logo_url + data.get(0).getShop_logo())
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .placeholder(R.drawable.profile)
                            .into(binding.image);
                } else {
                    Toast.makeText(getApplicationContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LoginModels>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createDemand() {
        hud = KProgressHUD.create(ShopLogo.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();

        if (path != null) {
            File file = new File(path);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            body = MultipartBody.Part.createFormData("logo", file.getName(), requestFile);
        }

        RequestBody uuuu = RequestBody.create(MediaType.parse("multipart/form-data"), user.getUserid());

        Call<List<MessageModels>> call = apicontroller.getInstance().getapi().logoChange(uuuu, body);
        call.enqueue(new Callback<List<MessageModels>>() {
            @Override
            public void onResponse(Call<List<MessageModels>> call, Response<List<MessageModels>> response) {
                hud.dismiss();
                List<MessageModels> data1 = response.body();

                if (data1 == null || data1.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Invalid response", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Integer.parseInt(data1.get(0).getMessage()) == 1) {
                    Toast.makeText(getApplicationContext(), "Image Upload Successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MyProfile.class));
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed! Please try again.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<MessageModels>> call, Throwable t) {
                hud.dismiss();
                Toast.makeText(ShopLogo.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
