package com.nativegame.juicymatch.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.config.GameCatModel;

import java.util.List;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.myViewHolder> {

    List<GameCatModel> dataProduct;
    String endtime;

    public ResultAdapter(List<GameCatModel> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.date.setText(dataProduct.get(position).getDate());
        holder.g0.setText(dataProduct.get(position).getWin_number());
        holder.g1.setText(dataProduct.get(position).getWin_number());
        holder.g2.setText(dataProduct.get(position).getWin_number());
        holder.g3.setText(dataProduct.get(position).getWin_number());
        holder.g4.setText(dataProduct.get(position).getWin_number());
        holder.g5.setText(dataProduct.get(position).getWin_number());

    }

    @Override
    public int getItemCount() {
        if (dataProduct.size() > 0) {
            return dataProduct.size();
        } else {
            return 0;
        }

    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView date, g0, g1, g2, g3, g4, g5, g6;
        LinearLayout llPlayGame;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
//            llPlayGame = itemView.findViewById(R.id.llPlayGame);
            date = itemView.findViewById(R.id.date);
            g0 = itemView.findViewById(R.id.j0);
            g1 = itemView.findViewById(R.id.j1);
            g2 = itemView.findViewById(R.id.j2);
            g3 = itemView.findViewById(R.id.j3);
            g4 = itemView.findViewById(R.id.j4);
            g5 = itemView.findViewById(R.id.j5);


        }
    }


}
