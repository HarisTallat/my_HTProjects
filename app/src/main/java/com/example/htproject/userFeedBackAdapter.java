package com.example.htproject;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class userFeedBackAdapter extends RecyclerView.Adapter<userFeedBackAdapter.ViewHolder>  {
    static ArrayList <userFeedBackData> personList;
    static feedback fromMain;
    int [] drawables ={R.drawable.simonsinek,R.drawable.sandeep,R.drawable.jayshetty,R.drawable.jayshetty,R.drawable.abdularham};
    public userFeedBackAdapter(Context context, ArrayList<userFeedBackData> list)
    {
        personList = list;
        fromMain = (feedback) context;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        TextView tvComment;
        ImageView ivImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvComment = itemView.findViewById(R.id.tvComment);
            ivImage = itemView.findViewById(R.id.imageView2);
        }
    }
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.feedbackrecyclerview,parent,false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(personList.get(position).getName());
        holder.tvComment.setText(personList.get(position).getFeedback());
        Random r = new Random();
        holder.ivImage.setImageResource(drawables[r.nextInt(drawables.length)] ) ;

        if(personList.get(position).getName().equals("Talha Zahid"))
        {
            holder.ivImage.setImageResource(R.drawable.talhazahid);

        }
        else  if(personList.get(position).getName().equals("Umair Zahid"))
        {
            holder.ivImage.setImageResource(R.drawable.umairzahid);

        }

        else  if(personList.get(position).getName().equals("Nabeel Sabir"))
        {
            holder.ivImage.setImageResource(R.drawable.nabeelsabir);

        }

        else  if(personList.get(position).getName().equals("Rao Junaid"))
        {
            holder.ivImage.setImageResource(R.drawable.raojunaid);

        }
        else  if(personList.get(position).getName().equals("Haris Tallat"))
        {
            holder.ivImage.setImageResource(R.drawable.haristallat);

        }
        else  if(personList.get(position).getName().equals("Awais Zahid"))
        {
            holder.ivImage.setImageResource(R.drawable.awaisbhai);

        }
        else  if(personList.get(position).getName().equals("Saqib Hussain"))
        {
            holder.ivImage.setImageResource(R.drawable.saqibbhai);

        }

        else  if(personList.get(position).getName().equals("Rehan Saleem"))
        {
            holder.ivImage.setImageResource(R.drawable.rehansaleem);

        }
    }
    public int getItemCount() {
        return personList.size();
    }
}
