package com.maybrightventures.lender.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maybrightventures.lender.dao.PagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arindamnath on 27/12/15.
 */
public class AppPagerAdapter extends FragmentStatePagerAdapter {

    private List<PagerFragment> pagerFragments = new ArrayList<>();

    public AppPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setData(List<PagerFragment> pagerFragments) {
        this.pagerFragments = pagerFragments;
        notifyDataSetChanged();
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
