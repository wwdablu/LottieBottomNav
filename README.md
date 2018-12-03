# LottieBottomNav  
Library which allows to create a bottom navigation view allowing the usage of Lottie views.  

## Creating Menu Items  
The LottieBottomNav contains an array of menu items. The process to create a menu item is:  
```
MenuItem homeItem = MenuItemBuilder.create("Dashboard", "home.json", MenuItem.Source.Assets, "dashboard")
    .selectedTextColor(Color.BLUE)
    .unSelectedTextColor(Color.RED)
    .pausedProgress(100)
    .autoPlay(false)
    .loop(false)
    .build();
```  
This allows us to create a menu with both the selected and unselected states. The other way to create is from an existing mmenu item object and then modifying any existing property. `Note: Only tag property is not copied`.  
```
MenuItem settings = MenuItemBuilder.createFrom(homeItem)
    .menuTitle("Settings")
    .selectedLottieName("settings.json")
    .unSelectedLottieName("settings.json")
    .tag("settings")
    .build();
```  
In the above approach properties like `textColor`, `autoPlay` and `loop` are of the same value from which it is created.  
  
  
## Update a particular menu item  
To update a particular menu item with different properties of the menu item, the following approach is needed.  
```
MenuItem cupidMessage = MenuItemBuilder.createFrom(bottomNav.getMenuItemFor(index))
    .selectedLottieName("message_cupid.json")
    .tag("cupid")
    .build();

bottomNav.updateMenuItemFor(index, cupidMessage);
```  
This would change the lottie used for the menu item in `index` and update it to use cupid lottie.  
  
  
## Callback  
The following callbacks are provided for each of the menu items.
```
void onMenuSelected(int oldIndex, int newIndex, MenuItem menuItem);
void onAnimationStart(int index, MenuItem menuItem);
void onAnimationEnd(int index, MenuItem menuItem);
void onAnimationCancel(int index, MenuItem menuItem);
```  
It should be noted that if `autoPlay` is enabled, then it must be noted that `onMenuSelected` will call `onAnimationStart` immediately. So it is better to perform quick tasks in these callback and move heavy tasks in separate threads.  
  

  
**Demo**:  
The demo video is present inside the demo folder. The GIF version is shown below.  
  
![Demo](https://github.com/wwdablu/LottieBottomNav/blob/master/demo/demo_1.0.0.gif)
