package edu.jsu.mcis.cs408.crosswordmagic.view;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TabLayoutAdapter extends FragmentStateAdapter {

    public static final int NUM_TABS = 2;

    ArrayList<Fragment> tabs;

    public TabLayoutAdapter(Fragment fragment) {
        super(fragment);

        tabs = new ArrayList<>();

        tabs.add(new PuzzleFragment());
        tabs.add(new ClueFragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {

        return tabs.get(position);
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }

    public ArrayList<Fragment> getTabs() { return tabs; }

}