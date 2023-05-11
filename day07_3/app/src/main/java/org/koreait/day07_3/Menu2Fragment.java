package org.koreait.day07_3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class Menu2Fragment extends Fragment {

    private TextView textView4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_menu2, container, false);

        textView4 = viewGroup.findViewById(R.id.textView4);

        return viewGroup;
    }

    public void updateDataFromSub(String message) {
        //Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
        textView4.setText(message);
    }

}