package com.wwdablu.soumya.lottiebottomnavsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.TypefaceCompat;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

import com.wwdablu.soumya.lottiebottomnav.FontBuilder;
import com.wwdablu.soumya.lottiebottomnav.FontItem;
import com.wwdablu.soumya.lottiebottomnav.ILottieBottomNavCallback;
import com.wwdablu.soumya.lottiebottomnav.LottieBottomNav;
import com.wwdablu.soumya.lottiebottomnav.MenuItem;
import com.wwdablu.soumya.lottiebottomnav.MenuItemBuilder;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ILottieBottomNavCallback {

    LottieBottomNav bottomNav;
    Button animateMessage;
    ArrayList<MenuItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNav = findViewById(R.id.bottom_nav);
        animateMessage = findViewById(R.id.btn_animate_message);

        animateMessage.setOnClickListener(view -> {

            if(bottomNav.getSelectedIndex() == 2) {

                MenuItem cupidMessage = MenuItemBuilder.createFrom(bottomNav.getMenuItemFor(2))
                        .selectedLottieName("message_cupid.json")
                        .tag("cupid")
                        .build();

                bottomNav.updateMenuItemFor(2, cupidMessage);
            }
        });

        FontItem fontItem = FontBuilder.create("Dashboard1")
                .selectedTextColor(Color.BLACK)
                .unSelectedTextColor(Color.GRAY)
                .selectedTextSize(12) //SP
                .unSelectedTextSize(8) //SP
                .setTypeface(Typeface.createFromAsset(getAssets(), "scriptin.ttf"))
                .build();

        MenuItem item1 = MenuItemBuilder.create("home.json", MenuItem.Source.Assets, fontItem, "dash")
                .pausedProgress(100)
                .autoPlay(false)
                .loop(false)
                .build();

        fontItem = FontBuilder.create(fontItem).setTitle("Gifts1").build();
        MenuItem item2 = MenuItemBuilder.createFrom(item1, fontItem)
                .selectedLottieName("gift.json")
                .unSelectedLottieName("gift.json")
                .build();

        fontItem = FontBuilder.create(fontItem).setTitle("Mail1").build();
        MenuItem item3 = MenuItemBuilder.createFrom(item1, fontItem)
                .selectedLottieName("message.json")
                .unSelectedLottieName("message.json")
                .build();

        fontItem = FontBuilder.create(fontItem).setTitle("Settings1").setTypeface(null).build();
        MenuItem item4 = MenuItemBuilder.createFrom(item1, fontItem)
                .selectedLottieName("settings.json")
                .unSelectedLottieName("settings.json")
                .build();

        list = new ArrayList<>(4);
        list.add(item1);
        list.add(item2);
        list.add(item3);
        list.add(item4);

        bottomNav.setCallback(this);
        bottomNav.setMenuItemList(list);
        bottomNav.setSelectedIndex(1);
    }

    @Override
    public void onMenuSelected(int oldIndex, int newIndex, MenuItem menuItem) {
        animateMessage.setEnabled(newIndex == 2);
    }

    @Override
    public void onAnimationStart(int index, MenuItem menuItem) {
        //
    }

    @Override
    public void onAnimationEnd(int index, MenuItem menuItem) {

        if(index == 2 && menuItem.getTag() != null && "cupid".equalsIgnoreCase(menuItem.getTag().toString())) {
           restoreMenuForMessage(menuItem);
        }
    }

    @Override
    public void onAnimationCancel(int index, MenuItem menuItem) {
        if(index == 2 && menuItem.getTag() != null && "cupid".equalsIgnoreCase(menuItem.getTag().toString())) {
            restoreMenuForMessage(menuItem);
        }
    }

    private void restoreMenuForMessage(MenuItem menuItem) {
        MenuItem item = MenuItemBuilder.createFrom(menuItem)
                .selectedLottieName("message.json")
                .build();

        bottomNav.updateMenuItemFor(2, item);
    }
}
