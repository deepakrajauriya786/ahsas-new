package com.nativegame.juicymatch.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.databinding.ActivityTimeSlotBinding;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.LoginModels;
import com.nativegame.juicymatch.ui.models.Times;
import com.nativegame.juicymatch.ui.models.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TimeSlotActivity extends AppCompatActivity {
    ActivityTimeSlotBinding binding;
    Times times;
    List<String> timelist;
    List<String> timelistAll;
    List<String> datelist;
    int selectedDate = 0;
    int selectedTime = 0;
    KProgressHUD hud;
    String  d_id;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTimeSlotBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        user =new User(this);
        d_id = getIntent().getStringExtra("d_id");

        binding.recycleviewDate.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.recycleviewDate.setItemAnimator(new DefaultItemAnimator());

        binding.lvlTime.setLayoutManager(new GridLayoutManager(this, 2));
        binding.lvlTime.setItemAnimator(new DefaultItemAnimator());




        processdata();


        binding.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(TimeSlotActivity.this);
                builder.setTitle("Booking Confirmation");
                builder.setMessage("Do you want to Confirm Booking for this Doctor?");
                builder.setNegativeButton("No", (dialog, i) -> {
                    dialog.dismiss();
                }).setPositiveButton("Yes", (dialog, i) -> {
                    //Toast.makeText( getContext(), "Implement late", Toast.LENGTH_SHORT ).show();
                    booking();
                    dialog.dismiss();
                });

                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.RED);
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);



            }
        });

    }

    private void booking() {

        hud = KProgressHUD.create(TimeSlotActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();


        Call<List<LoginModels>> call = apicontroller.getInstance().getapi().doctor_booking(user.getUserid(),d_id,datelist.get(selectedDate),timelist.get(selectedTime));
        call.enqueue(new Callback<List<LoginModels>>() {
            @Override
            public void onResponse(Call<List<LoginModels>> call, Response<List<LoginModels>> response) {
                List<LoginModels> data = response.body();
                hud.dismiss();

                if (data.get(0).getMessage() != null) {
                    if (data.get(0).getMessage().equals("1")) {

                        startActivity(new Intent(getApplicationContext(), WalletActivity.class));
                        Toast.makeText(getApplicationContext(), "Booking Successfully", Toast.LENGTH_SHORT).show();
                        finish();


                    }
                    if (data.get(0).getMessage().equals("0")) {
                        Toast.makeText(getApplicationContext(), "Failed .", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), " Failed !", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<List<LoginModels>> call, Throwable t) {
                hud.dismiss();
                Toast.makeText(getApplicationContext(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }


    public void processdata() {

        hud = KProgressHUD.create(TimeSlotActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setMaxProgress(100)
                .show();

        Call<JsonObject> call = apicontroller.getInstance().getapi().time_slot_fetch();

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                hud.dismiss();

                Gson gson = new Gson();
                times = gson.fromJson(response.body().toString(), Times.class);

                if (times.getResult().equalsIgnoreCase("true")) {


                    timelistAll = times.getTimeslotData().get(0).getTslot();
                    timelist = times.getTimeslotData().get(0).getTslot();
                    TimeAdapter timeAdapter;
                    datelist = times.getTimeslotData().get(0).getDays();

                    if (times.getTimeslotData().get(0).getStatus().equalsIgnoreCase("current")) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
                            Calendar calendar1 = Calendar.getInstance();
                            String currentDate = sdf.format(calendar1.getTime());
                            List<String> list = new ArrayList<>();
                            for (int i = 0; i < timelist.size(); i++) {
                                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                String formattedDate = df.format(calendar1.getTime());
                                String datadb = formattedDate + " " + timelist.get(i);
                                Date date1 = sdf.parse(currentDate);
                                Date date2 = sdf.parse(datadb);
                                if (date1.before(date2)) {
                                    list.add(timelist.get(i));
                                }
                            }
                            timelist =list;
                            timeAdapter = new TimeAdapter(list);
                            binding.lvlTime.setAdapter(timeAdapter);

                        } catch (Exception e) {
                            Log.e("Error", "" + e.toString());

                        }
                    } else {
                        timelist =timelistAll;
                        timeAdapter = new TimeAdapter(timelist);
                        binding.lvlTime.setAdapter(timeAdapter);
                    }

                    DateAdapter dateAdapter = new DateAdapter(times.getTimeslotData().get(0).getDays());
                    binding.recycleviewDate.setAdapter(dateAdapter);
                }


            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                hud.dismiss();
                call.cancel();
                t.printStackTrace();
            }
        });


    }


    public class DateAdapter extends RecyclerView.Adapter<DateAdapter.MyViewHolder> {

        private List<String> mCatlist;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.txt_date);
            }
        }

        public DateAdapter(List<String> mCatlist) {
            selectedDate = 0;
            this.mCatlist = mCatlist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.date_item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.title.setText(mCatlist.get(position).substring(0, 3) + "\n" + mCatlist.get(position).substring(4, 6));
            if (selectedDate == position) {
                holder.title.setBackground(getResources().getDrawable(R.drawable.border_border));
                holder.title.setTextColor(getResources().getColor(R.color.colorAccent));
            } else {
                holder.title.setBackground(getResources().getDrawable(R.drawable.border));
                holder.title.setTextColor(getResources().getColor(R.color.colorGray));

            }

            holder.title.setOnClickListener(v -> {
                selectedDate = position;
                if (times.getTimeslotData().get(0).getStatus().equalsIgnoreCase("current") && position == 0) {
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
                        Calendar calendar1 = Calendar.getInstance();
                        String currentDate = sdf.format(calendar1.getTime());
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < timelist.size(); i++) {
                            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                            String formattedDate = df.format(calendar1.getTime());
                            String datadb = formattedDate + " " + timelist.get(i);
                            Date date1 = sdf.parse(currentDate);
                            Date date2 = sdf.parse(datadb);

                            if (date1.before(date2)) {
                                list.add(timelist.get(i));
                            }
                        }
                        timelist =list;
                        TimeAdapter adapter = new TimeAdapter(list);
                        binding.lvlTime.setAdapter(adapter);

                    } catch (Exception e) {
                        Log.e("Error", "" + e.toString());

                    }
                } else {
                    timelist =timelistAll;
                    TimeAdapter adapter = new TimeAdapter(timelist);
                    binding.lvlTime.setAdapter(adapter);
                }


                notifyDataSetChanged();
            });

        }

        @Override
        public int getItemCount() {
            return mCatlist.size();
        }
    }


    public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.MyViewHolder> {

        private List<String> mCatlist;


        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView title;

            public MyViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.txt_date);
            }
        }

        public TimeAdapter(List<String> mCatlist) {
            selectedTime = 0;
            this.mCatlist = mCatlist;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView;
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.time_item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, int position) {
            holder.title.setText(mCatlist.get(position) + "");
            if (selectedTime == position) {
                holder.title.setBackground(getResources().getDrawable(R.drawable.border_border));
                holder.title.setTextColor(getResources().getColor(R.color.colorAccent));
            } else {
                holder.title.setBackground(getResources().getDrawable(R.drawable.border));
                holder.title.setTextColor(getResources().getColor(R.color.colorGray));

            }


            holder.title.setOnClickListener(v -> {
                selectedTime = position;
                notifyDataSetChanged();
            });

        }

        @Override
        public int getItemCount() {
            return mCatlist.size();
        }
    }


}