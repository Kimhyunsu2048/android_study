package org.koreait.day11_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostDataAdapter extends RecyclerView.Adapter<PostDataAdapter.ViewHolder> {

    private static List<PostData> items = new ArrayList<>();

    private static ItemClickListener listener;

    public  static void setListener(ItemClickListener listener) {
        PostDataAdapter.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.post_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PostData item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private TextView textView2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // 현재 클릭한 목록 순서 번호
                    PostData item = items.get(position);
                    listener.click(view, item);
                }
            });
        }

        public void setItem(PostData item) {
            imageView.setImageResource(item.getImageId());
            textView.setText(item.getSubject());
            textView2.setText(item.getContent());
        }
    }

    public void add(PostData item) {
        items.add(item);
    }
}
