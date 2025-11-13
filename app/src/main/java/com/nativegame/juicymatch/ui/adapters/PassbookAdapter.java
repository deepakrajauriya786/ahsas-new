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
import com.nativegame.juicymatch.ui.config.GameCatModel;

import java.util.List;


public class PassbookAdapter extends RecyclerView.Adapter<PassbookAdapter.myViewHolder>{

    List<GameCatModel> dataProduct;
    String endtime;

    public PassbookAdapter(List<GameCatModel> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.passbook_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.note.setText(dataProduct.get(position).getNote());
        holder.date.setText(dataProduct.get(position).getDate());

        if (dataProduct.get(position).getType().equals("0")){
            holder.balence.setText("+ ₹ " +dataProduct.get(position).getAmount());
            holder.price.setText(" Balance: ₹ "+dataProduct.get(position).getA_bal());
            holder.balence.setTextColor(Color.parseColor("#08C49F"));

        }else {
            holder.balence.setText("- ₹ " +dataProduct.get(position).getAmount());
            holder.price.setText(" Balance: ₹ "+dataProduct.get(position).getA_bal());
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
