package org.koreait.day08_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.koreait.day08_4.constants.ApiURL;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private List<Photo> items = new ArrayList<>();

    public void setItems(List<Photo> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_item, parent, false);

        return new ViewHolder(view, parent.getContext());
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Photo item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private Context context;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);

            image = itemView.findViewById(R.id.photo);
            this.context = context;
        }

        public void setItem(Photo photo) {
            String imageUrl = ApiURL.HOST + photo.getImageUrl();
            Glide.with(context).load(imageUrl).into(image);
        }
    }
}
