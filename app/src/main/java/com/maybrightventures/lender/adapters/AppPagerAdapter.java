package com.maybrightventures.lender.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.maybrightventures.lender.dao.PagerFragment;

import java.util.ArrayList;

/**
 * Created by arindamnath on 27/12/15.
 */
public class AppPagerAdapter extends FragmentPagerAdapter {

    private ArrayList<PagerFragment> pagerFragments;

    public AppPagerAdapter(FragmentManager fm, ArrayList<PagerFragment> pagerFragments) {
        super(fm);
        this.pagerFragments = pagerFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return pagerFragments.get(position).getFragment();
    }

    @Override
    public int getCount() {
        return pagerFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pagerFragments.get(position).getName();
    }
}
