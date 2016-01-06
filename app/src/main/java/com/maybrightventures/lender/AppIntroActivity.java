package com.maybrightventures.lender;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.maybrightventures.lender.adapters.ScreenSlideAdapter;
import com.maybrightventures.lender.util.CrossfadePageTransformer;

/**
 * Created by arindamnath on 04/01/16.
 */
public class AppIntroActivity extends AppCompatActivity {

    static final int TOTAL_PAGES = 4;
    Button btnSkip, btnDone;
    ImageButton btnNext;
    ViewPager viewpager;
    PagerAdapter pagerAdapter;
    LinearLayout circles;
    boolean isOpaque = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_intro);

        pagerAdapter = new ScreenSlideAdapter(getSupportFragmentManager(), TOTAL_PAGES);

        btnSkip = (Button)findViewById(R.id.btn_skip);
        btnNext = (ImageButton)findViewById(R.id.btn_next);
        btnDone = (Button) findViewById(R.id.done);
        viewpager = (ViewPager) findViewById(R.id.pager);

        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppIntroActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewpager.setCurrentItem(viewpager.getCurrentItem() + 1, true);
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AppIntroActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                finish();
            }
        });


        viewpager.setAdapter(pagerAdapter);
        viewpager.setPageTransformer(true, new CrossfadePageTransformer());
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == TOTAL_PAGES - 2 && positionOffset > 0) {
                    if (isOpaque) {
                        viewpager.setBackgroundColor(Color.TRANSPARENT);
                        isOpaque = false;
                    }
                } else {
                    if (!isOpaque) {
                        viewpager.setBackgroundColor(getResources().getColor(R.color.primary_material_light));
                        isOpaque = true;
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
                if (position == TOTAL_PAGES - 2) {
                    btnSkip.setVisibility(View.INVISIBLE);
                    btnNext.setVisibility(View.GONE);
                    btnDone.setVisibility(View.VISIBLE);
                } else if (position < TOTAL_PAGES - 2) {
                    btnSkip.setVisibility(View.VISIBLE);
                    btnNext.setVisibility(View.VISIBLE);
                    btnDone.setVisibility(View.GONE);
                } else if (position == TOTAL_PAGES - 1) {
                    startActivity(new Intent(AppIntroActivity.this, MainActivity.class));
                    overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
                    finish();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        buildCircles();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewpager != null) {
            viewpager.clearOnPageChangeListeners();
        }
    }

    private void buildCircles() {
        circles = (LinearLayout)findViewById(R.id.circles);
        float scale = getResources().getDisplayMetrics().density;
        int padding = (int) (5 * scale + 0.5f);
        for (int i = 0; i < TOTAL_PAGES - 1; i++) {
            ImageView circle = new ImageView(this);
            circle.setImageResource(R.mipmap.ic_page_indicator);
            circle.setLayoutParams(new ViewGroup.LayoutParams((int) (20 * scale), (int) (20 * scale)));
            circle.setPadding(padding, 0, padding, 0);
            circles.addView(circle);
        }
        setIndicator(0);
    }

    private void setIndicator(int index) {
        if (index < TOTAL_PAGES) {
            for (int i = 0; i < TOTAL_PAGES - 1; i++) {
                ImageView circle = (ImageView) circles.getChildAt(i);
                if (i == index) {
                    circle.setColorFilter(getResources().getColor(R.color.colorBase));
                } else {
                    circle.setColorFilter(getResources().getColor(R.color.colorAccent));
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (viewpager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewpager.setCurrentItem(viewpager.getCurrentItem() - 1);
        }
    }
}
