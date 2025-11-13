package com.nativegame.juicymatch.ui.adapters;

import static com.nativegame.juicymatch.ui.config.apicontroller.imageurl;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.models.DoctorDetailModel;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyBookingFetchAdapter extends RecyclerView.Adapter<MyBookingFetchAdapter.myViewHolder> {

    List<DoctorDetailModel> dataProduct;

    public MyBookingFetchAdapter(List<DoctorDetailModel> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_booking_doctor_item, parent, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(dataProduct.get(position).getName());
        holder.shopV.setText(dataProduct.get(position).getSubject());
        holder.experienceV.setText(dataProduct.get(position).getExperience());
        holder.percent.setText(dataProduct.get(position).getPercent() + "%");
        holder.total.setText(dataProduct.get(position).getTotal_patient() + "Patient Stories");
        holder.followerTxt.setText("Followers\n" + dataProduct.get(position).getFollow());
        holder.commentsTxt.setText("Comments\n" + dataProduct.get(position).getComment());
        holder.totalProductTxt.setText("Total\n" + dataProduct.get(position).getTotal_patient());
        holder.fees.setText("~ ₹ " + dataProduct.get(position).getFees());
        holder.city.setText("~ " +dataProduct.get(position).getCity()+" :");
        holder.address.setText(dataProduct.get(position).getAddress());
        holder.time.setText(dataProduct.get(position).getSlot_time()+", "+dataProduct.get(position).getSlot_date());




        Glide.with(holder.img.getContext())
                .load(imageurl + dataProduct.get(position).getImage()).placeholder(R.drawable.image).into(holder.img);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//
//                Intent intent = new Intent(holder.name.getContext(), TimeSlotActivity.class);
//                intent.putExtra("d_id", dataProduct.get(position).getD_id());
//
//                holder.name.getContext().startActivity(intent);


            }
        });
        holder.booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent = new Intent(holder.name.getContext(), TimeSlotActivity.class);
//                intent.putExtra("d_id", dataProduct.get(position).getD_id());
//                holder.name.getContext().startActivity(intent);


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
        TextView name, shopV, experienceV, percent, total, followerTxt, commentsTxt, totalProductTxt,fees,city,address,time,status;
        CircleImageView img;
        LinearLayout booking;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            img = itemView.findViewById(R.id.imageV);
            shopV = itemView.findViewById(R.id.shopV);
            experienceV = itemView.findViewById(R.id.experienceV);
            percent = itemView.findViewById(R.id.percent);
            total = itemView.findViewById(R.id.total);
            followerTxt = itemView.findViewById(R.id.followerTxt);
            commentsTxt = itemView.findViewById(R.id.commentsTxt);
            totalProductTxt = itemView.findViewById(R.id.totalProductTxt);
            booking = itemView.findViewById(R.id.booking);
            fees = itemView.findViewById(R.id.fees);
            city = itemView.findViewById(R.id.city);
            address = itemView.findViewById(R.id.address);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);


        }
    }


}
