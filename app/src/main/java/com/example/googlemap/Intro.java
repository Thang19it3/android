package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class Intro extends AppCompatActivity {

    private OnbroadingAdapter onbroadingAdapter;
    private LinearLayout layoutOnbroadingIndicators;
    private MaterialButton buttonOnboardingAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        layoutOnbroadingIndicators = findViewById(R.id.layout);
        buttonOnboardingAction = findViewById(R.id.buttonOnBroading);

        setupOnbroadingItems();

        ViewPager2 onbroadingViewPager = findViewById(R.id.viewPaver);
        onbroadingViewPager.setAdapter(onbroadingAdapter);

        setupOnbroadingIndicator();
        setCurrentOnbroadingIndicator(0);

        onbroadingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentOnbroadingIndicator(position);
            }
        });

        buttonOnboardingAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onbroadingViewPager.getCurrentItem() + 1 < onbroadingAdapter.getItemCount())
                {
                    onbroadingViewPager.setCurrentItem(onbroadingViewPager.getCurrentItem() + 1);
                }else
                {
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            }
        });
    }
    private void setupOnbroadingItems()
    {
        List<OnbroadinItem> onbroadinItems = new ArrayList<>();

        OnbroadinItem itemPayOnline = new OnbroadinItem();
        itemPayOnline.setTitle("Th??ng tin s??n b??ng");
        itemPayOnline.setSlogan("Th??ng tin s??n b??ng trong tay b???n");
        itemPayOnline.setImage(R.drawable.gif1);

        OnbroadinItem itemOnTheWay = new OnbroadinItem();
        itemOnTheWay.setTitle("T??m ?????i th??? nhanh");
        itemOnTheWay.setSlogan("T??m ?????i th??? c??ng tr??nh ?????, ch??i ?????p ??? g???n b???n");
        itemOnTheWay.setImage(R.drawable.gif3);

        OnbroadinItem itemOnThird = new OnbroadinItem();
        itemOnThird.setTitle("Tao l???p c??u l???c b???");
        itemOnThird.setSlogan("T???o c??u l???c b??? c???a ri??ng b???n v?? anh em");
        itemOnThird.setImage(R.drawable.gif2);

        onbroadinItems.add(itemPayOnline);
        onbroadinItems.add(itemOnTheWay);
        onbroadinItems.add(itemOnThird);

        onbroadingAdapter = new OnbroadingAdapter(onbroadinItems);
    }
    private  void setupOnbroadingIndicator()
    {
        ImageView[] indicators = new ImageView[onbroadingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0; i < indicators.length; i++)
        {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.onbroading_indicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnbroadingIndicators.addView(indicators[i]);
        }
    }
    private void setCurrentOnbroadingIndicator(int index)
    {
        int childCount = layoutOnbroadingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++)
        {
            ImageView imageView = (ImageView)layoutOnbroadingIndicators.getChildAt(i);
            if( i == index)
            {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onboading_indicator_active)
                );
            }else
            {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(),R.drawable.onbroading_indicator_inactive)
                );
            }
        }
        if(index == onbroadingAdapter.getItemCount() - 1)
        {
            buttonOnboardingAction.setText("Start");
        }else
        {
            buttonOnboardingAction.setText("Next");
        }
    }
}