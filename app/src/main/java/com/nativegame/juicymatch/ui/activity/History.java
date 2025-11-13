package com.nativegame.juicymatch.ui.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.databinding.ActivityHistoryBinding;
import com.nativegame.juicymatch.ui.adapters.AdapterColorSizeOneCity;
import com.nativegame.juicymatch.ui.config.GameCatModel;
import com.nativegame.juicymatch.ui.config.apicontroller;
import com.nativegame.juicymatch.ui.models.JodiModel;
import com.nativegame.juicymatch.ui.models.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class History extends AppCompatActivity {
    ActivityHistoryBinding binding;
    User user;

    int aa, bb;
    String date, gc_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setTitle(null);

        user = new User(this);

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        Date d = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date = df.format(d);

        binding.date.setText(date);


//        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());
//        String formattedDate = df.format(c);

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        History.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // on below line we are setting date to our text view.
                                binding.date.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                            }
                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();
            }
        });

        binding.filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processData1();
                processHarup();
                totalAdd();

            }
        });


        binding.nameGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Call<List<GameCatModel>> call = apicontroller.getInstance().getapi().game_category();
                call.enqueue(new Callback<List<GameCatModel>>() {
                    @Override
                    public void onResponse(Call<List<GameCatModel>> call, Response<List<GameCatModel>> response) {
                        List<GameCatModel> data = response.body();

                        Dialog popAddPosr = new Dialog(History.this);
                        popAddPosr.setContentView(R.layout.citydialog);

                        RecyclerView recyclerView = popAddPosr.findViewById(R.id.citydialogrecycler);
                        recyclerView.setLayoutManager(new GridLayoutManager(History.this, 1));
                        recyclerView.setHasFixedSize(true);
                        popAddPosr.show();

                        AdapterColorSizeOneCity categoryNameDialog = new AdapterColorSizeOneCity(History.this, data, "4", new AdapterColorSizeOneCity.onItemClicklistner() {
                            @Override
                            public void onitemClick(String cityStr, String csc) {
                                gc_id = csc;
                                binding.nameGame.setText(cityStr);

                                popAddPosr.dismiss();

                            }
                        });
                        recyclerView.setAdapter(categoryNameDialog);

                        EditText search = popAddPosr.findViewById(R.id.search1);
                        search.setVisibility(View.VISIBLE);
                        search.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {

                            }

                            @Override
                            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

                                categoryNameDialog.getFilter().filter(s);


                            }

                            @Override
                            public void afterTextChanged(Editable editable) {

                            }
                        });


                        TextView dialogclose = popAddPosr.findViewById(R.id.dialogapply);
                        dialogclose.setVisibility(View.GONE);


                    }

                    @Override
                    public void onFailure(Call<List<GameCatModel>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });


        binding.j0.setText("00\n0");
        binding.j1.setText("01\n0");
        binding.j2.setText("02\n0");
        binding.j3.setText("03\n0");
        binding.j4.setText("04\n0");
        binding.j5.setText("05\n0");
        binding.j6.setText("06\n0");
        binding.j7.setText("07\n0");
        binding.j8.setText("08\n0");
        binding.j9.setText("09\n0");
        binding.j10.setText("10\n0");
        binding.j11.setText("11\n0");
        binding.j12.setText("12\n0");
        binding.j13.setText("13\n0");
        binding.j14.setText("14\n0");
        binding.j15.setText("15\n0");
        binding.j16.setText("16\n0");
        binding.j17.setText("17\n0");
        binding.j18.setText("18\n0");
        binding.j19.setText("19\n0");
        binding.j20.setText("20\n0");
        binding.j21.setText("21\n0");
        binding.j22.setText("22\n0");
        binding.j23.setText("23\n0");
        binding.j24.setText("24\n0");
        binding.j25.setText("25\n0");
        binding.j26.setText("26\n0");
        binding.j27.setText("27\n0");
        binding.j28.setText("28\n0");
        binding.j29.setText("29\n0");
        binding.j30.setText("30\n0");
        binding.j31.setText("31\n0");
        binding.j32.setText("32\n0");
        binding.j33.setText("33\n0");
        binding.j34.setText("34\n0");
        binding.j35.setText("35\n0");
        binding.j36.setText("36\n0");
        binding.j37.setText("37\n0");
        binding.j38.setText("38\n0");
        binding.j39.setText("39\n0");
        binding.j40.setText("40\n0");
        binding.j41.setText("41\n0");
        binding.j42.setText("42\n0");
        binding.j43.setText("43\n0");
        binding.j44.setText("44\n0");
        binding.j45.setText("45\n0");
        binding.j46.setText("46\n0");
        binding.j47.setText("47\n0");
        binding.j48.setText("48\n0");
        binding.j49.setText("49\n0");
        binding.j50.setText("50\n0");
        binding.j51.setText("51\n0");
        binding.j52.setText("52\n0");
        binding.j53.setText("53\n0");
        binding.j54.setText("54\n0");
        binding.j55.setText("55\n0");
        binding.j56.setText("56\n0");
        binding.j57.setText("57\n0");
        binding.j58.setText("58\n0");
        binding.j59.setText("59\n0");
        binding.j60.setText("60\n0");
        binding.j61.setText("61\n0");
        binding.j62.setText("62\n0");
        binding.j63.setText("63\n0");
        binding.j64.setText("64\n0");
        binding.j65.setText("65\n0");
        binding.j66.setText("66\n0");
        binding.j67.setText("67\n0");
        binding.j68.setText("68\n0");
        binding.j69.setText("69\n0");
        binding.j70.setText("70\n0");
        binding.j71.setText("71\n0");
        binding.j72.setText("72\n0");
        binding.j73.setText("73\n0");
        binding.j74.setText("74\n0");
        binding.j75.setText("75\n0");
        binding.j76.setText("76\n0");
        binding.j77.setText("77\n0");
        binding.j78.setText("78\n0");
        binding.j79.setText("79\n0");
        binding.j80.setText("80\n0");
        binding.j81.setText("81\n0");
        binding.j82.setText("82\n0");
        binding.j83.setText("83\n0");
        binding.j84.setText("84\n0");
        binding.j85.setText("85\n0");
        binding.j86.setText("86\n0");
        binding.j87.setText("87\n0");
        binding.j88.setText("88\n0");
        binding.j89.setText("89\n0");
        binding.j90.setText("90\n0");
        binding.j91.setText("91\n0");
        binding.j92.setText("92\n0");
        binding.j93.setText("93\n0");
        binding.j94.setText("94\n0");
        binding.j95.setText("95\n0");
        binding.j96.setText("96\n0");
        binding.j97.setText("97\n0");
        binding.j98.setText("98\n0");
        binding.j99.setText("99\n0");


        binding.a0.setText("0");
        binding.a1.setText("0");
        binding.a2.setText("0");
        binding.a3.setText("0");
        binding.a4.setText("0");
        binding.a5.setText("0");
        binding.a5.setText("0");
        binding.a6.setText("0");
        binding.a7.setText("0");
        binding.a8.setText("0");
        binding.a9.setText("0");

        binding.b0.setText("0");
        binding.b1.setText("0");
        binding.b2.setText("0");
        binding.b3.setText("0");
        binding.b4.setText("0");
        binding.b5.setText("0");
        binding.b6.setText("0");
        binding.b7.setText("0");
        binding.b8.setText("0");
        binding.b9.setText("0");



    }


    public void processData1() {

        DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = readFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (date1 != null) {

            date = writeFormat.format(date1);
        }


//        binding.progressBar.setVisibility(View.VISIBLE);
        Call<List<JodiModel>> call = apicontroller.getInstance().getapi().jodi_fetch(user.getUserid(), gc_id,
                String.valueOf(date));

        call.enqueue(new Callback<List<JodiModel>>() {
            @Override
            public void onResponse(Call<List<JodiModel>> call, Response<List<JodiModel>> response) {
                List<JodiModel> data = response.body();
//                binding.progressBar.setVisibility(View.INVISIBLE);

                if (String.valueOf(response.body()).contains("null")) {
                    binding.j0.setText("00\n0");
                    binding.j1.setText("01\n0");
                    binding.j2.setText("02\n0");
                    binding.j3.setText("03\n0");
                    binding.j4.setText("04\n0");
                    binding.j5.setText("05\n0");
                    binding.j6.setText("06\n0");
                    binding.j7.setText("07\n0");
                    binding.j8.setText("08\n0");
                    binding.j9.setText("09\n0");
                    binding.j10.setText("10\n0");
                    binding.j11.setText("11\n0");
                    binding.j12.setText("12\n0");
                    binding.j13.setText("13\n0");
                    binding.j14.setText("14\n0");
                    binding.j15.setText("15\n0");
                    binding.j16.setText("16\n0");
                    binding.j17.setText("17\n0");
                    binding.j18.setText("18\n0");
                    binding.j19.setText("19\n0");
                    binding.j20.setText("20\n0");
                    binding.j21.setText("21\n0");
                    binding.j22.setText("22\n0");
                    binding.j23.setText("23\n0");
                    binding.j24.setText("24\n0");
                    binding.j25.setText("25\n0");
                    binding.j26.setText("26\n0");
                    binding.j27.setText("27\n0");
                    binding.j28.setText("28\n0");
                    binding.j29.setText("29\n0");
                    binding.j30.setText("30\n0");
                    binding.j31.setText("31\n0");
                    binding.j32.setText("32\n0");
                    binding.j33.setText("33\n0");
                    binding.j34.setText("34\n0");
                    binding.j35.setText("35\n0");
                    binding.j36.setText("36\n0");
                    binding.j37.setText("37\n0");
                    binding.j38.setText("38\n0");
                    binding.j39.setText("39\n0");
                    binding.j40.setText("40\n0");
                    binding.j41.setText("41\n0");
                    binding.j42.setText("42\n0");
                    binding.j43.setText("43\n0");
                    binding.j44.setText("44\n0");
                    binding.j45.setText("45\n0");
                    binding.j46.setText("46\n0");
                    binding.j47.setText("47\n0");
                    binding.j48.setText("48\n0");
                    binding.j49.setText("49\n0");
                    binding.j50.setText("50\n0");
                    binding.j51.setText("51\n0");
                    binding.j52.setText("52\n0");
                    binding.j53.setText("53\n0");
                    binding.j54.setText("54\n0");
                    binding.j55.setText("55\n0");
                    binding.j56.setText("56\n0");
                    binding.j57.setText("57\n0");
                    binding.j58.setText("58\n0");
                    binding.j59.setText("59\n0");
                    binding.j60.setText("60\n0");
                    binding.j61.setText("61\n0");
                    binding.j62.setText("62\n0");
                    binding.j63.setText("63\n0");
                    binding.j64.setText("64\n0");
                    binding.j65.setText("65\n0");
                    binding.j66.setText("66\n0");
                    binding.j67.setText("67\n0");
                    binding.j68.setText("68\n0");
                    binding.j69.setText("69\n0");
                    binding.j70.setText("70\n0");
                    binding.j71.setText("71\n0");
                    binding.j72.setText("72\n0");
                    binding.j73.setText("73\n0");
                    binding.j74.setText("74\n0");
                    binding.j75.setText("75\n0");
                    binding.j76.setText("76\n0");
                    binding.j77.setText("77\n0");
                    binding.j78.setText("78\n0");
                    binding.j79.setText("79\n0");
                    binding.j80.setText("80\n0");
                    binding.j81.setText("81\n0");
                    binding.j82.setText("82\n0");
                    binding.j83.setText("83\n0");
                    binding.j84.setText("84\n0");
                    binding.j85.setText("85\n0");
                    binding.j86.setText("86\n0");
                    binding.j87.setText("87\n0");
                    binding.j88.setText("88\n0");
                    binding.j89.setText("89\n0");
                    binding.j90.setText("90\n0");
                    binding.j91.setText("91\n0");
                    binding.j92.setText("92\n0");
                    binding.j93.setText("93\n0");
                    binding.j94.setText("94\n0");
                    binding.j95.setText("95\n0");
                    binding.j96.setText("96\n0");
                    binding.j97.setText("97\n0");
                    binding.j98.setText("98\n0");
                    binding.j99.setText("99\n0");
                    binding.totalJodi.setText("0");


                    Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_SHORT).show();

                } else {
                    if (data.size() > 0) {


                        binding.j0.setText("00\n" + data.get(0).getJ0());
                        binding.j1.setText("01\n" + data.get(0).getJ1());
                        binding.j2.setText("02\n" + data.get(0).getJ2());
                        binding.j3.setText("03\n" + data.get(0).getJ3());
                        binding.j4.setText("04\n" + data.get(0).getJ4());
                        binding.j5.setText("05\n" + data.get(0).getJ5());
                        binding.j6.setText("06\n" + data.get(0).getJ6());
                        binding.j7.setText("07\n" + data.get(0).getJ7());
                        binding.j8.setText("08\n" + data.get(0).getJ8());
                        binding.j9.setText("09\n" + data.get(0).getJ9());

                        binding.j10.setText("10\n" + data.get(0).getJ10());
                        binding.j11.setText("11\n" + data.get(0).getJ11());
                        binding.j12.setText("12\n" + data.get(0).getJ12());
                        binding.j13.setText("13\n" + data.get(0).getJ13());
                        binding.j14.setText("14\n" + data.get(0).getJ14());
                        binding.j15.setText("15\n" + data.get(0).getJ15());
                        binding.j16.setText("16\n" + data.get(0).getJ16());
                        binding.j17.setText("17\n" + data.get(0).getJ17());
                        binding.j18.setText("18\n" + data.get(0).getJ18());
                        binding.j19.setText("19\n" + data.get(0).getJ19());

                        binding.j20.setText("20\n" + data.get(0).getJ20());
                        binding.j21.setText("21\n" + data.get(0).getJ21());
                        binding.j22.setText("22\n" + data.get(0).getJ22());
                        binding.j23.setText("23\n" + data.get(0).getJ23());
                        binding.j24.setText("24\n" + data.get(0).getJ24());
                        binding.j25.setText("25\n" + data.get(0).getJ25());
                        binding.j26.setText("26\n" + data.get(0).getJ26());
                        binding.j27.setText("27\n" + data.get(0).getJ27());
                        binding.j28.setText("28\n" + data.get(0).getJ28());
                        binding.j29.setText("29\n" + data.get(0).getJ29());

                        binding.j30.setText("30\n" + data.get(0).getJ30());
                        binding.j31.setText("31\n" + data.get(0).getJ31());
                        binding.j32.setText("32\n" + data.get(0).getJ32());
                        binding.j33.setText("33\n" + data.get(0).getJ33());
                        binding.j34.setText("34\n" + data.get(0).getJ34());
                        binding.j35.setText("35\n" + data.get(0).getJ35());
                        binding.j36.setText("36\n" + data.get(0).getJ36());
                        binding.j37.setText("37\n" + data.get(0).getJ37());
                        binding.j38.setText("38\n" + data.get(0).getJ38());
                        binding.j39.setText("39\n" + data.get(0).getJ39());

                        binding.j40.setText("40\n" + data.get(0).getJ40());
                        binding.j41.setText("41\n" + data.get(0).getJ41());
                        binding.j42.setText("42\n" + data.get(0).getJ42());
                        binding.j43.setText("43\n" + data.get(0).getJ43());
                        binding.j44.setText("44\n" + data.get(0).getJ44());
                        binding.j45.setText("45\n" + data.get(0).getJ45());
                        binding.j46.setText("46\n" + data.get(0).getJ46());
                        binding.j47.setText("47\n" + data.get(0).getJ47());
                        binding.j48.setText("48\n" + data.get(0).getJ48());
                        binding.j49.setText("49\n" + data.get(0).getJ49());

                        binding.j50.setText("50\n" + data.get(0).getJ50());
                        binding.j51.setText("51\n" + data.get(0).getJ51());
                        binding.j52.setText("52\n" + data.get(0).getJ52());
                        binding.j53.setText("53\n" + data.get(0).getJ53());
                        binding.j54.setText("54\n" + data.get(0).getJ54());
                        binding.j55.setText("55\n" + data.get(0).getJ55());
                        binding.j56.setText("56\n" + data.get(0).getJ56());
                        binding.j57.setText("57\n" + data.get(0).getJ57());
                        binding.j58.setText("58\n" + data.get(0).getJ58());
                        binding.j59.setText("59\n" + data.get(0).getJ59());

                        binding.j60.setText("60\n" + data.get(0).getJ60());
                        binding.j61.setText("61\n" + data.get(0).getJ61());
                        binding.j62.setText("62\n" + data.get(0).getJ62());
                        binding.j63.setText("63\n" + data.get(0).getJ63());
                        binding.j64.setText("64\n" + data.get(0).getJ64());
                        binding.j65.setText("65\n" + data.get(0).getJ65());
                        binding.j66.setText("66\n" + data.get(0).getJ66());
                        binding.j67.setText("67\n" + data.get(0).getJ67());
                        binding.j68.setText("68\n" + data.get(0).getJ68());
                        binding.j69.setText("69\n" + data.get(0).getJ69());


                        binding.j70.setText("70\n" + data.get(0).getJ70());
                        binding.j71.setText("71\n" + data.get(0).getJ71());
                        binding.j72.setText("72\n" + data.get(0).getJ72());
                        binding.j73.setText("73\n" + data.get(0).getJ73());
                        binding.j74.setText("74\n" + data.get(0).getJ74());
                        binding.j75.setText("75\n" + data.get(0).getJ75());
                        binding.j76.setText("76\n" + data.get(0).getJ76());
                        binding.j77.setText("77\n" + data.get(0).getJ77());
                        binding.j78.setText("78\n" + data.get(0).getJ78());
                        binding.j79.setText("79\n" + data.get(0).getJ79());

                        binding.j80.setText("80\n" + data.get(0).getJ80());
                        binding.j81.setText("81\n" + data.get(0).getJ81());
                        binding.j82.setText("82\n" + data.get(0).getJ82());
                        binding.j83.setText("83\n" + data.get(0).getJ83());
                        binding.j84.setText("84\n" + data.get(0).getJ84());
                        binding.j85.setText("85\n" + data.get(0).getJ85());
                        binding.j86.setText("86\n" + data.get(0).getJ86());
                        binding.j87.setText("87\n" + data.get(0).getJ87());
                        binding.j88.setText("88\n" + data.get(0).getJ88());
                        binding.j89.setText("89\n" + data.get(0).getJ89());

                        binding.j90.setText("90\n" + data.get(0).getJ90());
                        binding.j91.setText("91\n" + data.get(0).getJ91());
                        binding.j92.setText("92\n" + data.get(0).getJ92());
                        binding.j93.setText("93\n" + data.get(0).getJ93());
                        binding.j94.setText("94\n" + data.get(0).getJ94());
                        binding.j95.setText("95\n" + data.get(0).getJ95());
                        binding.j96.setText("96\n" + data.get(0).getJ96());
                        binding.j97.setText("97\n" + data.get(0).getJ97());
                        binding.j98.setText("98\n" + data.get(0).getJ98());
                        binding.j99.setText("99\n" + data.get(0).getJ99());

                        binding.totalJodi.setText(data.get(0).getTotal_amount());

                        aa = Integer.parseInt(data.get(0).getTotal_amount());

                        totalAdd();

                    }

                }


            }

            @Override
            public void onFailure(Call<List<JodiModel>> call, Throwable t) {
//                binding.progressBar.setVisibility(View.INVISIBLE);
//                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void processHarup() {
        DateFormat readFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat writeFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = readFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (date1 != null) {

            date = writeFormat.format(date1);
        }


//        binding.progressBar.setVisibility(View.VISIBLE);
        Call<List<JodiModel>> call = apicontroller.getInstance().getapi().harup_fetch(user.getUserid(), "1", String.valueOf(date));

        call.enqueue(new Callback<List<JodiModel>>() {
            @Override
            public void onResponse(Call<List<JodiModel>> call, Response<List<JodiModel>> response) {
                List<JodiModel> data = response.body();
//                binding.progressBar.setVisibility(View.INVISIBLE);

                if (String.valueOf(response.body()).contains("null")) {
//                    Toast.makeText(getApplicationContext(), "No Data Available", Toast.LENGTH_SHORT).show();
                    binding.a0.setText("0");
                    binding.a1.setText("0");
                    binding.a2.setText("0");
                    binding.a3.setText("0");
                    binding.a4.setText("0");
                    binding.a5.setText("0");
                    binding.a5.setText("0");
                    binding.a6.setText("0");
                    binding.a7.setText("0");
                    binding.a8.setText("0");
                    binding.a9.setText("0");

                    binding.b0.setText("0");
                    binding.b1.setText("0");
                    binding.b2.setText("0");
                    binding.b3.setText("0");
                    binding.b4.setText("0");
                    binding.b5.setText("0");
                    binding.b6.setText("0");
                    binding.b7.setText("0");
                    binding.b8.setText("0");
                    binding.b9.setText("0");
                    binding.totalHarup.setText("0");


                } else {
                    if (data.size() > 0) {


                        binding.a0.setText(data.get(0).getA0());
                        binding.a1.setText(data.get(0).getA1());
                        binding.a2.setText(data.get(0).getA2());
                        binding.a3.setText(data.get(0).getA3());
                        binding.a4.setText(data.get(0).getA4());
                        binding.a5.setText(data.get(0).getA5());
                        binding.a6.setText(data.get(0).getA6());
                        binding.a7.setText(data.get(0).getA7());
                        binding.a8.setText(data.get(0).getA8());
                        binding.a9.setText(data.get(0).getA9());

                        binding.b0.setText(data.get(0).getB0());
                        binding.b1.setText(data.get(0).getB1());
                        binding.b2.setText(data.get(0).getB2());
                        binding.b3.setText(data.get(0).getB3());
                        binding.b4.setText(data.get(0).getB4());
                        binding.b5.setText(data.get(0).getB5());
                        binding.b6.setText(data.get(0).getB6());
                        binding.b7.setText(data.get(0).getB7());
                        binding.b8.setText(data.get(0).getB8());
                        binding.b9.setText(data.get(0).getB9());

                        binding.totalHarup.setText(data.get(0).getTotal_amount());

                        bb = Integer.parseInt(data.get(0).getTotal_amount());
                        totalAdd();
                    }

                }


            }

            @Override
            public void onFailure(Call<List<JodiModel>> call, Throwable t) {
//                binding.progressBar.setVisibility(View.INVISIBLE);
//                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void totalAdd() {

        int cc = aa + bb;
        binding.total.setText(String.valueOf(cc));
    }


}