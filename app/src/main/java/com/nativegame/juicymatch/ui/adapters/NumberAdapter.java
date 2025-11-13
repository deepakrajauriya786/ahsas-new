package com.nativegame.juicymatch.ui.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.models.NumberModel;

import java.util.List;


public class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.myViewHolder>{

    List<NumberModel> dataProduct;

    public NumberAdapter(List<NumberModel> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.number_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.number.setText(dataProduct.get(position).getNumber());
        holder.amount.setText(dataProduct.get(position).getAmount());

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
        TextView number,amount,delete;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            amount = itemView.findViewById(R.id.amount);
            delete = itemView.findViewById(R.id.delete);

        }
    }


}
