package com.nativegame.juicymatch.ui.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.models.WalletHistoryModel;

import java.util.List;

public class WalletHistoryAdapter extends RecyclerView.Adapter<WalletHistoryAdapter.myViewHolder>{

    List<WalletHistoryModel> dataProduct;

    public WalletHistoryAdapter(List<WalletHistoryModel> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wallet_history_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.note.setText(dataProduct.get(position).getNote());
        if (dataProduct.get(position).getNote().equals("Chat")){
            holder.date.setText(dataProduct.get(position).getDate()+" - "+dataProduct.get(position).getTime()+ " , Cost : "+dataProduct.get(position).getDuration());

        }else{
            holder.date.setText(dataProduct.get(position).getDate()+" - "+dataProduct.get(position).getTime()+ " , Call Duration : "+dataProduct.get(position).getDuration());

        }

        if (dataProduct.get(position).getType()==1){
            holder.balence.setText("+ ₹ " +dataProduct.get(position).getAmount());
            holder.price.setText("Bal: ₹ "+dataProduct.get(position).getA_bal());
            holder.balence.setTextColor(Color.parseColor("#08C49F"));

        }else {
            holder.price.setText("Bal: ₹ "+dataProduct.get(position).getB_bal());
            holder.balence.setText("- ₹ " +dataProduct.get(position).getAmount());
            holder.balence.setTextColor(Color.parseColor("#D30A05"));
        }







    }

    @Override
    public int getItemCount() {
        if (dataProduct.size() > 0){
            return  dataProduct.size();
        }else {
            return 0;
        }

    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView note,date,price,balence;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            note = itemView.findViewById(R.id.note);
            date = itemView.findViewById(R.id.date);
            price = itemView.findViewById(R.id.price);
            balence = itemView.findViewById(R.id.balence);

        }
    }

}
