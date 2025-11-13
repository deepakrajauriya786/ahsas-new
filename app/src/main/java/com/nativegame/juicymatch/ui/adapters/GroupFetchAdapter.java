package com.nativegame.juicymatch.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.activity.voiceCallAgora.GroupCallActivity;
import com.nativegame.juicymatch.ui.models.MessageModel;
import com.nativegame.juicymatch.ui.models.User;

import java.util.ArrayList;

public class GroupFetchAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModels;
    Context context;
    String recId;
    User user;
    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;

    public GroupFetchAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    public GroupFetchAdapter(ArrayList<MessageModel> messageModels, Context context, String recId) {
        this.messageModels = messageModels;
        this.context = context;
        this.recId = recId;
        user = new User(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.group_fetch, parent, false);
            return new SenderViewHolder(view);


    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel = messageModels.get(position);

        ((SenderViewHolder)holder).senderMsg.setText("Group Host Name : "+messageModel.getMessage() + "\nGroup ID : " + messageModel.getMessageId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), GroupCallActivity.class);
                intent.putExtra("receiver_id", messageModel.getMessageId());
                holder.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }


    public class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView senderMsg, senderTime;
        ImageView image ;
        LinearLayout ll;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            ll= itemView.findViewById(R.id.ll);
            image= itemView.findViewById(R.id.image);
            senderMsg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);

        }
    }


}























