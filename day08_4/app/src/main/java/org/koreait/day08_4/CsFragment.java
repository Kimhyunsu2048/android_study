package org.koreait.day08_4;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


public class CsFragment extends Fragment {

    private ViewPager2 viewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_cs, container, false);

        viewPager = viewGroup.findViewById(R.id.viewPager);
        CsAdapter adapter = new CsAdapter(getActivity());
        viewPager.setAdapter(adapter);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(3);

        // 뷰페이저2 이벤트
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);

                //Toast.makeText(getContext(), "onPageScrolled : " + position, Toast.LENGTH_LONG).show();
                Log.i("VIEWPAGER", "onPageScrolled : " + position);

            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                //Toast.makeText(getContext(), "onPageSelected : " + position, Toast.LENGTH_LONG).show();
                Log.i("VIEWPAGER", "onPageSelected : " + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);

                //Toast.makeText(getContext(), "onPageScrollStateChanged", Toast.LENGTH_LONG).show();
                Log.i("VIEWPAGER", "onPageScrollStateChanged");
            }
        });

        return viewGroup;
    }
}