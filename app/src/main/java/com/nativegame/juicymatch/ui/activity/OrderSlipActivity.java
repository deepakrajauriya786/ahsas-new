package com.nativegame.juicymatch.ui.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
//import com.theartofdev.edmodo.cropper.CropImage;
//import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.util.List;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.databinding.ActivityOrderSlipBinding;
import com.nativegame.juicymatch.ui.config.RealPathUtil;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.LoginModels;
import com.nativegame.juicymatch.ui.models.MessageModels;

import in.ekqr.lib.PaymentCallback;
import in.ekqr.lib.PaymentConfig;
import in.ekqr.lib.PaymentResult;
import in.ekqr.lib.PaymentSdk;
import in.ekqr.lib.PgError;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.nativegame.juicymatch.ui.models.PaymentModel;
import com.nativegame.juicymatch.ui.models.SliderModels;
import com.nativegame.juicymatch.ui.models.User;


public class OrderSlipActivity extends AppCompatActivity implements PaymentCallback {
    ActivityOrderSlipBinding binding;
    User user;
    String path;
    MultipartBody.Part body;
    List<LoginModels> data;
    KProgressHUD hud;
    String d_id = "";
    String amount = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderSlipBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        d_id = getIntent().getStringExtra("d_id");
        binding.coupon.setText("Get 20% Discount on rechage above ₹ " + 999);
        user = new User(getApplicationContext());


        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.planets_array,
                R.layout.spinner_my
        );
// Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                binding.coupon.setText("Get 20% Discount on rechage above ₹ " + 999);

                String selectedItem = (String) adapterView.getItemAtPosition(i);

                if (selectedItem.equals("11")) {
                    amount = "11";

                }
                if (selectedItem.equals("121")) {
                    amount = "121";

                }
                if (selectedItem.equals("251")) {
                    amount = "251";
                }
                if (selectedItem.equals("999")) {
                    Double dd = Double.valueOf((999 * 20 / 100));

                    amount = String.valueOf(dd + 999);
                    binding.coupon.setText("Get 20% Discount on rechage above ₹ " + 999 + "\n You Got " + dd + " coin on your Total Coin " + amount);

                }
                if (selectedItem.equals("5999")) {
                    Double dd = Double.valueOf((5999 * 40 / 100));

                    amount = String.valueOf(dd + 5999);
                    binding.coupon.setText("Get 20% Discount on rechage above ₹ " + 999 + "\n You Got " + dd + " coin on your Total Coin " + amount);


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        binding.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);

//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setAutoZoomEnabled(true)
////                        .setCropShape(CropImageView.CropShape.OVAL)
////                        .setAspectRatio(160, 160)
//                        .start(OrderSlipActivity.this);

            }
        });
        binding.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);

//                CropImage.activity()
//                        .setGuidelines(CropImageView.Guidelines.ON)
//                        .setAutoZoomEnabled(true)
////                        .setCropShape(CropImageView.CropShape.OVAL)
////                        .setAspectRatio(160, 160)
//                        .start(OrderSlipActivity.this);

            }
        });
        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();

            }
        });


        binding.myprofilesubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (path == null) {
//                    Toast.makeText(OrderSlipActivity.this, "Please Select First Image", Toast.LENGTH_SHORT).show();
//                    binding.image.requestFocus();
//                    return;
//                } else {
////                    Toast.makeText(OrderSlipActivity.this, "Order Upload Successfully.", Toast.LENGTH_SHORT).show();
//
//                    createDemand();
//                }

                if(amount.toString().isEmpty()){
                    Toast.makeText(OrderSlipActivity.this, "Please Select Amount."+amount, Toast.LENGTH_SHORT).show();

                }else{
                    payment();
                }




            }
        });


    }

    private void payment() {

        Call<List<PaymentModel>> call = apicontroller.getapi().createOrder(user.getUserid(),amount);
        call.enqueue(new Callback<List<PaymentModel>>() {
            @Override
            public void onResponse(Call<List<PaymentModel>> call, Response<List<PaymentModel>> response) {
                List<PaymentModel> data = response.body();

                Gson gson = new Gson();
                String ff = gson.toJson(data);
                Log.e("ffgghffgg", ff);


                if (response.code() == 200){
                    if (data.get(0).getMessage().toString().equals("1")) {

//                        Intent intent = new Intent(getApplicationContext(),UpiGatewayActivity.class);
//                        intent.putExtra("url",data.get(0).getPayment_url());
//                        intent.putExtra("amount", amount);
//                        startActivity(intent);

                        Log.d("dsfsdfsdsfd",data.get(0).getMsg().toString());

                        PaymentSdk.init(
                                new PaymentConfig.Builder(OrderSlipActivity.this)
                                        .sessionId(data.get(0).getPayment_url())
                                        .callback(OrderSlipActivity.this)
                                        .build());
                        PaymentSdk.startPayment(OrderSlipActivity.this);

                    }

                    if (data.get(0).getMessage().toString().equals("0")) {
                        Toast.makeText(getApplicationContext(), "Failed ! Please try again.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Failed ! Please try again.", Toast.LENGTH_SHORT).show();

                }


            }

            @Override
            public void onFailure(Call<List<PaymentModel>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void createDemand() {
        hud = KProgressHUD.create(OrderSlipActivity.this)
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
        RequestBody desc = RequestBody.create(MediaType.parse("multipart/form-data"), amount);
        RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"), "1");

        Call<List<MessageModels>> call = apicontroller.getInstance().getapi().payment_upload(uuuu, desc, type, body);
        call.enqueue(new Callback<List<MessageModels>>() {
            @Override
            public void onResponse(Call<List<MessageModels>> call, Response<List<MessageModels>> response) {
                List<MessageModels> data1 = response.body();
                hud.dismiss();

                if (Integer.parseInt(data1.get(0).getMessage()) == 1) {
                    Toast.makeText(getApplicationContext(), "Upload Successfully.", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
                if (Integer.parseInt(data1.get(0).getMessage()) == 0) {
                    Toast.makeText(getApplicationContext(), "Failed ! Please try again.", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<MessageModels>> call, Throwable t) {
                hud.dismiss();
                Toast.makeText(OrderSlipActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            path = RealPathUtil.getRealPath(this, uri);
            Bitmap bitmap = BitmapFactory.decodeFile(path);
            binding.image.setImageBitmap(bitmap);
        }


    }

    @Override
    public void onSuccess(@NonNull PaymentResult paymentResult) {
        Log.d("Success", paymentResult.transactionId);
        onTransactionSuccess(paymentResult.transactionId);

        Toast.makeText(this, "Success -> TransactionId" + paymentResult.transactionId, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailure(@NonNull PgError pgError) {
        Log.d("Success", pgError.toString());
        Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancel() {
        Log.d("Success", "cancelled");
        Toast.makeText(this, "Cancel", Toast.LENGTH_SHORT).show();
    }

    private void onTransactionSuccess(String client_txn_id) {


        Call<List<MessageModels>> call = apicontroller.getInstance().getapi().payment_confirm(user.getUserid(), amount, client_txn_id);
        call.enqueue(new Callback<List<MessageModels>>() {
            @Override
            public void onResponse(Call<List<MessageModels>> call, Response<List<MessageModels>> response) {
                List<MessageModels> data = response.body();

                Gson gson = new Gson();
                String ff = gson.toJson(data);
                Log.e("ffgghffgg", ff);

                if (String.valueOf(response.body()).contains("null")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderSlipActivity.this);
                    TextView textView = new TextView(OrderSlipActivity.this);

                    textView.setText("Payment Failed ");
                    textView.setPadding(20, 30, 20, 30);
                    textView.setTextSize(20F);
                    textView.setBackgroundColor(Color.RED);
                    textView.setTextColor(Color.BLACK);


                    builder.setCustomTitle(textView);
                    builder.setIcon(R.drawable.close);
                    builder.setMessage("Payment Failed Please Try Again .");
                    builder.setNegativeButton("Close", (dialog, i) -> {

                        startActivity(new Intent(OrderSlipActivity.this, MainActivity.class));
                        finish();
                        dialog.dismiss();

                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);


                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(OrderSlipActivity.this);
                    TextView textView = new TextView(OrderSlipActivity.this);

                    textView.setText("Payment Success ");
                    textView.setPadding(20, 30, 20, 30);
                    textView.setTextSize(20F);
                    textView.setBackgroundColor(Color.GREEN);
                    textView.setTextColor(Color.BLACK);


                    builder.setCustomTitle(textView);
                    builder.setIcon(R.drawable.ic_success);
                    builder.setMessage("Payment Successfully Added in Wallet .");
                    builder.setNegativeButton("Close", (dialog, i) -> {

                        startActivity(new Intent(OrderSlipActivity.this, MainActivity.class));
                        finish();
                        dialog.dismiss();

                    });

                    AlertDialog dialog = builder.create();
                    dialog.show();
                    dialog.setCancelable(false);
                    dialog.setCanceledOnTouchOutside(false);
                    dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
                    dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);


                }


            }

            @Override
            public void onFailure(Call<List<MessageModels>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });


    }


}