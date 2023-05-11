package org.koreait.day08_4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.koreait.day08_4.constants.ApiURL;
import org.koreait.day08_4.constants.Menus;

import java.util.List;


public class PhotoListFragment extends Fragment {

    private RecyclerView photoList;
    private Button photoBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_photo_list, container, false);

        photoBtn = viewGroup.findViewById(R.id.photo_btn);
        photoList = viewGroup.findViewById(R.id.photo_list);

        MainActivity mainActivity = (MainActivity) getActivity();

        photoBtn.setOnClickListener(new View.OnClickListener() { // 사진찍기 메뉴 이동
            @Override
            public void onClick(View view) {
                mainActivity.changeMenu(Menus.PHOTO);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        photoList.setLayoutManager(layoutManager);
        PhotoAdapter adapter = new PhotoAdapter();

        mainActivity.request(ApiURL.PHOTO_URL, new RequestCallback() {
            @Override
            public void process(String response) {
                ObjectMapper om = new ObjectMapper();
                try {
                    List<Photo> items = om.readValue(response, new TypeReference<List<Photo>>() {});
                    adapter.setItems(items);
                    photoList.setAdapter(adapter);

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return viewGroup;
    }
}