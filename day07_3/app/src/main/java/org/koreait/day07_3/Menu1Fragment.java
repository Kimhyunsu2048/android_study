package org.koreait.day07_3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Menu1Fragment extends Fragment {

    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_menu1, container, false);

        button = viewGroup.findViewById(R.id.button4);

        MainActivity activity = (MainActivity) getActivity();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.sendDataFromFragment("menu1에서 전송한 메세지!!!");
            }
        });

        return viewGroup;
    }
}