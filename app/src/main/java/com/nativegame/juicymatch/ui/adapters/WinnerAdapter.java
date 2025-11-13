package com.nativegame.juicymatch.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.models.WinnerModel;

import java.util.List;

public class WinnerAdapter extends RecyclerView.Adapter<WinnerAdapter.myViewHolder>{

    List<WinnerModel> dataProduct;

    public WinnerAdapter(List<WinnerModel> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.winner_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(dataProduct.get(position).getWinner_name());

        holder.time.setText(dataProduct.get(position).getGame_name());
        holder.game.setText(dataProduct.get(position).getWin_time());


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
        TextView name,time,game;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.number);
            time = itemView.findViewById(R.id.amount);
            game = itemView.findViewById(R.id.delete);


        }
    }

}
