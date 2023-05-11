package org.koreait.day10_3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<PostData> items = new ArrayList<>();
    private static ListItemListener listener;

    public static void setListener(ListItemListener listener) {
        ListAdapter.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // list_item.xml -> 객체화
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // holder : 새로 만들어지거나 재활용되는 list_item 객체
        // position : 현재 아이템 순서 번호
        PostData item = items.get(position);
        holder.setItem(item);

    }

    @Override
    public int getItemCount() {
        // 전체 아이템 갯수

        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private TextView textView2;



        public ViewHolder(@NonNull View itemView) { // item 객체 재활용시 교체 되는 부분 정의
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.touch(view, position);
                }
            });
        }

        public void setItem(PostData postData) {
            imageView.setImageResource(postData.getImageId());
            textView.setText(postData.getSubject());
            textView2.setText(postData.getContent());
        }

    }

    public void addItem(PostData item) {
        items.add(item);
    }

    public PostData getItem(int position) {
        return items.get(position);
    }
}
