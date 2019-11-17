package com.wwdablu.soumya.lottiebottomnav;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableString;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class FontBuilder {

    private FontItem fontItem;

    private FontBuilder(@NonNull FontItem fontItem) {
        this.fontItem = new FontItem(fontItem);
    }

    private FontBuilder(@NonNull String title) {
        this(new SpannableString(title));
    }

    private FontBuilder(@NonNull Spannable spannableTitle) {
        fontItem = new FontItem(spannableTitle, Color.BLACK, Color.GRAY, 10, 10, null);
    }

    public static FontBuilder create(@NonNull String title) {

        if(title.trim().length() == 0) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        return new FontBuilder(title);
    }

    public static FontBuilder create(@NonNull Spannable spannableTitle) {

        if(spannableTitle.length() == 0) {
            throw new IllegalArgumentException("Title spannable cannot be empty");
        }

        return new FontBuilder(spannableTitle);
    }

    public static FontBuilder create(@NonNull FontItem fontItem) {
        return new FontBuilder(fontItem);
    }

    public FontBuilder selectedTextColor(@ColorInt int color) {
        fontItem.setTextSelectedColor(color);
        return this;
    }

    public FontBuilder unSelectedTextColor(@ColorInt int color) {
        fontItem.setTextUnselectedColor(color);
        return this;
    }

    public FontBuilder setTypeface(@Nullable Typeface typeface) {
        fontItem.setTypefaceCompat(typeface);
        return this;
    }

    public FontBuilder setTitle(@NonNull String title) {
        fontItem.setTitle(title);
        return this;
    }

    public FontBuilder setTitle(@NonNull Spannable spannableTitle) {
        fontItem.setTitle(spannableTitle);
        return this;
    }

    /**
     * Provide the value in SP. For example for 10SP, just provide 10
     * @param textSize Text size in SP
     * @return FontBuilder
     */
    public FontBuilder selectedTextSize(int textSize) {
        fontItem.setSelectedTextSize(textSize);
        return this;
    }

    /**
     * Provide the value in SP. For example for 10SP, just provide 10
     * @param textSize Text size in SP
     * @return FontBuilder
     */
    public FontBuilder unSelectedTextSize(int textSize) {
        fontItem.setUnselectedTextSize(textSize);
        return this;
    }

    public FontItem build() {
        return fontItem;
    }
}
