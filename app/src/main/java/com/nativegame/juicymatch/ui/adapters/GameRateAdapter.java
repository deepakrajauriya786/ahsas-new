package com.nativegame.juicymatch.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.config.GameCatModel;

import java.util.List;


public class GameRateAdapter extends RecyclerView.Adapter<GameRateAdapter.myViewHolder>{

    List<GameCatModel> dataProduct;

    public GameRateAdapter(List<GameCatModel> dataProduct) {
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

        holder.name.setText(dataProduct.get(position).getName());
        holder.game.setText(dataProduct.get(position).getJodi_rate());
        holder.time.setText(dataProduct.get(position).getHarup_rate());


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
            game = itemView.findViewById(R.id.amount);
            time = itemView.findViewById(R.id.delete);


        }
    }

}
