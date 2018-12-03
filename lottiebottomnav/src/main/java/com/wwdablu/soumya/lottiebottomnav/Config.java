package com.wwdablu.soumya.lottiebottomnav;

import android.content.Context;

import androidx.annotation.NonNull;

final class Config {

    private int selectedMenuWidth;
    private int unselectedMenuWidth;

    private int selectedMenuHeight;
    private int unselectedMenuHeight;

    private boolean showTextOnUnselected;

    Config(@NonNull Context context) {

        selectedMenuWidth = context.getResources().getDimensionPixelSize(R.dimen.menu_width);
        selectedMenuHeight = context.getResources().getDimensionPixelSize(R.dimen.menu_height);

        unselectedMenuWidth = selectedMenuWidth;
        unselectedMenuHeight = selectedMenuHeight;

        showTextOnUnselected = true;
    }

    int getSelectedMenuWidth() {
        return selectedMenuWidth;
    }

    void setSelectedMenuWidth(int selectedMenuWidth) {

        if(selectedMenuWidth == -1) return;
        this.selectedMenuWidth = selectedMenuWidth;
    }

    int getUnselectedMenuWidth() {
        return unselectedMenuWidth;
    }

    void setUnselectedMenuWidth(int unselectedMenuWidth) {

        if(unselectedMenuWidth == -1) return;
        this.unselectedMenuWidth = unselectedMenuWidth;
    }

    int getSelectedMenuHeight() {
        return selectedMenuHeight;
    }

    void setSelectedMenuHeight(int selectedMenuHeight) {

        if(selectedMenuHeight == -1) return;
        this.selectedMenuHeight = selectedMenuHeight;
    }

    int getUnselectedMenuHeight() {
        return unselectedMenuHeight;
    }

    void setUnselectedMenuHeight(int unselectedMenuHeight) {

        if(unselectedMenuHeight == -1) return;
        this.unselectedMenuHeight = unselectedMenuHeight;
    }

    boolean isShowTextOnUnselected() {
        return showTextOnUnselected;
    }

    void setShowTextOnUnselected(boolean showTextOnUnselected) {
        this.showTextOnUnselected = showTextOnUnselected;
    }
}
