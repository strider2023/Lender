package com.mbv.pokket;

import android.content.Intent;
import android.graphics.Typeface;
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
import android.widget.TextView;

import com.mbv.pokket.adapters.AppPagerAdapter;
import com.mbv.pokket.dao.PagerFragment;
import com.mbv.pokket.fragments.BorrowFragment;
import com.mbv.pokket.fragments.DashboardEventsFragment;
import com.mbv.pokket.fragments.DashboardStatsFragment;
import com.mbv.pokket.fragments.DashboardTimelineFragment;
import com.mbv.pokket.fragments.LendFragment;
import com.mbv.pokket.fragments.WalletFragment;
import com.mbv.pokket.util.AppPreferences;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private AppPreferences appPreferences;
    private DrawerLayout drawer;
    private NavigationView navigationView;
    private ActionBarDrawerToggle toggle;
    private AppPagerAdapter appPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private TextView mTitle;
    private ArrayList<PagerFragment> pagerFragments = new ArrayList<>();
    private int navigationMenuId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.activity_open_translate, R.anim.activity_close_translate);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        mTitle.setTypeface(Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf"));

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        mViewPager = (ViewPager) findViewById(R.id.main_dashboard_container);
        tabLayout = (TabLayout) findViewById(R.id.main_dashboard_tabs);

        pagerFragments.add(new PagerFragment(DashboardTimelineFragment.newInstance(), "Home"));
        pagerFragments.add(new PagerFragment(DashboardEventsFragment.newInstance(), "Events"));
        pagerFragments.add(new PagerFragment(DashboardStatsFragment.newInstance(), "Stats"));
        appPagerAdapter = new AppPagerAdapter(getSupportFragmentManager(), false);
        appPagerAdapter.setData(pagerFragments);
        mViewPager.setAdapter(appPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_developer_board_white_24dp);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_event_white_24dp);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_timeline_white_24dp);

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
                //getSupportActionBar().setSubtitle(R.string.title_dashboard);
                mViewPager.setVisibility(View.VISIBLE);
                tabLayout.setVisibility(View.VISIBLE);
                findViewById(R.id.main_fragment_holder).setVisibility(View.GONE);
                break;
            case R.id.nav_lend:
                //getSupportActionBar().setSubtitle(R.string.title_lend);
                mViewPager.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                findViewById(R.id.main_fragment_holder).setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_holder, LendFragment.newInstance())
                        .commit();
                break;
            case R.id.nav_borrow:
                //getSupportActionBar().setSubtitle(R.string.title_borrow);
                mViewPager.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                findViewById(R.id.main_fragment_holder).setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_holder, BorrowFragment.newInstance())
                        .commit();
                break;
            case R.id.nav_manage_wallet:
                //getSupportActionBar().setSubtitle(R.string.title_wallet);
                mViewPager.setVisibility(View.GONE);
                tabLayout.setVisibility(View.GONE);
                findViewById(R.id.main_fragment_holder).setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment_holder, WalletFragment.newInstance())
                        .commit();
                break;
            case R.id.nav_transaction_history:
                startActivity(new Intent(MainActivity.this, TransactionHistoryActivity.class));
                break;
            case R.id.nav_settings:
                startActivity(new Intent(MainActivity.this, ProfileSettingsActivity.class));
                break;
            case R.id.nav_contact:
                startActivity(new Intent(MainActivity.this, ContactActivity.class));
                break;
            case R.id.nav_rate_us:
                //startActivity(new Intent(MainActivity.this, ContactActivity.class));
                break;
            case R.id.nav_logout:
                appPreferences.setLoggedOut();
                startActivity(new Intent(MainActivity.this, SplashActivity.class));
                finish();
                break;
        }
    }
}
