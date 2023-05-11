package org.koreait.day08_4;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.koreait.day08_4.cs.Cs1Fragment;
import org.koreait.day08_4.cs.Cs2Fragment;
import org.koreait.day08_4.cs.Cs3Fragment;

public class CsAdapter extends FragmentStateAdapter {

    public CsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        switch(position % 3) { // 0, 1, 2
            case 1:
                return new Cs2Fragment();
            case 2:
                return new Cs3Fragment();
            default :
                return new Cs1Fragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2000;
    }
}
