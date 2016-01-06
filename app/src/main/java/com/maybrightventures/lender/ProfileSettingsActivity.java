package com.maybrightventures.lender;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.maybrightventures.lender.dao.PagerFragment;
import com.maybrightventures.lender.fragments.EducationInfoFragment;
import com.maybrightventures.lender.adapters.AppPagerAdapter;
import com.maybrightventures.lender.fragments.ProfileActivityFragment;
import com.maybrightventures.lender.fragments.WorkInfoFragment;
import com.maybrightventures.lender.util.AppPreferences;

import java.util.ArrayList;

public class ProfileSettingsActivity extends AppCompatActivity {

    private AppPreferences appPreferences;
    private AppPagerAdapter appPagerAdapter;
    private ViewPager mViewPager;
    private ArrayList<PagerFragment> pagerFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pagerFragments.add(new PagerFragment(ProfileActivityFragment.newInstance(1), "Basic Info"));
        pagerFragments.add(new PagerFragment(EducationInfoFragment.newInstance(1), "Education"));
        pagerFragments.add(new PagerFragment(WorkInfoFragment.newInstance(1), "Work"));

        appPreferences = new AppPreferences(this);
        appPagerAdapter = new AppPagerAdapter(getSupportFragmentManager(), pagerFragments);

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(appPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sign_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.nav_signup_done:
                appPreferences.setLoggedIn();
                startActivity(new Intent(this, AppIntroActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
