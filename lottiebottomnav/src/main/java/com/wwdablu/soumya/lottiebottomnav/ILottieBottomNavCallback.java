package com.wwdablu.soumya.lottiebottomnav;

public interface ILottieBottomNavCallback {
    void onMenuSelected(int oldIndex, int newIndex, MenuItem menuItem);
    void onAnimationStart(int index, MenuItem menuItem);
    void onAnimationEnd(int index, MenuItem menuItem);
    void onAnimationCancel(int index, MenuItem menuItem);
}
