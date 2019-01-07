package com.example.roshan.tripperstrip;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.format.DateFormat;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    public List<City>  city_list;
    public Context context;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClicklistener(OnItemClickListener listener){

        mListener = listener;
    }

    public RecyclerAdapter(List<City>  city_list){
        this.city_list  =city_list;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list, parent,false);
        context = parent.getContext();


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.setIsRecyclable(false);


        String desc_data = city_list.get(position).getDesc();
        holder.setDescText(desc_data);

        String image_url = city_list.get(position).getImage_url();
        holder.setPlaceImage(image_url);

        String infom = city_list.get(position).getInform();
        holder.setInform(infom);

        String best_time = city_list.get(position).getBestTime();

  String fare = city_list.get(position).getTicket();

 String how_reach = city_list.get(position).getReach();


    }

    @Override
    public int getItemCount() {
        return city_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private View mView;
        private TextView descView;
        private ImageView placeImage;

        TextView mtextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){

                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION ){

                            mListener.onItemClick(position);
                        }


                    }
                }
            });


        }

        public void setDescText(String descText) {
            descView = mView.findViewById(R.id.blog_desc);
            descView.setText(descText);
        }

        public void setPlaceImage(String downloadUri) {

            placeImage = mView.findViewById(R.id.place_image);

            RequestOptions placeholderOption = new RequestOptions();
            placeholderOption.placeholder(R.drawable.image_placeholder);

            Glide.with(context).applyDefaultRequestOptions(placeholderOption)
                    .load(downloadUri).into(placeImage);

        }


        public void setInform(String inform){

            mtextView = mView.findViewById(R.id.city_info);

            //  mtextView.setText(inform);

        }
    }




}
