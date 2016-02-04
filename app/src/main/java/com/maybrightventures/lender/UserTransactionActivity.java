package com.maybrightventures.lender;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MenuItem;

import com.maybrightventures.lender.adapters.AppPagerAdapter;
import com.maybrightventures.lender.dao.PagerFragment;
import com.maybrightventures.lender.fragments.DashboardFragment;
import com.maybrightventures.lender.fragments.UserTransactionFragment;

import java.util.ArrayList;

public class UserTransactionActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private AppPagerAdapter appPagerAdapter;
    private ArrayList<PagerFragment> pagerFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_user_transaction);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(getIntent().getIntExtra("type", -1) == DashboardFragment.BORROW) {
            pagerFragments.add(new PagerFragment(UserTransactionFragment.newInstance(UserTransactionFragment.PENDING), "Applied"));
        }
        pagerFragments.add(new PagerFragment(UserTransactionFragment.newInstance(UserTransactionFragment.OPEN), "Open"));
        pagerFragments.add(new PagerFragment(UserTransactionFragment.newInstance(UserTransactionFragment.CLOSE), "Closed"));

        appPagerAdapter = new AppPagerAdapter(getSupportFragmentManager());
        appPagerAdapter.setData(pagerFragments);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.user_transaction_tabs);
        mViewPager = (ViewPager) findViewById(R.id.container);

        mViewPager.setAdapter(appPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
