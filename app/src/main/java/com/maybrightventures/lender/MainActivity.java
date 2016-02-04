package com.maybrightventures.lender;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.maybrightventures.lender.adapters.AppPagerAdapter;
import com.maybrightventures.lender.dao.PagerFragment;
import com.maybrightventures.lender.fragments.DashboardFragment;
import com.maybrightventures.lender.fragments.LendFragment;
import com.maybrightventures.lender.fragments.LenderAllocateFundsFragment;
import com.maybrightventures.lender.util.AppPreferences;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AppPreferences appPreferences;
    private AppPagerAdapter appPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private ArrayList<PagerFragment> pagerFragments = new ArrayList<>();
    private int navigationMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.main_dashboard_container);
        tabLayout = (TabLayout) findViewById(R.id.main_dashboard_tabs);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        appPreferences = new AppPreferences(this);

        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationSelection(R.id.nav_dashboard);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //AppEventsLogger.activateApp(this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("navigationSelection", navigationMenuId);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        navigationMenuId = item.getItemId();
        item.setChecked(true);
        navigationSelection(navigationMenuId);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void navigationSelection(int id) {
        switch (id) {
            case R.id.nav_dashboard:
                getSupportActionBar().setSubtitle(R.string.title_dashboard);
                mViewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                findViewById(R.id.main_content_borrow).setVisibility(View.GONE);
                findViewById(R.id.main_content_manage_wallet).setVisibility(View.GONE);

                pagerFragments.clear();
                pagerFragments.add(new PagerFragment(DashboardFragment.newInstance(DashboardFragment.LEND), "Lent"));
                pagerFragments.add(new PagerFragment(DashboardFragment.newInstance(DashboardFragment.BORROW), "Borrowed"));
                appPagerAdapter = new AppPagerAdapter(getSupportFragmentManager());
                appPagerAdapter.setData(pagerFragments);
                mViewPager.setAdapter(appPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
                break;
            case R.id.nav_lend:
                getSupportActionBar().setSubtitle(R.string.title_lend);
                mViewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                findViewById(R.id.main_content_borrow).setVisibility(View.GONE);
                findViewById(R.id.main_content_manage_wallet).setVisibility(View.GONE);

                pagerFragments.clear();
                pagerFragments.add(new PagerFragment(LendFragment.newInstance(), "Requests"));
                pagerFragments.add(new PagerFragment(LenderAllocateFundsFragment.newInstance(), "Allocate Funds"));
                appPagerAdapter = new AppPagerAdapter(getSupportFragmentManager());
                appPagerAdapter.setData(pagerFragments);
                mViewPager.setAdapter(appPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
                break;
            case R.id.nav_borrow:
                getSupportActionBar().setSubtitle(R.string.title_borrow);
                mViewPager.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                findViewById(R.id.main_content_borrow).setVisibility(View.VISIBLE);
                findViewById(R.id.main_content_manage_wallet).setVisibility(View.GONE);
                break;
            case R.id.nav_manage_wallet:
                mViewPager.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                findViewById(R.id.main_content_borrow).setVisibility(View.GONE);
                findViewById(R.id.main_content_manage_wallet).setVisibility(View.VISIBLE);
                break;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this, ProfileSettingsActivity.class));
                break;
            case R.id.nav_contact:
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
                break;
            case R.id.nav_logout:
                appPreferences.setLoggedOut();
                startActivity(new Intent(MainActivity.this, SplashActivity.class));
                finish();
                break;
        }
    }
}
