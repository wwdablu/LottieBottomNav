package com.wwdablu.soumya.lottiebottomnav;

import android.text.TextUtils;

import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.wwdablu.soumya.lottiebottomnav.MenuItem.Source;

public final class MenuItemBuilder {

    private MenuItem menuItem;

    private MenuItemBuilder(String lottieName,
                            Source lottieSource,
                            FontItem fontItem,
                            Object tag) {

        menuItem = new MenuItem();

        menuItem.fontItem = fontItem;

        menuItem.selectedLottieName = lottieName;
        menuItem.unselectedLottieName = lottieName;

        menuItem.lottieSource = lottieSource;

        menuItem.tag = tag;
    }

    public static MenuItemBuilder create(@NonNull  String lottieName,
                                         @NonNull  Source lottieSource,
                                         @NonNull  FontItem fontItem,
                                         @Nullable Object tag) throws IllegalArgumentException {

        if (TextUtils.isEmpty(lottieName)) {
            throw new IllegalArgumentException("Lottie file must be provided.");
        }

        return new MenuItemBuilder(lottieName, lottieSource, fontItem, tag);
    }

    public static MenuItemBuilder createFrom(@NonNull MenuItem menuItem) {
        return createFrom(menuItem, FontBuilder.create(menuItem.fontItem).build());
    }

    public static MenuItemBuilder createFrom(@NonNull MenuItem menuItem, @NonNull FontItem fontItem) {

        MenuItemBuilder builder = create(
                menuItem.selectedLottieName,
                menuItem.lottieSource,
                fontItem,
                null);

        builder.menuItem.selectedLottieName = menuItem.selectedLottieName;
        builder.menuItem.unselectedLottieName = menuItem.unselectedLottieName;

        builder.menuItem.lottieProgress = menuItem.lottieProgress;

        builder.menuItem.loop = menuItem.loop;

        return builder;
    }

    public MenuItemBuilder selectedLottieName(String lottieName) {
        menuItem.selectedLottieName = lottieName;
        return this;
    }

    public MenuItemBuilder unSelectedLottieName(String lottieName) {
        menuItem.unselectedLottieName = lottieName;
        return this;
    }

    public MenuItemBuilder pausedProgress(@FloatRange(from = 0, to = 1) float progress) {

        if (progress <= 0) progress = 0;
        else if (progress >= 1) progress = 1;

        menuItem.lottieProgress = progress;
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
