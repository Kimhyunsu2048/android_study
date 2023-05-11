package org.koreait.day08_4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.ViewHolder> {

    private static List<Diary> items = new ArrayList<>();

    private static ItemClickListener listener;

    public static void setListener(ItemClickListener listener) {
        DiaryAdapter.listener = listener;
    }

    public void add(Diary item) {
        items.add(item);
    }

    public void setItems(List<Diary> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.diary_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Diary item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView diaryId;
        private TextView diarySubject;
        private TextView diaryDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            diaryId = itemView.findViewById(R.id.diary_id);
            diarySubject = itemView.findViewById(R.id.diary_list_subject);
            diaryDate = itemView.findViewById(R.id.diary_list_date);

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

                    listener.click(items.get(position));
                }
            });
        }

        public void setItem(Diary item) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm");

            diaryId.setText("" + item.getId());
            diarySubject.setText(item.getSubject());
            diaryDate.setText(formatter.format(item.getRegDt()));
        }
    }
}
