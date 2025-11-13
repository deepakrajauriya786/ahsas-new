package com.nativegame.juicymatch.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.gson.Gson;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.MessageModels;
import com.nativegame.juicymatch.ui.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpiGatewayActivity extends AppCompatActivity  {
    WebView mWebView;
    Context context;
    String amount;


    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_gateway);
        user = new User(this);
        if (0 != (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE)) {
            WebView.setWebContentsDebuggingEnabled(true);
        }

        context = this;
        mWebView = (WebView) findViewById(R.id.payment_webview);
        initWebView();

        // 👍 Call the Create Order API from your server and you will get the Payment URL.
        //    you will also get UPI intent if you are using Enterprise Plan.
        //    you can use upi intent in payment url and it will directly ask for UPI App.
        // 🚫 Do not Call UPIGateway API in Android App Directly
        String PAYMENT_URL = getIntent().getStringExtra("url");
        amount = getIntent().getStringExtra("amount");
//        String PAYMENT_URL = "upi://pay?pa=...";

        Log.d("ffgghffgg",PAYMENT_URL);

        if (PAYMENT_URL.startsWith("upi:")) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(PAYMENT_URL));
            startActivity(intent);
        } else {
            mWebView.loadUrl(PAYMENT_URL);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.getSettings().setSupportMultipleWindows(true);
        // Do not change Useragent otherwise it will not work. even if not working uncommit below
        // mWebView.getSettings().setUserAgentString("Mozilla/5.0 (Linux; Android 4.4.4; One Build/KTU84L.H4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.135 Mobile Safari/537.36");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.addJavascriptInterface(new WebviewInterface(), "Interface");
    }

    public class WebviewInterface {
        @JavascriptInterface
        public void paymentResponse(String client_txn_id, String txn_id) {
            Log.i("dffddff", client_txn_id);
            Log.i("dffddff", txn_id);
            // this function is called when payment is done (success, scanning ,timeout or cancel by user).
            // You must call the check order status API in server and get update about payment.
            // 🚫 Do not Call UpiGateway API in Android App Directly.
//            Toast.makeText(context, "Order ID: "+client_txn_id+", Txn ID: "+txn_id, Toast.LENGTH_SHORT).show();
            // Close the Webview.


            onTransactionSuccess(client_txn_id, txn_id);


        }

        @JavascriptInterface
        public void errorResponse() {
            // this function is called when Transaction in Already Done or Any other Issue.
            Toast.makeText(context, "Transaction Error.", Toast.LENGTH_SHORT).show();
            // Close the Webview.
        }
    }

    private void onTransactionSuccess(String client_txn_id, String txn_id) {


        Call<List<MessageModels>> call = apicontroller.getInstance().getapi().redirect_page(user.getUserid(), amount, client_txn_id, txn_id);
        call.enqueue(new Callback<List<MessageModels>>() {
            @Override
            public void onResponse(Call<List<MessageModels>> call, Response<List<MessageModels>> response) {
                List<MessageModels> data = response.body();

                Gson gson = new Gson();
                String ff = gson.toJson(data);
                Log.e("ffgghffgg", ff);

                if (String.valueOf(response.body()).contains("null")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpiGatewayActivity.this);
                    TextView textView = new TextView(UpiGatewayActivity.this);

                    textView.setText("Payment Failed ");
                    textView.setPadding(20, 30, 20, 30);
                    textView.setTextSize(20F);
                    textView.setBackgroundColor(Color.RED);
                    textView.setTextColor(Color.BLACK);


                    builder.setCustomTitle(textView);
                    builder.setIcon(R.drawable.close);
                    builder.setMessage("Payment Failed Please Try Again .");
                    builder.setNegativeButton("Close", (dialog, i) -> {

                        startActivity(new Intent(UpiGatewayActivity.this, MainActivity.class));
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(UpiGatewayActivity.this);
                    TextView textView = new TextView(UpiGatewayActivity.this);

                    textView.setText("Payment Success ");
                    textView.setPadding(20, 30, 20, 30);
                    textView.setTextSize(20F);
                    textView.setBackgroundColor(Color.GREEN);
                    textView.setTextColor(Color.BLACK);


                    builder.setCustomTitle(textView);
                    builder.setIcon(R.drawable.ic_success);
                    builder.setMessage("Payment Successfully Added in Wallet .");
                    builder.setNegativeButton("Close", (dialog, i) -> {

                        startActivity(new Intent(UpiGatewayActivity.this, MainActivity.class));
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