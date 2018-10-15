## AndroidAPI28 BottomNavigationView+ViewPager+Fragment

>参考文章[使用谷歌官方BottomNavigationView实现非md风格的底部状态栏](https://www.jianshu.com/p/24278f3259b3),[基于android P(9.0)版本的BottomNavigationView使用教程(去除动画效果，水波纹效果)](https://blog.csdn.net/qq_33915851/article/details/80867986)
>BottomNavigationView在API28中相对以前版本有友好改动，有取消动画等接口，不用像以前使用反射
#### BottomNavigationView
- **组成：View+menu**

##### 属性
- 底部导航栏内容:app:menu="@menu/bottom_navigation"
- 图标颜色变换:app:itemIconTint="@drawable/app_tab_color"
- 文字颜色变换:app:itemTextColor="@drawable/app_tab_color"
```xml
<android.support.design.widget.BottomNavigationView
    android:id="@+id/mAppBottomBnv"
    android:layout_width="match_parent"
    android:layout_height="48dp"
    app:itemIconSize="24dp"
    app:itemBackground="@null"
    app:itemHorizontalTranslationEnabled="false"
    app:itemTextAppearanceActive="@style/bottom_tab_title_active"
    app:itemTextAppearanceInactive="@style/bottom_tab_title_inactive"
    android:background="@android:color/white"
    app:layout_constraintBottom_toBottomOf="parent"
    app:labelVisibilityMode="labeled"
    app:menu="@menu/bottom_navigation" />
```
##### 更改单个布局参数
```xml
<?xml version="1.0" encoding="utf-8"?>
<resources xmlns:tools="http://schemas.android.com/tools">
    <!-- icon与text间隔大小 -->
    <dimen tools:override="true" name="design_bottom_navigation_margin">2dp</dimen>
    <!-- 导航栏高度 -->
    <dimen tools:override="true" name="design_bottom_navigation_height">48dp</dimen>
    <!-- text大小 -->
    <dimen tools:override="true" name="design_bottom_navigation_text_size">12sp</dimen>
    <!-- text大小 -->
    <dimen tools:override="true" name="design_bottom_navigation_active_text_size">12sp</dimen>
</resources>
```

#### ViewPager
- **list+ViewPagerAdapter**

#### 连动
>ViewPager监听添加BottomNavigationView改变,BottomNavigationView添加ViewPager改变
```kotlin
private fun initListener() {
    mAppBottomBnv.setOnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.app_navigation_tab_one -> {
                mAppVp.currentItem = 0
            }
            R.id.app_navigation_contact -> {
                mAppVp.currentItem = 1
            }
            R.id.app_navigation_find -> {
                mAppVp.currentItem = 2
            }
            R.id.app_navigation_mine -> {
                mAppVp.currentItem = 3
            }
        }
        false
    }
    mAppVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {

        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(position: Int) {
            mAppBottomBnv.menu.getItem(position).isChecked = true
        }

    })
}
```
---

