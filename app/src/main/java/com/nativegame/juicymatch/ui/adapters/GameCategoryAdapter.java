package com.nativegame.juicymatch.ui.adapters;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.config.GameCatModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GameCategoryAdapter extends RecyclerView.Adapter<GameCategoryAdapter.myViewHolder>{

    List<GameCatModel> dataProduct;
    String endtime;

    public GameCategoryAdapter(List<GameCatModel> dataProduct) {
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

        holder.name.setText(dataProduct.get(position).getName());

        if (dataProduct.get(position).getType().equals("1")){
            holder.llPlayGame.setBackgroundColor(Color.parseColor("#001B6E"));
            holder.playName.setText("PLAY GAME");
        }else {
            holder.llPlayGame.setBackgroundColor(Color.parseColor("#B60000"));
            holder.playName.setText("TIME OUT");
        }

        DateFormat readFormat = new SimpleDateFormat( "hh:mm:ss");
        DateFormat writeFormat = new SimpleDateFormat( "hh:mm aa");
        Date date = null;
        Date date2 = null;
        try {
            date = readFormat.parse( dataProduct.get(position).getStart_time() );
            date2 = readFormat.parse( dataProduct.get(position).getEnd_time() );
        } catch ( ParseException e ) {
            e.printStackTrace();
        }


        if( date != null & date2 != null ) {

            holder.startTime.setText(String.valueOf( writeFormat.format( date )));
            holder.endTime.setText(" - "+ writeFormat.format( date2 ));
            endtime = writeFormat.format( date2 );
        }

        holder.llPlayGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataProduct.get(position).getType().equals("1")){

//                    Intent intent = new Intent(holder.name.getContext(), GameSubCategoryActivity.class);
//                    intent.putExtra("gc_id",dataProduct.get(position).getGc_id());
//                    intent.putExtra("name",dataProduct.get(position).getName());
//                    intent.putExtra("time",dataProduct.get(position).getEnd_time());
//                    holder.name.getContext().startActivity(intent);


                }else {

                    Toast.makeText( holder.name.getContext(), "Game Time Over", Toast.LENGTH_SHORT).show();

                }

            }
        });



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
        TextView name,endTime,startTime,playName;
        LinearLayout llPlayGame;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
//            llPlayGame = itemView.findViewById(R.id.llPlayGame);
//            name = itemView.findViewById(R.id.name);
//            startTime = itemView.findViewById(R.id.startTime);
//            endTime = itemView.findViewById(R.id.endTime);
//            playName = itemView.findViewById(R.id.playName);


        }
    }

    public String timeConversion(long value) {
        String videoTime;
        int duration = (int) value;
        int hrs = (duration / 3600000);
        int mns = (duration / 60000) % 60000;
        int scs = duration % 60000 / 1000;
        if (hrs > 0) {
            videoTime = String.format("%02d:%02d:%02d", hrs, mns, scs);
        } else {
            videoTime = String.format("%02d:%02d", mns, scs);
        }
        return videoTime;

    }

}
