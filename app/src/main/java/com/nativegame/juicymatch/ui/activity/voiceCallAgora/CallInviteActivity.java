package com.nativegame.juicymatch.ui.activity.voiceCallAgora;

import static com.nativegame.juicymatch.ui.config.apicontroller.shop_logo_url;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.databinding.ActivityCallInviteBinding;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.LoginModels;
import com.nativegame.juicymatch.ui.models.User;
import com.zegocloud.uikit.ZegoUIKit;
import com.zegocloud.uikit.prebuilt.call.ZegoUIKitPrebuiltCallService;
import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.RoomStateChangedListener;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import im.zego.zegoexpress.constants.ZegoRoomStateChangedReason;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallInviteActivity extends AppCompatActivity {
    User user;
    ActivityCallInviteBinding binding;
    int time = 5;
    int coin = 0;
    private String receiver_id = "";
    private long callStartTime = 0;
    private long callEndTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCallInviteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.d("receiver_id1122", receiver_id);
        user = new User(this);
        receiver_id = getIntent().getStringExtra("receiver_id");
        binding.name.setText(getIntent().getStringExtra("name"));
        binding.time.setText("( " + 5 + " Coins / mins )");
        Glide.with(getApplicationContext())
                .load(shop_logo_url + getIntent().getStringExtra("image")).placeholder(R.drawable.logo).into(binding.profileImage);
        Glide.with(getApplicationContext())
                .load(shop_logo_url + getIntent().getStringExtra("image")).placeholder(R.drawable.logo).into(binding.profileImage1);

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        initVoiceButton();
        walletFetch();


    }


    @Override
    protected void onStart() {
        Log.d("receiver_id", receiver_id);
        super.onStart();
    }

    private void countdata() {
        new CountDownTimer(time * 60 * 1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                binding.imageView5.setText(String.format("%02d", minutes)
                        + ":" + String.format("%02d", seconds));
            }

            public void onFinish() {
                binding.imageView5.setText("END");
                ZegoUIKitPrebuiltCallService.unInit();
                finish();
            }
        }.start();

    }

    private void walletFetch() {

        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().logIn(user.getUserphone());
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
                List<LoginModels> data = response.body();

                if (Integer.parseInt(data.get(0).getMessage()) == 1) {

                    if (!data.get(0).getU_wallet().isEmpty()) {
                        if (Integer.parseInt(data.get(0).getU_wallet()) > 4) {

                            coin = Integer.parseInt(data.get(0).getU_wallet());

                        } else {
                            Toast.makeText(getApplicationContext(), "Please add coins.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Please add coins.", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }
                if (Integer.parseInt(data.get(0).getMessage()) == 0) {
                    Toast.makeText(getApplicationContext(), "Please add coins.", Toast.LENGTH_SHORT).show();
                    finish();
                }


            }

            @Override
            public void onFailure(Call<List<LoginModels>> call, Throwable t) {
                finish();
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void initVoiceButton() {
        ZegoSendCallInvitationButton newVoiceCall = findViewById(R.id.new_voice_call);
        newVoiceCall.setIsVideoCall(false);
        newVoiceCall.setOnClickListener(v -> {
            Log.d("receiver_id", receiver_id);
            String targetUserID = receiver_id;
            String[] split = targetUserID.split(",");
            List<ZegoUIKitUser> users = new ArrayList<>();
            for (String userID : split) {
                String userName = userID + "_name";
                users.add(new ZegoUIKitUser(userID, userName));
            }
            newVoiceCall.setInvitees(users);
            timeDurationEvent();
            Log.d("receiver_id", "" + newVoiceCall.toString());

        });
    }

    private void callTransaction(String duration,String totalDuration) {

        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().callTransaction(user.getSender_id(),receiver_id,duration,totalDuration);
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
                List<LoginModels> data = response.body();
                Gson gson = new Gson();
                String json = gson.toJson(data);
                 Log.d("CallEvent",json.toString());

                if (Integer.parseInt(data.get(0).getMessage()) == 1) {

//                    user.setUserid(data.get(0).getU_id());
//                    binding.appBarMain.balance.setText(data.get(0).getU_wallet()+" Coins");

//                    Toast.makeText(getApplicationContext(), "Success .", Toast.LENGTH_SHORT).show();

                }
                if (Integer.parseInt(data.get(0).getMessage()) == 0) {
//                    Toast.makeText(getApplicationContext(), "Failed ! .", Toast.LENGTH_SHORT).show();
//                    startActivity(new Intent(getApplicationContext(), SignUp.class));
                }


            }

            @Override
            public void onFailure(Call<List<LoginModels>> call, Throwable t) {

                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void timeDurationEvent() {

        ZegoUIKit.addRoomStateChangedListener(new RoomStateChangedListener() {
            @Override
            public void onRoomStateChanged(String roomID,
                                           ZegoRoomStateChangedReason reason,
                                           int errorCode,
                                           JSONObject extendedData) {
                Log.d("CallEvent", "roomID: " + roomID);
                Log.d("CallEvent", "reason: " + reason.name());

                switch (reason) {
                    case LOGINING:
                        Log.d("CallEvent", "Connecting to the room...");
                        break;

                    case LOGINED:
                        Log.d("CallEvent", "Connected to the room.");
                        callStartTime = System.currentTimeMillis();
                        break;

                    case LOGOUT:

                        Log.d("CallEvent", "Disconnected from the room.");
                        callEndTime = System.currentTimeMillis();

                        if (callStartTime != 0) {
                            long durationMillis = callEndTime - callStartTime;
                            long seconds = durationMillis / 1000;
                            String durationStr = formatDuration(seconds);
                            long minutes = (long) Math.ceil(seconds / 60.0);


                            Log.d("CallEvent", "Call duration: " + minutes);
                            binding.imageView5.setText(durationStr + "\nCall Duration Time");
                            Toast.makeText(getApplicationContext(),
                                    "Call End",
                                    Toast.LENGTH_LONG).show();

                            callTransaction(String.valueOf(minutes),durationStr);


                            // Reset
                            callStartTime = 0;
                            callEndTime = 0;
                        }
                        break;

                    default:
                        Log.d("CallEvent", "Other state: " + reason.name());
                        break;
                }
            }
        });
    }

    private String formatDuration(long totalSeconds) {
        long minutes = totalSeconds / 60;
        long seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

//    public void addCallFragment() {
//        long appID = 301281619;
//        String appSign = "18a9a4356a65adfe46b1a1e6274e3685630887cf4ce43009a485f69aea4bff5e";
//
//        String callID = "1";
//        String userID ="1"; // yourUserID, userID should only contain numbers, English characters, and '_'.
//        String userName ="deepak";   // yourUserName
//
//        // You can also use GroupVideo/GroupVoice/OneOnOneVoice to make more types of calls.
//        ZegoUIKitPrebuiltCallConfig config = ZegoUIKitPrebuiltCallConfig.oneOnOneVoiceCall();
//
//        ZegoUIKitPrebuiltCallFragment fragment = ZegoUIKitPrebuiltCallFragment.newInstance(
//                appID, appSign, userID, userName, callID, config);
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, fragment)
//                .commitNow();
//    }

}