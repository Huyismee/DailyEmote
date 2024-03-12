package com.huyismeee.dailyemote;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.huyismeee.dailyemote.database.Record;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewholder> {

    Context context;
    ArrayList<Record> records;
    public MyAdapter(Context context, ArrayList<Record> records) {

        this.context = context;
        this.records = records;
    }

    @NonNull
    @Override
    public MyViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.list_item,parent,false);
        return new MyViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewholder holder, int position) {
        Record record = records.get(position);
       String pathEmo  = record.getEmotion();
        String pathWeather  = record.getWeather();

        File imgFileEmo = new File(pathEmo);

        if(imgFileEmo.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFileEmo.getAbsolutePath());
            holder.imEmo.setImageBitmap(myBitmap);
        }

        File imgFileWhether = new File(pathWeather);

        if(imgFileWhether.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFileWhether.getAbsolutePath());
            holder.imWheather.setImageBitmap(myBitmap);
        }

        if (record.getImage()!=null){
            byte[] imageData = record.getImage();
            Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
            holder.imNote.setImageBitmap(bitmap);
        }


        holder.txtDay.setText("Day");





    }

    @Override
    public int getItemCount() {
        return records.size();
    }

    public  static  class MyViewholder extends RecyclerView.ViewHolder{

        ImageView imEmo,imWheather,imNote;

        TextView txtDay;
        public MyViewholder(@NonNull View itemView) {
            super(itemView);


            imEmo = itemView.findViewById(R.id.imEmo);
            imWheather = itemView.findViewById(R.id.imWheather);
            imNote = itemView.findViewById(R.id.imNote);

            txtDay = itemView.findViewById(R.id.txtDay);

        }

    }
}
