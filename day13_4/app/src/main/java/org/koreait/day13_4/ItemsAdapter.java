package org.koreait.day13_4;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    private static List<Item> items = new ArrayList<>();
    private static ItemClickListener listener;

    public static void setListener(ItemClickListener listener) {
        ItemsAdapter.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView[] textViews;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViews = new TextView[3];
            textViews[0] = itemView.findViewById(R.id.textView);
            textViews[1] = itemView.findViewById(R.id.textView2);
            textViews[2] = itemView.findViewById(R.id.textView3);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Item item = items.get(position);

                    listener.click(view, item);
                }
            });
        }

        public void setItem(Item item) {
            textViews[0].setText("" + item.getId()); // 게시글 번호
            textViews[1].setText(item.getTitle()); // 게시글 제목
            textViews[2].setText(item.getBody()); // 게시글 내용
        }
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
}
