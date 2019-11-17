package com.wwdablu.soumya.lottiebottomnav;

import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class FontItem {

    private Spannable spannableTitle;
    private @ColorInt int textSelectedColor;
    private @ColorInt int textUnselectedColor;
    private int selectedTextSize;
    private int unselectedTextSize;
    private Typeface typeface;

    FontItem(@NonNull FontItem fontItem) {
        this.spannableTitle = fontItem.spannableTitle;
        this.textSelectedColor = fontItem.textSelectedColor;
        this.textUnselectedColor = fontItem.textUnselectedColor;
        this.selectedTextSize = fontItem.selectedTextSize;
        this.unselectedTextSize = fontItem.unselectedTextSize;
        this.typeface = fontItem.typeface;
    }

    FontItem(@NonNull String title,
             int textSelectedColor,
             int textUnselectedColor,
             int selectedTextSize,
             int unselectedTextSize,
             @Nullable Typeface typeface) {

        this(new SpannableString(title), textSelectedColor, textUnselectedColor, selectedTextSize,
                unselectedTextSize, typeface);
    }

    FontItem(@NonNull Spannable spannableTitle,
             int textSelectedColor,
             int textUnselectedColor,
             int selectedTextSize,
             int unselectedTextSize,
             @Nullable Typeface typeface) {

        this.spannableTitle = spannableTitle;
        this.textSelectedColor = textSelectedColor;
        this.textUnselectedColor = textUnselectedColor;
        this.selectedTextSize = selectedTextSize;
        this.unselectedTextSize = unselectedTextSize;
        this.typeface = typeface;
    }

    void setTitle(@NonNull String title) {
        this.spannableTitle = new SpannableString(title);
    }

    void setTitle(@NonNull Spannable spannableTitle) {
        this.spannableTitle = spannableTitle;
    }

    void setTextSelectedColor(@ColorInt int textSelectedColor) {
        this.textSelectedColor = textSelectedColor;
    }

    void setTextUnselectedColor(@ColorInt int textUnselectedColor) {
        this.textUnselectedColor = textUnselectedColor;
    }

    void setTypefaceCompat(@Nullable Typeface typeface) {
        this.typeface = typeface;
    }

    Spannable getSpannableTitle() {
        return spannableTitle;
    }

    void setSelectedTextSize(int selectedTextSize) {
        this.selectedTextSize = selectedTextSize;
    }

    void setUnselectedTextSize(int unselectedTextSize) {
        this.unselectedTextSize = unselectedTextSize;
    }

    public @ColorInt int getTextSelectedColor() {
        return textSelectedColor;
    }

    public @ColorInt int getTextUnselectedColor() {
        return textUnselectedColor;
    }

    public int getSelectedTextSize() {
        return selectedTextSize;
    }

    public int getUnselectedTextSize() {
        return unselectedTextSize;
    }

    @Nullable
    public Typeface getTypeface() {
        return typeface;
    }
}
