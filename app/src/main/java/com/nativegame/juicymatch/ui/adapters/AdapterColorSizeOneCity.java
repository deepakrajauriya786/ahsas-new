package com.nativegame.juicymatch.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.config.GameCatModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterColorSizeOneCity extends RecyclerView.Adapter<AdapterColorSizeOneCity.MyViewHolder> implements Filterable {

    Context context;
    String s;
    onItemClicklistner onItemClicklistner;
    List<GameCatModel> dataProduct;
    List<GameCatModel> backuodata;

    public AdapterColorSizeOneCity(Context context, List<GameCatModel> dataProduct, String s, onItemClicklistner onItemClicklistner) {
        this.context = context;
        this.dataProduct = dataProduct;
        this.s = s;
        this.onItemClicklistner = onItemClicklistner;
        backuodata = new ArrayList<>(dataProduct);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sizecoloritemonecity, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {


            holder.tagtext.setText(dataProduct.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    onItemClicklistner.onitemClick(dataProduct.get(position).getName(),dataProduct.get(position).getGc_id());


            }
        });

    }

    @Override
    public int getItemCount() {
        return dataProduct.size();
    }

    public interface onItemClicklistner {
        void onitemClick(String city , String csc);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<GameCatModel> filtereddata = new ArrayList<>();
            if (charSequence.toString().isEmpty()){
                filtereddata.addAll(backuodata);
            }
            else {
                for (GameCatModel obj : backuodata){
                    if (obj.getName().toLowerCase().contains(charSequence.toString().toLowerCase()))
                        filtereddata.add(obj);
                }
            }

            FilterResults results = new FilterResults();
            results.values = filtereddata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            dataProduct.clear();
            dataProduct.addAll((ArrayList<GameCatModel>) filterResults.values);
            notifyDataSetChanged();

        }
    };


    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout tagcart;
        TextView tagtext;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tagcart = itemView.findViewById(R.id.carttag);
            tagtext = itemView.findViewById(R.id.tagname);
        }
    }
}
