package com.wwdablu.soumya.lottiebottomnav;

import androidx.annotation.ColorInt;

public class MenuItem {

    public enum Source {
        Assets,
        Raw
    }

    String menuTitle;
    @ColorInt int menuTextSelectedColor;
    @ColorInt int menuTextUnselectedColor;

    String selectedLottieName;
    String unselectedLottieName;

    Source lottieSource;
    float  lottieProgress;

    boolean autoPlay;
    boolean loop;

    Object tag;

    MenuItem() {}

    public Object getTag() {
        return tag;
    }
}
