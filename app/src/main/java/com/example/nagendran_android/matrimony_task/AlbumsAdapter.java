package com.example.nagendran_android.matrimony_task;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by ItropeAndroid on 9/28/2017.
 */
public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.MyHolder> {

    List<Album> list;
    ImageLoader imageLoader;
    private Context mContext;

    public AlbumsAdapter(List<Album> list, ImageLoader imageLoader) {
        this.list = list;
        this.imageLoader=imageLoader;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.album_card,parent,false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Album album = list.get(position);
        holder.name.setText(album.getFirst_name());
        holder.city.setText(album.getCity());
        String image1 = album.getProfile_pic();
        imageLoader.displayImage(image1, holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView name, city;
        ImageView image;

        public MyHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.albumimage);
            name = (TextView) itemView.findViewById(R.id.name);
            city = (TextView) itemView.findViewById(R.id.area);
        }
    }
}