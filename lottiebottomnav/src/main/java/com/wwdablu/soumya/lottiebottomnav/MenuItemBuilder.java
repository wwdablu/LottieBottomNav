package com.wwdablu.soumya.lottiebottomnav;

import android.graphics.Color;
import android.text.TextUtils;

import com.wwdablu.soumya.lottiebottomnav.MenuItem.Source;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class MenuItemBuilder {

    private MenuItem menuItem;

    private MenuItemBuilder(String menuTitle,
                            String lottieName,
                            Source lottieSource,
                            Object tag) {

        menuItem = new MenuItem();

        menuItem.menuTitle = menuTitle;

        menuItem.menuTextSelectedColor = Color.BLACK;
        menuItem.menuTextUnselectedColor = Color.GRAY;

        menuItem.selectedLottieName = lottieName;
        menuItem.unselectedLottieName = lottieName;

        menuItem.lottieSource = lottieSource;

        menuItem.tag = tag;
    }

    public static MenuItemBuilder create(@NonNull  String menuTitle,
                                         @NonNull  String lottieName,
                                         @NonNull  Source lottieSource,
                                         @Nullable Object tag) throws IllegalArgumentException {

        if(TextUtils.isEmpty(menuTitle)) {
            throw new IllegalArgumentException("Menu name cannot be empty.");
        } else if (TextUtils.isEmpty(lottieName)) {
            throw new IllegalArgumentException("Lottie file must be provided.");
        }

        return new MenuItemBuilder(menuTitle, lottieName, lottieSource, tag);
    }

    public static MenuItemBuilder createFrom(@NonNull MenuItem menuItem) {

        MenuItemBuilder builder = create(menuItem.menuTitle,
                menuItem.selectedLottieName,
                menuItem.lottieSource,
                null);

        builder.menuItem.selectedLottieName = menuItem.selectedLottieName;
        builder.menuItem.unselectedLottieName = menuItem.unselectedLottieName;

        builder.menuItem.menuTextSelectedColor = menuItem.menuTextSelectedColor;
        builder.menuItem.menuTextUnselectedColor = menuItem.menuTextUnselectedColor;

        builder.menuItem.lottieProgress = menuItem.lottieProgress;

        builder.menuItem.autoPlay = menuItem.autoPlay;
        builder.menuItem.loop = menuItem.loop;

        return builder;
    }

    public MenuItemBuilder menuTitle(@NonNull String menuTitle) {
        this.menuItem.menuTitle = menuTitle;
        return this;
    }

    public MenuItemBuilder selectedTextColor(@ColorInt int color) {
        menuItem.menuTextSelectedColor = color;
        return this;
    }

    public MenuItemBuilder unSelectedTextColor(@ColorInt int color) {
        menuItem.menuTextUnselectedColor = color;
        return this;
    }

    public MenuItemBuilder selectedLottieName(String lottieName) {
        menuItem.selectedLottieName = lottieName;
        return this;
    }

    public MenuItemBuilder unSelectedLottieName(String lottieName) {
        menuItem.unselectedLottieName = lottieName;
        return this;
    }

    public MenuItemBuilder pausedProgress(float progress) {

        if (progress <= 0) progress = 0;
        else if (progress >= 100) progress = 100;

        menuItem.lottieProgress = progress;
        return this;
    }

    public MenuItemBuilder autoPlay(boolean autoPlay) {
        menuItem.autoPlay = autoPlay;
        return this;
    }

    public MenuItemBuilder loop(boolean loop) {
        menuItem.loop = loop;
        return this;
    }

    public MenuItemBuilder tag(Object tag) {
        menuItem.tag = tag;
        return this;
    }

    public MenuItem build() {
        return menuItem;
    }
}
