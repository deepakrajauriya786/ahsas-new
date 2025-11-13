package com.nativegame.juicymatch.ui.adapters;

import static com.nativegame.juicymatch.ui.config.apicontroller.imageurl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.activity.DoctorFetchActivity;
import com.nativegame.juicymatch.ui.models.ServiceListItem;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.myViewHolder> {

    List<ServiceListItem> dataProduct;
    String endtime;

    public SubCategoryAdapter(List<ServiceListItem> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(dataProduct.get(position).getServiceTitle());
        Glide.with(holder.img.getContext())
                .load(imageurl + dataProduct.get(position).getServiceVideo()).placeholder(R.drawable.image).into(holder.img);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(holder.name.getContext(), DoctorFetchActivity.class);
                intent.putExtra("sc_id", dataProduct.get(position).getServiceSubcatId());
                intent.putExtra("name", dataProduct.get(position).getServiceTitle());
                intent.putExtra("c_id", dataProduct.get(position).getServiceCatId());
                holder.name.getContext().startActivity(intent);


            }
        });


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
        TextView name, endTime, startTime, playName;
        CircleImageView img;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.image);


        }
    }


}
