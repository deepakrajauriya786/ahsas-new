package com.nativegame.juicymatch.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.FirebaseDatabase;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.models.MessageModel;
import com.nativegame.juicymatch.ui.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatAdapter extends RecyclerView.Adapter{

    ArrayList<MessageModel> messageModels;
    Context context;
    String recId;
    User user;
    int SENDER_VIEW_TYPE = 1;
    int RECIEVER_VIEW_TYPE = 2;

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    public ChatAdapter(ArrayList<MessageModel> messageModels, Context context, String recId) {
        this.messageModels = messageModels;
        this.context = context;
        this.recId = recId;
        user = new User(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sender, parent, false);
            return new SenderViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciever, parent, false);
            return new RecieverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (messageModels.get(position).getuId().equals(user.getSender_id()))
        {
            return SENDER_VIEW_TYPE;
        }
        else {
            return RECIEVER_VIEW_TYPE;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessageModel messageModel = messageModels.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete message")
                        .setMessage("Are you sure you what to delete this message")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String receiverRoom = recId + user.getSender_id();
                                database.getReference().child("chats").child(receiverRoom)
                                        .child(messageModel.getMessageId())
                                        .removeValue();

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .show();

                return false;
            }
        });

        Date date=new Date(messageModel.getTimestamp());
        SimpleDateFormat sfd = new SimpleDateFormat("hh:mm aa");
        String ff=  sfd.format(date);


        if (holder.getClass() == SenderViewHolder.class){
            if (messageModel.getIsImage()==1){
                ((SenderViewHolder)holder).image.setVisibility(View.VISIBLE);
                ((SenderViewHolder)holder).ll.setVisibility(View.GONE);
                Glide.with(context).load(messageModel.getMessage()).placeholder(R.drawable.loader).into(((SenderViewHolder)holder).image);
            }else {
                ((SenderViewHolder)holder).image.setVisibility(View.GONE);
                ((SenderViewHolder)holder).ll.setVisibility(View.VISIBLE);
                ((SenderViewHolder)holder).senderMsg.setText(messageModel.getMessage());
                ((SenderViewHolder)holder).senderTime.setText(ff);
            }

        }
        else {
            if (messageModel.getIsImage()==1){
                ((RecieverViewHolder)holder).image.setVisibility(View.VISIBLE);
                ((RecieverViewHolder)holder).ll.setVisibility(View.GONE);
                Glide.with(context).load(messageModel.getMessage()).placeholder(R.drawable.loader).into(((RecieverViewHolder)holder).image);
            }else {
                ((RecieverViewHolder)holder).image.setVisibility(View.GONE);
                ((RecieverViewHolder)holder).ll.setVisibility(View.VISIBLE);
                ((RecieverViewHolder)holder).recieverMsg.setText(messageModel.getMessage());
                ((RecieverViewHolder)holder).recieverTime.setText(ff);
            }

        }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class RecieverViewHolder extends RecyclerView.ViewHolder {
        ImageView image ;
        LinearLayout ll;
        TextView recieverMsg, recieverTime;

        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            recieverMsg= itemView.findViewById(R.id.recieverText);
            recieverTime= itemView.findViewById(R.id.recieverTime);
            ll= itemView.findViewById(R.id.ll);
            image= itemView.findViewById(R.id.image);

        }

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
