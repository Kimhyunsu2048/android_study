package org.koreait.day07_3;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class SubFragment extends Fragment {

    private EditText editText;
    private Button button5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_sub, container, false);

        editText = viewGroup.findViewById(R.id.editText);
        button5 = viewGroup.findViewById(R.id.button5);

        Menu2Fragment menu2 = (Menu2Fragment) getParentFragment();

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = editText.getText().toString();
                menu2.updateDataFromSub(message);
                editText.setText("");
            }
        });

        return viewGroup;
    }
}