package com.nativegame.juicymatch.ui.adapters;


import static com.nativegame.juicymatch.ui.config.apicontroller.sliderurl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.nativegame.juicymatch.R;
import com.nativegame.juicymatch.ui.models.SliderModels;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class HomeSliderAdapter extends SliderViewAdapter<HomeSliderAdapter.SliderAdapterViewHolder> {

    List<SliderModels> dataProduct;

    public HomeSliderAdapter(List<SliderModels> dataProduct) {
        this.dataProduct = dataProduct;

    }


    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
        return new SliderAdapterViewHolder(inflate);
    }


    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

        Glide.with(viewHolder.imageSlider.getContext())
                .load(sliderurl + dataProduct.get(position).getImg()).placeholder(R.drawable.image).into(viewHolder.imageSlider);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                if (dataProduct.get(position).getLink_type().equals("product")) {
//                    Intent intent = new Intent(viewHolder.imageSlider.getContext(), DetailActivity.class);
//                    intent.putExtra("p_id",dataProduct.get(position).getUrl());
//                    intent.putExtra("cs_id","");
//                    intent.putExtra("coupon","");
//                    viewHolder.imageSlider.getContext().startActivity(intent);
//                }
//                if (dataProduct.get(position).getLink_type().equals("shop")) {
//                    Intent intent = new Intent(viewHolder.imageSlider.getContext(), VendorProfile.class);
//                    intent.putExtra("s_id",dataProduct.get(position).getUrl());
//                    viewHolder.imageSlider.getContext().startActivity(intent);
//                }
//                if (dataProduct.get(position).getLink_type().equals("url")) {
//                    String theurl = "http://google.com";
//                    Uri urlstr = Uri.parse(dataProduct.get(position).getUrl());
//                    Intent urlintent = new Intent();
//                    urlintent.setData(urlstr);
//                    urlintent.setAction(Intent.ACTION_VIEW);
//                    viewHolder.imageSlider.getContext().startActivity(urlintent);
//                }



            }
        });

//        viewHolder.imageSlider.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(viewHolder.imageSlider.getContext(), DetailActivity.class);
//                intent.putExtra("image",imageurl+filterposition.get(position).getImg1());
//                intent.putExtra("image2",imageurl+filterposition.get(position).getImg2());
//                intent.putExtra("name",filterposition.get(position).getName());
//                intent.putExtra("shortDesc",filterposition.get(position).getShort_description());
//                intent.putExtra("mrp",filterposition.get(position).getMrp());
//                intent.putExtra("dp",filterposition.get(position).getDp());
//                intent.putExtra("discount",filterposition.get(position).getDiscount());
//                intent.putExtra("qty",filterposition.get(position).getQty());
//                intent.putExtra("longDesc",filterposition.get(position).getLong_description());
//                intent.putExtra("p_id",filterposition.get(position).getP_id());
//                viewHolder.imageSlider.getContext().startActivity(intent);
//
//            }
//        });

    }

    @Override
    public int getCount() {
        if (dataProduct.size() > 0){
            return  dataProduct.size();
        }else {
            return 0;
        }
    }

    static class SliderAdapterViewHolder extends ViewHolder {

        View itemView;
        ImageView imageSlider;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageSlider = itemView.findViewById(R.id.myimage);
            this.itemView = itemView;
        }
    }
}

