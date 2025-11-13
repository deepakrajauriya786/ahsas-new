package com.nativegame.juicymatch.ui.activity.voiceCallAgora;//package in.ahsas.dating.ui.activity.voiceCallAgora;
//import static in.ahsas.dating.ui.config.apicontroller.shop_logo_url;
//import android.graphics.PorterDuff;
//import android.os.Bundle;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import in.ahsas.dating.R;
//import androidx.core.content.ContextCompat;
//import in.ahsas.dating.databinding.ActivityChatRoomBinding;
//import in.ahsas.dating.databinding.ActivityVoiceCallBinding;
//import in.ahsas.dating.ui.activity.voiceCallAgora.media.RtcTokenBuilder2;
//import in.ahsas.dating.ui.models.User;
//import io.agora.rtc2.ChannelMediaOptions;
//import io.agora.rtc2.Constants;
//import io.agora.rtc2.IRtcEngineEventHandler;
//import io.agora.rtc2.RtcEngine;
//import io.agora.rtc2.RtcEngineConfig;
//import android.Manifest;
//import android.content.pm.PackageManager;
//import android.os.CountDownTimer;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//
//public class VoiceCallActivity extends AppCompatActivity {
//    // Fill in the app ID from Agora Console
//    private String appId = "b81e74ec590d40ef8398a799d781ff7a";
//    // Fill in the channel name
//    private String channelName = "";
//    int user_id;
//    // Fill in the temporary token generated from Agora Console
//    //    private String token = "007eJxTYFAMTpXQO72yR1vp2dffcy8FRBx7a3BIwP1Og/KTMOE7n/IUGJIsDFPNTVKTTS0NUkwMUtMsjC0tEs0tLVPMLQzT0swT91ibpzcEMjKk9TxmZGSAQBCfg6EktbgkMy/dkIEBAAT5Ico=";
//    private String token = "";
////    live token generated from Agora Console
////    private String token = "007eJxTYFjy5PMnnkll9gt+3PtUe7PIqjJj1tMTJuGuneskngfyuD1QYEiyMEw1N0lNNrU0SDExSE2zMLa0SDS3tEwxtzBMSzNPLLAxTxfgY2BISXuqz8gAgSA+B0NJanFJZl66IReDkYGFkbGJoZG5MQDgXCOn";
//
//    private static final int PERMISSION_REQ_ID = 22;
//    // Get the value of the environment variable AGORA_APP_CERTIFICATE. Make sure you set this variable to the App certificate you obtained from Agora console
//    static String appCertificate = "05e3eb5658614f0395a9fcddcd4da618";
//    // Replace channelName with the name of the channel you want to join
//    // Fill in your actual user ID
//    static int uid = 0;
//    // Token validity time in seconds
//    static int tokenExpirationInSeconds = 3600;
//    // The validity time of all permissions in seconds
//    static int privilegeExpirationInSeconds = 180;
//    private RtcEngine mRtcEngine;
//
//    private final IRtcEngineEventHandler mRtcEventHandler = new IRtcEngineEventHandler() {
//        // Callback when successfully joining the channel
//        @Override
//        public void onJoinChannelSuccess(String channel, int uid, int elapsed) {
//            super.onJoinChannelSuccess(channel, uid, elapsed);
//            runOnUiThread(() -> {
//                Log.d("receiver_id", String.valueOf(uid));
//                Toast.makeText(VoiceCallActivity.this, "Join channel success" + uid, Toast.LENGTH_SHORT).show();
//            });
//        }
//
//        // Callback when a remote user or host joins the current channel
//        @Override
//        // Listen for remote hosts in the channel to get the host's uid information
//        public void onUserJoined(int uid, int elapsed) {
//            super.onUserJoined(uid, elapsed);
//            runOnUiThread(() -> {
//                Toast.makeText(VoiceCallActivity.this, "User joined: " + uid, Toast.LENGTH_SHORT).show();
//            });
//        }
//
//        // Callback when a remote user or host leaves the current channel
//        @Override
//        public void onUserOffline(int uid, int reason) {
//            super.onUserOffline(uid, reason);
//            runOnUiThread(() -> {
//                Toast.makeText(VoiceCallActivity.this, "User offline: " + uid, Toast.LENGTH_SHORT).show();
//            });
//        }
//
//        @Override
//        public void onUserMuteAudio(int uid, boolean muted) {
//            super.onUserMuteAudio(uid, muted);
//        }
//    };
//
//    // Obtain the permissions required for experiencing real-time audio interaction
//    private String[] getRequiredPermissions() {
//        // Determine the permissions required when targetSDKVersion is 31 or above
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.S) {
//            return new String[]{
//                    Manifest.permission.RECORD_AUDIO, // Audio recording permission
//                    Manifest.permission.READ_PHONE_STATE, // Read phone state permission
//                    Manifest.permission.BLUETOOTH_CONNECT // Bluetooth connection permission
//            };
//        } else {
//            return new String[]{
//                    Manifest.permission.RECORD_AUDIO,
//            };
//        }
//    }
//
//    private boolean checkPermissions() {
//        for (String permission : getRequiredPermissions()) {
//            int permissionCheck = ContextCompat.checkSelfPermission(this, permission);
//            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private void initializeAndJoinChannel() {
//        try {
//
//            // Create an RtcEngineConfig object and configure it
//            RtcEngineConfig config = new RtcEngineConfig();
//            config.mContext = getBaseContext();
//            config.mAppId = appId;
//            config.mEventHandler = mRtcEventHandler;
//            // Create and initialize the RtcEngine
//            mRtcEngine = RtcEngine.create(config);
//        } catch (Exception e) {
//            Toast.makeText(VoiceCallActivity.this, "Error: " + e, Toast.LENGTH_SHORT).show();
//
//            throw new RuntimeException("Check the error.");
//        }
//        // Create a ChannelMediaOptions object and configure it
//        ChannelMediaOptions options = new ChannelMediaOptions();
//        // Set the user role to BROADCASTER (host) or AUDIENCE (audience)
//        options.clientRoleType = Constants.CLIENT_ROLE_BROADCASTER;
//        // Set the channel profile
//        options.channelProfile = Constants.CHANNEL_PROFILE_COMMUNICATION;
//        // Publish the audio collected by the microphone
//        options.publishMicrophoneTrack = true;
//        // Automatically subscribe to all audio streams
//        options.autoSubscribeAudio = true;
//        // Join the channel using a uid, temporary token and channel name.
//        // Ensure that the uid is unique within the channel.
//        // If you set the uid to 0, the engine  generates a random uid.
//        // The onJoinChannelSuccess callback is triggered upon success.
//        mRtcEngine.joinChannel(token, channelName, user_id, options);
//    }
//    // Get the permissions required for experiencing real-time audio interaction
//
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (checkPermissions()) {
//            initializeAndJoinChannel();
//        }
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        if (mRtcEngine != null) {
//            // Leave the channel
//            mRtcEngine.leaveChannel();
//            mRtcEngine = null;
//            // Destroy the engine
//            RtcEngine.destroy();
//        }
//    }
//
//    public void onEncCallClicked(View view) {
//        if (mRtcEngine != null) {
//            // Leave the channel
//            mRtcEngine.leaveChannel();
//            mRtcEngine = null;
//            // Destroy the engine
//            RtcEngine.destroy();
//        }
//        finish();
//    }
//
//    public void onLocalAudioMuteClicked(View view) {
//        ImageView iv = (ImageView) view;
//        if (iv.isSelected()) {
//            iv.setSelected(false);
//            iv.clearColorFilter();
//        } else {
//            iv.setSelected(true);
//            iv.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
//        }
//
//        // Stops/Resumes sending the local audio stream.
//        mRtcEngine.muteLocalAudioStream(iv.isSelected());
//    }
//
//    // Tutorial Step 5
//    public void onSwitchSpeakerphoneClicked(View view) {
//        ImageView iv = (ImageView) view;
//        if (iv.isSelected()) {
//            iv.setSelected(false);
//            iv.clearColorFilter();
//        } else {
//            iv.setSelected(true);
//            iv.setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.MULTIPLY);
//        }
//
//        // Enables/Disables the audio playback route to the speakerphone.
//        //
//        // This method sets whether the audio is routed to the speakerphone or earpiece. After calling this method, the SDK returns the onAudioRouteChanged callback to indicate the changes.
//        mRtcEngine.setEnableSpeakerphone(view.isSelected());
//    }
//
//// Gets RtmCallManager instance
////    RtmCallManager = RtmClient.getRtmCallManager();
//
////    void inviteCall(final String peerUid, final String channel) {
////        // Creates LocalInvitation
////        LocalInvitation invitation = RtmCallManager.createLocalInvitation(peerUid);
////        invitation.setContent(channel);
////        // Sends call invitation
////        RtmCallManager.sendLocalInvitation(invitation);
////    }
////
////    // Cancel a call invitation.
////    void cancelLocalInvitation() {
////        if (RtmCallManager != null && invitation != null) {
////            RtmCallManager.cancelLocalInvitation(invitation);
////        }
////    }
////
////    // Accept a call invitation.
////    void answerCall(final RemoteInvitation invitation) {
////        if (RtmCallManager != null && invitation != null) {
////            RtmCallManager.acceptRemoteInvitation(invitation);
////        }
////    }
////
////    // Refuse a call invitation.
////    void refuseRemoteInvitation(@NonNull RemoteInvitation invitation) {
////        if (RtmCallManager != null) {
////            RtmCallManager.refuseRemoteInvitation(invitation);
////        }
////    }
//
//
//    User user;
//    ActivityVoiceCallBinding binding;
//    int time = 5;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityVoiceCallBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        user = new User(this);
//        user_id = Integer.parseInt(user.getUserid());
//        channelName =getIntent().getStringExtra("receiver_id");
//        binding.name.setText(getIntent().getStringExtra("name"));
//        binding.time.setText("( ₹ " + time + "/mins )");
//
//        binding.backArrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mRtcEngine != null) {
//                    // Leave the channel
//                    mRtcEngine.leaveChannel();
//                    mRtcEngine = null;
//                    // Destroy the engine
//                    RtcEngine.destroy();
//                }
//                finish();
//            }
//        });
//
//        Glide.with(getApplicationContext())
//                .load(shop_logo_url + getIntent().getStringExtra("image")).placeholder(R.drawable.logo).into(binding.profileImage);
// Glide.with(getApplicationContext())
//                .load(shop_logo_url + getIntent().getStringExtra("image")).placeholder(R.drawable.logo).into(binding.profileImage1);
//
//
//        RtcTokenBuilder2 token1 = new RtcTokenBuilder2();
//        token = token1.buildTokenWithUid(appId, appCertificate, channelName, uid, RtcTokenBuilder2.Role.ROLE_SUBSCRIBER,
//                tokenExpirationInSeconds, privilegeExpirationInSeconds);
//
//        Log.d("tokenhgfyg", token);
//
//        Log.d("receiver_id", String.valueOf(user_id));
//
//        if (checkPermissions()) {
//            initializeAndJoinChannel();
//        } else {
//            ActivityCompat.requestPermissions(this, getRequiredPermissions(), PERMISSION_REQ_ID);
//        }
//
//        countdata();
//
//
//    }
//
//
//    private void countdata() {
//        new CountDownTimer(time * 60 * 1000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                int seconds = (int) (millisUntilFinished / 1000);
//                int minutes = seconds / 60;
//                seconds = seconds % 60;
//                binding.imageView5.setText(String.format("%02d", minutes)
//                        + ":" + String.format("%02d", seconds));
//            }
//
//            public void onFinish() {
//                binding.imageView5.setText("END");
//                if (mRtcEngine != null) {
//                    // Leave the channel
//                    mRtcEngine.leaveChannel();
//                    mRtcEngine = null;
//                    // Destroy the engine
//                    RtcEngine.destroy();
//                }
//                finish();
//            }
//        }.start();
//
//    }
//}