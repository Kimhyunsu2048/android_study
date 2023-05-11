package org.koreait.day08_4;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.koreait.day08_4.constants.ApiURL;
import org.koreait.day08_4.constants.Menus;

import java.time.LocalDateTime;
import java.util.List;

public class DiaryListFragment extends Fragment {

    private RecyclerView diaryList;
    private Button moveWriteBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_diary_list, container, false);

        MainActivity mainActivity = (MainActivity) getActivity();

        // 목록은 회원이 작성한것만 노출
        if (!SessionData.isLogin()) {
            mainActivity.changeMenu(Menus.LOGIN);

            return viewGroup;
        }

        diaryList = viewGroup.findViewById(R.id.diary_list);
        moveWriteBtn = viewGroup.findViewById(R.id.move_diray_write);

        moveWriteBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mainActivity.changeMenu(Menus.DIARY);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        diaryList.setLayoutManager(layoutManager);

        DiaryAdapter.setListener(new ItemClickListener() {
            @Override
            public void click(Diary item) {
                Intent intent = new Intent(getContext(), DiaryViewActivity.class);
                intent.putExtra("item", item);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


        DiaryAdapter adapter = new DiaryAdapter();

        String url = ApiURL.DIARY_URL + "/" + SessionData.user.getUserId();

        mainActivity.request(url, new RequestCallback() {
            @Override
            public void process(String response) {
                ObjectMapper om = new ObjectMapper();
                om.registerModule(new JavaTimeModule()); // java.time 패키지 대응

                try {
                    List<Diary> items = om.readValue(response, new TypeReference<List<Diary>>() {});
                    adapter.setItems(items);
                    diaryList.setAdapter(adapter);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        return viewGroup;
    }


}