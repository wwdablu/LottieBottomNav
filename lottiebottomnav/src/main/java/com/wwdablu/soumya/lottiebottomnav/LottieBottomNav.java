package com.wwdablu.soumya.lottiebottomnav;

import android.animation.Animator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import com.wwdablu.soumya.lottiebottomnav.databinding.LottieMenuItemBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class LottieBottomNav extends LinearLayout {

    private List<MenuItem> menuItemList;
    private ArrayList<LottieMenuItemBinding> lottieViews;
    private ILottieBottomNavCallback callback;
    private Config config;

    private int selectedIndex;

    public LottieBottomNav(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        selectedIndex = 0;
        callback = new LottieBottomNavCallbackImpl();
        extractProperties(attrs);
    }

    /**
     * Assign the menu items that are to be displayed
     * @param menuItemList List of menu items
     */
    public void setMenuItemList(@NonNull List<MenuItem> menuItemList) {

        this.menuItemList = menuItemList;
        lottieViews = new ArrayList<>(menuItemList.size());

        prepareMenuItems();
    }

    public void updateMenuItemFor(int index, @NonNull MenuItem menuItem) {

        if(menuItemList == null || index < 0 || index > menuItemList.size()) {
            return;
        }

        lottieViews.get(index).lmiMenuItem.pauseAnimation();
        menuItemList.set(index, menuItem);

        LottieMenuItemBinding binder = LottieViewCreator.from(this, menuItem, selectedIndex == index, config);
        lottieViews.set(index, binder);

        removeViewAt(index);
        ViewGroup.LayoutParams params = binder.menuContainer.getLayoutParams();
        params.width = (getWidth() / menuItemList.size());
        binder.menuContainer.setLayoutParams(params);
        binder.getRoot().setTag(index);
        binder.getRoot().setOnClickListener(view -> switchSelectedMenu((int) view.getTag()));
        binder.lmiMenuItem.addAnimatorListener(animatorListener);
        if(!config.isShowTextOnUnselected()) {
            binder.lmiMenuText.setVisibility(isSelected(index) ? View.VISIBLE : View.INVISIBLE);
        }
        addView(binder.getRoot(), index);
        binder.lmiMenuItem.setProgress(0F);
        binder.lmiMenuItem.playAnimation();
    }

    /**
     * Set the index of the to be displayed as selected
     * @param index Index
     */
    public void setSelectedIndex(int index) {

        if(lottieViews == null || lottieViews.size() == 0 || selectedIndex == index) {
            return;
        }

        //Boundary checks
        if (index < 0) index = 0;
        else if (index >= lottieViews.size()) index = lottieViews.size() - 1;

        switchSelectedMenu(index);
    }

    /**
     * Returns the currently selected menu index
     * @return Index
     */
    public int getSelectedIndex() {
        return selectedIndex;
    }

    /**
     * Returns the menu item associated with the index
     * @param index Index
     * @return MenuItem
     */
    @Nullable
    public MenuItem getMenuItemFor(int index) {

        if(menuItemList == null || index < 0 || index > menuItemList.size()) {
            return null;
        }

        return menuItemList.get(index);
    }

    /**
     * Sets the callback to listen for the menu changes
     * @param callback Callback
     */
    public void setCallback(@Nullable ILottieBottomNavCallback callback) {
        this.callback = callback == null ? new LottieBottomNavCallbackImpl() : callback;
    }

    @Override
    public void setOrientation(int orientation) {
        super.setOrientation(HORIZONTAL);
    }

    @Override
    public void setGravity(int gravity) {
        super.setGravity(Gravity.CENTER);
    }

    private void prepareMenuItems() {

        int index = 0;
        lottieViews.clear();

        for(MenuItem menuItem : menuItemList) {
            LottieMenuItemBinding binder = LottieViewCreator.from(this, menuItem, selectedIndex == index, config);
            binder.getRoot().setTag(index);
            binder.getRoot().setOnClickListener(view -> switchSelectedMenu((int) view.getTag()));
            binder.lmiMenuItem.addAnimatorListener(animatorListener);

            if(index == selectedIndex) {
                binder.lmiMenuItem.setProgress(0F);
                binder.lmiMenuItem.playAnimation();
            }

            lottieViews.add(binder);
            index++;
        }

        if(getWidth() == 0) {
            getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    updateUI();
                }
            });
        } else {
            updateUI();
        }
    }

    private void switchSelectedMenu(int newIndex) {

        if(newIndex == selectedIndex) {
            return;
        }

        //Pause any existing, or else it might impact
        lottieViews.get(selectedIndex).lmiMenuItem.pauseAnimation();

        LottieMenuItemBinding binding = lottieViews.get(newIndex);
        MenuItem menuItem = menuItemList.get(newIndex);

        binding.lmiMenuItem.setAnimation(menuItem.selectedLottieName);
        binding.lmiMenuText.setTextColor(menuItem.fontItem.getTextSelectedColor());
        binding.lmiMenuText.setTextSize(TypedValue.COMPLEX_UNIT_SP, menuItem.fontItem.getSelectedTextSize());

        binding.lmiMenuItem.playAnimation();

        ViewGroup.LayoutParams params = binding.lmiMenuItem.getLayoutParams();
        params.width = config.getSelectedMenuWidth();
        params.height = config.getSelectedMenuHeight();
        binding.lmiMenuItem.setLayoutParams(params);

        binding.lmiMenuText.setVisibility(View.VISIBLE);

        callback.onMenuSelected(selectedIndex, newIndex, menuItem);

        //Set the unselected menu item properties

        binding = lottieViews.get(selectedIndex);
        menuItem = menuItemList.get(selectedIndex);

        binding.lmiMenuItem.setAnimation(menuItem.unselectedLottieName);
        binding.lmiMenuText.setTextColor(menuItem.fontItem.getTextUnselectedColor());
        binding.lmiMenuText.setTextSize(TypedValue.COMPLEX_UNIT_SP, menuItem.fontItem.getUnselectedTextSize());

        binding.lmiMenuItem.pauseAnimation();
        binding.lmiMenuItem.setProgress(menuItem.lottieProgress);

        params = binding.lmiMenuItem.getLayoutParams();
        params.width = config.getUnselectedMenuWidth();
        params.height = config.getUnselectedMenuHeight();
        binding.lmiMenuItem.setLayoutParams(params);

        if(!config.isShowTextOnUnselected()) {
            binding.lmiMenuText.setVisibility(View.GONE);
        }

        selectedIndex = newIndex;
    }

    private void updateUI() {

        removeAllViews();

        int menuItemWidth = getWidth() / menuItemList.size();

        for(LottieMenuItemBinding binding : lottieViews) {
            View view = binding.getRoot();

            ViewGroup.LayoutParams params = binding.menuContainer.getLayoutParams();
            params.width = menuItemWidth;
            binding.menuContainer.setLayoutParams(params);

            addView(view);
        }

        invalidate();
    }

    private void extractProperties(@Nullable AttributeSet attributeSet) {

        config = new Config(getContext());
        if(attributeSet == null) {
            return;
        }

        TypedArray properties = getContext().obtainStyledAttributes(attributeSet, R.styleable.LottieBottomNav);
        config.setSelectedMenuWidth(properties.getDimensionPixelSize(R.styleable.LottieBottomNav_menu_selected_width, -1));
        config.setSelectedMenuHeight(properties.getDimensionPixelSize(R.styleable.LottieBottomNav_menu_selected_height, -1));
        config.setUnselectedMenuWidth(properties.getDimensionPixelSize(R.styleable.LottieBottomNav_menu_unselected_width, -1));
        config.setUnselectedMenuHeight(properties.getDimensionPixelSize(R.styleable.LottieBottomNav_menu_unselected_height, -1));
        config.setShowTextOnUnselected(properties.getBoolean(R.styleable.LottieBottomNav_menu_text_show_on_unselected, true));
        properties.recycle();
    }

    private boolean isSelected(int index) {
        return selectedIndex == index;
    }

    Animator.AnimatorListener animatorListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationStart(Animator animator) {
            callback.onAnimationStart(selectedIndex, menuItemList.get(selectedIndex));
        }

        @Override
        public void onAnimationEnd(Animator animator) {
            callback.onAnimationEnd(selectedIndex, menuItemList.get(selectedIndex));
        }

        @Override
        public void onAnimationCancel(Animator animator) {
            callback.onAnimationCancel(selectedIndex, menuItemList.get(selectedIndex));
        }

        @Override
        public void onAnimationRepeat(Animator animator) {
            //
        }
    };
}
