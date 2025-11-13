package com.nativegame.juicymatch.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;

import java.util.List;


public class CrossAdapter extends RecyclerView.Adapter<CrossAdapter.myViewHolder>{

    List<String> dataProduct;

    public CrossAdapter(List<String> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cross_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(dataProduct.get(position));

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
        TextView name,endTime,startTime;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);

        }
    }


}
