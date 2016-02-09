package com.mbv.pokket;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.mbv.pokket.dao.PagerFragment;
import com.mbv.pokket.fragments.EducationInfoFragment;
import com.mbv.pokket.adapters.AppPagerAdapter;
import com.mbv.pokket.fragments.ProfileFragment;
import com.mbv.pokket.fragments.UserKYCFragment;
import com.mbv.pokket.fragments.WorkInfoFragment;
import com.mbv.pokket.util.AppPreferences;

import java.util.ArrayList;

public class ProfileSettingsActivity extends AppCompatActivity {

    private AppPreferences appPreferences;
    private AppPagerAdapter appPagerAdapter;
    private ViewPager mViewPager;
    private ArrayList<PagerFragment> pagerFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_profile_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        pagerFragments.add(new PagerFragment(ProfileFragment.newInstance(1), "Basic Info"));
        pagerFragments.add(new PagerFragment(EducationInfoFragment.newInstance(1), "Education"));
        pagerFragments.add(new PagerFragment(WorkInfoFragment.newInstance(1), "Work"));
        pagerFragments.add(new PagerFragment(UserKYCFragment.newInstance(1), "KYC"));

        appPreferences = new AppPreferences(this);
        appPagerAdapter = new AppPagerAdapter(getSupportFragmentManager(), true);
        appPagerAdapter.setData(pagerFragments);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(appPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
